package filterClasses;

import model.City;
import model.Measurement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class CreateHTML {
    public CreateHTML(City city, String path) {
        Path processedPath = processPath(city, Path.of(path));
        generateHTML(city, processedPath);
    }

    /**
     * A partir de un objeto City y un Path, returnea un Path con el path al directorio que le hayas pasado y
     * dentro un archivo llamado "[nombre_City]-dd-MM-yyyy.html", donde dd-MM-yyyy es la fecha en la que se llamó
     * a este método.
     * @author Daniel Rodríguez Muñoz
     * @param city City
     * @param path Path
     * @return Path
     * @see model.City
     * @see java.nio.file.Path
     */
    private Path processPath(City city, Path path) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        return Paths.get(path + File.separator + city.getName() + "-" + dtf.format(now) + ".html");
    }

    /**
     * Este método generará un html a partir de un Path y una City dados.
     * Si la ruta ya existe, pedirá por consola si quieres sobreescribir el archivo (borrar el anterior y
     * guardar este en la ruta dada). De recibir una respuesta afirmativa, lo hará, a no ser que no pueda borrar
     * dicho archivo, en cuyo caso lo notificará por consola y acabará la ejecución sin modificar ningún archivo.
     * En el caso de recibir una respuesta negativa, terminará sin modificar nada.
     * @author Daniel Rodríguez Muñoz
     * @param city City
     * @param path Path
     * @see java.nio.file.Path
     * @see model.City
     * @see java.util.Scanner
     */
    private void generateHTML(City city, Path path) {
        File html = path.toFile();
        if (!html.exists()) {
            try (FileWriter writer = new FileWriter(html)) {
                writer.write(writeHTML(city));
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("'" + html.getName() + "' already exists, would you like to overwrite the file? [y/n]");
            String answer = "";
            while (!(answer.equalsIgnoreCase("y")) &&
                    !(answer.equalsIgnoreCase("n"))) {
                answer = sc.next();
            }
            if (answer.equalsIgnoreCase("y")) {
                boolean result_of_deleting = html.delete();
                if (!result_of_deleting) {
                    System.out.println("Unable to delete "+ html.getName());
                } else {
                    generateHTML(city, path);
                }
            }
        }
    }

    /**
     * Este método returneará un String con el html generado a partir del objeto City pasado por parámetro.
     * @autor Daniel Rodríguez Muñoz
     * @param city City
     * @return String
     * @see model.City
     */
    private String writeHTML(City city) {
        StringBuilder sb = new StringBuilder();
        sb.append(""); // esto está aquí para prevenir que returnee nulos, aunque sea redundante.
        String firstMeasDate = formateameDate(city.getFirstMeasurementDate());
        String lastMeasDate = formateameDate(city.getLastMeasurementDate());
        sb.append("<!doctype html> \n" +
                "<html lang=\"es\"> \n" +
                "\t<head> \n" +
                "\t\t<meta charset=\"utf-8\"/> \n" +
                "\t\t<title>El tiempo en " + city.getName() + "</title> \n" +
                "\t</head> \n" +
                "\t<body> \n" +
                "\t\t<h1>Estadísticas de <i>" + city.getName() + "</i>:</h1> \n" +
                "\t\t<p> \n\t\t\tPrimera medición registrada: <i>" + firstMeasDate + "</i><br/> \n" +
                "\t\t\tÚltima medición registrada: <i>" + lastMeasDate + "</i><br/> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Estaciones asociadas:</b><br/> \n" +
                "\t\t\t<ul> \n");
        for (String station: city.getAssociatedStationList()) {
            sb.append("\t\t\t\t<li>" + station + "</li> \n");
        }
        sb.append("\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Información meteorológica:</b><br/> \n" +
                "\t\t\t<ul> \n");
        for (Measurement measure: city.getMeteoMeasurements()) {
            if (measure.getMagnitude() != 89) {
                sb.append("\t\t\t\t<li><i>" + measure.getMagnitudeName() + "</i> \n" +
                        "\t\t\t\t\t<ul> \n" +
                        "\t\t\t\t\t\t<li>" + measure.getMagnitudeName() + " media mensual: " +
                        measure.getAverageValue() + measure.getMeasurementUnitName() + "</li> \n" +
                        "\t\t\t\t\t\t<li>Máxima registrada: " + measure.getMaxValue() +
                        measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMaxValue()) + "</li> \n" +
                        "\t\t\t\t\t\t<li>Mínima registrada: " + measure.getMinValue() +
                        measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMinValue()) + "</li> \n" +
                        "\t\t\t\t\t\t<li>Evolución mensual: <br/> \n" +
                        "\t\t\t\t\t\t\t<img src=\"" + /* aquí iría la jFreeChart*/ "name=\"" +
                        measure.getMagnitudeName() + "\" id=\"" + measure.getMagnitude() + "\"/> \n" +
                        "\t\t\t\t\t\t</li> \n" +
                        "\t\t\t\t\t</ul> \n" +
                        "\t\t\t\t</li>");
            } else {
                sb.append("\t\t\t\t<li><i>" + measure.getMagnitudeName() + "</i> \n" +
                        "\t\t\t\t\t<ul> \n" +
                        "\t\t\t\t\t\t<li>" + measure.getMagnitudeName() + "media mensual: " +
                        measure.getAverageValue() + measure.getMeasurementUnitName() + "</li> \n" +
                        "\t\t\t\t\t\t<li> Lista de días en los que ha llovido: \n" +
                        "\t\t\t\t\t\t\t<ul> \n");
                if (measure.getDaysOnWhichRained().isEmpty()) {
                    sb.append("\t\t\t\t\t\t\t\t<li>No llovió ningún día.</li> \n");
                } else {
                    for (int i = 0; i < measure.getDaysOnWhichRained().size(); i++) {
                        sb.append("\t\t\t\t\t\t\t\t<li>" + formateameDate(measure.getDaysOnWhichRained().get(i)) +"-" +
                                "Precipitación: " + measure.getRainMeasurements().get(i) +
                                measure.getMeasurementUnitName() + "</li> \n");
                    }
                }
                sb.append("\t\t\t\t\t\t\t</ul> \n" +
                        "\t\t\t\t\t\t</li>" +
                        "\t\t\t\t\t\t<li>Histograma: <br/> \n" +
                        "\t\t\t\t\t\t\t<img src=\"" + /* aquí iría la jFreeChart*/ "name=\"" +
                        measure.getMagnitudeName() + "\" id=\"" + measure.getMagnitude() + "\"/> \n" +
                        "\t\t\t\t\t\t</li> \n" +
                        "\t\t\t\t\t</ul> \n" +
                        "\t\t\t\t</li>");
            }
        }
        sb.append("\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Información sobre contaminación:</b><br/> \n" +
                "\t\t\t<ul> \n");
        for (Measurement measure: city.getContaminationMeasurements()) {
            sb.append("\t\t\t\t<li><i>" + measure.getMagnitudeName() + "</i> \n" +
                    "\t\t\t\t\t<ul> \n" +
                    "\t\t\t\t\t\t<li>Valor medio mensual: " +
                    measure.getAverageValue() + measure.getMeasurementUnitName() + "</li> \n" +
                    "\t\t\t\t\t\t<li>Máxima registrada: " + measure.getMaxValue() +
                    measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMaxValue()) + "</li> \n" +
                    "\t\t\t\t\t\t<li>Mínima registrada: " + measure.getMinValue() +
                    measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMinValue()) + "</li> \n" +
                    "\t\t\t\t\t\t<li>Evolución mensual: <br/> \n" +
                    "\t\t\t\t\t\t\t<img src=\"" + /* aquí iría la jFreeChart*/ "name=\"" +
                    measure.getMagnitudeName() + "\" id=\"" + measure.getMagnitude() + "\"/> \n" +
                    "\t\t\t\t\t\t</li> \n" +
                    "\t\t\t\t\t</ul> \n" +
                    "\t\t\t\t</li>");
        }
        sb.append("\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Hecho por:</b> <br/> \n" +
                "\t\t\t<ul> \n" +
                "\t\t\t\t<li><i>Daniel Rodríguez Muñoz (loli)</i></li> \n" +
                "\t\t\t\t<li><i>Jaime Salcedo Vallejo</i></li> \n" +
                "\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t</body> \n" +
                "</html>");
        return sb.toString();
    }

    /**
     * Este método formatea el objeto Date pasado por parámetro al patrón "dd/MM/yyyy - HH:mm:ss",
     * devolviéndolo como String.
     * @author Daniel Rodríguez Muñoz
     * @param date Date
     * @return String
     */
    private String formateameDate(Date date) {
        String formattedDate = date.toGMTString();
        formattedDate = formattedDate.replace("GMT", "");
        return formattedDate;
    }
}
