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
public class Measurements {
    private int type;
    private double averageValue;
    private JFreeChart chart;
    private String momentMinValue;
    private double minValue;
    private String momentMaxValue;
    private double maxValue;
    private List<String> daysOnWhichRained = new ArrayList<>();
    private List<Double> rainMeasurements = new ArrayList<>();
}
