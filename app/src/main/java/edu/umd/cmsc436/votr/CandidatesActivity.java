package edu.umd.cmsc436.votr;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CandidatesActivity extends AppCompatActivity {

    private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    HashSet<String> cand = new HashSet<String>();
    String arr[] =new String[5];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);
        //API code here?
        //populate lists and inflate items using candidateslistadapter
        arr[0] = "Hillary Clinton";
        arr[1] = "Bernie Sanders";
        arr[2] = "Donlad Trump";
        arr[3] = "Ted Cruz";
        arr[4] = "John Kasich";

        candidateDataFromXml();
        CandidatesListAdapter adapter = new CandidatesListAdapter(this, candidates);
        ListView list= (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView <? > arg0, View view, int position, long id) {
                setContentView(R.layout.activity_candidates);
                try {
                    String[] res = new TwitterAsync().execute(arr[position]).get();

                    ArrayList<String> n = new ArrayList<String>();
                    for (int i = 0; i < res.length; ++i) {
                        n.add(res[i]);
                    }

                    TweetAdapter adap = new TweetAdapter(CandidatesActivity.this, n);

                    ((ListView) findViewById(R.id.listView)).setAdapter(adap);

                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        });
    }


    private void candidateDataFromXml() {
        DocumentBuilder builder;
        String name = "";
        String party = "";
        String age = "";
        String pic_loc = "";

        Drawable pic = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(getAssets().open("candidates/candidates.xml"));
            NodeList list = document.getDocumentElement().getChildNodes();
            //loop through candidates
            for (int i = 0; i < list.getLength(); i++) {
                Node currCandidate = list.item(i);

                NodeList candidateAttributes = currCandidate.getChildNodes();

                for (int j = 0; j < candidateAttributes.getLength(); j++) {

                    Node attribute = candidateAttributes.item(j);
                    if (attribute.getNodeName() != null) {
                        if (attribute.getNodeName().equals("name")) {
                            name = attribute.getTextContent();
                        } else if (attribute.getNodeName().equals("party")) {
                            party = attribute.getTextContent();
                        } else if (attribute.getNodeName().equals("age")) {
                            age = attribute.getTextContent();
                        } else if (attribute.getNodeName().equals("picture_string")) {
                            pic_loc = attribute.getTextContent();
                            pic = Drawable.createFromStream(getAssets().open("images/" + pic_loc + ".jpg"), null);
                        }
                    }
                }
                //add candidate to list

                if (age.length() != 0 && !cand.contains(name)) {
                    cand.add(name);
                    Candidate can = new Candidate(name, party, Integer.parseInt(age), pic);
                    candidates.add(can);
                }
            }

        } catch (DOMException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
