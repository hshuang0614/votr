package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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