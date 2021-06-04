package hello;


import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
public class pri {



		private demo1[] sI = new demo1[186760];
			
		    private String sportsman_id,name,mail_id,phone_no,country,state,sports_name,debut,gender,no_of_awards;
			int reccount = 0;
		    int reccount1=0;
		public void getData(){
		    		@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the sportsman_id: ");
				sportsman_id= scanner.next();
				System.out.println("Enter the name: ");
				name = scanner.next();
				System.out.println("Enter the mail_id: ");
				mail_id = scanner.next();
				System.out.println("Enter the phone_no: ");
				phone_no = scanner.next();
				System.out.println("Enter the country: ");
				country = scanner.next();
				System.out.println("Enter the state: ");
				state = scanner.next();
				System.out.println("Enter the spots_name: ");
				sports_name= scanner.next();
				System.out.println("Enter the debut : ");
				debut = scanner.next();    
				System.out.println("Enter the gender: ");
				gender= scanner.next();
				System.out.println("Enter the no_of_awards : ");
				no_of_awards= scanner.next();
				System.out.println("WAITING");
		}
		public void add(){
		        String data=sportsman_id +","+  name +","+ mail_id +","+ phone_no +","+  country  +","+ state  +","+ sports_name+ "," + debut  + "," + gender + "," +  no_of_awards;

		 try{			
					RandomAccessFile recordfile = new RandomAccessFile ("C:\\Users\\Nayana M\\Desktop\\fs_mini_proj\\registration.txt","rw");
					recordfile.seek(recordfile.length());
					long pos = recordfile.getFilePointer();
					recordfile.writeBytes(data+"\n");
					recordfile.close();
					
					RandomAccessFile indexfile = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\index.txt","rw");
					indexfile.seek(indexfile.length());
					indexfile.writeBytes(sportsman_id+","+pos+"\n");
					indexfile.close();
					
					RandomAccessFile indexfile1 = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\index1.txt","rw");
					indexfile1.seek(indexfile1.length());
					indexfile1.writeBytes(name+","+pos+"\n");
					indexfile1.close();
				}
				catch(IOException e){
					System.out.println(e);
				}		 
		}     
		
		public void unPack(){
			try{
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Nayana M\\Desktop\\fs_mini_proj\\registration.txt"));
	    		String line;
	                try{
	              
	                while((line = reader.readLine())!= null){
	                	if(line.contains("*"))
							continue;
	                	int count = 0;
	                	StringTokenizer st = new StringTokenizer(line,",");
	                	while (st.hasMoreTokens()){
	           	    	 count += 1;
	           	    	 if(count==1) {
	           	         System.out.println("sportsman_id: "+st.nextToken());
	           	    	 System.out.println("name: "+st.nextToken());
	           	    	 System.out.println("mail_id: "+st.nextToken());
	           	    	 System.out.println("phone_no: "+st.nextToken());
	           	    	 System.out.println("country: "+st.nextToken());
	           	    	 System.out.println("state: "+st.nextToken());
	           	    	 System.out.println("sports_name: "+st.nextToken());
	           	    	 System.out.println("debut: "+st.nextToken());
	           	    	 System.out.println("gender: "+st.nextToken());	
	           	    	 System.out.println("no_of_awards: "+st.nextToken());	           	    	 
	           	    	 System.out.println();
	           	    	 }
	           	    	 
	           	    	 else
	           	    		 break;
	                }
	                }
	                }
	                catch(Exception e){return;}
	    		
	    		}
				catch(IOException e){
					return;
				}
		}
		
		    public void priIndex(){

				String line,prikey = null,pos = null;
				int count = 0;
				int sIIndex = 0;
				reccount=0;
				RandomAccessFile indexfile;
				try {
					indexfile = new RandomAccessFile("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\index.txt","rw");

					try {
						
						while((line = indexfile.readLine())!= null){
		                                     if(line.contains("*")) {
			                		continue;
			                	}
							count = 0;
							StringTokenizer st = new StringTokenizer(line,",");
							while (st.hasMoreTokens()){
							 count+=1;
							 if(count==1)
						    prikey = st.nextToken();
							 pos = st.nextToken();                 
						    }
							sI[sIIndex] = new demo1();
							sI[sIIndex].setRecPos(pos);
							sI[sIIndex].setprikey(prikey);
							reccount++;
							sIIndex++;
							 
		                                        if(sIIndex==186760)
		                                        {
		                                      
		                                            break;
		                                            
		                                        }
		                                }
					} catch (IOException e) {
						
						e.printStackTrace();
						return;
					}
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
					return;
				} 
				
				System.out.println("total records" + reccount);
				if (reccount==1) { return;}
				sortIndex();
			}
		   
			
			public void sortIndex() {
				demo1 temp = new demo1();
				
				for(int i=0; i<reccount; i++)
				    {	
						for(int j=i+1; j<reccount; j++) {
							if(sI[i].getprikey().compareTo(sI[j].getprikey())  > 0)
				            {
				                temp.setprikey(sI[i].getprikey()); 
						        temp.setRecPos(sI[i].getRecPos());
						
					        	sI[i].setprikey(sI[j].getprikey());
					        	sI[i].setRecPos(sI[j].getRecPos());
						
					        	sI[j].setprikey(temp.getprikey());
					        	sI[j].setRecPos(temp.getRecPos());
				            }
						}
							
					}	
				
			}
			
