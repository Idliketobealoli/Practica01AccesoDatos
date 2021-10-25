package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase será la que utilicemos para plasmarla en el html. Se compone del nombre de la ciudad,
 * las fechas en las que fueron registradas la primera y la última medición en dicha ciudad,
 * una lista de nombres de estaciones asociadas, una lista de measurements meteorológicas y una
 * lista de measurements de contaminantes.
 * @author Daniel Rodríguez Muñoz
 * @see City
 * @see filterClasses.ProcessData
 * @see filterClasses.CreateHTML
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String name;
    private Date firstMeasurementDate; // fecha primera medicion registrada
    private Date lastMeasurementDate; // fecha ultima medicion registrada
    private ArrayList<String> associatedStationList = new ArrayList<>();
    private ArrayList<Measurement> meteoMeasurements = new ArrayList<>();
    private ArrayList<Measurement> contaminationMeasurements = new ArrayList<>();
}

