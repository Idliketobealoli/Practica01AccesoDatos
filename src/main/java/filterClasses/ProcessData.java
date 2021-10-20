package filterClasses;

import model.*;
import utils.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessData {
    List<Calidad_aire_datos_mes> cadm;
    List<Calidad_aire_datos_meteo_mes> cadmm;
    List<Calidad_aire_estaciones> cae;
    List<Calidad_aire_zonas> caz;
    Map<String, Integer> codeCity;

    private Map<String, Integer> setValues() {
        Map<String, Integer> map = new HashMap<String, Integer>(24);
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
            cadm = Util.getCalidad_aire_datos_mes();
            cadmm = Util.getCalidad_aire_datos_meteo_mes();
            cae = Util.getCalidad_aire_estaciones();
            caz = Util.getCalidad_aire_zonas();
            codeCity = setValues();
            filter(city);
            // continuar metiendo filtros aqui.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filter(String cityName) {
        // con esto dejaremos solo las tuplas correspondientes a la ciudad que introduzcamos
        int cityCode = cityNameToMunicipio(cityName);
        // esto filtra las listas y las actualiza
        cadm = cadm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cadmm = cadmm.stream().filter(x -> x.getMunicipio() == cityCode).collect(Collectors.toList());
        cae = cae.stream().filter(x -> x.getEstacion_municipio().equalsIgnoreCase(cityName)).collect(Collectors.toList());
        caz = caz.stream().filter(x -> x.getZona_calidad_aire_municipio().equalsIgnoreCase(cityName)).collect(Collectors.toList());
    }

    private int cityNameToMunicipio(String cityName) {
        return codeCity.get(cityName);
    }
}
