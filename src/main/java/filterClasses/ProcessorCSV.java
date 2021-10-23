package filterClasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


// DEPRECATED
public class ProcessorCSV {
    public String processCSV(Path path, String city) {
        switch (path.getName(4).toString()) {
            case "calidad_aire_datos_mes.csv":
                // este csv contiene 
                return processCADM(path, city);
            // break;
            case "calidad_aire_datos_meteo_mes.csv":
                // return processCADMM(line);
            break;
            case "calidad_aire_estaciones.csv":
                // return processCAE(line);
            break;
            case "calidad_aire_zonas.csv":
                // return processCAZ(line);
            break;
            default: return null;
        }
        return null;
    }

    private String processCADM(Path path, String city) {
        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path)/*.stream().filter().map()*/.forEach(x -> sb.append(x + "\n"));
        } catch (IOException e) {
            System.err.println(e);
        }
        return sb.toString();
    }
}