import filterClasses.CreateHTML;
import filterClasses.ProcessData;

public class App {
    public static void main(String[] args) {
        ProcessData pd = new ProcessData("Leganés");
        CreateHTML html = new CreateHTML(pd.desiredCity, "C:\\Users\\User\\Desktop\\pruebas");
    }
}
