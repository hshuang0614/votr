package edu.umd.cmsc436.votr;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.services.civicinfo.model.PollingLocation;
import com.google.api.services.civicinfo.model.SimpleAddressType;
import com.google.api.services.civicinfo.model.VoterInfoResponse;

import java.util.List;

import edu.umd.cmsc436.votr.CivicInfo.GetPollingLocationsListener;
import edu.umd.cmsc436.votr.CivicInfo.GetPollingLocationsQuery;
import edu.umd.cmsc436.votr.CivicInfo.GetPollingLocationsTask;

public class PollingLocationActivity extends FragmentActivity implements OnMapReadyCallback, GetPollingLocationsListener {
    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    private LatLng mLatLng;

    private String mStreetAddress, mCity, mState, mTitle, mPollingHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_polling_location);

        mCity = getIntent().getStringExtra("city");
        mState = getIntent().getStringExtra("state");
        mStreetAddress = getIntent().getStringExtra("streetAddress");

        String fullAddress = mStreetAddress + ", " + mCity + ", " + mState;

        try {
            GetPollingLocationsQuery query = new GetPollingLocationsQuery(fullAddress);
            VoterInfoResponse response = new GetPollingLocationsTask(this).execute(query).get();

            handleVoterInfoResponse(response);
        } catch (Exception e) {
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MarkerOptions options = new MarkerOptions();

        options.position(mLatLng);

        if (mTitle != null) {
            options.title(mTitle);
        }

        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
    }

    private void handleVoterInfoResponse(VoterInfoResponse response) {
        if (response != null &&
                response.getPollingLocations() != null &&
                response.getPollingLocations().size() > 0) {

            PollingLocation pollingLocation = response.getPollingLocations().get(0);
            if (pollingLocation.getAddress() != null) {
                SimpleAddressType addressType = pollingLocation.getAddress();

                String address = simpleAddressTypeToString(addressType);
                mLatLng = AddressHelper.latLngFromAddress(getApplicationContext(), address);

                if (addressType.getLocationName() != null &&
                        addressType.getLocationName().trim().length() > 0) {
                    mTitle = addressType.getLocationName().trim();
                }

                mMapFragment = new SupportMapFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.map_container, mMapFragment)
                        .commit();

                mMapFragment.getMapAsync(this);
            }
        }

        if (response != null) {
            List<PollingLocation> locations = response.getPollingLocations();

            if (locations == null || locations.isEmpty()) {
                String message = "No polling locations found";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                finish();
            }
        } else if (response == null) {
            String message = "No polling locations found";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onPollingLocationsFound(VoterInfoResponse response) {
    }

    @Override
    public void onPollingLocationError(Exception exception) {
    }

    private static String simpleAddressTypeToString(SimpleAddressType addressType) {
        String address = "";

        if (addressType.getLine1() != null && addressType.getLine1().trim().length() > 0) {
            address += addressType.getLine1();
        }

        if (addressType.getLine2() != null && addressType.getLine2().trim().length() > 0) {
            address += ", " + addressType.getLine2();
        }

        if (addressType.getLine3() != null && addressType.getLine3().trim().length() > 0) {
            address += ", " + addressType.getLine3();
        }

        if (addressType.getCity() != null && addressType.getCity().trim().length() > 0) {
            address += ", " + addressType.getCity();
        }

        if (addressType.getState() != null && addressType.getState().trim().length() > 0) {
            address += ", " + addressType.getState();
        }

        if (addressType.getZip() != null && addressType.getZip().trim().length() > 0) {
            address += ", " + addressType.getZip();
        }

        return address;
    }
}
