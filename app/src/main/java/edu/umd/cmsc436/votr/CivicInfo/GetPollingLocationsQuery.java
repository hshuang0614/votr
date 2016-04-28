package edu.umd.cmsc436.votr.civicinfo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.*;
import com.google.api.client.json.jackson2.*;

import com.google.api.services.civicinfo.*;
import com.google.api.services.civicinfo.model.VoterInfoResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetPollingLocationsQuery {
    // Constants
    private static final NetHttpTransport httpTransport = new NetHttpTransport();
    private static final JacksonFactory jsonSerializer = new JacksonFactory();
    private static final HttpRequestInitializer httpRequestInitializer =
            new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest httpRequest) {
                    // Nothing to do.
                }
            };

    // Credentials
    private final static String apiKey = "AIzaSyCHddvK_JT8tMZvzjwvztCA3E1Jc_zHlxw";

    // Query
    private CivicInfo.Elections.VoterInfoQuery query;

    public GetPollingLocationsQuery(String address) throws IOException {
        initializeVoterInfoQuery(address);
    }

    public GetPollingLocationsQuery(Context context, float latitude, float longitude) throws Exception {
        initializeVoterInfoQuery(addressFromLatLon(context, latitude, longitude));
    }

    public VoterInfoResponse execute() throws IOException {
        return this.query.execute();
    }

    private void initializeVoterInfoQuery(String address) throws IOException {
        this.query = getCivicInfo().elections().voterInfoQuery(address);
        this.query.setKey(apiKey);
        this.query.setFields("earlyVoteSites,pollingLocations");
    }

    private static CivicInfo getCivicInfo() {
        return new CivicInfo(httpTransport, jsonSerializer, httpRequestInitializer);
    }

    private static String getLatLonError(float latitude, float longitude) {
        return "Could not find an address at coordinates: (" + latitude + ", " + longitude + ")";
    }

    private static String addressFromLatLon(Context context, float latitude, float longitude) throws Exception {
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            // Here 1 signifies the max amount of addresses we want returned.
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses == null || addresses.isEmpty()) {
                throw new Exception(getLatLonError(latitude, longitude));
            }

            return addressToString(addresses.get(0));
        } catch (IOException e) {
            throw new Exception(getLatLonError(latitude, longitude));
        }
    }

    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    private static String addressToString(Address address) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            if (i > 0) {
                sb.append(", ");
            }

            String line = address.getAddressLine(i);
            if (line != null) {
                sb.append(line);
            }
        }

        // City
        sb.append(", " + address.getLocality());

        // State
        sb.append(", " + address.getAdminArea());

        // Zipcode
        sb.append(", " + address.getPostalCode());

        return sb.toString();
    }
}
