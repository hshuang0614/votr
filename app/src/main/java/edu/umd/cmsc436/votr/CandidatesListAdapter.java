package edu.umd.cmsc436.votr;

/**
 * Created by webforce on 4/21/2016.
 */
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class CandidatesListAdapter extends BaseAdapter {

    //internal array list of candidate objects.
    private ArrayList<Candidate> list = new ArrayList<Candidate>();

    private static LayoutInflater inflater = null;
    private Context mContext;

    public CandidatesListAdapter(Context c, ArrayList<Candidate> candidates){
        mContext = c;
        inflater = LayoutInflater.from(mContext);
        list = candidates;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Candidate candidate = list.get(position);
        RelativeLayout candidateLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.candidate, parent, false);

        final TextView theName = (TextView) candidateLayout.findViewById(R.id.name);
        theName.setText(candidate.getName());

        final TextView theParty = (TextView) candidateLayout.findViewById(R.id.party);
        theParty.setText(candidate.getParty());

        final TextView theAge = (TextView) candidateLayout.findViewById(R.id.age);
        theAge.setText(candidate.getAge() + " years old");

       final TextView theStatus = (TextView) candidateLayout.findViewById(R.id.status);
       theStatus.setText(candidate.getStatus());

        final ImageView picture = (ImageView) candidateLayout.findViewById(R.id.picture);
        //set picture to bitmap
        InputStream str = null;
        try {
            str = mContext.getAssets().open("images/" + candidate.getPic_Loc() + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        picture.setImageBitmap(BitmapFactory.decodeStream(str));


        return candidateLayout;
    }

    //possible need for async task here to retrieve image from url

}