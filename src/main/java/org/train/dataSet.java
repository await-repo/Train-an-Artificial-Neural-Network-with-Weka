package org.train;

public class dataSet {

    String datasetTrain;
    String datasetTest;

    public dataSet() {
    }

    /*
    Un data set se compone de dos partes
        -El set de train para entrenar a la red neuronal
        -El set de test para probar los resultados de predicción de la red neuronal
    */
    public dataSet(String datasetTrain, String datasetTest) {
        this.datasetTrain = datasetTrain;
        this.datasetTest = datasetTest;
    }

    public String getDatasetTrain() {
        return datasetTrain;
    }

    public void setDatasetTrain(String datasetTrain) {
        this.datasetTrain = datasetTrain;
    }

    public String getDatasetTest() {
        return datasetTest;
    }

    public void setDatasetTest(String datasetTest) {
        this.datasetTest = datasetTest;
    }

    @Override
    public String toString() {
        return "dataSet{" +
                "datasetTrain='" + datasetTrain + '\'' +
                ", datasetTest='" + datasetTest + '\'' +
                '}';
    }
}
