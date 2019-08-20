package de.intelligence.regression;

import de.intelligence.data.Example;
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
            // -- Gradient Ascent for optimizing the w and b parameter
            // init data for this epoch
            Vector dLogLwb_dw = new Vector(D);
            double dLogLwb_db = 0;
            // compute error for each training data example
            for (Example<Double> examples : trainingDataSet) {
                Vector xi = examples.getFeatureVector();
                double yi = examples.getLabel().getValue();
                double factor = (double)N / (Math.exp(b + w.multiply(xi)) + 1.0);
                // calculate with partial derivatives
                dLogLwb_dw.subInPlace(xi.scale(factor));
                dLogLwb_db -= factor;
            }
            // set learned parameters
            w.addInPlace(dLogLwb_dw.scale(alpha));
            b += dLogLwb_db * alpha;

            System.out.println("updated parameters with w=" + w + " and b=" + String.format("%.4f", b));
        }
        return new LogisticRegressionModel(w, b);
    }
}