		        public void search(){
		            System.out.println("Enter the sportsman_id to search: ");
							Scanner scanner = new Scanner(System.in);
							String  sportsman_id = scanner.next();
							System.out.println(reccount);
							int pos = binarySearch(sI, 0, reccount-1, sportsman_id);
		                                        
							
							if (pos == -1) {
								System.out.println("Record not found in the record file");
								return ;
							}
							
							RandomAccessFile recordfile;
							try {
								recordfile = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\registration.txt","rw");
								try {
									recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
									String record = recordfile.readLine();
									int length=record.length();
									StringTokenizer st = new StringTokenizer(record,",");
									int count = 0;
		                                                        
				               	    
				                	while (st.hasMoreTokens()){
				                		     count+=1;
				                		     
				                		     
				                		     
				                  	    	 if(count==1){
				                  	    	 String tmp_sportsman_id = st.nextToken();			                  	    	
				                  	    	String tmp_name = st.nextToken();
				                  	    	String tmp_mail_id = st.nextToken();
				                  	    	String tmp_phone_no = st.nextToken();
				                  	    	String tmp_country = st.nextToken();
				                  	    	String tmp_state = st.nextToken();
				                  	    	String tmp_sports_name = st.nextToken();
				                  	    	String tmp_debut = st.nextToken();
				                  	    	String tmp_gender = st.nextToken();
				                  	    	String tmp_no_of_awards = st.nextToken();
				                  	    	
				                  	    	
				                  	    	 
				                  	    	 if(tmp_sportsman_id.contains("*"))
                                             {
                                                 System.out.println("it has been deleted");
                                                 break;
                                             }
					              System.out.println("sportsman_id: "+sportsman_id);
	                  	         this.sportsman_id = tmp_sportsman_id;
	                  	    	
	                  	          //String name = st.nextToken();
	                     	      System.out.println("name: "+tmp_name);
	                     	      this.name = tmp_name;
	                     	       
	                     	      // String mail_id = st.nextToken();
	                     	       System.out.println("mail_id: "+tmp_mail_id);
	                     	       this.mail_id= tmp_mail_id;
	                  	    	 
	                     	        //String phone_no = st.nextToken();
	                     	        System.out.println("phone_no: "+tmp_phone_no);
	                     	        this.phone_no =tmp_phone_no;
	                     	      
	                     	       //String country = st.nextToken();
	                     	        System.out.println("country: "+tmp_country);
	                     	        this.country = tmp_country;
	                     	     
	                     	       //String state = st.nextToken();
	                     	        System.out.println("state: "+tmp_state);
	                     	        this.state = tmp_state;
	                     	        
	                     	        

	                     	       // String sports_name = st.nextToken();
	                     	        System.out.println("sports_name: "+tmp_sports_name);
	                     	        this.sports_name = tmp_sports_name;
	                     	        

	                     	      //  String debut = st.nextToken();
	                     	        System.out.println("debut: "+tmp_debut);
	                     	        this.debut = tmp_debut;
	                     	        

	                     	       // String gender = st.nextToken();
	                     	        System.out.println("gender: "+gender);
	                     	        this.gender = tmp_gender;		
	                     	        
	                     	       // String no_of_awards = st.nextToken();
	                     	        System.out.println("no_of_awards : "+no_of_awards );
	                     	        this.no_of_awards  = tmp_no_of_awards ;	
	                     	        
	                     	       System.out.println();
	                     	       
	                     	       
	                     	  	
	                  	    	 	System.out.println("Do you want to modify????? Y/N");
	                  	    	 	String modi = scanner.next();
	                  	    	 	
		                  	    	 	if ( modi.equalsIgnoreCase("y"))
		                  	    	 	{
		                  	    	 		System.out.println("What do you want to change");
		                  	    	 		System.out.println("Enter your choice");
		                  	    	 		System.out.println("1 sportsman_id \n 2. name \n 3.mail_id \n 4.phone_no \n 5.country\n 6. state \n 7. sports_name \n 8. debut \n 9.gender \n  10. no_of_awards \n");

		                  	    	           int choice = scanner.nextInt();
		                  	    	           switch(choice)
		                  	    	           {
			                  	    	           case 1 :System.out.println("Enter the sportsman_id  ");
			                  	    	           tmp_sportsman_id =scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 2 :System.out.println("Enter the name ");
			                  	    	           tmp_name=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 3: System.out.println("Enter the mail_id");
			                  	    	           tmp_mail_id=scanner.next();
			                  	    	           break;
			                  	    	           
			                  	    	           case 4 :System.out.println("Enter the phone_no ");
			                  	    	           tmp_phone_no=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 5 :System.out.println("Enter the c ountry ");
			                  	    	           tmp_country=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 6: System.out.println("Enter the state ");
			                  	    	           tmp_state=scanner.next();
			                  	    	           break;
			                  	    	           
			                  	    	           case 7 :System.out.println("Enter the sports_name ");
			                  	    	           tmp_sports_name=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 8 :System.out.println("Enter the debut ");
			                  	    	           tmp_debut=scanner.next();
			                  	    	           break;
			                  	    	     
			                  	    	           case 9: System.out.println("Enter the gender ");
			                  	    	           tmp_gender =scanner.next();
			                  	    	           break;
			                  	    	           
			                  	    	           case 10 :System.out.println("Enter the no_of_awards  ");
			                  	    	           tmp_no_of_awards=scanner.next();
			                  	    	           break;
			                  	    	     
		                  	    	           }
		                  	    	           long pointer=recordfile.getFilePointer();
	   String pack =  tmp_sportsman_id +","+  tmp_name +","+ tmp_mail_id +","+ tmp_phone_no +","+ tmp_country +","+ tmp_state +","+ tmp_sports_name + "," + tmp_debut + "," + tmp_gender + "," +  tmp_no_of_awards;
	   if( pack.length()>length)
	   {
	 	
	 		if(reccount==1) {
	 			pointer = 0;
	 		}
	 		else {
	 			pointer = pointer-(length+1);
	 		}
	 		
	 		recordfile.seek(pointer);
	 		recordfile.writeBytes("*");
	      recordfile.seek(recordfile.length());
	      recordfile.writeBytes(pack+"\n");
	      recordfile.close();
	   }
	 else {
	 		if(reccount==1) {
	 			pointer = 0;
	 		}
	 		else {
	 			pointer = pointer-(length+1);
	 		}
	 		
	 	recordfile.seek(pointer);
	 	recordfile.writeBytes(pack);
	 	recordfile.close();	
	 }
		                  	    	 	}
		                  	    	 	else
		                  	    	 	{
		                  	    	 		System.out.println("ok done");
		                  
	                  	    	 	}
	                     	       
	                     	       
				                           	       
				                  	    	 }				                     	     			  				                  	    	 			                  	   
				                  	    	 else
				                  	    		 break;
				                       }
				                	
								} 
									catch (NumberFormatException e) {
									
									e.printStackTrace();
								} 
								catch (IOException e) {
									
									e.printStackTrace();
								}
								
								
								}
														
			                	catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							catch(NoSuchElementException e) {
								e.printStackTrace();
							}
						
		        }
		        
