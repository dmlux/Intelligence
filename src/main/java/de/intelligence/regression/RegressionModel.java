package de.intelligence.regression;

import de.intelligence.math.Vector;

public interface RegressionModel<T> {

    T predict(Vector x);
}
