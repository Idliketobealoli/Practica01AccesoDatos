package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    private int estacion_codigo;
    private String estacion_municipio;
    private String estacion_fecha_alta;
    private String estacion_tipo_area;
    private String estacion_tipo_estacion;
    private String estacion_subarea_rural;
    private String estacion_direccion_postal;
    private int estacion_coord_UTM_ETRS89_x;
    private int estacion_coord_UTM_ETRS89_y;
    private String estacion_coord_longitud;
    private String estacion_coord_latitud;
    private int estacion_altitud;
    private String estacion_analizador_NO;
    private String estacion_analizador_NO2;
    private String estacion_analizador_PM10;
    private String estacion_analizador_PM2_5;
    private String estacion_analizador_O3;
    private String estacion_analizador_TOL;
    private String estacion_analizador_BEN;
    private String estacion_analizador_XIL;
    private String estacion_analizador_CO;
    private String estacion_analizador_SO2;
    private String estacion_analizador_HCT;
    private String estacion_analizador_HNM;
}
