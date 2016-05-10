package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectStateActivity extends AppCompatActivity {
    private String[] mStateCodes;
    private String[] mStateNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);

        mStateCodes = getResources().getStringArray(R.array.stateCodes);
        mStateNames = getResources().getStringArray(R.array.stateNames);

        int listItemLayout = android.R.layout.simple_dropdown_item_1line;

        final Spinner statesDropdown = (Spinner) findViewById(R.id.state);
        statesDropdown.setAdapter(new ArrayAdapter<>(this, listItemLayout, mStateNames));

        Button getVotingDates = (Button) findViewById(R.id.get_voting_dates);
        getVotingDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = (String) statesDropdown.getSelectedItem();

                Intent intent = new Intent(SelectStateActivity.this, VotingDatesActivity.class);
                intent.putExtra("stateCode", mStateCodes[statesDropdown.getSelectedItemPosition()]);
                intent.putExtra("stateName", state);

                startActivity(intent);
            }
        });
    }
}
