package edu.umd.cmsc436.votr;

import com.aliasi.classify.JointClassification;
import com.aliasi.classify.LMClassifier;


public class SentimentClassifier {
    String[] categories;
    public static LMClassifier c;

    public SentimentClassifier() {
        this.categories = this.c.categories();
    }

    public String classify(String text) {
        JointClassification classification = this.c.classify(text);
        return classification.bestCategory();
    }
}