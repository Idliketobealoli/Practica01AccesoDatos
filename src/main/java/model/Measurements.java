package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jfree.chart.JFreeChart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurements {
    private int type;
    private double averageValue;
    private JFreeChart chart;
    private Date momentMinValue;
    private double minValue;
    private Date momentMaxValue;
    private double maxValue;
    private List<Date> daysOnWhichRained = new ArrayList<>();
    private List<Double> rainMeasurements = new ArrayList<>();
}
