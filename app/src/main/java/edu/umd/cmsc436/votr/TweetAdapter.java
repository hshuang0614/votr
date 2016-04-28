package edu.umd.cmsc436.votr;

/**
 * Created by webforce on 4/21/2016.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class TweetAdapter extends BaseAdapter {

    //internal array list of candidate objects.
    private ArrayList<String> list = new ArrayList<String>();

    private static LayoutInflater inflater = null;
    private Context mContext;
    Drawable pic;

    public TweetAdapter(Context c, ArrayList<String> str){
        mContext = c;
        inflater = LayoutInflater.from(mContext);
        list = str;
        try {


            pic = (Drawable.createFromStream(mContext.getAssets().open("images/Twitter_logo_blue.png"), null));
        }catch (Exception e){};
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
        final String str = list.get(position);
        RelativeLayout candidateLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.tweet, parent, false);

        final TextView theName = (TextView) candidateLayout.findViewById(R.id.name);
        theName.setText(str);

        final ImageView picture = (ImageView) candidateLayout.findViewById(R.id.picture);

        //set picture to bitmap
        try {
            picture.setImageDrawable(pic);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return candidateLayout;
    }
}