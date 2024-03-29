package de.intelligence.regression;

import de.intelligence.math.Vector;

public class LogisticRegressionModel implements RegressionModel<Double> {

    private Vector w;
    private double b;

    public LogisticRegressionModel(Vector w, double b) {
        this.w = w;
        this.b = b;
    }

    @Override
    public Double predict(Vector x) {
        return this.predict(x, .5);
    }

    public Double predict(Vector x, double threshold) {
        return 1.0 / (1.0 + Math.exp(-(w.multiply(x) + b))) > threshold ? 1.0 : 0.0;
    }
}
