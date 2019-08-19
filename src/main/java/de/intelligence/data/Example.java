package de.intelligence.data;

import de.intelligence.math.Vector;

public class Example<T> {

    private final Vector featureVector;
    private final Label<T> label;

    public Example(Vector featureVector, Label<T> label) {
        this.featureVector = featureVector;
        this.label = label;
    }

    public Vector getFeatureVector() {
        return featureVector;
    }

    public Label<T> getLabel() {
        return label;
    }

    public double getFeature(int index) {
        return featureVector.get(index);
    }
}