		        int binarySearch(demo1 s[], int l, int r, String prikey) {
		    	
		    	int mid;
		    	while (l<=r) {
		            
		    		mid = (l+r)/2;
		    		if(s[mid].getprikey().compareTo(prikey)==0) {return mid;}
		    		if(s[mid].getprikey().compareTo(prikey)<0) l = mid + 1;
		    		if(s[mid].getprikey().compareTo(prikey)>0) r = mid - 1;
		    	}
		    	return -1;
		    }
		       /* int binarySearch1(demo s[], int l, int r, String place) {
		        	
		        	int mid;
		        	while (l<=r) {
		                
		        		mid = (l+r)/2;
		        		if(s[mid].getid().compareTo(place)==0) {return mid;}
		        		if(s[mid].getid().compareTo(place)<0) l = mid + 1;
		        		if(s[mid].getid().compareTo(place)>0) r = mid - 1;
		        	}
		        	return -1;
		        }*/

		  public  void indexing() 
		  {
		         try{
		        RandomAccessFile hey=new RandomAccessFile("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\registration.txt","rw");
		  
		    			

		        RandomAccessFile indexfile=new RandomAccessFile("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\index.txt","rw");
		        String line;
		        long pos=hey.getFilePointer();
		        while((line = hey.readLine())!=null)
		        {
		            if(line.contains("*")) {
			                		continue;
			                	}

		            String[] columns=line.split(",");
		                                 	         
		            indexfile.writeBytes(columns[0]+","+pos+"\n");
		            pos=hey.getFilePointer();
		        } indexfile.close();
		        hey.close();
		                		       
		         }
		    
		    catch(IOException e)
		    {
		        System.out.println(e);
		    }
		  }
		  
		 public   void delete() throws IOException {
		         System.out.println("Enter the primary key to delete record ");
							Scanner scanner = new Scanner(System.in);
							String prikey = scanner.next();
		        int pos = binarySearch(sI, 0, reccount-1, prikey);
		        System.out.println("WAIT FOR FEW SECONDS....: ");	
							if (pos == -1) {
								System.out.println("Record not found in the record file");
								return ;
							}
		                                        RandomAccessFile recordfile;
		                                        
							
								recordfile = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\registration.txt","rw");
								try {
									recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
		                                                        recordfile.writeBytes("*");
		                                                        recordfile.close();
		                                                
		                                                        }    
		                                                            
		                                             catch (NumberFormatException e) {
									
									e.printStackTrace();
								} 
								catch (IOException e) {
									
									e.printStackTrace();
								}
								
								
								}
									


		}
