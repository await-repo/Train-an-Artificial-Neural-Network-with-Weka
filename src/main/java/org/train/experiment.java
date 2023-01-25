package org.train;

import java.util.*;

public class experiment {

    //ubicacion del dataset
    String datasetTrain;
    String datasetTest;

    //String datasetTrain, String datasetTest;
    ArrayList<model> nodeExperiments = new ArrayList<>();
    ArrayList<model> epochExperiments = new ArrayList<>();
    ArrayList<model> learningRateExperiments = new ArrayList<>();
    ArrayList<model> momentumExperiments = new ArrayList<>();

    //Resultados del conjunto de entrenamiento entrenado en el conjunto de Entrenamiento
    ArrayList<model> trainTrain = new ArrayList<>();
    //Resultados del conjunto de entrenamiento entrenado en el conjunto de Prueba
    ArrayList<model> trainTest = new ArrayList<>();


    public experiment(String datasetTrain, String datasetTest) {
        this.datasetTrain = datasetTrain;
        this.datasetTest = datasetTest;
    }

    public void getExperiment (model model, ArrayList<model> exp) throws Exception {
        trainMultilayerPerceptron Experimento = new trainMultilayerPerceptron(model);
        Experimento.setTrain(datasetTrain);
        Experimento.setTest(datasetTest);
        Experimento.init();
        exp.add( Experimento.getModel(model) );
    }

    //Experimentos con las Neuronas
    public void generateExperiments (model model, Object[] tests, ArrayList<model> exp, String arg) throws Exception  {
        for (Object test : tests) {
            switch (arg) {
                case "neurona": model.hiddenLayers = String.valueOf(test); break;
                case "epoca": model.epoch = (int) test; break;
                case "learningRate": model.learningRate = (Double) test; break;
                case "momentum": model.momentum = (Double) test; break;
            }
            getExperiment(model,exp);
        }
        switch (arg) {
            case "neurona": nodeExperiments = exp; break;
            case "epoca": epochExperiments = exp; break;
            case "learningRate": learningRateExperiments = exp; break;
            case "momentum": momentumExperiments = exp; break;
        }
    }




    //Muestra los expermentos
    public void printExperiments(ArrayList<model> exp) {
        exp.forEach(System.out::println);
    }

    //Ordena los experimentos de peor a mejor
    public void sortExperiments (ArrayList<model> exp) {
        exp.sort(Comparator.comparing(o -> o.instClassifCorrectly));
    }

    public void printExperiment (String arg) {
        switch (arg) {
            case "neural": printExperiments(nodeExperiments); break;
            case "epoch": printExperiments(epochExperiments); break;
            case "learningRate": printExperiments(learningRateExperiments); break;
            case "momentum": printExperiments(momentumExperiments); break;
        }
    }

    //Muestra el resultado de todos los Experimentos
    public void printAllExperiments () {
        System.out.println("\nExperimentos ajuntando solo las Neuronas por Capa \n");
        printExperiments(nodeExperiments);

        System.out.println("\nExperimentos ajuntando solo las Epocas \n");
        printExperiments(epochExperiments);

        System.out.println("\nExperimentos ajuntando solo el Learning Rate \n");
        printExperiments(learningRateExperiments);

        System.out.println("\nExperimentos ajuntando solo el Momentum \n");
        printExperiments(momentumExperiments);
    }




    //Imprime el promedio
    public void mean(ArrayList<model> exp){
        Double sum = 0.0;
        for (org.train.model model: exp){
            sum += model.instClassifCorrectly;
        }
        System.out.println( "La media es : "+sum/exp.size() );
    }

    public void printNodeMean () {
        mean(nodeExperiments);
    }

    public void printEpochMean () {
        mean(epochExperiments);
    }

    public void printLearningRateMean () {
        mean(learningRateExperiments);
    }

    public void printMomentumMean () {
        mean(momentumExperiments);
    }


    //Muestra los experimentos de neorunas por epocas
    public void printNodeExperiments () {
        System.out.println("\nExperimentos ajuntando solo las Neuronas por Capa \n");
        printExperiments(nodeExperiments);
    }

    //Muestra los experimentos de las Epocas
    public void printEpochExperiments () {
        System.out.println("\nExperimentos ajuntando solo las Epocas \n");
        printExperiments(epochExperiments);
    }

    //Muestra los experimentos del Learning Rate
    public void printLearningRateExperiments () {
        System.out.println("\nExperimentos ajuntando solo el Learning Rate \n");
        printExperiments(learningRateExperiments);
    }

    //Muestra los experimentos del Momentum
    public void printMomentumExperiments () {
        System.out.println("\nExperimentos ajuntando solo el Momentum \n");
        printExperiments(momentumExperiments);
    }




    /*
    Con esta prueba podemos determinar si nuestro modelo de prueba esta sobre ajustado, una prueba
    sobre ajustada no puede clasificar correctamente escenarion reales
    */

    public void testOverfitting (model model, int[] tests) throws Exception {
        for (int i : tests) {
            model.epoch = i;
            overfitting (model);
        }
        System.out.println("\n\nDatos de Entrenamiento entrenados sobre el conjunto de Entrenamiento\n");
        printExperiments(trainTrain);

        System.out.println("\n\nDatos de Entrenamiento entrenados sobre el conjunto de Prueba\n");
        printExperiments(trainTest);
    }

    public void overfitting (model model) throws Exception {
        trainMultilayerPerceptron Experiment = new trainMultilayerPerceptron(model);
        Experiment.setTrain(datasetTrain);
        Experiment.setTest(datasetTest);

        Experiment.generateClassifier(); //se genera el classificador
        Experiment.useTrainingSet(); //sobre el conunto de entrenamiento
        Experiment.suppliedTestSet(); //sobre el conjunto de prueba

        trainTrain.add( Experiment.getModelTrain(model) );
        trainTest.add( Experiment.getModelTest(model) );
    }


    public ArrayList<model> getNodeExperiments() {
        return nodeExperiments;
    }

    public ArrayList<model> getEpochExperiments() {
        return epochExperiments;
    }

    public ArrayList<model> getLearningRateExperiments() {
        return learningRateExperiments;
    }

    public ArrayList<model> getMomentumExperiments() {
        return momentumExperiments;
    }
}
