package filterClasses;

import model.*;
import org.jfree.chart.JFreeChart;
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
            List<Measurement> measurementList = new ArrayList<>(); // = setUpMeasurementList(); BORRAR EL "NEW ARRAYLIST"
            setUpCity(desiredCity, measurementList);
            System.out.println(desiredCity);
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

    private List<Measurement> setUpMeasurementList() {
        List<Measurement> measList = new ArrayList<>();
        int count = 0;
        int code;
        while(count < index_to_codes.size()){
            Measurement meas = new Measurement();
            code = index_to_codes.get(count);
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
            if(!(code == 89)){
                meas.setAverageValue(giveMeAverageValue(meas.getData()));
                meas.setMomentMinValue(giveMeMomentMinValue(meas.getData()));
                meas.setMinValue(giveMeMinValue(meas.getData()));
                meas.setMomentMaxValue(giveMeMomentMaxValue(meas.getData()));
                meas.setMaxValue(giveMeMaxValue(meas.getData()));
                meas.setChart(giveMeChart(meas.getData()));
            }
            count++;
            measList.add(meas);
        }
        return measList;
    }
    //método que devuelve el nombre de las mediciones
    private String giveMeMagnitudeName(int code) {
        return codeMagnitude.get(code);
    }
    //método que devuelve el nombre de las unidades de mediciones
    private String giveMeMeasurementUnitName(int code) {
        return codeMeasurementUnit.get(code);
    }

    //método para calcular la media
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
    //método para saber la fecha en la que se produjo el valor mínimo
    private Date giveMeMomentMinValue(List<Calidad_aire_datos> cad){
        ArrayList<Date> dateList = new ArrayList<Date>();
        double lowestValue = 0;
        int positionMinValue = 0;
        for (Calidad_aire_datos ca : cad) {
            for (int i = 0; i < ca.getListH().size(); i++) {
                if (ca.getListH().get(i) > lowestValue) {
                    lowestValue = ca.getListH().get(i);
                    positionMinValue = i;
                }
            }
            Date date = new Date (ca.getFecha_medicion().getYear(), ca.getFecha_medicion()
                    .getMonth(), ca.getFecha_medicion().getDate(), ca.getHour().get(positionMinValue), 0, 0);
            dateList.add(date);
        }
        Optional<Date> result = dateList.stream().min(Date::compareTo);
        return result.orElse(null);
    }
    //método para saber el valor mínimo
    private double giveMeMinValue(List<Calidad_aire_datos> cad) throws NullPointerException{
        ArrayList<Double> doubleList = new ArrayList<>();
        for(Calidad_aire_datos c : cad){
            doubleList.add(c.getListH().stream().min(Comparator.comparing(Double::doubleValue)).get());
        }
        Optional<Double>result = doubleList.stream().min(Comparator.comparing(x -> x));
        return result.orElse(null);
    }
    //método para saber la fecha en la que se produjo el valor máximo
    private Date giveMeMomentMaxValue(List<Calidad_aire_datos> cad){
        ArrayList<Date> dateList = new ArrayList<Date>();
        double highestValue = 0;
        int positionMaxValue = 0;
        for (Calidad_aire_datos ca : cad) {
            for (int i = 0; i < ca.getListH().size(); i++) {
                if (ca.getListH().get(i) > highestValue) {
                    highestValue = ca.getListH().get(i);
                    positionMaxValue = i;
                }
            }
            Date date = new Date (ca.getFecha_medicion().getYear(), ca.getFecha_medicion().getMonth(), ca.getFecha_medicion().getDate(), ca.getHour().get(positionMaxValue), 0, 0);
            dateList.add(date);
        }
        Optional<Date> result = dateList.stream().max(Date::compareTo);
        return result.orElse(null);
    }
    //método para saber el valor máximo
    private double giveMeMaxValue(List<Calidad_aire_datos> cad) throws NullPointerException{
        ArrayList<Double> list = new ArrayList<>();
        for(Calidad_aire_datos c : cad){
            list.add(c.getListH().stream().max(Comparator.comparing(Double::doubleValue)).get());
        }
        Optional<Double>result = list.stream().max(Comparator.comparing(x -> x));
        return result.orElse(null);
    }
    //método para crear un histograma (incompleto)
    private JFreeChart giveMeChart(List<Calidad_aire_datos> cad) {
        JFreeChart chart = null;
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
        return codeCity.get(cityName);
    }
}
