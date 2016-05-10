package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.content.res.AssetManager;
import android.location.LocationManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Streams;

import org.w3c.dom.Document;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainActivity extends AppCompatActivity {
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Twitter twitter =TwitterFactory.getSingleton();


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
        }catch(Exception e) {e.printStackTrace();};


        // Initialize LocationManager.
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Initialize buttons
        Button candidatesButton = (Button) findViewById(R.id.candidates);
        Button pollingLocationButton = (Button) findViewById(R.id.polling_location);
        Button earlyVoteSites = (Button) findViewById(R.id.early_vote_sites);

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
                Intent i = new Intent(MainActivity.this, PollingLocationActivity.class);
                startActivity(i);
            }
        });

        earlyVoteSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}