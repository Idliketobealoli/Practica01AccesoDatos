import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LectorCSV {
    Path thisPath = Paths.get(".");
    Path resources = Paths.get(thisPath + File.separator + "src" + File.separator + "main" + File.separator + "resources");
    Path results = Paths.get(resources + File.separator + "results");

    //este método leerá el csv línea a línea, filtrando los campos que necesitemos más adelante en función de qué csv sea.
    public String buildNewCSVcontent(String nameOfFile) {
        StringBuilder sBuilder = new StringBuilder();
        Path csv = Paths.get(resources + File.separator + nameOfFile);
        // ProcessorCSV pcsv = new ProcessorCSV();
        try {
            if (Files.exists(csv) && !Files.isDirectory(csv) && Files.isReadable(csv)) {
                Files.readAllLines(csv).stream().forEach(x -> sBuilder.append(/* pcsv.processCSV(nameOfFile, */x/*)*/));
            }
            else {
                System.out.println("'" + nameOfFile + "' does not exist, is a directory or is not readable.");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return sBuilder.toString();
    }

    // Copia un csv cuyo nombre le pases por parámetro de la carpeta resources a la carpeta copies.
    public void saveModifiedCSV(String nameOfFile, String content) {
        try {
            // esto guarda en la variable "csv" el path del archivo de la carpeta resources que queramos leer.
            Path csv = Paths.get(resources + File.separator + nameOfFile);
            Path csvCopia = Paths.get( results + File.separator + nameOfFile);
            // Crea una copia del archivo que haya en el path del primer parámetro en el path del segundo parámetro.
            // Si el archivo ya existe, lo reemplaza.
            Files.copy(csv, csvCopia, StandardCopyOption.REPLACE_EXISTING);
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
