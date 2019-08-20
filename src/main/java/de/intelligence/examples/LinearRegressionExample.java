package de.intelligence.examples;

import de.intelligence.data.FullDataSet;
import de.intelligence.data.Label;
import de.intelligence.math.Randomizer;
import de.intelligence.math.Vector;
import de.intelligence.regression.LinearRegression;
import de.intelligence.regression.RegressionModel;

public class LinearRegressionExample {

    public static void main(String[] args) {
        // generate random data to train
        FullDataSet<Double> dataSet = generateData();

        // generate a model for linear regression
        RegressionModel<Double> model = new LinearRegression().generateModel(dataSet, 9e-4, 30000);

        // use the model to predict a value
        System.out.println(model.predict(Vector.of(12)));
    }

    private static FullDataSet<Double> generateData() {
        // start algorithm by first create a data set
        FullDataSet<Double> dataSet = new FullDataSet<>(1);
        // fill data set with examples that have some noise on it
        for (int i = 0; i < 10000; i++) {
            // generate random x
            int x = Randomizer.getRandomIntInRange(0, 50);
            // generate noisy y for the given x and use it as an example in the dataset
            dataSet.addExample(Vector.of(x), new Label<>(f(x) + Randomizer.getRandomDoubleInRange(-15, 15)));
        }
        return dataSet;
    }

    private static double f(int x) {
        return 5.42353 * x - 2.34223;
    }
}
