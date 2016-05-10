package edu.umd.cmsc436.votr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import edu.umd.cmsc436.votr.OpenFEC.ElectionDateLoaderTask;

public class VotingDatesActivity extends AppCompatActivity {

    static class ElectionMetadata {
        String electionType;
        Date electionDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_dates);

        try {
            String stateCode = getIntent().getStringExtra("stateCode");
            String stateName = getIntent().getStringExtra("stateName");

            InputStream inputStream = getAssets().open("states/" + stateCode.toLowerCase() + ".png");
            Drawable flag = Drawable.createFromStream(inputStream, null);

            Void params = null;
            Context context = getApplicationContext();

            JSONObject json = new ElectionDateLoaderTask(context, stateCode.toUpperCase())
                    .execute(params)
                    .get();

            ArrayList<ElectionMetadata> electionMetadataList = new ArrayList<ElectionMetadata>();

            if (json != null) {
                if (json.has("results")) {
                    JSONArray results = json.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);

                        if (result.has("election_date") && result.has("election_type_full")) {
                            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
                            ElectionMetadata metadata = new ElectionMetadata();


                            metadata.electionType = result.getString("election_type_full");
                            metadata.electionDate = dateParser.parse(result.getString("election_date"));

                            electionMetadataList.add(metadata);
                        }
                    }
                }
            }

            if (!electionMetadataList.isEmpty()) {
                ImageView stateFlag = (ImageView) findViewById(R.id.state_flag);
                stateFlag.setImageDrawable(flag);

                TextView title = (TextView) findViewById(R.id.title);
                title.setText(stateName + " Election Dates:");

                TextView primaryElection = (TextView) findViewById(R.id.primary_election);
                TextView generalElection = (TextView) findViewById(R.id.general_election);

                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy", Locale.US);

                for (ElectionMetadata e : electionMetadataList) {
                    if (e.electionType.toLowerCase().contains("primary")) {
                        String date = dateFormat.format(e.electionDate);
                        primaryElection.setText("Primary Election on " + date);
                        break;
                    }
                }

                for (ElectionMetadata e : electionMetadataList) {
                    if (e.electionType.toLowerCase().contains("general")) {
                        String date = dateFormat.format(e.electionDate);
                        generalElection.setText("General Election on " + date);
                        break;
                    }
                }
            } else {
                String message = "No voting dates found for the selected state";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                finish();
            }
        } catch (Exception e) {
        }
    }
}
