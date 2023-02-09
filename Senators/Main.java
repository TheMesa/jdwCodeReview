import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        File file = new File("senators.json");
        FileInputStream fileStream = new FileInputStream(file);
        GUI gui = new GUI(fileStream);
        gui.display();
    }
}
