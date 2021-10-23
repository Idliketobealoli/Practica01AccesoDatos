package filterClasses;

import model.City;
import model.Measurement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class CreateHTML {
    public CreateHTML(City city, String path) {
        Path processedPath = processPath(city, Path.of(path));
        generateHTML(city, processedPath);
    }

    private Path processPath(City city, Path path) {
        return Paths.get(path + File.separator + city.getName() + "-" +
                city.getLastMeasurementDate().getDate() + "-" +
                (city.getLastMeasurementDate().getMonth() + 1) + "-" +
                (city.getLastMeasurementDate().getYear() + 1900) + ".html");
    }

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
            while ((!answer.equalsIgnoreCase("y")) |
                    (!answer.equalsIgnoreCase("n"))) {
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

    private String writeHTML(City city) {
        StringBuilder sb = new StringBuilder();
        sb.append(""); // esto está aquí para prevenir que returnee nulos, aunque sea redundante.
        String firstMeasDate = formateameDate(city.getFirstMeasurementDate());
        String lastMeasDate = formateameDate(city.getLastMeasurementDate());
        lastMeasDate = lastMeasDate.replace("GMT", "");
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
                        "\t\t\t\t\t\t<li>" + measure.getMagnitudeName() + "media mensual: " +
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
                for (int i = 0; i < measure.getDaysOnWhichRained().size(); i++) {
                    sb.append("\t\t\t\t\t\t\t\t<li>" + formateameDate(measure.getDaysOnWhichRained().get(i)) +"-" +
                            "Precipitación: " + measure.getRainMeasurements().get(i) +
                            measure.getMeasurementUnitName() + "</li> \n");
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

    private String formateameDate(Date date) {
        String result = date.toGMTString();
        result = result.replace("GMT", "");
        return result;
    }
}
