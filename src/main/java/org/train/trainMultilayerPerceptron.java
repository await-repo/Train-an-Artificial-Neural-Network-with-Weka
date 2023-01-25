package org.train;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class trainMultilayerPerceptron {


    MultilayerPerceptron RNA = new MultilayerPerceptron();
    Instances train;   // from somewhere - conjunto de prueba
    Instances test;    // from somewhere - counjunto de entrenamiento

    Evaluation evaluation;  //variable auxiliar para instanciar la evaluacion

    Evaluation evaluationTraining;
    Evaluation evaluationTest;

    Classifier classifierRNA;

    public trainMultilayerPerceptron(String hiddenLayers, int epoch, Double learningRate, Double momentum) {
        RNA.setHiddenLayers(hiddenLayers); //Nodos y Capas Ocultas
        RNA.setTrainingTime(epoch); //Numero de Epocas
        RNA.setLearningRate(learningRate); //Learning Rate
        RNA.setMomentum(momentum); //Momentum
    }


    public trainMultilayerPerceptron(model model) {
        RNA.setHiddenLayers(model.hiddenLayers); //Nodos y Capas Ocultas
        RNA.setTrainingTime(model.epoch); //Numero de Epocas
        RNA.setLearningRate(model.learningRate); //Learning Rate
        RNA.setMomentum(model.momentum); //Momentum
    }

    //Cargamos el archivo
    public Instances loadData (String dataset) throws IOException {
        Instances data = new Instances( new BufferedReader( new FileReader( dataset ) ) );
        data.setClassIndex(data.numAttributes() - 1);
        return data;
    }

    //cargamos el conjunto de entrabamiento
    public void setTrain (String dataset) throws IOException {
        train = loadData(dataset);   // from somewhere - conjunto de prueba
    }

    //cargamos el conjunto de prueba
    public void setTest (String dataset) throws IOException {
        test =  loadData(dataset);    // from somewhere - counjunto de entrenamiento
    }


    //si quisieramos ocupar una validacion manual utilizariamos lo siguiente
    //inicializamos el experimento
    public void init () throws Exception {
        if ( !dataSetsValid() ) //si los datasets no son validos
            return;

        // train classifier - entrenamos el conjunto de entrenamiento
        Classifier cls = RNA;
        cls.buildClassifier(train);

        //evaluamos el conjunto entrenado (train) sobre el conjunto de prueba
        evaluation = new Evaluation(train);
        evaluation.evaluateModel(cls, test);
    }


    public boolean dataSetsValid () {
        if (train == null || test == null) {
            System.out.println("Faltan datasets (train o test). Añadelos para continuar");
            return false;
        } else {
            return true;
        }
    }


    public void generateClassifier () throws Exception {
        if ( !dataSetsValid() ) //si los datasets no son validos
            return;
        // train classifier - entrenamos el conjunto de entrenamiento
        classifierRNA = RNA;
        classifierRNA.buildClassifier(train);
    }

    public void suppliedTestSet () throws Exception {
        if ( !dataSetsValid() ) //si los datasets no son validos
            return;

        //evaluamos el conjunto entrenado (train) sobre el conjunto de prueba
        evaluationTraining = new Evaluation(train);
        evaluationTraining.evaluateModel(classifierRNA, test);
    }

    public void useTrainingSet () throws Exception {
        if ( !dataSetsValid() ) //si los datasets no son validos
            return;

        //evaluamos el conjunto entrenado (train) sobre el conjunto de entrenamiento
        evaluationTest = new Evaluation(train);
        evaluationTest.evaluateModel(classifierRNA, train);
    }


    public double getIncorrectTraining() {
        return evaluationTraining.pctIncorrect();
    }

    public double getIncorrectTest() {
        return evaluationTest.pctIncorrect();
    }


    public double getCorrect () {
        return evaluation.pctCorrect();
    }


    public model getModel (model model) {
        return new model(RNA.getHiddenLayers(),RNA.getTrainingTime(),RNA.getLearningRate(),RNA.getMomentum(),evaluation.pctCorrect());
    }


    //obtiene el modelo del entrenamiento sobre el cunjunto de entrenamientp
    public model getModelTrain (model model) {
        return new model(RNA.getHiddenLayers(),RNA.getTrainingTime(),RNA.getLearningRate(),RNA.getMomentum(),evaluationTraining.pctIncorrect());
    }

    //obtiene el modelo entrenamiento sobre el cunjunto de prueba
    public model getModelTest (model model) {
        return new model(RNA.getHiddenLayers(),RNA.getTrainingTime(),RNA.getLearningRate(),RNA.getMomentum(),evaluationTest.pctIncorrect());
    }




    //setter

    public void setHiddenLayers(String hiddenLayers) {
        RNA.setHiddenLayers(hiddenLayers); //Nodos y Capas Ocultas
    }

    public void setTrainingTime(int epoch) {
        RNA.setTrainingTime(epoch); //Numero de Epocas
    }

    public void setLearningRate(Double learningRate) {
        RNA.setLearningRate(learningRate); //Learning Rate
    }

    public void setMomentum(Double momentum) {
        RNA.setMomentum(momentum); //Momentum
    }


}


