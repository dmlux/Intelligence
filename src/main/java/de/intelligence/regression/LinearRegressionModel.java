package de.intelligence.regression;

import de.intelligence.math.Vector;

public class LinearRegressionModel implements RegressionModel<Double> {

    private Vector w;
    private double b;

    public LinearRegressionModel(Vector w, double b) {
        this.w = w;
        this.b = b;
    }

    @Override
    public Double predict(Vector x) {
        return w.multiply(x) + b;
    }
}