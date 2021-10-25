package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.chart.JFreeChart;

import java.util.*;

/**
 * Esta clase será la que agrupe cada objeto Calidad_arire_datos en función de su magnitud,
 * así como contendrá la media, máxima y mínima de los datos que tenga.
 * Tendrá tambien el jFreeChart de dichos datos y la unidad de medida de estos.
 * Si son datos de lluvia, en lugar de tener máxima, mínima y media, tendrá una lista de días
 * en los que ha llovido y las medidas de dicha lluvia.
 * Estos objetos pojo serán almacenados en la clase City.
 * @author Jaime Salcedo Vallejo
 * @see Calidad_aire_datos
 * @see City
 * @see filterClasses.ProcessData
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    private int magnitude;
    private String magnitudeName;
    private String measurementUnitName;
    private double averageValue;
    private JFreeChart chart;
    private Date momentMinValue;
    private double minValue;
    private Date momentMaxValue;
    private double maxValue;
    private List<Calidad_aire_datos> data = new ArrayList<>();
    private List<Date> daysOnWhichRained = new ArrayList<>();
    private List<Double> rainMeasurements = new ArrayList<>();
}
