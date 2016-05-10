package edu.umd.cmsc436.votr.OpenFEC;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import edu.umd.cmsc436.votr.R;

public class ElectionDateLoaderTask extends AsyncTask<Void, Void, JSONObject> {
    private final String resource;

    public ElectionDateLoaderTask(Context context, String state) {
        String apiKey = context.getString(R.string.open_fec_api_key);

        resource = "https://api.open.fec.gov/v1/election-dates/" +
                "?sort=-election_date&office_sought=P&page=1&per_page=20&election_year=2016" +
                "&election_state=" + state + "&api_key=" + apiKey;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(resource);
            connection = (HttpsURLConnection) url.openConnection();

            return new JSONObject(read(connection));
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    private static String read(HttpsURLConnection connection) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line + "\n");
        }

        return stringBuffer.toString();
    }
}
