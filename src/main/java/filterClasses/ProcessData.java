package filterClasses;

import model.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utils.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Esta clase se encargará de leer los csv's y cargarlos en los objetos correspondientes,
 * así como de cargar todos los datos necesarios en los objetos Measurement y City.
 * Básicamente, esta clase deja las cosas listas para pasarlas a un archivo html.
 * @author Daniel Rodríguez Muñoz
 * @author Jaime Salcedo Vallejo
 * @see CreateHTML
 * @see model.City
 * @see model.Measurement
 * @see model.Calidad_aire_datos
 * @see model.Calidad_aire_estaciones
 * @see model.Calidad_aire_zonas
 * @see utils.Util
 */
public class ProcessData {
    public City desiredCity = new City();
    private List<Calidad_aire_datos> cadm;
    private List<Calidad_aire_datos> cadmm;
    private List<Calidad_aire_estaciones> cae;
    private List<Calidad_aire_zonas> caz;
    private Map<String, Integer> codeCity;
    public Map<Integer, String> codeMagnitude;
    public Map<Integer, String> codeMeasurementUnit;
    public Map<Integer, Integer> index_to_codes;

    /**
     * Este método setea los valores del mapa index_to_codes.
     * @author Daniel Rodríguez Muñoz
     * @return Map
     */
    private Map<Integer, Integer> setValuesIndexToCodes() {
        Map<Integer, Integer> map = new HashMap<>(19);
        map.put(0, 81);
        map.put(1, 83);
        map.put(2, 86);
        map.put(3, 88);
        map.put(4, 89);
        map.put(5, 1);
        map.put(6, 6);
        map.put(7, 7);
        map.put(8, 8);
        map.put(9, 9);
        map.put(10, 10);
        map.put(11, 12);
        map.put(12, 14);
        map.put(13, 20);
        map.put(14, 22);
        map.put(15, 30);
        map.put(16, 42);
        map.put(17, 44);
        map.put(18, 431);
        return map;
    }

    /**
     * Este método setea los valores del mapa codeMeasurementUnit.
     * @author Daniel Rodríguez Muñoz
     * @return Map
     */
    private Map<Integer, String> setValuesCodeMeasureUnit() {
        Map<Integer, String> map = new HashMap<>(19);
        map.put(81, "m/s");
        map.put(83, "ºc");
        map.put(86, "%");
        map.put(88, "W/m²");
        map.put(89, "l/m²");
        map.put(1, "μg/m³");
        map.put(6, "mg/m³");
        map.put(7, "μg/m³");
        map.put(8, "μg/m³");
        map.put(9, "μg/m³");
        map.put(10, "μg/m³");
        map.put(12, "μg/m³");
        map.put(14, "μg/m³");
        map.put(20, "μg/m³");
        map.put(22, "μg/m³");
        map.put(30, "μg/m³");
        map.put(42, "mg/m³");
        map.put(44, "mg/m³");
        map.put(431, "μg/m³");
        return map;
    }

    /**
     * Este método setea los valores del mapa codeMagnitude.
     * @author Daniel Rodríguez Muñoz
     * @return Map
     */
    private Map<Integer, String> setValuesCodeMagnitude() {
        Map<Integer, String> map = new HashMap<>(19);
        map.put(81, "Velocidad del viento");
        map.put(83, "Temperatura");
        map.put(86, "Humedad relativa");
        map.put(88, "Radiación solar");
        map.put(89, "Precipitación");
        map.put(1, "Dióxido de azufre");
        map.put(6, "Monóxido de carbono");
        map.put(7, "Monóxido de nitrógeno");
        map.put(8, "Dióxido de nitrógeno");
        map.put(9, "Partículas en suspensión < PM2,5");
        map.put(10, "Partículas en suspensión < PM10");
        map.put(12, "Óxidos de nitrógeno");
        map.put(14, "Ozono");
        map.put(20, "Tolueno");
        map.put(22, "Black Carbon");
        map.put(30, "Benceno");
        map.put(42, "Hidrocarburos totales");
        map.put(44, "Hidrocarburos no metánicos");
        map.put(431, "MetaParaXileno");
        return map;
    }

