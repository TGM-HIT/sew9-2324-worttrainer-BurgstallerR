import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Eine Klasse welche den WortTrainer Testet.
 *
 * @author Burgstaller Raffael
 * @version 22.11.2023
 */
public class WordTrainerTest {

    @Test
    public void compare_MatchingWordAndURL_ReturnsTrue() {
        WordPair pair = new WordPair("Katze", "https://example.com/cat.jpg");
        boolean result = pair.compare("Katze", "https://example.com/cat.jpg");
        Assertions.assertTrue(result);
    }

    @Test
    public void compare_NonMatchingWord_ReturnsFalse() {
        WordPair pair = new WordPair("Hund", "https://example.com/dog.jpg");
        boolean result = pair.compare("Katze", "https://example.com/dog.jpg");
        Assertions.assertFalse(result);
    }

    @Test
    public void compare_NonMatchingURL_ReturnsFalse() {
        WordPair pair = new WordPair("Hund", "https://example.com/dog.jpg");
        boolean result = pair.compare("Hund", "https://example.com/cat.jpg");
        Assertions.assertFalse(result);
    }

    @Test
    public void compare_CaseInsensitiveComparison_ReturnsTrue() {
        WordPair pair = new WordPair("Katze", "https://example.com/cat.jpg");
        boolean result = pair.compare("katze", "https://example.com/cat.jpg");
        Assertions.assertTrue(result);
    }

    @Test
    public void compare_NullURL_ReturnsFalse() {
        WordPair pair = new WordPair("Katze", "https://example.com/cat.jpg");
        boolean result = pair.compare("Katze", null);
        Assertions.assertFalse(result);
    }

    @Test
    public void compare_NullWord_ReturnsFalse() {
        WordPair pair = new WordPair("Katze", "https://example.com/cat.jpg");
        boolean result = pair.compare(null, "https://example.com/cat.jpg");
        Assertions.assertFalse(result);
    }

    @Test
    public void testSaveAndLoadWordTrainer() {
        WordTrainer wordTrainer = new WordTrainer();
        wordTrainer.setCorrectTries(5);
        wordTrainer.setWrongTries(3);
        wordTrainer.saveWordTrainer();
        WordTrainer newWordTrainer = new WordTrainer();
        newWordTrainer.loadWordTrainer();

        Assertions.assertEquals(5, newWordTrainer.getCorrectTries());
        Assertions.assertEquals(3, newWordTrainer.getWrongTries());
    }

}
