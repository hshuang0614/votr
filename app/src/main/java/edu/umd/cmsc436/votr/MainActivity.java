package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aliasi.classify.LMClassifier;
import com.aliasi.util.Streams;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupTwitter();

        // Initialize buttons
        Button candidatesButton = (Button) findViewById(R.id.candidates);
        Button pollingLocationButton = (Button) findViewById(R.id.polling_location);
        Button votingDates = (Button) findViewById(R.id.voting_dates);

        // Setup listeners
        candidatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CandidatesActivity.class);
                startActivity(i);
            }
        });

        pollingLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EnterAddressActivity.class);
                startActivity(i);
            }
        });

        votingDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelectStateActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupTwitter() {
        Twitter twitter = TwitterFactory.getSingleton();

        twitter.setOAuthConsumer("RK0ePlZwYZalKH9ZZVFjIVJVR",
                "xWihEroKYZyRUaCFYhaumTL6cM8F7R6yilSEK5dFP6X9LnlGMQ");

        twitter.setOAuthAccessToken(new AccessToken(
                "3711878716-0NSO7UxyiXkLfFPdexICDSBQRVWTSsSj1RYg6vA",
                "x88A8QvV4jXXvatUmnWIYqa6h75pfr1wobVxcPewxSFh4"));

        try {
            InputStream fileIn = null;
            BufferedInputStream bufIn = null;
            ObjectInputStream objIn = null;

            Object var4;
            try {
                fileIn = getAssets().open("classifier.txt");
                bufIn = new BufferedInputStream(fileIn);
                objIn = new ObjectInputStream(bufIn);
                var4 = objIn.readObject();
            } finally {
                Streams.closeQuietly(objIn);
                Streams.closeQuietly(bufIn);
                Streams.closeQuietly(fileIn);
            }
            SentimentClassifier.c = (LMClassifier) var4;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
