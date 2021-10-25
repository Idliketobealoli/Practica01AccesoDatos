import filterClasses.CreateHTML;
import filterClasses.ProcessData;

public class App {
    public static void main(String[] args) {
        ProcessData pd = new ProcessData(args[0]);
        CreateHTML html = new CreateHTML(pd.desiredCity, args[1]);
    }
}
