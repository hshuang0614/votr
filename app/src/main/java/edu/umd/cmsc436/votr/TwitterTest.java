package edu.umd.cmsc436.votr;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.net.URL;

import twitter4j.*;
import twitter4j.auth.AccessToken;

/**s
 * Created by kk on 4/26/16.
 */
public class TwitterTest extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... tags) {

        try {
            Twitter twitter = TwitterFactory.getSingleton();

            twitter.setOAuthConsumer("RK0ePlZwYZalKH9ZZVFjIVJVR",
                    "xWihEroKYZyRUaCFYhaumTL6cM8F7R6yilSEK5dFP6X9LnlGMQ");

            twitter.setOAuthAccessToken(new AccessToken(
                    "3711878716-0NSO7UxyiXkLfFPdexICDSBQRVWTSsSj1RYg6vA",
                    "x88A8QvV4jXXvatUmnWIYqa6h75pfr1wobVxcPewxSFh4"));

            Query query = new Query(tags[0]);
            QueryResult result = twitter.search(query);

            for (twitter4j.Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    protected void onProgressUpdate(String result) {
    }
}
