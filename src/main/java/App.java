public class App {
    public static void main(String[] args) {
        LectorCSV lcsv = new LectorCSV();
        // lcsv.saveCSV("calidad_aire_datos_mes.csv");
        String fileCalidadAireDatosMes = lcsv.buildNewCSVcontent("calidad_aire_datos_mes.csv");
        lcsv.saveModifiedCSV("calidad_aire_datos_mes.csv", fileCalidadAireDatosMes);
    }
}
