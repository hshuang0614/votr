package edu.umd.cmsc436.votr;

/**
 * Created by eric on 4/21/2016.
 */
import android.content.Intent;
import android.graphics.Bitmap;


public class Candidate {
    private String Name;
    private String Party;
    private int Age;

    //fields to set candidates picture
    private Bitmap image;
    private String imageURL;

    //final static fields for use with intent extras
    final static String N = "name";
    final static String P = "party";
    final static String A = "age";


    public Candidate(String n, String p, int a){
        this.Name = n;
        this.Party = p;
        this.Age = a;
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

    public Candidate(Intent i){
        this.Name = i.getStringExtra(N);
        this.Party = i.getStringExtra(P);
        this.Age = Integer.parseInt(i.getStringExtra(A));
    }

    public static void packIntent(Intent i, String nn, String aa, String pp){
        i.putExtra(N,nn);
        i.putExtra(A,aa);
        i.putExtra(P,pp);
    }



}
