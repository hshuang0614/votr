package edu.umd.cmsc436.votr;

import android.os.AsyncTask;
import android.util.Log;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**s
 * Created by kk on 4/26/16.
 */
public class TwitterAsync extends AsyncTask<String, Void, String[]> {
    protected String[] doInBackground(String... tags) {

        try {

            SentimentClassifier s = new SentimentClassifier();
            Twitter twitter = TwitterFactory.getSingleton();

            Query query = new Query(tags[0]);
            QueryResult result = twitter.search(query);

            String[] twts =new String[15];
            int i = 0;


            for (twitter4j.Status status : result.getTweets()) {
                twts[i++] = status.getText();
                Log.i("HI", s.classify(status.getText()));
            }

            return twts;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    protected void onProgressUpdate(String result) {
    }
}