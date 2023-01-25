## **Entrenamiento de una Red Neuronal Artificial utilizando Weka**

Weka es un Software dedicado al machine learning desarrollado en Java por la **universidad de Waikato**

### **Resumen**

El proposito de este proyecto fue utilzar la API de weka para crear un **perceptrón multicapa** (Red Neuronal Artificial) para entrenar un modelo que sea capaz de detectar mediante imagenes digitales un posible incendio forestal o un potencial incendio forestal.

La **RNA (Red Neuronal Artificial)** fue entrenada con un conjunto de datos de imagenes digitales de multiples incendios forestales recolectados de diversos data set disponibles en internet. Una vez recabada la muestra el objetivo principal fue obtener un conjunto de imagenes que sea objetivo para que un modelo de datos pueda ser entrenado correctamente.

### **¿Cómo ejecutar este proyecto?**

1. Primero debes clonar el repositorio en tu maquina local.

2. Posteriormente instalar el Jar de Weka, esta parte es la más importante ya que el **Perceptron Multicapa (RNA)** y sus herramientas de entrenamiento estan incluidas en la libreria (Jar) de Weka. Para instalar Weka en nuestro proyecto, tenemos dos opciones:

    * Descargar manualmente Weka desde su sitio web y agregar el Jar manualmente al proyecto. Visite la página de Weka para más información https://www.cs.waikato.ac.nz/ml/weka/

    * Agregar la dependencia de Weka al **POM.xml** y posteriormente instalar la nueva dependencia. Puedes acceder desde el sitio web para copiar la dependencia.

        Visite el repositorio de maven para descargar la version más reciente de weka https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-stable/3.8.0
    
        Actualmente (enero 2023) añadiendo el siguiente fragmento al POM.xml de tu proyecto deberías poder instalar Weka.

        ```xml
        <!-- https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-stable -->
        <dependency>
            <groupId>nz.ac.waikato.cms.weka</groupId>
            <artifactId>weka-stable</artifactId>
            <version>3.8.0</version>
        </dependency>
        ```

3. Si hasta ahora tu editor de código o IDE no te muestra errores de sintaxis significa que se ha instalado correctamente Weka dentro de tu proyecto. 

    Debemos observar principalmente la clase **`trainMultilayerPerceptron`** ya que aquí es donde se importan las librerias de Weka. Si observamos errores en las sigueientes lineas

    ```java
    import weka.classifiers.Classifier;
    import weka.classifiers.evaluation.Evaluation;
    import weka.classifiers.functions.MultilayerPerceptron;
    import weka.core.Instances;
    ```
    Debemos de revisar de nuevo el proceso de instalación de la libreria Weka. En caso contrario, podemos continuar.

4. Con el proyecto descargado y la libreria de Weka dentro del proyecto, sin errores de importacion de liberias o errores de sintaxis. Ahora podemos configurar algunas lineas del proyecto. 

    En la clase **`init`** vamos a modificar la siguiente linea.

    ```java
    String route = "Your Path\\Exp\\";
    ```

    > #### **Nota**
    > La clase **`init`** sirve unicamente para probar que los documentos esten organizados en el formato  **datasetTrain** y **datasetTest**, podemos omitir la modificación de esta clase o simplemente eliminarla.

    En la clase **`testTrain`** vamos a modificar la siguiente linea.

    ```java
    String experimentsRoute = "Your Path\\Exp\\";
    ```

    **Debera agregar la ruta absoluta de la carpeta donde se encuentren todos tus experimentos.** Más adelante explicare la extension y tipo de archivo para los experimentos, así como su nomemclatura y organizacion en carpetas. Por ahora simplemente indica la ruta hacia la carpeta inmediata donde guardarás tus experimentos.

5. Si todo a salido bien hasta ahora, **podemos iniciar con la parte de los experimentos**

    Un experimento no es más que una prueba que se realiza utilizando un conjunto de datos dividos en dos partes:

    * Set de entrenamiento **`datasetTrain`**
    * Set para probar los resultados de clasificación **`datasetTest`**

    Ahora sabemos que dentro de un experimento hay dos archivos (uno de entrenamiento y uno de prueba), lo siguiente es determinar la cantidad de experimentos totales para la prueba. Para nuestro proyecto utilizamos 7 experimentos, la cantidad es deacuerdo a tu elección. Si quieres modificar la cantidad de experimentos, debes modificar la siguiente línea.

    ```java
    for (Double mean: means){
        System.out.println(mean/7);
    }
    ```

    Cambiamos el número 7 por la cantidad de experimentos que vamos a realizar.


    ### **Experimentos: Formato y organización de las carpetas**

    Un experimento, en escencia es una carpeta que dentro contiene dos archivos con extensión **.arff**, el primer archivo debe ser el data set para entrenamiento **`datasetTrain`**, el entrenamiento sirve para que la Red Neuronal pueda entender cómo debe clasificar las imagenes, dependiendo de sus caracteristicas debe ser el valor identificado, ya sea incendio, no incendo o humo, ya que el humo es un indicativo de un potencial incendio.

    El segundo archivo dentro de la carpeta debe ser el data set de prueba **`datasetTest`** el cual sirve para probar la eficiencia de clasificación de la Red Neuronal, recordemos que una prueba con 100% de eficiencia en clasificación puede ser un indicativo de sobre ajuste.

    > #### **Nota: El orden importa**
    > Los archivos deben aparecer dentro de la carpeta en el siguiente orden :
    > * `datasetTrain` 
    > * `datasetTest`
    
    > Esto se debe a que internamente el algoritmo creado toma como primer parametro un data set de entrenamiento y el segundo parametro es un data set de prueba.

    La imagen de abajo muestra una forma valida en la cual podemos escribir la nomenclatura para sus experimentos, note como primero esta el archivo de entrenamiento y segundo el archivo de prueba.

    ![FileNaming](https://user-images.githubusercontent.com/115047831/214491655-ffaa3658-4416-4b54-b993-c523f796f005.PNG)

    En la siguiente imagen podemos observar como estan organizadas las carpetas para realizar 7 experimentos, dentro de cada carpeta existe tanto un archivo de entrenamiento como un archivo de prueba, ambos son unicos, sin elementos repetidos. La nomenclatura permite que las carpetas esten ordenadas.

    ![folderNaming](https://user-images.githubusercontent.com/115047831/214492918-0bd96604-ff9f-4475-90f0-1d7f8ff3c4d3.PNG)

     > #### **Nota: Data sets únicos**
    > Notará que cada carpeta contiene datos únicos, por ejemplo, si su muestra tiene 1000 datos y decide dividirlos en 5 experimentos, cada carpeta de experimento deberia tener 200 datos sin ser repetidos, el 70% - 80% de los datos deben estar en el data set de entrenamiento mientras que el data set de prueba solo debe contener entre 20% - 30% de los datos, estos siempre **deben ser estrictamente únicos** para evitar un sesgo en nuestra muestra.