    /**
     * Este método setea los valores del mapa codeCity.
     * @author Daniel Rodríguez Muñoz
     * @return Map
     */
    private Map<String, Integer> setValuesCodeCity() {
        Map<String, Integer> map = new HashMap<>(24);
        map.put("Alcalá de Henares", 5);
        map.put("Alcobendas", 6);
        map.put("Alcorcón", 7);
        map.put("Algete", 9);
        map.put("Aranjuez", 13);
        map.put("Arganda del Rey", 14);
        map.put("El Atazar", 16);
        map.put("Colmenar Viejo", 45);
        map.put("Collado Villalba", 47);
        map.put("Coslada", 49);
        map.put("Fuenlabrada", 58);
        map.put("Getafe", 65);
        map.put("Guadalix de la Sierra", 67);
        map.put("Leganés", 74);
        map.put("Majadahonda", 80);
        map.put("Móstoles", 92);
        map.put("Orusco de Tajuña", 102);
        map.put("Puerto de Cotos", 120);
        map.put("Rivas-Vaciamadrid", 123);
        map.put("San Martín de Valdeiglesias", 133);
        map.put("Torrejón de Ardoz", 148);
        map.put("Valdemoro", 161);
        map.put("Villa del Prado", 171);
        map.put("Villarejo de Salvanés", 180);
        return map;
    }

