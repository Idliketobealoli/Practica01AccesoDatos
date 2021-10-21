package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calidad_aire_datos_mes {
    private int provincia;
    private int municipio;
    private int estacion;
    private int magnitud;
    private String punto_muestreo;
    private int ano;
    private int mes;
    private int dia;
    private List<Double> listH = new ArrayList<>(24);
    private List<Character> listV = new ArrayList<>(24);
}
