package MyApp;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyApp {
	
	Map<Integer,Integer> myApp;
    Map<Integer,List<Integer>> followers;
    Deque<Integer> PostList;
    /** Initialize your data structure */
    public MyApp() {
    	myApp =  new HashMap<>();
        followers = new HashMap<>();
        PostList = new LinkedList<>();
    }
    
    /** Compose a new post. */
    public void postMessage(int userId, int tweetId) {
    	myApp.put(tweetId,userId);
    	PostList.addFirst(tweetId);
    }
    
    /**Retrieve the 10 most recent post ids in the user's news feed. Each item in the news feed
    must be posted by users who the user followed or by the user herself. Posts must be ordered
    from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> Folowerlist = followers.get(userId);
            List<Integer> result = new ArrayList<>();
            for(int n:PostList)
            {
                if(result.size()==10)
                    break;
                if((Folowerlist!=null && Folowerlist.contains(myApp.getOrDefault(n,-1))) || myApp.getOrDefault(n,-1)==userId)
                {
                	result.add(n);
                }        
            }
            return result;
        }
        
        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            List<Integer> FList = followers.get(followerId);
            if(FList!=null && FList.contains(followeeId))
            {
                return;
            }
            else if(FList==null)
            {
            	List<Integer> ls = new ArrayList<>();
                ls.add(followeeId);
                followers.put(followerId,ls);
            }
            else{
                followers.get(followerId).add(followeeId);
            }
            
        }
        
        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            List<Integer> FWList = followers.get(followerId);
            if(FWList==null || FWList.isEmpty() || !FWList.contains(followeeId))
            {
                return;
            }
            followers.get(followerId).remove(new Integer(followeeId));
        }
        
        
        public static void main(String args[]){  
       	 MyApp obj = new MyApp();
   		 for(int i=0;i<10;i++) {
   			 int userId = 0;
   			 int tweetId = 0;
   			 int followerId = 0;
   			 int followeeId = 0;
       	    		 System.out.println(" Enter 1 to posts a new message");
       	    		 System.out.println(" Enter 2 to news feed");
       	    		 System.out.println(" Enter 3 to follows user");
       	    		 System.out.println(" Enter 4 to unfollows user");
       	    		 System.out.println("<-------Select Your Option-------->");
       	    		 Scanner sc = new Scanner(System.in);
       	    		 String  Option = sc.nextLine();
       	    		 
       	    		 if(Option.equals("1")) {
       	    			 System.out.println("<------Post a new massage------>");
       	    			 
       	    			  
       	    			   try {
       	    				   System.out.println("Enter user ID :");
       	    				   userId = sc.nextInt();
       	    				   System.out.println("Enter Post Message Id :");
       	    				   tweetId = sc.nextInt();
       	    				} catch (Exception e) {
       	    					// TODO: handle exception
       	    					System.out.println(e);
       	    				}
       	    				
       	    				obj.postMessage(userId, tweetId);
       	    		 }
       	    		 
       	    		 else if(Option.equals("2")) {
       	    			 System.out.println("<-------News Feed----->");
       	    			 
       	    			 System.out.println("Enter User ID : ");
       	    			   try {
       	    				   userId = sc.nextInt();
       	    				} catch (Exception e) {
       	    					// TODO: handle exception
       	    					System.out.println(e);
       	    				}
       	    			 
       	    			 List<Integer> param_2 = obj.getNewsFeed(userId);
       	    			 System.out.println("Result : "+param_2);
       	    			 
       	    			 
       	    		 }
       	    		 
       	    		 else if(Option.equals("3") || Option.equals("4")) {
       	    			 
   		    			 if(Option.equals("3")) {
   		    				 System.out.println("<-------Follow user------> ");
   		    			 }else if(Option.equals("4"))
   		    			 {
   		    				 System.out.println("<-------Unfollow user------>");
   		    			 }
   		    			 
   	    			   try {
   	    				   System.out.println("Follower User Id :");
   	    				   followerId = sc.nextInt();
   	    				   System.out.println("Followee User Id :");
   	    				   followeeId = sc.nextInt();
   	    				} catch (Exception e) {
   	    					// TODO: handle exception
   	    					System.out.println(e);
   	    				}
   		    				
   	    				 if(Option.equals("3")) {
   	    					 obj.follow(followerId, followeeId);
   	    				 }else if(Option.equals("4"))
   	    				 {
   	    					 obj.unfollow(followerId, followeeId);
   	    				 }
   		    				  
       	    		 }
       	    		 else
       	    		 {
       	    			System.out.println("Select Right option"); 
       	    		 }
       	    		 i=1;
       	    		 continue;
   	
   		 }
       	}

}
