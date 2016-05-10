//package edu.umd.cmsc436.votr;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by kk on 4/28/16.
// */
//public class TwitterView extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button candidatesButton = (Button) findViewById(R.id.candidates);
//
//        candidatesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, CandidatesActivity.class);
//                startActivity(i);
//            }
//        });
//    }
//}
