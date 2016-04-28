package edu.umd.cmsc436.votr.civicinfo;

import android.os.AsyncTask;

import com.google.api.services.civicinfo.model.VoterInfoResponse;

import java.io.IOException;

public class GetPollingLocationsTask extends AsyncTask<GetPollingLocationsQuery, Void, VoterInfoResponse> {
    private final GetPollingLocationsListener listener;

    public GetPollingLocationsTask(GetPollingLocationsListener listener) {
        this.listener = listener;
    }

    @Override
    protected VoterInfoResponse doInBackground(GetPollingLocationsQuery... params) {
        GetPollingLocationsQuery query = params[0];

        try {
            return query.execute();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(VoterInfoResponse voterInfoResponse) {
        listener.onPollingLocationsFound(voterInfoResponse);
    }
}
