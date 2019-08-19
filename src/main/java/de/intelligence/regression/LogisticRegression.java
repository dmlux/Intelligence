package de.intelligence.regression;

import de.intelligence.data.FullDataSet;
import de.intelligence.math.Vector;

public class LogisticRegression implements Regression<Double> {
    @Override
    public RegressionModel<Double> generateModel(FullDataSet<Double> trainingDataSet) {
        return this.generateModel(trainingDataSet, 1e-3, 15000);
    }

    public RegressionModel<Double> generateModel(FullDataSet<Double> trainingDataSet, double alpha, int epochs) {
        // extract information
        int N = trainingDataSet.size();
        int D = trainingDataSet.featureDimension();
        // create initial data
        Vector w = new Vector(D);
        double b = 0;
        // train the model ad optimize w and b parameters
        for (int e = 0; e < epochs; e++) {

        }
        return new LogisticRegressionModel(w, b);
    }
}
