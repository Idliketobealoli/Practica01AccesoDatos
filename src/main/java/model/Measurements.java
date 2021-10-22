package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.chart.JFreeChart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurements {
    private int type;
    private String typeName;
    private String measurementUnitName;
    private double averageValue;
    private JFreeChart chart;
    private String momentMinValue;
    private double minValue;
    private String momentMaxValue;
    private double maxValue;
    private List<String> daysOnWhichRained;
    private List<Double> rainMeasurements;
}
