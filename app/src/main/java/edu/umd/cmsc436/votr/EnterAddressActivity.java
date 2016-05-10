package edu.umd.cmsc436.votr;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private static final int SHOW_POLL_LOC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);

        stateCodes = getResources().getStringArray(R.array.stateCodes);
        stateNames = getResources().getStringArray(R.array.stateNames);

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

                        startActivityForResult(intent, SHOW_POLL_LOC_REQUEST);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SHOW_POLL_LOC_REQUEST &&
                resultCode == PollingLocationActivity.NO_LOCATION_FOUND) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No polling locations found")
                    .setMessage(getString(R.string.polling_location_not_found_message))
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();

            alert.show();
        }
    }
}