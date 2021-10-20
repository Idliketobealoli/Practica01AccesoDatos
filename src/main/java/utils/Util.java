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
            cadm.setH1(Integer.parseInt(st.nextToken()));
            cadm.setV1(st.nextToken().charAt(0));
            cadm.setH2(Integer.parseInt(st.nextToken()));
            cadm.setV2(st.nextToken().charAt(0));
            cadm.setH3(Integer.parseInt(st.nextToken()));
            cadm.setV3(st.nextToken().charAt(0));
            cadm.setH4(Integer.parseInt(st.nextToken()));
            cadm.setV4(st.nextToken().charAt(0));
            cadm.setH5(Integer.parseInt(st.nextToken()));
            cadm.setV5(st.nextToken().charAt(0));
            cadm.setH6(Integer.parseInt(st.nextToken()));
            cadm.setV6(st.nextToken().charAt(0));
            cadm.setH7(Integer.parseInt(st.nextToken()));
            cadm.setV7(st.nextToken().charAt(0));
            cadm.setH8(Integer.parseInt(st.nextToken()));
            cadm.setV8(st.nextToken().charAt(0));
            cadm.setH9(Integer.parseInt(st.nextToken()));
            cadm.setV9(st.nextToken().charAt(0));
            cadm.setH10(Integer.parseInt(st.nextToken()));
            cadm.setV10(st.nextToken().charAt(0));
            cadm.setH11(Integer.parseInt(st.nextToken()));
            cadm.setV11(st.nextToken().charAt(0));
            cadm.setH12(Integer.parseInt(st.nextToken()));
            cadm.setV12(st.nextToken().charAt(0));
            cadm.setH13(Integer.parseInt(st.nextToken()));
            cadm.setV13(st.nextToken().charAt(0));
            cadm.setH14(Integer.parseInt(st.nextToken()));
            cadm.setV14(st.nextToken().charAt(0));
            cadm.setH15(Integer.parseInt(st.nextToken()));
            cadm.setV15(st.nextToken().charAt(0));
            cadm.setH16(Integer.parseInt(st.nextToken()));
            cadm.setV16(st.nextToken().charAt(0));
            cadm.setH17(Integer.parseInt(st.nextToken()));
            cadm.setV17(st.nextToken().charAt(0));
            cadm.setH18(Integer.parseInt(st.nextToken()));
            cadm.setV18(st.nextToken().charAt(0));
            cadm.setH19(Integer.parseInt(st.nextToken()));
            cadm.setV19(st.nextToken().charAt(0));
            cadm.setH20(Integer.parseInt(st.nextToken()));
            cadm.setV20(st.nextToken().charAt(0));
            cadm.setH21(Integer.parseInt(st.nextToken()));
            cadm.setV21(st.nextToken().charAt(0));
            cadm.setH22(Integer.parseInt(st.nextToken()));
            cadm.setV22(st.nextToken().charAt(0));
            cadm.setH23(Integer.parseInt(st.nextToken()));
            cadm.setV23(st.nextToken().charAt(0));
            cadm.setH24(Integer.parseInt(st.nextToken()));
            cadm.setV24(st.nextToken().charAt(0));
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
            cadmm.setH1(Integer.parseInt(st.nextToken()));
            cadmm.setV1(st.nextToken().charAt(0));
            cadmm.setH2(Integer.parseInt(st.nextToken()));
            cadmm.setV2(st.nextToken().charAt(0));
            cadmm.setH3(Integer.parseInt(st.nextToken()));
            cadmm.setV3(st.nextToken().charAt(0));
            cadmm.setH4(Integer.parseInt(st.nextToken()));
            cadmm.setV4(st.nextToken().charAt(0));
            cadmm.setH5(Integer.parseInt(st.nextToken()));
            cadmm.setV5(st.nextToken().charAt(0));
            cadmm.setH6(Integer.parseInt(st.nextToken()));
            cadmm.setV6(st.nextToken().charAt(0));
            cadmm.setH7(Integer.parseInt(st.nextToken()));
            cadmm.setV7(st.nextToken().charAt(0));
            cadmm.setH8(Integer.parseInt(st.nextToken()));
            cadmm.setV8(st.nextToken().charAt(0));
            cadmm.setH9(Integer.parseInt(st.nextToken()));
            cadmm.setV9(st.nextToken().charAt(0));
            cadmm.setH10(Integer.parseInt(st.nextToken()));
            cadmm.setV10(st.nextToken().charAt(0));
            cadmm.setH11(Integer.parseInt(st.nextToken()));
            cadmm.setV11(st.nextToken().charAt(0));
            cadmm.setH12(Integer.parseInt(st.nextToken()));
            cadmm.setV12(st.nextToken().charAt(0));
            cadmm.setH13(Integer.parseInt(st.nextToken()));
            cadmm.setV13(st.nextToken().charAt(0));
            cadmm.setH14(Integer.parseInt(st.nextToken()));
            cadmm.setV14(st.nextToken().charAt(0));
            cadmm.setH15(Integer.parseInt(st.nextToken()));
            cadmm.setV15(st.nextToken().charAt(0));
            cadmm.setH16(Integer.parseInt(st.nextToken()));
            cadmm.setV16(st.nextToken().charAt(0));
            cadmm.setH17(Integer.parseInt(st.nextToken()));
            cadmm.setV17(st.nextToken().charAt(0));
            cadmm.setH18(Integer.parseInt(st.nextToken()));
            cadmm.setV18(st.nextToken().charAt(0));
            cadmm.setH19(Integer.parseInt(st.nextToken()));
            cadmm.setV19(st.nextToken().charAt(0));
            cadmm.setH20(Integer.parseInt(st.nextToken()));
            cadmm.setV20(st.nextToken().charAt(0));
            cadmm.setH21(Integer.parseInt(st.nextToken()));
            cadmm.setV21(st.nextToken().charAt(0));
            cadmm.setH22(Integer.parseInt(st.nextToken()));
            cadmm.setV22(st.nextToken().charAt(0));
            cadmm.setH23(Integer.parseInt(st.nextToken()));
            cadmm.setV23(st.nextToken().charAt(0));
            cadmm.setH24(Integer.parseInt(st.nextToken()));
            cadmm.setV24(st.nextToken().charAt(0));
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
        List<Calidad_aire_zonas> cazList = new ArrayList<Calidad_aire_zonas>();
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
