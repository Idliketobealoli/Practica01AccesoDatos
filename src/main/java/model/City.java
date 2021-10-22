package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.chart.JFreeChart;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String name;
    private String measurementStartDate; // fecha primera medicion registrada
    private String measurementEndDate; // fecha ultima medicion registrada
    private double averageValue;
    private JFreeChart chart;
    private String momentMinValue;
    private double minValue;
    private String momentMaxValue;
    private double maxValue;
    private List<String> daysOnWhichRained;
    private List<Double> rainMeasurements;
    private ArrayList<String> associatedStationList;
    private ArrayList<Calidad_aire_datos> measurements;
}

