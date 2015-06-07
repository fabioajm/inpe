import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwuiterTest {

	public static void main(String[] args) throws TwitterException {
		List<String> tweets = new ArrayList<String>();
		Twitter twitter = TwitterFactory.getSingleton(); 
		twitter.setOAuthConsumer("iPA4ovi2sts843KCAG9UQBDzU", "uSi7UBiuMvpMaoGurhfayzATFQcCalgxXnCPC1wCTtgJ1ancr3"); 
		twitter.setOAuthAccessToken(new AccessToken("49827325-AjbIJlLVANhViqB2g6zAEJESd6BpF0yz4NDvmy0Mn", "GWO4NNerfHnGcZ5BnDd3rTPTcwHWxOGrvuHYDTgxclqJQ"));
		Query query = new Query("source: Just setting up my Twitter");
		QueryResult result; 
		do { 
			result = twitter.search(query); 
			for (Status status : result.getTweets()) { 
				
				tweets.add(status.getCreatedAt() + ":" + status.getUser().getScreenName() + ":" + status.getText()); 
				} 
			} while ((query = result.nextQuery()) != null);
		
		for (String string : tweets) {
			System.out.println(string);
		}



	}
}
