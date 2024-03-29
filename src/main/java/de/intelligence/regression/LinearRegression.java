package de.intelligence.regression;

import de.intelligence.data.Example;
import de.intelligence.data.FullDataSet;
import de.intelligence.math.Vector;

public class LinearRegression implements Regression<Double> {

    @Override
    public RegressionModel<Double> generateModel(FullDataSet<Double> dataSet) {
        return this.generateModel(dataSet, 1e-3, 15000);
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
            // -- Gradient Descent for optimizing the w and b parameter
            // init data for this epoch
            Vector dl_dw = new Vector(D);
            double dl_db = 0;
            // compute error for each training data example
            for (Example<Double> examples : trainingDataSet) {
                Vector xi = examples.getFeatureVector();
                double yi = examples.getLabel().getValue();
                // calculate with partial derivatives
                dl_dw.addInPlace(xi.scale(-2 * (yi - (w.multiply(xi) + b))));
                dl_db += -2 * (yi - (w.multiply(xi) + b));
            }
            // set learned parameters
            w.subInPlace(dl_dw.scaleInPlace(1.0 / N * alpha));
            b -= (1.0 / N) * alpha * dl_db;

            System.out.println("updated parameters with w=" + w + " and b=" + String.format("%.4f", b));
//            if (e % 400 == 0)
//                System.out.println("epoch: " + e + ", loss: " + avgLoss(dataSet));
        }
        return new LinearRegressionModel(w, b);
    }

//    private double avgLoss(FullDataSet<Double> dataSet) {
//        int N = dataSet.size();
//        double totalError = 0;
//        for (Example<Double> example : dataSet) {
//            Vector x = example.getFeatureVector();
//            double y = example.getLabel().getValue();
//            double c = (y - (w.multiply(x) + b));
//            totalError += c * c;
//        }
//        return totalError / (double) N;
//    }
}
