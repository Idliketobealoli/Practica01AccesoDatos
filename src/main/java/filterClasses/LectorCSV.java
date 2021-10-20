package filterClasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LectorCSV {
    Path thisPath = Paths.get(".");
    Path resources = Paths.get(thisPath + File.separator + "src" + File.separator + "main" + File.separator + "resources");

    // lee el csv linea a linea y lo devuelve como String
    public String readCSV(String nameOfFile) {
        Path csv = Paths.get(resources + File.separator + nameOfFile);
        StringBuilder sb = new StringBuilder();
        if (Files.exists(csv) && !Files.isDirectory(csv) && Files.isReadable(csv)) {
            try {
                Files.readAllLines(csv).forEach(x -> sb.append(x + "\n"));
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        else {
            System.out.println("'" + nameOfFile + "' does not exist, is a directory or is not readable.");
        }
        return sb.toString();
    }

    //este método leerá el csv línea a línea, filtrando los campos que necesitemos más adelante en función de qué csv sea.
    public String buildNewCSVcontent(String nameOfFile, String city) {
        String result = "";
        Path csv = Paths.get(resources + File.separator + nameOfFile);
        ProcessorCSV pcsv = new ProcessorCSV();
        if (Files.exists(csv) && !Files.isDirectory(csv) && Files.isReadable(csv)) {
            // Files.readAllLines(csv).stream().forEach(x -> sBuilder.append(pcsv.processCSV(nameOfFile, x)));
            result = pcsv.processCSV(csv, city);
        }
        else {
            System.out.println("'" + nameOfFile + "' does not exist, is a directory or is not readable.");
        }
        return result;
    }

    // Copia un csv cuyo nombre le pases por parámetro de la carpeta resources a la carpeta copies.
    public void saveModifiedCSV(String nameOfFile, String content) {
        Path csvModified = Paths.get(resources + File.separator + nameOfFile);
        if (Files.exists(csvModified)) {
            csvModified = Paths.get( resources + File.separator + "copia_" + nameOfFile);
        }
        try {
            csvModified.toFile().createNewFile();
            File file = new File(csvModified.toString());
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    // A este método tú le das un path y se carga lo que esté en ese path.
    // Lo usaremos para borrar las copias una vez terminemos de operar para no dejar basura de por medio.
    public void destroyFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
