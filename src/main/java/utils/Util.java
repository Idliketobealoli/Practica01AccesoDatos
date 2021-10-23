package utils;

import model.Calidad_aire_datos;
import model.Calidad_aire_estaciones;
import model.Calidad_aire_zonas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Util {
    // Creamos un método que lea los csv "calidad_aire_datos(_meteo)_mes.csv" y los convierta en un ArrayList de objetos pojo Calidad_aire_datos.
    public static List<Calidad_aire_datos> getCalidad_aire_datos(String csvName) throws IOException {
        // Cogemos el working directory.
        String WORKING_DIRECTORY = System.getProperty("user.dir");
        // Y creamos un objeto Path con el path al csv que le pasemos.
        Path csv = Paths.get(WORKING_DIRECTORY + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + csvName);
        final List<String> lines = Files.readAllLines(csv);
        List<Calidad_aire_datos> cadList = new ArrayList<>();
        // Esto lo hacemos con un for normal porque necesitamos el índice para el lines.get(indice).
        // Empezamos con i = 1 para que no coja la primera línea del csv, ya que esa no nos interesa.
        for(int i = 1; i < lines.size(); i++) {
            StringTokenizer st = new StringTokenizer(lines.get(i), ";");
            Calidad_aire_datos cad = new Calidad_aire_datos();
            // Metemos la tupla de cada columna del csv dentro del objeto cad (o las tuplas que necesitemos, que de momento tiene pinta de que son todas).
            cad.setProvincia(parseInt(st.nextToken()));
            cad.setMunicipio(parseInt(st.nextToken()));
            cad.setEstacion(parseInt(st.nextToken()));
            cad.setMagnitud(parseInt(st.nextToken()));
            cad.setPunto_muestreo(st.nextToken());
            int ano = parseInt(st.nextToken());
            int mes = parseInt(st.nextToken());
            int dia = parseInt(st.nextToken());
            Date date = new Date(ano, mes, dia);
            cad.setFecha_medicion(date);
            int count = 1;
            while (st.hasMoreTokens()) {
                // Para evitar que el programa explote, si el token leído es un character (^[a-zA-Z]),
                // seteará este H de la listH a null y meterá el token en la listV. De lo contrario, procederá normal.
                // Esto está hecho así porque de lo contrario, metería characters dentro de listH, lo que reventaría el programa.
                String token = st.nextToken();
                if (!token.matches("^[a-zA-Z]")){ // otra forma de hacer esto sería "!(^[0-9])" y así sabríamos que no es número.
                    // Como necesitamos que los decimales estén separados por puntos,
                    // pero en los csv los separan con comas, simplemente en cada token que sea un valor numérico,
                    // reemplazamos la coma por un punto si la tiene y luego lo parseamos a double.
                    cad.getListH().add(Double.parseDouble(token.replace(',', '.')));
                    cad.getListV().add(st.nextToken().charAt(0));
                } else {
                    cad.getListH().add(null);
                    cad.getListV().add(token.charAt(0));
                }
                cad.getHour().add(count);
                count++;
            }
            // Una vez esto ha sido seteado, lo añadimos a la lista de objetos cad.
            cadList.add(cad);
        }
        return cadList;
    }

    private static String noDoubleDelimiter(String x) {
        while (x.contains(";;") || x.endsWith(";")) {
            x = Pattern.compile(";{2}").matcher(x).replaceAll(";" + null + ";");
            if (x.endsWith(";")) {
                x = Pattern.compile(";$").matcher(x).replaceAll(";" + null);
            }
        }
        return x;
    }

    public static List<Calidad_aire_estaciones> getCalidad_aire_estaciones() throws IOException {
        Path csv = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "calidad_aire_estaciones.csv");
        final List<String> lines = Files.readAllLines(csv);
        List<Calidad_aire_estaciones> caeList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String lineNoDD = noDoubleDelimiter(lines.get(i));
            StringTokenizer st = new StringTokenizer(lineNoDD, ";");
            Calidad_aire_estaciones cae = new Calidad_aire_estaciones();
            cae.setEstacion_codigo(parseInt(st.nextToken()));
            cae.setZona_calidad_aire_descripcion(st.nextToken());
            cae.setEstacion_municipio(st.nextToken());
            cae.setEstacion_fecha_alta(st.nextToken());
            cae.setEstacion_tipo_area(st.nextToken());
            cae.setEstacion_tipo_estacion(st.nextToken());
            cae.setEstacion_subarea_rural(st.nextElement().toString());
            cae.setEstacion_direccion_postal(st.nextToken());
            cae.setEstacion_coord_UTM_ETRS89_x(parseInt(st.nextToken()));
            cae.setEstacion_coord_UTM_ETRS89_y(parseInt(st.nextToken()));
            cae.setEstacion_coord_longitud(st.nextToken());
            cae.setEstacion_coord_latitud(st.nextToken());
            cae.setEstacion_altitud(parseInt(st.nextToken()));
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
            caz.setZona_calidad_aire_codigo(parseInt(st.nextToken()));
            caz.setZona_calidad_aire_descripcion(st.nextToken());
            caz.setZona_calidad_aire_municipio(st.nextToken());
            cazList.add(caz);
        }
        return cazList;
    }
}
