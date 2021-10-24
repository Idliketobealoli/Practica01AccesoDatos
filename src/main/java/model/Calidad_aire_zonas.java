package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daniel Rodríguez Muñoz
 * @deprecated
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calidad_aire_zonas {
    private int zona_calidad_aire_codigo;
    private String zona_calidad_aire_descripcion;
    private String zona_calidad_aire_municipio;
}
