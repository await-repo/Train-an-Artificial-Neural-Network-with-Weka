package org.train;

import java.io.File;
import java.util.*;

public class testTrain {

    public static void main (String []args) throws Exception {

        //Aqui iran todas las rutas de todos los experimentos (Tanto el cunjunto de entrenamiento como el de Prueba)
        List<dataSet> allDataSets = new LinkedList<>();

        //Agregamos los archivos en el vector allDataSets (En este ejemplo seria un k=3)


        //Ruta de la carpeta donde esten las carpetas con los experimentos
        String experimentsRoute = "Your Path\\Exp\\"; //Añade la ruta a la carpeta con tus experimentos
        File experimentFolder = new File(experimentsRoute);


        //Recorre la carpeta donde estaran los experimentos (Cada experimento debe tener una carpeta)
        for (File file : Objects.requireNonNull(experimentFolder.listFiles())) {
            File[] experiments = file.listFiles();
            assert experiments != null;
            allDataSets.add(new dataSet(experiments[0].toString(), experiments[1].toString()));
        }


        //Set De Pruebas
        //Parametros que se ajustaran en el modelo
        String[] neuronsPerLayerParams = { "3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26" };

        String[] LayersParams = { "15","15,15","15,15,15","15,15,15,15","15,15,15,15,15",
                                  "20","20,20","20,20,20","20,20,20,20","20,20,20,20,20",
                                  "21","21,21","21,21,21","21,21,21,21","21,21,21,21,21",
                                  "24","24,24","24,24,24","24,24,24,24","24,24,24,24,24",
                                  "25","25,25","25,25,25","25,25,25,25","25,25,25,25,25",
                                  "26","26,26","26,26,26","26,26,26,26","26,26,26,26,26" };

        Integer[] epochParams = { 1,10,20,30,40,50,100,200,300,400,500,600,700,800,900,1000,1100,1200,1400,1600,1800,2000, 2200,2500 };

        Double[] learningRateParams = { 0.0001,0.00015,0.0003,0.0005,0.0007,0.001,0.01,0.015,0.02,0.025,0.03,0.035,0.4 };

        Double[] momentumParams = { 0.0001,0.0005,0.0007,0.001,0.01,0.03,0.05,0.07,0.09,0.01,0.15,0.2,0.25,0.3, };

        //Modelos
        //son modelos de ejemplo donde se muestran las configuraciones que puede tener un modelo
        //los parametros que recibe son new modelo (hiddenLayers,epoch,learningRate,momentum, instClasiffyCorrectly)
        model neuronsPerLayerModel = new model("1", 2, 0.1D, 0.1D, 0.0D);
        model epochModel = new model("1", 3, 0.15D, 0.15D, 0.0D);
        model learningRateModel = new model("1", 4, 0.2D, 0.2D, 0.0D);
        model momentumModel = new model("2", 5, 0.3D, 0.3D, 0.0D);


        int i = 1;
        System.out.println("Todos los experimentos de Neuronas por Epocas");
        for (dataSet route : allDataSets) {
            System.out.println("\n\n\nExperimento " + i);
            //Instanciamos el experimento con las rutas de los experimentos
            experiment experiments = new experiment(route.datasetTrain, route.datasetTest);
            //Generando Experimentos
            experiments.generateExperiments(neuronsPerLayerModel, neuronsPerLayerParams, new ArrayList<>(), "neurona");
            //Imprimiento los experimentos de las Neuronas
            experiments.printNodeExperiments();
            experiments.printNodeMean();
            i++;
        }

        //Aqui se calcula el promedio por experimento y por ajuste
        ArrayList<ArrayList<model>> matriz = new ArrayList<>();
        Double[] means = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

        for (ArrayList<model> m: matriz){
            int index = 0;
            for (model inst : m){
                means[index] += inst.instClassifCorrectly;
                index++;
            }
        }
        //Aqui se imprime el promedio por experimento, se divide enntre 7 porque fue un k = 7
        System.out.println("\n\n\n");
        for (Double mean: means){
            System.out.println(mean/7);
        }


        i = 1;
        System.out.println("Todos los experimentos por Epocas");
        for (dataSet route : allDataSets) {
            System.out.println("\n\n\nExperimento " + i);
            //Instanciamos el experimento con las rutas de los experimentos
            experiment experiments = new experiment(route.datasetTrain, route.datasetTest);
            //Generando Experimentos
            experiments.generateExperiments(epochModel, epochParams, new ArrayList<>(), "epoca");
            //Imprimiento los experimentos de las Neuronas
            experiments.printEpochExperiments();
            experiments.printEpochMean();
            i++;
        }


        i = 1;
        System.out.println("Todos los experimentos por LearningRate");
        for (dataSet route: allDataSets) {
            System.out.println("\n\n\nExperimento "+i);
            //Instanciamos el experimento con las rutas de los experimentos
            experiment experiments = new experiment(route.datasetTrain, route.datasetTest);
            //Generando Experimentos
            experiments.generateExperiments(learningRateModel,learningRateParams,new ArrayList<>(),"learningRate");
            //Imprimiento los experimentos de las Neuronas
            experiments.printLearningRateExperiments();
            experiments.printLearningRateMean();
            i++;
        }


        i = 1;
        System.out.println("Todos los experimentos por Momentum");
        for (dataSet route: allDataSets) {
            System.out.println("\n\n\nExperimento "+i);
            //Instanciamos el experimento con las rutas de los experimentos
            experiment experiments = new experiment(route.datasetTrain, route.datasetTest);
            //Generando Experimentos
            experiments.generateExperiments(momentumModel,momentumParams,new ArrayList<>(),"momentum");
            //Imprimiento los experimentos de las Neuronas
            experiments.printMomentumExperiments();
            experiments.printMomentumMean();
            i++;
        }


    }
}