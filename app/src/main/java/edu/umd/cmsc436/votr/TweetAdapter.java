package edu.umd.cmsc436.votr;

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
    Drawable side, up, down;
    private double score;

    ArrayList<String> str2;

    public TweetAdapter(Context c, ArrayList<String> str, ArrayList<String> str2) {
        mContext = c;
        inflater = LayoutInflater.from(mContext);
        list = str;
        this.str2 = str2;
        this.score = 0;

        int size = 0;

        for (String scoreStr : str2) {
            if (scoreStr != null) {
                ++size;

                if (scoreStr.equals("neu")) {
                    this.score += 50;
                } else if (scoreStr.equals("neg")) {
                    this.score += 0;
                } else
                    this.score += 100;
            }
        }

        score /= size;

        try {
            down = (Drawable.createFromStream(mContext.getAssets().open("images/down.png"), null));
            up = (Drawable.createFromStream(mContext.getAssets().open("images/up.png"), null));

            side = (Drawable.createFromStream(
                    mContext.getAssets().open("images/side.jpg"), null));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return Double.valueOf(score).intValue();
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
        try {
            final String str = list.get(position);
            RelativeLayout candidateLayout = (RelativeLayout)
                    LayoutInflater.from(mContext).inflate(R.layout.tweet, parent, false);

            final TextView theName = (TextView) candidateLayout.findViewById(R.id.name);
            theName.setText(str);

            final ImageView picture = (ImageView) candidateLayout.findViewById(R.id.picture);
            String s = str2.get(position);

            // Set picture bitmap.
            if (s.equals("neg")) {
                picture.setImageDrawable(down);
            } else if (s.equals("pos")) {
                picture.setImageDrawable(up);
            } else {
                picture.setImageDrawable(side);
            }
            return candidateLayout;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}