package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Esta clase pojo es nuestra principal fuente de datos.
 * Cada objeto de esta clase está asociado a una medición particular de un día concreto,
 * de ahí que tenga el atributo fecha_medición.
 * Contendrá, además de el número de provincia, municipio, estación y magnitud, y fecha_medición,
 * una lista de doubles con las mediciones de ese día en concreto, una lista de characters para
 * determinar si dichas mediciones son o no válidas, y un set de integers que tendrá las horas de
 * 0 a 23 para poder saber a qué hora del día pertenece cada medición.
 * Una lista de objetos Calidad_aire_datos será contenida en cada objeto Measurement, en
 * función de la magnitud de ambos.
 * @author Daniel Rodríguez Muñoz
 * @see Measurement
 * @see utils.Util
 */
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
