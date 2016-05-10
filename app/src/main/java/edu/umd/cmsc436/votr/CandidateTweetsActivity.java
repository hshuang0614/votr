package edu.umd.cmsc436.votr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class CandidateTweetsActivity extends AppCompatActivity {

    private String mCandidateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_tweets);

        try {
            mCandidateName = getIntent().getStringExtra("name");



            String[] res = new TwitterAsync().execute(mCandidateName).get();

            ArrayList<String> n = new ArrayList<String>();
            ArrayList<String> v = new ArrayList<>();
            SentimentClassifier s = new SentimentClassifier();

            for (int i = 0; i < res.length; ++i) {
                String st = res[i];
                n.add(st);
                try {
                    v.add(s.classify(st));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                ;
            }

            TweetAdapter adap = new TweetAdapter(this, n, v);

            String firstOnly = mCandidateName.substring(0,  mCandidateName.indexOf(" "));

            setTitle(firstOnly + "'s Tweets - " + adap.getScore() + "% Approval");

            ((ListView) findViewById(R.id.tweets)).setAdapter(adap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
