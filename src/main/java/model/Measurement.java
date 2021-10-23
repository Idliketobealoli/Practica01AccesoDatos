package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.chart.JFreeChart;

import java.util.*;

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
    private List<Calidad_aire_datos> data;
    private List<Date> daysOnWhichRained;
    private List<Double> rainMeasurements;
}
