package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.api.services.civicinfo.model.Election;

import org.json.JSONObject;

import edu.umd.cmsc436.votr.OpenFEC.ElectionDateLoaderTask;

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
}
