package edu.umd.cmsc436.votr;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CandidatesActivity extends AppCompatActivity {
    private ArrayList<Candidate> candidates = new ArrayList<Candidate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        candidateDataFromXml();

        ListView listView = (ListView) findViewById(R.id.listView);
        CandidatesListAdapter adapter = new CandidatesListAdapter(this, candidates);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(CandidatesActivity.this, CandidateDetailActivity.class);
                Candidate candidate = candidates.get(position);
                candidate.packIntent(i);
                startActivity(i);
            }
        });

        listView.setAdapter(adapter);


    }

    private void candidateDataFromXml() {
        DocumentBuilder builder;
        String name = "";
        String party = "";
        String age = "";
        String bio = "";
        String status = "";
        String pic_loc = "";
        InputStream str ;
        Bitmap pic = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(getAssets().open("candidates/candidates.xml"));
            NodeList list = document.getDocumentElement().getChildNodes();

            //loop through candidates
            for (int i = 0; i < list.getLength(); i++) {
                Node currCandidate = list.item(i);

                NodeList candidateAttributes = currCandidate.getChildNodes();

                if (candidateAttributes.getLength() > 0) {
                    for (int j = 0; j < candidateAttributes.getLength(); j++) {
                        Node attribute = candidateAttributes.item(j);
                        if (attribute.getNodeName() != null) {
                            if (attribute.getNodeName().equals("name")) {
                                name = attribute.getTextContent();
                            } else if (attribute.getNodeName().equals("party")) {
                                party = attribute.getTextContent();
                            } else if (attribute.getNodeName().equals("age")) {
                                age = attribute.getTextContent();
                            } else if (attribute.getNodeName().equals("status")) {
                                status = attribute.getTextContent();
                            } else if (attribute.getNodeName().equals("bio")) {
                                bio = attribute.getTextContent();
                            } else if (attribute.getNodeName().equals("picture_string")) {
                                pic_loc = attribute.getTextContent();
                            }
                        }
                    }

                    // Add candidate to list
                    Candidate can = new Candidate(name, party, Integer.parseInt(age), status, bio, pic_loc);
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
