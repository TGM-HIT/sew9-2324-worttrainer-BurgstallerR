import java.util.ArrayList;

/**
 * Ein Interface welches für ein Strategy Pattern verwendet wird.
 *
 * @author Burgstaller Raffael
 * @version 22.11.2023
 */
public interface SaveStrategy {
    WordTrainer load();
    void save(int cTries, int wTries, ArrayList<WordPair> words);
}
