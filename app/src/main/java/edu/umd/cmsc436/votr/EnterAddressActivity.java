package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EnterAddressActivity extends AppCompatActivity {
    private String[] stateCodes;
    private String[] stateNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);

        initializeStates();

        int listItemLayout = android.R.layout.simple_dropdown_item_1line;

        final Spinner statesDropdown = (Spinner) findViewById(R.id.state);
        statesDropdown.setAdapter(new ArrayAdapter<>(this, listItemLayout, stateNames));

        final TextView streetAddress = (TextView) findViewById(R.id.streetAddress);
        final TextView city = (TextView) findViewById(R.id.city);

        Button findPollingLocationButton = (Button) findViewById(R.id.find_polling_location);
        findPollingLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notEmpty(streetAddress) && notEmpty(city)) {
                    String streetAddressStr = streetAddress.getText().toString().trim();
                    String cityStr = city.getText().toString().trim();
                    String stateCodeStr = stateCodes[statesDropdown.getSelectedItemPosition()];

                    String fullAddress = streetAddressStr + ", " + cityStr + ", " + stateCodeStr;

                    if (AddressHelper.latLngFromAddress(getApplicationContext(), fullAddress) == null) {
                        String message = "The provided address is invalid";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        EnterAddressActivity self = EnterAddressActivity.this;
                        Intent intent = new Intent(self, PollingLocationActivity.class);

                        intent.putExtra("city", cityStr);
                        intent.putExtra("state", stateCodeStr);
                        intent.putExtra("streetAddress", streetAddressStr);

                        startActivity(intent);
                    }
                } else {
                    String message = "Please enter a street address and city";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private static boolean notEmpty(TextView textView) {
        return textView.getText().toString().trim().length() > 0;
    }

    private void initializeStates() {
        stateCodes = new String[51];  // 50 states + DC
        stateNames = new String[51];

        // Set first item to blank.
        int stateCount = 0;

        // Add all US states.
        stateCodes[stateCount] = "AL";
        stateNames[stateCount++] = "Alabama";
        stateCodes[stateCount] = "AK";
        stateNames[stateCount++] = "Alaska";
        stateCodes[stateCount] = "AZ";
        stateNames[stateCount++] = "Arizona";
        stateCodes[stateCount] = "AR";
        stateNames[stateCount++] = "Arkansas";
        stateCodes[stateCount] = "CA";
        stateNames[stateCount++] = "California";
        stateCodes[stateCount] = "CO";
        stateNames[stateCount++] = "Colorado";
        stateCodes[stateCount] = "CT";
        stateNames[stateCount++] = "Connecticut";
        stateCodes[stateCount] = "DE";
        stateNames[stateCount++] = "Delaware";
        stateCodes[stateCount] = "FL";
        stateNames[stateCount++] = "Florida";
        stateCodes[stateCount] = "GA";
        stateNames[stateCount++] = "Georgia";
        stateCodes[stateCount] = "HI";
        stateNames[stateCount++] = "Hawaii";
        stateCodes[stateCount] = "ID";
        stateNames[stateCount++] = "Idaho";
        stateCodes[stateCount] = "IA";
        stateNames[stateCount++] = "Iowa";
        stateCodes[stateCount] = "IL";
        stateNames[stateCount++] = "Illinois";
        stateCodes[stateCount] = "IN";
        stateNames[stateCount++] = "Indiana";
        stateCodes[stateCount] = "KS";
        stateNames[stateCount++] = "Kansas";
        stateCodes[stateCount] = "KY";
        stateNames[stateCount++] = "Kentucky";
        stateCodes[stateCount] = "LA";
        stateNames[stateCount++] = "Louisiana";
        stateCodes[stateCount] = "ME";
        stateNames[stateCount++] = "Maine";
        stateCodes[stateCount] = "MD";
        stateNames[stateCount++] = "Maryland";
        stateCodes[stateCount] = "MA";
        stateNames[stateCount++] = "Massachusetts";
        stateCodes[stateCount] = "MI";
        stateNames[stateCount++] = "Michigan";
        stateCodes[stateCount] = "MN";
        stateNames[stateCount++] = "Minnesota";
        stateCodes[stateCount] = "MS";
        stateNames[stateCount++] = "Mississippi";
        stateCodes[stateCount] = "MO";
        stateNames[stateCount++] = "Missouri";
        stateCodes[stateCount] = "MT";
        stateNames[stateCount++] = "Montana";
        stateCodes[stateCount] = "NE";
        stateNames[stateCount++] = "Nebraska";
        stateCodes[stateCount] = "NV";
        stateNames[stateCount++] = "Nevada";
        stateCodes[stateCount] = "NH";
        stateNames[stateCount++] = "New Hampshire";
        stateCodes[stateCount] = "NJ";
        stateNames[stateCount++] = "New Jersey";
        stateCodes[stateCount] = "NM";
        stateNames[stateCount++] = "New Mexico";
        stateCodes[stateCount] = "NY";
        stateNames[stateCount++] = "New York";
        stateCodes[stateCount] = "NC";
        stateNames[stateCount++] = "North Carolina";
        stateCodes[stateCount] = "ND";
        stateNames[stateCount++] = "North Dakota";
        stateCodes[stateCount] = "OH";
        stateNames[stateCount++] = "Ohio";
        stateCodes[stateCount] = "OK";
        stateNames[stateCount++] = "Oklahoma";
        stateCodes[stateCount] = "OR";
        stateNames[stateCount++] = "Oregon";
        stateCodes[stateCount] = "PA";
        stateNames[stateCount++] = "Pennsylvania";
        stateCodes[stateCount] = "RI";
        stateNames[stateCount++] = "Rhode Island";
        stateCodes[stateCount] = "SC";
        stateNames[stateCount++] = "South Carolina";
        stateCodes[stateCount] = "SD";
        stateNames[stateCount++] = "South Dakota";
        stateCodes[stateCount] = "TN";
        stateNames[stateCount++] = "Tennessee";
        stateCodes[stateCount] = "TX";
        stateNames[stateCount++] = "Texas";
        stateCodes[stateCount] = "UT";
        stateNames[stateCount++] = "Utah";
        stateCodes[stateCount] = "VT";
        stateNames[stateCount++] = "Vermont";
        stateCodes[stateCount] = "VA";
        stateNames[stateCount++] = "Virginia";
        stateCodes[stateCount] = "WA";
        stateNames[stateCount++] = "Washington";
        stateCodes[stateCount] = "DC";
        stateNames[stateCount++] = "Washington DC";
        stateCodes[stateCount] = "WV";
        stateNames[stateCount++] = "West Virgina";
        stateCodes[stateCount] = "WI";
        stateNames[stateCount++] = "Wisconsin";
        stateCodes[stateCount] = "WY";
        stateNames[stateCount] = "Wyoming";
    }
}