package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calidad_aire_datos {
    private int provincia;
    private int municipio;
    private int estacion;
    private int magnitud;
    private String punto_muestreo;
    private int ano;
    private int mes;
    private int dia;
    private Map<Integer, Double> listH = new HashMap<>(24);
    private Map<Integer, Character> listV = new HashMap<>(24);
    private Set<Integer> hour = new HashSet<>(24);
}
