package de.intelligence.examples;

import de.intelligence.data.Example;
import de.intelligence.data.FullDataSet;
import de.intelligence.data.Label;
import de.intelligence.math.Randomizer;
import de.intelligence.math.Vector;
import de.intelligence.regression.LogisticRegression;
import de.intelligence.regression.RegressionModel;

public class LogisticRegressionExample {
    public static void main(String[] args) {
        // generate random data to train
        FullDataSet<Double> dataSet = generateData();

        // generate a model for linear regression
        RegressionModel<Double> model = new LogisticRegression().generateModel(dataSet, 9e-2, 1000000);

        // use the model to predict a value
        System.out.println(model.predict(Vector.of(9, 27)));
    }

    private static FullDataSet<Double> generateData() {
        // start algorithm by first create a data set
        FullDataSet<Double> dataSet = new FullDataSet<>(2);
        dataSet.addExample(Vector.of(2.7810836, 2.550537003), new Label<>(0.0));
        dataSet.addExample(Vector.of(1.465489372, 2.362125076), new Label<>(0.0));
        dataSet.addExample(Vector.of(3.396561688, 4.400293529), new Label<>(0.0));
        dataSet.addExample(Vector.of(1.38807019, 1.850220317), new Label<>(0.0));
        dataSet.addExample(Vector.of(3.06407232, 3.005305973), new Label<>(0.0));
        dataSet.addExample(Vector.of(7.627531214, 2.759262235), new Label<>(1.0));
        dataSet.addExample(Vector.of(5.332441248, 2.088626775), new Label<>(1.0));
        dataSet.addExample(Vector.of(6.922596716, 1.77106367), new Label<>(1.0));
        dataSet.addExample(Vector.of(8.675418651, -0.2420686549), new Label<>(1.0));
        dataSet.addExample(Vector.of(7.673756466, 3.508563011), new Label<>(1.0));
//        // generate passed students
//        for (int i = 0; i < 5000; i++) {
//            // generate random x
//            int slept = Randomizer.getRandomIntInRange(6, 10);
//            int hoursLearned = Randomizer.getRandomIntInRange(15, 30);
//            // generate noisy y for the given x and use it as an example in the dataset
//            dataSet.addExample(Vector.of(slept, hoursLearned), new Label<>(1.0));
//        }
//        // generate failed students
//
//        for (int i = 0; i < 5000; i++) {
//            // generate random x
//            int slept = Randomizer.getRandomIntInRange(4, 6);
//            int hoursLearned = Randomizer.getRandomIntInRange(5, 25);
//            // generate noisy y for the given x and use it as an example in the dataset
//            dataSet.addExample(Vector.of(slept, hoursLearned), new Label<>(0.0));
//        }
        return dataSet;
    }
}
