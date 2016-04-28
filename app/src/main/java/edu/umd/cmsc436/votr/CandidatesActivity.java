package edu.umd.cmsc436.votr;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CandidatesActivity extends AppCompatActivity {

    private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);
        //API code here?
        //populate lists and inflate items using candidateslistadapter
        candidateDataFromXml();
        CandidatesListAdapter adapter = new CandidatesListAdapter(this, candidates);
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
                Candidate can = new Candidate(name,party,Integer.parseInt(age),pic);
                candidates.add(can);
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
