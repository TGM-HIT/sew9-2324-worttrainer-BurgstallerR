import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Eine Klasse welche für das Laden und Speicher aus bzw in ein JSON File Benutzt wird. Dabei wird eine Strategy Pattern verwendet.
 *
 * @author Burgstaller Raffael
 * @version 22.11.2023
 */
public class SaveJson implements SaveStrategy {
    String filePath = "src/main/java/stats.json";
    @Override
    public int[] load() {
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject jsonObj = new JSONObject(tokener);

            // Zugriff auf Daten im JSONObject
            int cTries = jsonObj.getInt("CorrectTries");
            int wTries = jsonObj.getInt("WrongTries");

            return new int[]{cTries, wTries};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[]{-1,-1};
    }

    @Override
    public void save(int cTries, int wTries) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("CorrectTries", cTries);
        jsonObj.put("WrongTries", wTries);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObj.toString());
            System.out.println("Statistik gespeichert");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
