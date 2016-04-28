package edu.umd.cmsc436.votr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.api.services.civicinfo.model.VoterInfoResponse;

import edu.umd.cmsc436.votr.civicinfo.GetPollingLocationsListener;
import edu.umd.cmsc436.votr.civicinfo.GetPollingLocationsQuery;
import edu.umd.cmsc436.votr.civicinfo.GetPollingLocationsTask;

public class CivicInfoTestActivity extends AppCompatActivity implements GetPollingLocationsListener {
    private static final String TAG = CivicInfoTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civic_info_test);

        try {
            GetPollingLocationsQuery query = new GetPollingLocationsQuery("301 Largo Rd, Largo, MD");
            GetPollingLocationsTask task = new GetPollingLocationsTask(this);

            task.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPollingLocationsFound(VoterInfoResponse response) {
        response.getEarlyVoteSites(); // Can be multiple
        response.getPollingLocations(); // Should only be one
    }
}
