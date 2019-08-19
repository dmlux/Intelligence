package de.intelligence;

import de.intelligence.data.FullDataSet;
import de.intelligence.data.Label;
import de.intelligence.math.Vector;
import de.intelligence.regression.LinearRegression;
import de.intelligence.regression.RegressionModel;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        FullDataSet<Double> dataSet = new FullDataSet<>(1);

        // fill data with noise
        for (int i = 0; i < 1000; i++) {
            int x = getRandomIntInRange(0, 50);
            Label<Double> y = new Label<>(f(x) + getRandomDoubleInRange(-5, 5));
            Vector featureVector = new Vector(1);
            featureVector.set(0, x);
            dataSet.addExample(featureVector, y);
        }

        LinearRegression regression = new LinearRegression();
        RegressionModel<Double> model = regression.generateModel(dataSet);

        Vector testVector = new Vector(1);
        testVector.set(0, 12);
        System.out.println(model.predict(testVector));
    }

    private static double f(int x) {
        return 2.7 * x + 9.45;
    }

    private static int getRandomIntInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static double getRandomDoubleInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }
}
