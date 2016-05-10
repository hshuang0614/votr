package edu.umd.cmsc436.votr.CivicInfo;

import android.content.Context;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.*;
import com.google.api.client.json.jackson2.*;

import com.google.api.services.civicinfo.*;
import com.google.api.services.civicinfo.model.VoterInfoResponse;

import java.io.IOException;

import edu.umd.cmsc436.votr.AddressHelper;

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
        initializeVoterInfoQuery(AddressHelper.addressFromLatLng(context, latitude, longitude));
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
}
