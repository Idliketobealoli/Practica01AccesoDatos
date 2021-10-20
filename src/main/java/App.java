public class App {
    public static void main(String[] args) {
        LectorCSV lcsv = new LectorCSV();
        // lcsv.saveCSV("calidad_aire_datos_mes.csv");
        String fileCalidadAireDatosMes = lcsv.buildNewCSVcontent("calidad_aire_datos_mes.csv", "Parla");
        lcsv.saveModifiedCSV("calidad_aire_datos_mes.csv", fileCalidadAireDatosMes);
        System.out.println(lcsv.readCSV("copia_calidad_aire_datos_mes.csv"));
    }
}