    public ProcessData(String city) {
        try {
            setUpMapsAndLists();
            filter(city);
            desiredCity.setName(city);
            /*
            cadm.forEach(System.out::println);
            System.out.println("");
            cadmm.forEach(System.out::println);
            System.out.println("");
            cae.forEach(System.out::println);
            System.out.println("");
            caz.forEach(System.out::println);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
             */
            List<Measurement> measurementList = setUpMeasurementList();
            setUpCity(desiredCity, measurementList);
            // System.out.println(desiredCity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A este método le pasas un objeto City y una lista de Measurement y te setea todos los atributos necesarios de
     * dicho objeto, dejándolo listo para pasarlo a un html.
     * @author Daniel Rodríguez Muñoz
     * @param city City
     * @param measureList List
     * @see model.City
     * @see model.Measurement
     */
    private void setUpCity(City city, List<Measurement> measureList) {
        city.setMeteoMeasurements(giveMeMeteoMeasurements(measureList));
        city.setContaminationMeasurements(giveMeContaminationMeasurements(measureList));
        city.setFirstMeasurementDate(giveMeFirstDate(city));
        city.setLastMeasurementDate(giveMeLastDate(city));
        city.setAssociatedStationList(giveMeStations());
    }

    /**
     * Este método devolverá un ArrayList de objetos Measurement cuya magnitud
     * entre dentro de los valores de los datos meteorológicos.
     * @author Daniel Rodríguez Muñoz
     * @param measureList List
     * @return ArrayList
     * @see model.Measurement
     */
    private ArrayList<Measurement> giveMeContaminationMeasurements(List<Measurement> measureList) {
        ArrayList<Measurement> contaminationMeasurements = new ArrayList<>();
        int counter = 0;
        int code;
        while (counter < index_to_codes.size()) {
            code = index_to_codes.get(counter);
            if (!(code >= 81 && code <= 89)) {
                int finalCode = code;
                List<Measurement> m = measureList.stream().filter(x -> x.getMagnitude() == finalCode).collect(Collectors.toList());
                contaminationMeasurements.addAll(m);
            }
            counter++;
        }
        return contaminationMeasurements;
    }

    /**
     * Este método devolverá un ArrayList de objetos Measurement cuya magnitud
     * entre dentro de los valores de los datos de los agentes contaminantes.
     * @author Daniel Rodríguez Muñoz
     * @param measureList List
     * @return ArrayList
     * @see model.Measurement
     */
    private ArrayList<Measurement> giveMeMeteoMeasurements(List<Measurement> measureList) {
        ArrayList<Measurement> meteoMeasurements = new ArrayList<>();
        int counter = 0;
        int code;
        while (counter < index_to_codes.size()) {
            code = index_to_codes.get(counter);
            if (code >= 81 && code <= 89) {
                int finalCode = code;
                List<Measurement> m = measureList.stream().filter(x -> x.getMagnitude() == finalCode).collect(Collectors.toList());
                meteoMeasurements.addAll(m);
            }
            counter++;
        }
        return meteoMeasurements;
    }

    /**
     * Este método returnea una lista de Strings con los nombres de las estaciones asociadas a una determinada ciudad.
     * @author Daniel Rodríguez Muñoz
     * @return ArrayList
     */
    private ArrayList<String> giveMeStations() {
        ArrayList<String> stationList = new ArrayList<>();
        cae.forEach(x -> stationList.add(x.getEstacion_direccion_postal()));
        return stationList;
    }

    /**
     * Este método returnea un Optional de objeto Date con la fecha de la primera medición registrada de la ciudad
     * en cuestión. Si no hay ninguna fecha registrada, returnea null.
     * @author Daniel Rodrígez Muñoz
     * @param city City
     * @return Optional Date
     */
    private Date giveMeFirstDate(City city) {
        List<Date> dateList = new ArrayList<>();
        for (Measurement measure: city.getMeteoMeasurements()) {
            dateList.add(measure.getData().stream().min(Comparator.comparing(Calidad_aire_datos::getFecha_medicion)).get().getFecha_medicion());
        }
        for (Measurement measure: city.getContaminationMeasurements()) {
            dateList.add(measure.getData().stream().min(Comparator.comparing(Calidad_aire_datos::getFecha_medicion)).get().getFecha_medicion());
        }
        Optional<Date> result = dateList.stream().min(Comparator.comparing(x -> x));
        return result.orElse(null);
    }

    /**
     * Este método returnea un Optional de objeto Date con la fecha de la última medición registrada de la ciudad
     * en cuestión. Si no hay ninguna fecha registrada, returnea null.
     * @author Daniel Rodrígez Muñoz
     * @param city City
     * @return Optional Date
     */
    private Date giveMeLastDate(City city) {
        List<Date> dateList = new ArrayList<>();
        for (Measurement measure: city.getMeteoMeasurements()) {
            dateList.add(measure.getData().stream().max(Comparator.comparing(Calidad_aire_datos::getFecha_medicion)).get().getFecha_medicion());
        }
        for (Measurement measure: city.getContaminationMeasurements()) {
            dateList.add(measure.getData().stream().max(Comparator.comparing(Calidad_aire_datos::getFecha_medicion)).get().getFecha_medicion());
        }
        Optional<Date> result = dateList.stream().max(Comparator.comparing(x -> x));
        return result.orElse(null);
    }

    /**
     * Este método construye una lista de objetos measurement y, en función del código del objeto, el cual
     * coincide con su número index del csv, hace un set de magnitud, nombre de magnitud y una unidad de medida.
     * Posteriormente, y dependiendo del código del objeto Measurement, hará un set del resto de datos de la
     * medición.
     * @author Jaime Salcedo Vallejo
     * @return List
     */
    private List<Measurement> setUpMeasurementList() {
        List<Measurement> measList = new ArrayList<>();
        int count = 0;
        int code;
        while(count < index_to_codes.size()){
            boolean ignoreEverything = false;
            code = index_to_codes.get(count);
            if ((cae.get(0).getEstacion_analizador_NO().equalsIgnoreCase("null") && code == 7) ||
                    (cae.get(0).getEstacion_analizador_NO2().equalsIgnoreCase("null") && code == 8) ||
                    (cae.get(0).getEstacion_analizador_PM10().equalsIgnoreCase("null") && code == 10) ||
                    (cae.get(0).getEstacion_analizador_PM2_5().equalsIgnoreCase("null") && code == 9) ||
                    (cae.get(0).getEstacion_analizador_O3().equalsIgnoreCase("null") && code == 14) ||
                    (cae.get(0).getEstacion_analizador_TOL().equalsIgnoreCase("null") && code == 20) ||
                    (cae.get(0).getEstacion_analizador_BEN().equalsIgnoreCase("null") && code == 30) ||
                    (cae.get(0).getEstacion_analizador_XIL().equalsIgnoreCase("null") && code == 431) ||
                    (cae.get(0).getEstacion_analizador_CO().equalsIgnoreCase("null") && code == 6) ||
                    (cae.get(0).getEstacion_analizador_SO2().equalsIgnoreCase("null") && code == 1) ||
                    (cae.get(0).getEstacion_analizador_HCT().equalsIgnoreCase("null") && code == 42) ||
                    (cae.get(0).getEstacion_analizador_HNM().equalsIgnoreCase("null") && code == 44) ||
                    (code == 22 || code == 12)) {ignoreEverything = true;}
            if (!ignoreEverything) {
                Measurement meas = new Measurement();
                meas.setMagnitude(code);
                meas.setMagnitudeName(giveMeMagnitudeName(code));
                meas.setMeasurementUnitName(giveMeMeasurementUnitName(code));
                if (!(code >= 81 && code <= 89)){
                    for(Calidad_aire_datos cad: cadm){
                        if(cad.getMagnitud() == meas.getMagnitude()){
                            meas.getData().add(cad);
                        }
                    }
                }else {
                    for(Calidad_aire_datos cad: cadmm){
                        if(cad.getMagnitud() == meas.getMagnitude()){
                            meas.getData().add(cad);
                        }
                    }
                }
                meas.setAverageValue(giveMeAverageValue(meas.getData()));
                if(!(code == 89)){
                    meas.setMomentMinValue(giveMeMomentMinValue(meas.getData()));
                    meas.setMinValue(giveMeMinValue(meas.getData()));
                    meas.setMomentMaxValue(giveMeMomentMaxValue(meas.getData()));
                    meas.setMaxValue(giveMeMaxValue(meas.getData()));
                } else {
                    meas.setDaysOnWhichRained(giveMeDaysOnWhichRained(meas.getData()));
                    meas.setRainMeasurements(giveMeRainMeasurements(meas.getData()));
                }
                meas.setChart(giveMeChart(meas.getData()));
                measList.add(meas);
            }
            count++;
        }
        return measList;
    }

    /**
     * Este método solo se ejecutará si el código del objeto Measurement es 89, y crea un ArrayList donde
     * se guardarán las fechas de los días que haya llovido y finalmente lo devolverá.
     * @author Jaime Salcedo Vallejo
     * @param data List
     * @return List
     */
    private List<Date> giveMeDaysOnWhichRained(List<Calidad_aire_datos> data) {
        ArrayList<Date> daysOnWhichRained = new ArrayList<>();
        for (Calidad_aire_datos cad : data) {
            boolean rained = false;
            for (int i = 0; i < cad.getListV().size(); i++) {
                if ((cad.getListV().get(i).equals('V')) && (cad.getListH().get(i) != null)) {
                    rained = true;
                }
            }
            if (rained) {
                daysOnWhichRained.add(cad.getFecha_medicion());
            }
        }
        return daysOnWhichRained;
    }

    /**
     * Este método, al igual que el anterior, solo se ejecutará si el código del objeto Measurement es 89.
     * Crea un ArrayList en el que se guardarán las mediciones de lluvia de los días en los que haya llovido
     * y finalmente lo devolverá.
     * @author Jaime Salcedo Vallejo
     * @param data List
     * @return ArrayList
     */
    private List<Double> giveMeRainMeasurements(List<Calidad_aire_datos> data) {
        ArrayList<Double> rainMeasurements = new ArrayList<>();
        for (Calidad_aire_datos cad : data) {
            for (int i = 0; i < cad.getListV().size(); i++) {
                if ((cad.getListV().get(i).equals('V')) && (cad.getListH().get(i) != null)) {
                    rainMeasurements.add(cad.getListH().get(i));
                }
            }
        }
        return rainMeasurements;
    }

    /**
     * Este sencillo método devuelve el nombre de la magnitud asociada al código que le hayamos
     * pasado por parámetro.
     * @author Jaime Salcedo Vallejo
     * @param code int
     * @return String
     */
    private String giveMeMagnitudeName(int code) {
        return codeMagnitude.get(code);
    }

    /**
     * Este método, de manera similar al anterior, devuelve el nombre de la unidad de medida asociada
     * al código que le hayamos pasado por parámetro.
     * @author Jaime Salcedo Vallejo
     * @param code int
     * @return String
     */
    private String giveMeMeasurementUnitName(int code) {
        return codeMeasurementUnit.get(code);
    }

    /**
     * Este método calcula la media sumando los valores de cada objeto Calidad_aire_datos dentro de
     * nuestra lista y lo divide entre el número total de objetos que ha recorrido nuestro contador en la
     * lista
     * @author Jaime Salcedo Vallejo
     * @param data List
     * @return double
     */
    private double giveMeAverageValue(List<Calidad_aire_datos> data){
        double result = 0;
        int count = 0;
        for(Calidad_aire_datos cad : data){
            for(int i = 0;i < cad.getListV().size(); i++){
               if(!(cad.getListV().get(i).equals('N'))){
                   result = result + cad.getListH().get(i);
                   count++;
               }
            }
        }
        return (result/count);
    }

    /**
     * Este método crea un ArrayList de fechas, posteriormente se crearán objetos Date que se guardarán
     * dentro del ArrayList para proceder al filtrado del mismo. Se realizará el filtrado de manera que
     * obtengamos el momento en el que se produjo el valor mínimo.
     * @author Jaime Salcedo Vallejo
     * @param cad List
     * @return Optional Date
     */
    private Date giveMeMomentMinValue(List<Calidad_aire_datos> cad){
        ArrayList<Date> dateList = new ArrayList<Date>();
        double lowestValue = 0;
        int positionMinValue = 0;
        for (Calidad_aire_datos ca : cad) {
            for (int i = 0; i < ca.getListH().size(); i++) {
                if (ca.getListH().get(i) != null) {
                    if (ca.getListH().get(i) > lowestValue) {
                        lowestValue = ca.getListH().get(i);
                        positionMinValue = i;
                    }
                }
            }
            Date date = new Date (ca.getFecha_medicion().getYear(), ca.getFecha_medicion()
                    .getMonth(), ca.getFecha_medicion().getDate(), ca.getHour().get(positionMinValue), 0, 0);
            dateList.add(date);
        }
        Optional<Date> result = dateList.stream().min(Date::compareTo);
        return result.orElse(null);
    }

    /**
     * Este método crea un ArrayList de Double, recorre los objetos Calidad_aire_datos dentro de nuestra
     * lista pasada por parámetro y busca los valores, los añade al ArrayList y después lo filtra
     * para buscar el mínimo y lo guarda en una variable result para después devolverla
     * @author Jaime Salcedo Vallejo
     * @param cad List
     * @return double
     * @throws NullPointerException
     */
    private double giveMeMinValue(List<Calidad_aire_datos> cad) throws NullPointerException{
        ArrayList<Double> doubleList = new ArrayList<>();
        for(Calidad_aire_datos c : cad){
            int i = 0;
            double currentMinValue = -987_689_999;
            // Esto nos asegura que currentMinValue no será nulo,
            // sino el primer elemento de la listH que tenga un valor asignado
            while (i < c.getListH().size() || currentMinValue == -987689999) {
                if (c.getListH().get(i) == null) {}
                else {
                    currentMinValue = c.getListH().get(i);
                }
                i++;
            }
            // Esto nos asegura coger el valor no nulo más pequeño de la listH, ignorando nulos sin soltar excepción.
            for (int j = 0; j < c.getListH().size(); j++) {
                if (c.getListH().get(j) == null) {}
                else if (c.getListH().get(j) < currentMinValue) {
                    currentMinValue = c.getListH().get(j);
                }
            }
            doubleList.add(currentMinValue);
        }
        Optional<Double>result = doubleList.stream().min(Comparator.comparing(x -> x));
        return result.orElse(null);
    }

    /**
     * Este método crea un ArrayList de fechas, posteriormente se crearán objetos Date que se guardarán
     * dentro del ArrayList para proceder al filtrado del mismo. Se realizará el filtrado de manera que
     * obtengamos el momento en el que se produjo el valor máximo.
     * @author Jaime Salcedo Vallejo
     * @param cad List
     * @return Date
     */
    private Date giveMeMomentMaxValue(List<Calidad_aire_datos> cad){
        ArrayList<Date> dateList = new ArrayList<Date>();
        double highestValue = 0;
        int positionMaxValue = 0;
        for (Calidad_aire_datos ca : cad) {
            for (int i = 0; i < ca.getListH().size(); i++) {
                if (ca.getListH().get(i) != null) {
                    if (ca.getListH().get(i) > highestValue) {
                        highestValue = ca.getListH().get(i);
                        positionMaxValue = i;
                    }
                }
            }
            Date date = new Date (ca.getFecha_medicion().getYear(), ca.getFecha_medicion().getMonth(), ca.getFecha_medicion().getDate(), ca.getHour().get(positionMaxValue), 0, 0);
            dateList.add(date);
        }
        Optional<Date> result = dateList.stream().max(Date::compareTo);
        return result.orElse(null);
    }

    /**
     * Este método crea un ArrayList de Double, recorre los objetos Calidad_aire_datos dentro de nuestra
     * lista pasada por parámetro y busca los valores, los añade al ArrayList y después lo filtra
     * para buscar el máximo y lo guarda en una variable result para después devolverla
     * @author Jaime Salcedo Vallejo
     * @param cad List
     * @return double
     * @throws NullPointerException
     */
    private double giveMeMaxValue(List<Calidad_aire_datos> cad) throws NullPointerException{
        ArrayList<Double> list = new ArrayList<>();
        for(Calidad_aire_datos c : cad){
            int i = 0;
            double currentMaxValue = 987_689_999;
            // Esto nos asegura que currentMaxValue no será nulo,
            // sino el primer elemento de la listH que tenga un valor asignado
            while (i < c.getListH().size() || currentMaxValue == 987689999) {
                if (c.getListH().get(i) == null) {}
                else {
                    currentMaxValue = c.getListH().get(i);
                }
                i++;
            }
            // Esto nos asegura coger el valor no nulo más grande de la listH, ignorando nulos sin soltar excepción.
            for (int j = 0; j < c.getListH().size(); j++) {
                if (c.getListH().get(j) == null) {}
                else if (c.getListH().get(j) > currentMaxValue) {
                    currentMaxValue = c.getListH().get(j);
                }
            }
            list.add(currentMaxValue);
        }
        Optional<Double>result = list.stream().max(Comparator.comparing(x -> x));
        return result.orElse(null);
    }

    /**
     * Este método crea gráficos en función de los datos que le pasemos. También podemos decirle el tipo de
     * gráfico y dar nombres a los diferentes ejes y añadir leyendas
     * @author Jaime Salcedo Vallejo
     * @param cad List
     * @return JFreeChart
     */
    private JFreeChart giveMeChart(List<Calidad_aire_datos> cad) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Calidad_aire_datos data : cad) {
            for (int i = 0; i < data.getListV().size(); i++) {
                if (data.getListV().get(i).equals('V') && data.getListH().get(i) != null) {
                    dataSet.setValue(data.getListH().get(i), data.getFecha_medicion(), data.getHour().get(i));
                }
            }
        }
        String nameMagnitude = codeMagnitude.get(cad.get(0).getMagnitud());
        JFreeChart chart = ChartFactory.createLineChart(
                nameMagnitude, "Tiempo", "Valor",
                dataSet, PlotOrientation.VERTICAL,
                true, true, false);
        return chart;
    }

