package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String name;
    private String measurementStartDate; // fecha primera medicion
    private String measurementEndDate; // fecha ultima medicion
    private ArrayList<String> associatedStationList;
    private ArrayList<Measurements> measurements;
}

