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
> Notará que cada carpeta contiene datos únicos, por ejemplo, si su muestra tiene 1000 datos y decide dividirlos en 5 experimentos, cada carpeta de experimento deberia tener 200 datos sin ser repetidos, el **70% - 80%** de los datos deben estar en el data set de entrenamiento mientras que el data set de prueba solo debe contener entre **20% - 30%** de los datos, estos siempre **deben ser estrictamente únicos** para evitar un sesgo en nuestra muestra.

### **Contenido de un data set**

Los data sets tienen un formato de archivo **.arff** que Weka puede comprender, recordemos que los datos fueron recolectados a partir de las imagenes así que nuestros datos son númericos y cada uno representa una propiedad que consideramos importante para identificar una imagen con un indendio forestal o un potencial incendio forestal. Si quiere leer más sobre **¿Cómo seleccionamos los criterios en una imagen digital para detectar incendios o potenciales incendios forestales?** puede dirigirse al siguiente enlace.

**Data set de entrenamiento `datasetTrain`**

Un data set de entrenamiento tiene el proposito de indicarle a la RNA (Red Neuronal Artificial) cómo debe clasificar las imagenes deacuerdo a las propiedades de la misma. Existen tres resultados posibles: **Incendio Forestal**, **No Incendio Forestal** y **Humo (Potencial incendio)**

La unica diferencia con respecto a un data set de prueba es que los date set de entrenamiento además de incluir las caracteristicas de la imagen incluyen también el tipo de imagen clasificada. Recordemos que hay tres tipos

* I Incendio forestal
* H Humo (Indicador de un potencial incendio forestal)
* N No Incendio (Usualmente una imagen de un bosque)

Un ejemplo de dato sería:

```java
@data
0.909871, 0.868103, 0.786057, 0.054851, 0.094699, 0.164685, 0.454538,0.483698, 0.660998, 0.87915, 0.098228, 0.088623, 0.135032, 0.907758, 0.019858, 0.102397, 0.060539, 0.893162, 0.433071, 0.367095, 0.080959, 0.024309, 0.100327, H
```

Donde los 23 campos númericos son propiedades de las imagenes en un rango del 0 al 1 **(Datos normalizados)** mientras que el último valor **H** indica que en la imagen aparece **Humo** dentro del bosque lo cual es un indicativo de un potencial incendio forestal. 

**Data set de prueba `datasetTest`**

Un data set de prueba tiene el objetivo de determinar la eficiencia en la clasficación correcta de las imagenes. Recordemos que un porcentaje de **100%** es un gran indicativo de sobre ajuste, por lo cual nuestro modelo no será capaz de predecir nuevos modelos de datos con una muestra más diversa.

La unica diferencia de un data set de entrenamiento a uno de prueba es la ultima propiedad de la imagen, mientras que un data set de entrenamiento tiene 24 propiedades (23 caracteristicas de la imagen y 1 valor indicativo del tipo de imagen), un data set de prueba solo tiene las primeras 23 propiedades ya que se omite el tipo de imagen, es decir, en un data set de prueba solo nos indica las propiedades el la imagen, es responsabilidad de la **RNA** clasficar correctamente el tipo imagen y determinar si existe un incendio, potencial incendio o solo un bosque.

Un ejemplo de dato sería

```java
@data
0.992139, 0.807171, 0.018447, 0.040006, 0.34239, 0.137877, 0.201309, 0.783856, 0.275122, 0.796637, 0.267656, 0.113834, 0.981481, 0.991247, 0.03458, 0.111466, 0.044437, 0.853764, 0.450304, 0.991933, 0.216157, 0.500293, 0.137176
```

El tipo de imagen para estos datos corresponde a un **Incendio Forestal** pero la RNA (Red Neuronal Artificial) debe identificar el tipo de imagen a la que corresponde, la eficiencia de nuestro resultado dependera principalmente de la muestra en el data set de entrenamiento.


### **Archivos de tipo arff**

Weka procesa archivos de tipo arff en sus modelos de entrenamientos de una RNA (Red Neuronal Artificial) la sintaxis para la creaccion de un archivo arff válido es la siguiente:

1. Establecemos un nombre para identificar nuestro data set
    ```java
    @relation 'Dataset Final-weka'
    ```

2. Indicamos las propiedades o atributos para los datos con la palabra reservada **`@attribute`** posteriormente escribimos el nombre para el atributo y finalmente indicamos el tipo de dato, en este caso será **`numeric`**

    Un ejemplo sería como el siguiente

     ```java
    @attribute red_mean numeric
    ```

3. Si nuestro data set es un conjunto para entrenamiento debemos indicarle ademas un atributo de tipo clase, especificando los posibles resultados.

    ```java
    @attribute class {N,H,I}
    ```
Si nuestro data set es para prueba debemos omitir este tipo de atributo ya que será la Red Neuronal Artificial la encargada de asignarle su tipo de imagen.

4. Finalmente establecemos todos nuestros datos.

Como ejemplo mostraremos nuestro data set solo que **estará simplificado** a 3 datos (uno solo dato por tipo de imagen: Incendio, No incendio o Humo). Nuestro data set cuenta con 23 caracteristica y un atributo de tipo clase que indica el valor de cada tipo de imagen: **{ N, H, I }**

```java
@relation 'Dataset Final-weka'

@attribute red_mean numeric
@attribute green_mean numeric
@attribute blue_mean numeric
@attribute red_std numeric
@attribute green_std numeric
@attribute blue_std numeric
@attribute R_entropy numeric
@attribute G_entropy numeric
@attribute B_entropy numeric
@attribute gray_mean numeric
@attribute gray_std numeric
@attribute hue_mean numeric
@attribute sat_mean numeric
@attribute value_mean numeric
@attribute hue_std numeric
@attribute sat_std numeric
@attribute value_std numeric
@attribute L_mean numeric
@attribute a_mean numeric
@attribute b_mean numeric
@attribute L_std numeric
@attribute a_std numeric
@attribute b_std numeric
@attribute class {N,H,I}

@data
0.909871,0.868103,0.786057,0.054851,0.094699,0.164685,0.454538,0.483698,0.660998,0.87915,0.098228,0.088623,0.135032,0.907758,0.019858,0.102397,0.060539,0.893162,0.433071,0.367095,0.080959,0.024309,0.100327,H
0.862623,0.356777,0.023933,0.222189,0.507277,0.161192,0.731795,0.856931,0.352589,0.445144,0.449196,0.04142,0.973722,0.859773,0.045717,0.132355,0.241371,0.559171,0.860651,0.810216,0.350026,0.481839,0.209218,I
0.121068,0.119435,0.080938,0.102583,0.133397,0.103119,0.594362,0.570017,0.568692,0.117225,0.136098,0.178419,0.415473,0.11951,0.091271,0.355676,0.127638,0.120608,0.389955,0.33636,0.160041,0.079202,0.095007,N
```
