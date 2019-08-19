package de.intelligence.regression;

import de.intelligence.data.FullDataSet;

public interface Regression<T> {

    RegressionModel generateModel(FullDataSet<T> dataSet);
}
