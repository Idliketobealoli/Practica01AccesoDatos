package utils;

import model.Calidad_aire_datos_mes;
import model.Calidad_aire_datos_meteo_mes;
import model.Calidad_aire_estaciones;
import model.Calidad_aire_zonas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Util {
    // creamos un metodo que lea el csv "calidad_aire_datos_mes.csv" y lo convierta en un ArrayList de objetos pojo.Calidad_aire_datos_mes
    public static List<Calidad_aire_datos_mes> getCalidad_aire_datos_mes() throws IOException {
        // cogemos el working directory
        String WORKING_DIRECTORY = System.getProperty("user.dir");
        // y creamos un objeto Path con el path al csv "calidad_aire_datos_mes.csv"
        Path csv = Paths.get(WORKING_DIRECTORY + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "calidad_aire_datos_mes.csv");
        final List<String> lines = Files.readAllLines(csv);
        List<Calidad_aire_datos_mes> cadmList = new ArrayList<>();
        // esto lo hacemos con un for normal porque necesitamos el indice para el lines.get(indice)
        // empezamos con i = 1 para que no coja la primera linea del csv, ya que esa no nos interesa
        for(int i = 1; i < lines.size(); i++) {
            StringTokenizer st = new StringTokenizer(lines.get(i), ";");
            Calidad_aire_datos_mes cadm = new Calidad_aire_datos_mes();
            // metemos la tupla de cada columna del csv dentro del objeto cadm (o las tuplas que necesitemos, que de momento tiene pinta de que son todas)
            cadm.setProvincia(Integer.parseInt(st.nextToken()));
            cadm.setMunicipio(Integer.parseInt(st.nextToken()));
            cadm.setEstacion(Integer.parseInt(st.nextToken()));
            cadm.setMagnitud(Integer.parseInt(st.nextToken()));
            cadm.setPunto_muestreo(Integer.parseInt(st.nextToken()));
            cadm.setAno(Integer.parseInt(st.nextToken()));
            cadm.setMes(Integer.parseInt(st.nextToken()));
            cadm.setDia(Integer.parseInt(st.nextToken()));
            while (st.hasMoreTokens()) {
                // ojo, si queda exactamente un token, se meterá en el bucle y añadirá uno a
                // listH, pero explotará cuando intente meter uno a listV, pues no quedarían más.
                cadm.getListH().add(Integer.parseInt(st.nextToken()));
                cadm.getListV().add(st.nextToken().charAt(0));
            }
            // una vez seteado todo, lo anadimos a la lista de objetos cadm
            cadmList.add(cadm);
        }
        return cadmList;
    }

    public static List<Calidad_aire_datos_meteo_mes> getCalidad_aire_datos_meteo_mes() throws IOException {
        // esto es lo mismo que en el anterior metodo, pero sin usar la variable "WORKING DIRECTORY", pero es menos legible de esta forma creo yo
        Path csv = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "calidad_aire_datos_meteo_mes.csv");
        final List<String> lines = Files.readAllLines(csv);
        List<Calidad_aire_datos_meteo_mes> cadmmList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            StringTokenizer st = new StringTokenizer(lines.get(i), ";");
            Calidad_aire_datos_meteo_mes cadmm = new Calidad_aire_datos_meteo_mes();
            cadmm.setProvincia(Integer.parseInt(st.nextToken()));
            cadmm.setMunicipio(Integer.parseInt(st.nextToken()));
            cadmm.setEstacion(Integer.parseInt(st.nextToken()));
            cadmm.setMagnitud(Integer.parseInt(st.nextToken()));
            cadmm.setPunto_muestreo(Integer.parseInt(st.nextToken()));
            cadmm.setAno(Integer.parseInt(st.nextToken()));
            cadmm.setMes(Integer.parseInt(st.nextToken()));
            cadmm.setDia(Integer.parseInt(st.nextToken()));
            while (st.hasMoreTokens()) {
                cadmm.getListH().add(Integer.parseInt(st.nextToken()));
                cadmm.getListV().add(st.nextToken().charAt(0));
            }
            cadmmList.add(cadmm);
        }
        return cadmmList;
    }

    public static List<Calidad_aire_estaciones> getCalidad_aire_estaciones() throws IOException {
        Path csv = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "calidad_aire_estaciones.csv");
        final List<String> lines = Files.readAllLines(csv);
        List<Calidad_aire_estaciones> caeList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            StringTokenizer st = new StringTokenizer(lines.get(i), ";");
            Calidad_aire_estaciones cae = new Calidad_aire_estaciones();
            cae.setEstacion_codigo(Integer.parseInt(st.nextToken()));
            cae.setZona_calidad_aire_descripcion(st.nextToken());
            cae.setEstacion_municipio(st.nextToken());
            cae.setEstacion_fecha_alta(st.nextToken());
            cae.setEstacion_tipo_area(st.nextToken());
            cae.setEstacion_tipo_estacion(st.nextToken());
            cae.setEstacion_subarea_rural(st.nextToken());
            cae.setEstacion_direccion_postal(st.nextToken());
            cae.setEstacion_coord_UTM_ETRS89_x(Integer.parseInt(st.nextToken()));
            cae.setEstacion_coord_UTM_ETRS89_y(Integer.parseInt(st.nextToken()));
            cae.setEstacion_coord_longitud(st.nextToken());
            cae.setEstacion_coord_latitud(st.nextToken());
            cae.setEstacion_altitud(Integer.parseInt(st.nextToken()));
            cae.setEstacion_analizador_NO(st.nextToken());
            cae.setEstacion_analizador_NO2(st.nextToken());
            cae.setEstacion_analizador_PM10(st.nextToken());
            cae.setEstacion_analizador_PM2_5(st.nextToken());
            cae.setEstacion_analizador_O3(st.nextToken());
            cae.setEstacion_analizador_TOL(st.nextToken());
            cae.setEstacion_analizador_BEN(st.nextToken());
            cae.setEstacion_analizador_XIL(st.nextToken());
            cae.setEstacion_analizador_CO(st.nextToken());
            cae.setEstacion_analizador_SO2(st.nextToken());
            cae.setEstacion_analizador_HCT(st.nextToken());
            cae.setEstacion_analizador_HNM(st.nextToken());
            caeList.add(cae);
        }
        return caeList;
    }

    public static List<Calidad_aire_zonas> getCalidad_aire_zonas() throws IOException {
        Path csv = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "calidad_aire_zonas.csv");
        final List<String> lines = Files.readAllLines(csv);
        List<Calidad_aire_zonas> cazList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            StringTokenizer st = new StringTokenizer(lines.get(i), ";");
            Calidad_aire_zonas caz = new Calidad_aire_zonas();
            caz.setZona_calidad_aire_codigo(Integer.parseInt(st.nextToken()));
            caz.setZona_calidad_aire_descripcion(st.nextToken());
            caz.setZona_calidad_aire_municipio(st.nextToken());
            cazList.add(caz);
        }
        return cazList;
    }
}
