import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Eine Klasse welche für das Laden und Speicher aus bzw in ein JSON File Benutzt wird. Dabei wird eine Strategy Pattern verwendet.
 *
 * @author Burgstaller Raffael
 * @version 22.11.2023
 */
public class SaveJson implements SaveStrategy {
    String filePath = "src/main/java/stats.json";
    @Override
    public WordTrainer load() {
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject jsonObj = new JSONObject(tokener);

            // Zugriff auf Daten im JSONObject
            int cTries = jsonObj.getInt("CorrectTries");
            int wTries = jsonObj.getInt("WrongTries");

            JSONArray wordsArray = jsonObj.getJSONArray("WordPairs");
            ArrayList<WordPair> words = new ArrayList<>();

            for (int i = 0; i < wordsArray.length(); i++) {
                JSONObject wordObject = wordsArray.getJSONObject(i);
                String word = wordObject.getString("word");
                String url = wordObject.getString("url");
                words.add(new WordPair(word, url));
            }

            return new WordTrainer(words, cTries, wTries);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new WordTrainer(new ArrayList<>(), -1, -1);
    }

    @Override
    public void save(int cTries, int wTries, ArrayList<WordPair> words) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("CorrectTries", cTries);
        jsonObj.put("WrongTries", wTries);

        JSONArray wordsArray = new JSONArray();
        for (WordPair wordPair : words) {
            JSONObject wordObject = new JSONObject();
            wordObject.put("word", wordPair.getWord());
            wordObject.put("url", wordPair.getUrl());
            wordsArray.put(wordObject);
        }
        jsonObj.put("WordPairs", wordsArray);


        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObj.toString());
            System.out.println("Statistik gespeichert");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
