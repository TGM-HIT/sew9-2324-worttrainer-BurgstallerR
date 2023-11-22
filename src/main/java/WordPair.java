/**
 * Eine Klasse welches ein Wort-Bild Paar darstellt  und auch vergleichen kann.
 *
 * @author Burgstaller Raffael
 * @version 10.11.2023
 */
public class WordPair {
    private String word;
    private String url;

    public WordPair(String word, String url){
        this.setWord(word);
        this.setUrl(url);
    }

    public WordPair(){
        this.setWord("Katze");
        this.setUrl("url");
    }

    public boolean compare(String userWord, String userUrl){
        if(userWord == null || userUrl == null) {
            return false;
        }
        return userWord.equalsIgnoreCase(word) && userUrl.equals(url);
    }

    public String getUrl() {
        return url;
    }

    public String getWord() {
        return word;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
