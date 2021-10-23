package filterClasses;

import model.*;
import org.jfree.chart.JFreeChart;
import utils.Util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class ProcessData {
    private List<Calidad_aire_datos> cadm;
    private List<Calidad_aire_datos> cadmm;
    private List<Calidad_aire_estaciones> cae;
    private List<Calidad_aire_zonas> caz;
    private Map<String, Integer> codeCity;
    public static Map<Integer, String> codeMagnitude;
    public static Map<Integer, String> codeMeasurementUnit;
    public static Map<Integer, Integer> index_to_codes;

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

    private Map<Integer, String> setValuesCodeMagnitude() {
        Map<Integer, String> map = new HashMap<>(19);
        map.put(81, "Velocidad del viento");
        map.put(83, "Temperatura");
        map.put(86, "Humedad relativa");
        map.put(88, "Radiación solar");
        map.put(89, "precipitación");
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
            City desiredCity = new City();
            setUpMapsAndLists();
            filter(city);
            desiredCity.setName(city);
            cadm.forEach(System.out::println);
            System.out.println("");
            cadmm.forEach(System.out::println);
            System.out.println("");
            cae.forEach(System.out::println);
            System.out.println("");
            caz.forEach(System.out::println);
            setUpCAD();
            setUpMeasurementList();
            setUpCity();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpCity() {
    }

    private void setUpMeasurementList() {
        Measurement measurement = new Measurement();
        setMeasurement(measurement);
        ArrayList<Measurement> contMeasurementList = new ArrayList<>();
        contMeasurementList.add(measurement);
    }
    private void setMeasurement(Measurement measurement){
        //primary//measurement.setMagnitude(giveMeMagnitude());
        //primary//measurement.setMagnitudeName(giveMeMagnitudeName());
        //primary//measurement.setMeasurementUnitName(giveMeMeasurementUnitName());
        //measurement.setAverageValue(giveMeAverageValue);
        //primary//measurement.setChart(giveMeChart());
        //measurement.setMomentMinValue(giveMeMomentMinValue ());
        //measurement.setMinValue(giveMeMinValue());
        //measurement.setMomentMaxValue(giveMeMomentMaxValue());
        //measurement.setMaxValue(giveMeMaxValue());
        //measurement.setData(giveMeData());
    }

    //estos métodos hay que cambiarlos, están así para hacer un commit sin errores
    private int giveMeMagnitude(int magnitud) {
        return magnitud;
    }

    private String giveMeMagnitudeName(String magnitudname) {
        return magnitudname;
    }

    private String giveMeMeasurementUnitName(String measurementunit) {
        return measurementunit;
    }

    private JFreeChart giveMeChart(JFreeChart chart) {
        return chart;
    }
//--------------------------------------------------------------


    private void setUpCAD() {
    }

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

    /*
    private String getNewestMeasure() {
        String mostRecentCADM = cadm.stream().
        String mostRecentCADMM = cadmm.stream()
        return
    }

    private String getOldestMeasure() {
        return
    }
     */

    public void filter(String cityName) {
        // con esto dejaremos solo las tuplas correspondientes a la ciudad que introduzcamos
        int cityCode = cityNameToMunicipio(cityName);
        // esto filtra las listas y las actualiza
        // List<Calidad_aire_datos_mes> resultCADM = cadm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cadm = cadm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cadmm = cadmm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cae = cae.stream().filter(x -> x.getEstacion_municipio().equalsIgnoreCase(cityName)).collect(Collectors.toList());
        caz = caz.stream().filter(x -> x.getZona_calidad_aire_municipio().equalsIgnoreCase(cityName)).collect(Collectors.toList());
    }

    private int cityNameToMunicipio(String cityName) {
        return codeCity.get(cityName);
    }
}
