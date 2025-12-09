package pt.ulusofona.lp2.greatprogrammingjourney;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGame {
    public static File SaveGameFile(){
        try (FileWriter myWriter = new FileWriter("filename.txt", true)) {
            myWriter.write("\nAppended text!");
            System.out.println("Successfully appended to the file.");
        } catch (IOException E) {

        }
        return new File("oi");
    }
}
