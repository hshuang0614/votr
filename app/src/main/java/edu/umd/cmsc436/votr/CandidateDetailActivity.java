package edu.umd.cmsc436.votr;

/**
 * Created by George on 5/9/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class CandidateDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.candidate_detail);
        final Candidate candidate = new Candidate(getIntent());

        final ImageView pic = (ImageView) findViewById(R.id.picture);
        InputStream str = null;
        try {
            str = getAssets().open("images/" + candidate.getPic_Loc() + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pic.setImageBitmap(BitmapFactory.decodeStream(str));

        final TextView name = (TextView) findViewById(R.id.name);
        name.setText(candidate.getName());

        final TextView party = (TextView) findViewById(R.id.party);
        party.setText(candidate.getParty());

        final TextView age = (TextView) findViewById(R.id.age);
        age.setText(candidate.getAge() + " years old");

        final TextView status = (TextView) findViewById(R.id.status);
        status.setText(candidate.getStatus());

        final TextView bio = (TextView) findViewById(R.id.bio);
        bio.setText(candidate.getBio());

        final Button showTweets = (Button) findViewById(R.id.show_tweets);
        showTweets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CandidateDetailActivity.this, CandidateTweetsActivity.class);
                intent.putExtra("name", candidate.getName());

                startActivity(intent);
            }
        });
    }
}