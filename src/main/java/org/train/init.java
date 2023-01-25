package org.train;

import java.io.File;
import java.util.Objects;

public class init {
    public static void main (String []args) {

        String route = "Your Path\\Exp\\"; //Añade la ruta a la carpeta con tus experimentos
        File experimentFolder = new File(route);

        /*
        Recorremos todos los data sets, cada muestra debe tener datos variados para
        evitar un sobre ajuste o sesgo
        */
        for(File file: Objects.requireNonNull(experimentFolder.listFiles())) {
            File[] experiments = file.listFiles();
            System.out.println(experiments[0].toString());
            System.out.println(experiments[1].toString());
            System.out.println();
        }
    }


}
