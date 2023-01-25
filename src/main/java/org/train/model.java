package org.train;

public class model {

    /*
    Con los parametros podemos ajustar el modelo para clasificar correctamente
    */

    String hiddenLayers; //Nodos y Capas Ocultas
    int epoch; //Numero de Epocas
    Double learningRate; //Learning Rate
    Double momentum; //Momentum

    Double instClassifCorrectly; //Clases que fueron clasificadas correctamente

    /*
    Con el constructor podemos establecer los parametros, dependiendo de las metricas indicadas será la
    precision o eficiencia del modelo
    */
    public model(String hiddenLayers, int epoch, Double learningRate, Double momentum, Double instClassifCorrectly) {
        this.hiddenLayers = hiddenLayers;
        this.epoch = epoch;
        this.learningRate = learningRate;
        this.momentum = momentum;

        this.instClassifCorrectly = instClassifCorrectly;
    }

    public Double geIinstClassifCorrectly() {
        return instClassifCorrectly;
    }

    @Override
    public String toString() {
        return "modelo { " +
                "hiddenLayers = '" + hiddenLayers + '\'' +
                " , epoch = " + epoch +
                " , learningRate = " + learningRate +
                " , momentum = " + momentum +
                " , instances classified correctly = " + instClassifCorrectly +
                " } ";
    }
}
