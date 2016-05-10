package edu.umd.cmsc436.votr;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.lang.Object;
import java.lang.Class;
import java.lang.Object.*;
import java.lang.*;

import java.net.URL;


import twitter4j.*;
import twitter4j.auth.AccessToken;

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