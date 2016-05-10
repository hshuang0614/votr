package edu.umd.cmsc436.votr;

/**
 * Created by eric on 4/21/2016.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.InputStream;
import java.io.Serializable;
import java.util.jar.Attributes;


public class Candidate implements Serializable {
    private String Name;
    private String Party;
    private int Age;
    private String Status;
    private String Pic_Loc;
    private String Bio;

    //fields to set candidates picture
    //private Bitmap Pic;

    //final static fields for use with intent extras
    final static String N = "name";
    final static String P = "party";
    final static String A = "age";
    final static String S = "status";
    final static String B = "bio";

    public Candidate(String n, String p, int a, String s, String bio, String pic_loc){
        this.Name = n;
        this.Party = p;
        this.Age = a;
        this.Status = s;
        this.Pic_Loc = pic_loc;
        this.Bio = bio;
    }

    public String getName(){
        return this.Name;
    }

    public int getAge(){
        return this.Age;
    }

    public String getParty(){
        return this.Party;
    }

    public String getStatus(){
        return this.Status;
    }

    public String getBio(){
        return this.Bio;
    }

    public String getPic_Loc(){
        return this.Pic_Loc;
    }

    public Candidate(Intent i){
        this.Name = i.getStringExtra(N);
        this.Party = i.getStringExtra(P);
        this.Status = i.getStringExtra(S);
        this.Bio = i.getStringExtra(B);
        this.Age = i.getIntExtra(A, 0);
        this.Pic_Loc = i.getStringExtra("pic");
    }

    public void packIntent(Intent i){
        i.putExtra(N, Name);
        i.putExtra(A, Age);
        i.putExtra(P, Party);
        i.putExtra(S, Status);
        i.putExtra(B, Bio);
        i.putExtra("pic", Pic_Loc);

    }


}
