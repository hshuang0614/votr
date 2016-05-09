package edu.umd.cmsc436.votr.civicinfo;

import com.google.api.services.civicinfo.model.VoterInfoResponse;

public interface GetPollingLocationsListener {
    void onPollingLocationsFound(VoterInfoResponse response);
    void onPollingLocationError(Exception exception);
}
