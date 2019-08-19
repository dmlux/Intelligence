package de.intelligence.data;

import de.intelligence.math.Vector;

public interface DataSet<T> extends Iterable<Example<T>> {

    void addExample(Vector featureVector, Label<T> label);

    int size();
}
