package de.intelligence.data;

import de.intelligence.math.Vector;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UnlabeledDataSet implements DataSet<Void> {

    private final Set<Example<Void>> examples;
    private final int featureVectorDimension;

    public UnlabeledDataSet(int featureVectorDimension) {
        examples = new HashSet<>();
        this.featureVectorDimension = featureVectorDimension;
    }

    @Override
    public void addExample(Vector featureVector, Label<Void> label) {
        if (featureVector.size() != featureVectorDimension)
            throw new IllegalArgumentException("Feature vectors for a data set need to have equal sizes");
        examples.add(new Example<Void>(featureVector, null));
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<Example<Void>> iterator() {
        return examples.iterator();
    }
}
