package de.intelligence.regression;

import de.intelligence.data.Example;
import de.intelligence.data.FullDataSet;
import de.intelligence.math.Vector;

public class LinearRegression implements Regression<Double> {

    private Vector w;
    private double b;

    @Override
    public RegressionModel<Double> generateModel(FullDataSet<Double> dataSet) {
        // extract information
        int D = dataSet.featureDimension();
        // create initial data
        w = new Vector(D);
        b = 0;
        // train the model ad optimize w and b parameters
        // The value when error is not considered to be better
        double alpha = 0.001;
        int epochs = 15000;
        // train data
        for (int e = 0; e < epochs; e++) {
            updateBW(dataSet, alpha);
            if (e % 400 == 0)
                System.out.println("epoche: " + e + ", loss: " + avgLoss(dataSet));
        }
        return new LinearRegressionModel(w, b);
    }

    private void updateBW(FullDataSet<Double> dataSet, double alpha) {
        int D = dataSet.featureDimension();
        int N = dataSet.size();

        Vector dl_dw = new Vector(D);
        double dl_db = 0;

        for (Example<Double> e : dataSet) {
            Vector xi = e.getFeatureVector();
            double yi = e.getLabel().getValue();

            dl_dw.addInPlace(xi.scale(- 2 * (yi - (w.multiply(xi) + b))));
            dl_db += -2 * (yi - (w.multiply(xi) + b));
        }

        w.subInPlace(dl_dw.scaleInPlace(1.0 / N * alpha));
        b -= (1.0 / N) * alpha * dl_db;

        System.out.println("updated w=" + w + " and b=" + b);
    }

    private double avgLoss(FullDataSet<Double> dataSet) {
        int N = dataSet.size();
        double totalError = 0;
        for (Example<Double> example : dataSet) {
            Vector x = example.getFeatureVector();
            double y = example.getLabel().getValue();
            double c = (y - (w.multiply(x) + b));
            totalError += c * c;
        }
        return totalError / (double) N;
    }
}
