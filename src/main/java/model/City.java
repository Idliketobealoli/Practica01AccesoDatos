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

public class City {
    private String name;
    private Date firstMeasurementDate; // fecha primera medicion registrada
    private Date lastMeasurementDate; // fecha ultima medicion registrada
    private ArrayList<String> associatedStationList;
    private ArrayList<Measurement> meteoMeasurements;
    private ArrayList<Measurement> contaminationMeasurements;
}

