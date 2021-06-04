package hello;


import java.util.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
public class sec {


		private demo2[] sI = new demo2[200000];
			
		private String sportsman_id,name,mail_id,phone_no,country,state,sports_name,debut,gender,no_of_awards;
		//private String Date_of_Joining,Salary,SSN,Phone_No;
		int reccount = 0;

		public void getData(){
		    		@SuppressWarnings("resource")
		    		Scanner scanner = new Scanner(System.in);
		    		System.out.println("Enter the sportsman_id: ");
		    		sportsman_id = scanner.next();
					System.out.println("Enter the name: ");
					name = scanner.next();
					System.out.println("Enter the mail_id: ");
					mail_id= scanner.next();
					System.out.println("Enter the phone_no: ");
					phone_no = scanner.next();
					System.out.println("Enter the country: ");
					country = scanner.next();
					System.out.println("Enter the state: ");
					state = scanner.next();
					System.out.println("Enter the sports_name: ");
					sports_name = scanner.next();
					System.out.println("Enter the debut: ");
					debut = scanner.next();
					System.out.println("Enter the gender: ");
					gender = scanner.next();
					System.out.println("Enter the no_of_awards: ");
					no_of_awards = scanner.next();
					System.out.println("WAITING");
		}
		public void add(){
			String data = sportsman_id +","+  name +","+ mail_id +","+ phone_no +","+  country  +","+ state  +","+ sports_name+ "," + debut  + "," + gender + "," +  no_of_awards;
			try{			                                                                                                    
				RandomAccessFile recordfile = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\registration.txt","rw");
				recordfile.seek(recordfile.length());
				long pos = recordfile.getFilePointer();
				recordfile.writeBytes(data+"\n");
				recordfile.close();
				
						
				RandomAccessFile indexfile1 = new RandomAccessFile ("C:\\\\\\\\Users\\\\\\\\Nayana M\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\index1.txt","rw");
				indexfile1.seek(indexfile1.length());
				indexfile1.writeBytes(name+","+pos+"\n");
				indexfile1.close();
			}
			catch(IOException e){
				System.out.println(e);
			} 
				
		 
		}                     
		@SuppressWarnings("resource")
		public void priIndex(){

			String line,prikey = null,pos = null;
			int count = 0;
			int sIIndex = 0;
			reccount=0;
			RandomAccessFile indexfile;
			try {
				indexfile = new RandomAccessFile("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\Sheshank\\\\\\\\\\\\\\\\Desktop\\\\\\\\\\\\\\\\fs_mini_proj\\\\\\\\\\\\\\\\index1.txt","rw");

				try {
					
					while((line = indexfile.readLine())!= null){
	                                     if(line.contains("*")) 
	                                     {
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
						sI[sIIndex] = new demo2();
						sI[sIIndex].setRecPos(pos);
						sI[sIIndex].setseckey(prikey);
						reccount++;
						sIIndex++;
	                                        if(sIIndex==200000)
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
			demo2 temp = new demo2();
			
			for(int i=0; i<reccount; i++)
			    {	
					for(int j=i+1; j<reccount; j++) {
						if(sI[i].getseckey().compareTo(sI[j].getseckey())  > 0)
			            {
			                temp.setseckey(sI[i].getseckey()); 
					        temp.setRecPos(sI[i].getRecPos());
					
				        	sI[i].setseckey(sI[j].getseckey());
				        	sI[i].setRecPos(sI[j].getRecPos());
					
				        	sI[j].setseckey(temp.getseckey());
				        	sI[j].setRecPos(temp.getRecPos());
			            }
					}			
				}		
		}
		
		        public void search(){
		        	 System.out.println("Enter the name to search: ");
		             @SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
		             String name = scanner.next();
		             
		             
		             int oripos = binarySearch(sI, 0, reccount-1, name);
		             
		             if (oripos == -1) {
		                 System.out.println("Record not found in the record file");
		                 return ;
		             }
		             
		             int pos = oripos;
		             
		             do {
		                 readFile(pos);
		                 pos--;
		                 if (pos < 0) { break;}
		             }while(sI[pos].getseckey().compareTo(name)==0);
		             
		             pos = oripos + 1 ;             
		             while(sI[pos].getseckey().compareTo(name)==0 && pos > reccount-1) {
		                 readFile(pos);
		                 pos++;
		             }
		        }
		 public void readFile(int pos) {
		            
		            RandomAccessFile recordfile;
		            try {
		                recordfile = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\registration.txt","rw");
		                try {
		                    recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
		                    String record = recordfile.readLine();
		                    StringTokenizer st = new StringTokenizer(record,",");
		                    
		                    int count = 0;
		                       
		                    while (st.hasMoreTokens()){
		                             count += 1;
		                               if(count==1){
		                            	     String  tmp_sportsman_id = st.nextToken();			                  	    	
				                  	    	String tmp_name= st.nextToken();
				                  	    	String tmp_mail_id = st.nextToken();
				                  	    	String tmp_phone_no = st.nextToken();
				                  	    	String tmp_country = st.nextToken();
				                  	    	String tmp_state = st.nextToken();
				                  	    	String tmp_sports_name = st.nextToken();
				                  	    	String tmp_debut = st.nextToken();
				                  	    	String tmp_gender = st.nextToken();
				                  	    	String tmp_no_of_awards = st.nextToken(); 
                                          
		                            	      System.out.println("sportsman_id: "+ tmp_sportsman_id);
				                  	          this.sportsman_id = tmp_sportsman_id;
				                  	    	
				                  	         // String tmp_name = st.nextToken();
				                     	      System.out.println("place: "+tmp_name);
				                     	      this.name  = name ;
				                     	       
				                     	       //String tmp_mail_id = st.nextToken();
				                     	       System.out.println("mail_id: "+tmp_mail_id);
				                     	       this.mail_id = tmp_mail_id;
				                  	    	 
				                     	       // String tmp_phone_no = st.nextToken();
				                     	        System.out.println("phone_no: "+tmp_phone_no);
				                     	        this.phone_no= tmp_phone_no;
				                     	      
				                     	       // String tmp_country= st.nextToken();
				                     	        System.out.println("country: "+tmp_country);
				                     	        this.country = tmp_country;
				                     	     
				                     	       // String tmp_state = st.nextToken();
				                     	        System.out.println("state: "+tmp_state);
				                     	        this.state = tmp_state;
				                     	        
				                     	        

				                     	       // String tmp_sports_name = st.nextToken();
				                     	        System.out.println("sports_name: "+tmp_sports_name);
				                     	        this.sports_name = tmp_sports_name;
				                     	        

				                     	     //  String tmp_debut = st.nextToken();
				                     	        System.out.println("debut : "+tmp_debut );
				                     	        this.debut  = tmp_debut ;
				                     	        


					                     	     //  String tmp_gender = st.nextToken();
					                     	        System.out.println("gender: "+tmp_gender);
					                     	        this.gender = tmp_gender;
					                     	      
					                     	   //  String tmp_no_of_awards = st.nextToken();
					                     	        System.out.println("no_of_awards: "+tmp_no_of_awards);
					                     	        this.no_of_awards = tmp_no_of_awards;
					                     	        
					                     	        
				                  	    	 }		                     	     			  				                  	    	 			                  	   
	                  	    	 else
	                  	    		 break;
		                       }
		                    
		                    recordfile.close();
		                } 
		                    catch (NumberFormatException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                } 
		                catch (IOException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            }  
		            catch (FileNotFoundException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		            catch (NullPointerException e) {
		            	e.printStackTrace();
		            }
		            
		 }
		 
		   int binarySearch(demo2 s[], int l, int r, String name) {
		    	
		    	int mid;
		    	while (l<=r) {
		            
		    		mid = (l+r)/2;
		    		if(s[mid].getseckey().compareTo(name)==0) {return mid;}
		    		if(s[mid].getseckey().compareTo(name)<0) l = mid + 1;
		    		if(s[mid].getseckey().compareTo(name)>0) r = mid - 1;
		    	}
		    	return -1;
		    }

		        public  void indexing() 
				  {
				         try{
				        RandomAccessFile hey=new RandomAccessFile("C:\\\\\\\\Users\\\\\\\\Nayana M\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\registration.txt","rw");
				        
				        RandomAccessFile indexfile=new RandomAccessFile("C:\\\\\\\\Users\\\\\\\\Nayana M\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\index1.txt","rw");
				        String line;
				        long pos=hey.getFilePointer();
				        while((line = hey.readLine())!=null)
				        {
				            if(line.contains("*")) 
				            {
				            	continue;
					        }
				            String[] columns=line.split(",");
				         
				            indexfile.writeBytes(columns[1]+","+pos+"\n");
				            pos=hey.getFilePointer();
				        } 
				        
				        indexfile.close();
				        hey.close();

				        }
				    
				    catch(IOException e)
				    {
				        System.out.println(e);
				    }
				  }

		     public   void delete() throws IOException {
			 indexing();
		     
		     System.out.println("Enter the place to delete: ");
		     @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
		     String place = scanner.next();
		     String ans = "n";
		     int pos;
		     int oripos = binarySearch(sI, 0, reccount-1, name);
		     System.out.println("WAIT FOR FEW SECONDS....: ");
		     if (oripos == -1) {
		         System.out.println("Record not found in the record file");
		         return ;
		     }
		     
		     pos = oripos;
		     
		     do {
		         readFile(pos);
		         
		         System.out.println("Do You Want To delete This Record ?(y/n) ");
		         ans = scanner.next();
		         
		         if (ans.compareTo("y")==0) {
		             markDelete(pos, place);
		         }
		         pos--;
		         if (pos < 0) { break;}
		     }while(sI[pos].getseckey().compareTo(place)==0);
		         
		     
		     pos = oripos + 1 ;
		     
		     
		     while(sI[pos].getseckey().compareTo(place)==0 && pos > reccount-1){
		         readFile(pos);
		         
		         System.out.println("Do You Want To delete This Record ?(y/n) ");
		         ans = scanner.next();
		         
		         if (ans.compareTo("y")==0) {
		             markDelete(pos, name);
		             break;
		         }
		         pos++;
		         if (pos > reccount-1) { break;}
		     }
		}
		 public void markDelete(int pos, String ssn) {
		     try {
		         RandomAccessFile recordfilee = new RandomAccessFile ("C:\\\\Users\\\\Nayana M\\\\Desktop\\\\fs_mini_proj\\\\registration.txt","rw");
		         @SuppressWarnings("resource")
				RandomAccessFile indexfilee = new RandomAccessFile ("C:\\\\\\\\Users\\\\\\\\Nayana M\\\\\\\\Desktop\\\\\\\\fs_mini_proj\\\\\\\\index1.txt","rw");
		     
		             recordfilee.seek(Long.parseLong(sI[pos].getRecPos()));
		             recordfilee.writeBytes("*");
		             System.out.println("Done");
		             recordfilee.close();
		             String line;
		             String indexName;
		             long indexPos = 0;
		             long delPosi;
		             //delPosi = indexfilee.getFilePointer();
		             while((line = indexfilee.readLine())!=null) {
		                 if(line.contains("*"))
		                     continue;
		                 StringTokenizer st = new StringTokenizer(line,",");
		                delPosi = indexfilee.getFilePointer();
		                 delPosi = delPosi - (line.length()+2);
		                 
		                 //System.out.println("Delposi: " + delPosi);
		                 
		                 while(st.hasMoreTokens()) {
		                     indexName=st.nextToken();
		                     indexPos= Long.parseLong(st.nextToken());
		                     //System.out.println("indexPos: " + indexPos);
		                     //System.out.println("getrecpos: " + Long.parseLong(sI[pos].getRecPos()));
		                     if(indexName.equals(ssn) && indexPos == Long.parseLong(sI[pos].getRecPos()) ) {
		                         indexfilee.seek(delPosi);
		                         indexfilee.writeBytes("*");
		                         indexfilee.close();
		                         System.out.println("Deleted");
		                         indexing();
		                         return;
		                     }
		                 }
		             }
		             }
		         
		         catch (Exception e) {
		             e.printStackTrace();
		         }
		 }

		}

		                
		
