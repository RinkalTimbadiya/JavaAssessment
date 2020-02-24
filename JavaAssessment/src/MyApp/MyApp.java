package MyApp;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
      //  System.out.println(myApp);
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
                System.out.println(followers);
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
        
        
}
