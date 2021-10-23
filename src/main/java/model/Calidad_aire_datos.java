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
    private Date fecha_medicion;
    private List<Double> listH = new ArrayList<>(24);
    private List <Character> listV = new ArrayList<>(24);
    private Set<Integer> hour = new HashSet<>(24);
}