    /**
     * Este método llama a los diferentes métodos encargados de añadir los valores correspondientes a los mapas de
     * magnitudes, unidades de medida, codigos de ciudad y nombres de magnitudes, así como los métodos que leen los
     * csv's y los pasan a objetos de su tipo correspondiente.
     * @author Daniel Rodríguez Muñoz
     * @throws IOException ioe
     * @see model.Calidad_aire_datos
     * @see model.Calidad_aire_zonas
     * @see model.Calidad_aire_estaciones
     */
    private void setUpMapsAndLists() throws IOException {
        index_to_codes = setValuesIndexToCodes();
        codeMagnitude = setValuesCodeMagnitude();
        codeMeasurementUnit = setValuesCodeMeasureUnit();
        cadm = Util.getCalidad_aire_datos("calidad_aire_datos_mes.csv");
        cadmm = Util.getCalidad_aire_datos("calidad_aire_datos_meteo_mes.csv");
        cae = Util.getCalidad_aire_estaciones();
        caz = Util.getCalidad_aire_zonas();
        codeCity = setValuesCodeCity();
    }

    /**
     * Este método filtra las listas de objetos Calidad_aire_[X] quedándose sólo con aquellos cuyo nombre de ciudad
     * corresponda con el pasado por argumento o cuyo código de ciudad corresponda con el código de ciudad asociado a la
     * ciudad pasada por argumento, devolviendo las mismas listas, pero ya filtradas.
     * @author Daniel Rodríguez Muñoz
     * @param cityName String
     * @see model.Calidad_aire_datos
     * @see model.Calidad_aire_zonas
     * @see model.Calidad_aire_estaciones
     */
    public void filter(String cityName) {
        // Con esto dejaremos solo las tuplas correspondientes a la ciudad que introduzcamos.
        int cityCode = cityNameToMunicipio(cityName);
        // Esto filtra las listas y las actualiza.
        cadm = cadm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cadmm = cadmm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cae = cae.stream().filter(x -> x.getEstacion_municipio().equalsIgnoreCase(cityName)).collect(Collectors.toList());
        caz = caz.stream().filter(x -> x.getZona_calidad_aire_municipio().equalsIgnoreCase(cityName)).collect(Collectors.toList());
    }

    /**
     * Este método returnea el código de ciudad asociado a la ciudad que le pasemos por parámetro.
     * @author Daniel Rodríguez Muñoz
     * @param cityName String
     * @return int
     */
    private int cityNameToMunicipio(String cityName) {
        int code = -1;
        try {
            code = codeCity.get(cityName);
        } catch (Exception e) {
            System.out.println("There is no data for the selected city. Please make sure you typed it correctly. (case sensitive)");
            System.exit(42_069);
        }
        return code;
    }
}
