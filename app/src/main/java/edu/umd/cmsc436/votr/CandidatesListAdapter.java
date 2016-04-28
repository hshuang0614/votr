package edu.umd.cmsc436.votr;

/**
 * Created by webforce on 4/21/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        theName.setText("Name:" + candidate.getName());

        final TextView theParty = (TextView) candidateLayout.findViewById(R.id.party);
        theParty.setText("Party:" + candidate.getParty());

        final TextView theAge = (TextView) candidateLayout.findViewById(R.id.age);
        theAge.setText("Age:" + candidate.getAge());

        final ImageView picture = (ImageView) candidateLayout.findViewById(R.id.picture);
        //set picture to bitmap
        picture.setImageDrawable(candidate.getImage());


        return candidateLayout;
    }

    //possible need for async task here to retrieve image from url

}