package de.intelligence.data;

import de.intelligence.math.Vector;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FullDataSet<T> implements DataSet<T> {
    private final Set<Example<T>> examples;
    private final int featureVectorDimension;

    public FullDataSet(int featureVectorDimension) {
        examples = new HashSet<>();
        this.featureVectorDimension = featureVectorDimension;
    }

    @Override
    public void addExample(Vector featureVector, Label<T> label) {
        if (featureVector.size() != featureVectorDimension)
            throw new IllegalArgumentException("Feature vectors for a data set need to have equal sizes");
        if (label == null)
            throw new IllegalArgumentException("This data set needs a label for each feature vector");
        examples.add(new Example<T>(featureVector, label));
    }

    @Override
    public int size() {
        return examples.size();
    }

    @Override
    public Iterator<Example<T>> iterator() {
        return examples.iterator();
    }
}
