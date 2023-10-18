import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class WordTrainer {
    private ArrayList<WordPair> pairList = new ArrayList<WordPair>();
    private int correctTries;
    private int wrongTries;

    public WordTrainer() {
        pairList.add(new WordPair("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/1200px-Hauskatze_langhaar.jpg"));
        pairList.add(new WordPair("Hund", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg/450px-Harzer_Fuchs_H%C3%BCndin_2.jpg"));
        pairList.add(new WordPair("Giraffe", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Giraffen.jpg/450px-Giraffen.jpg"));
        pairList.add(new WordPair("Adler", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Kaiseradler_Aquila_heliaca_e_amk.jpg/330px-Kaiseradler_Aquila_heliaca_e_amk.jpg"));
    }

    public void training() {
        boolean guess = false;
        Random r =  new Random();
        do{
            int rint = r.nextInt(pairList.size());
            System.out.println(pairList.get(rint).getUrl());
            String t = JOptionPane.showInputDialog("Welches Tier sehen sie auf dem Bild: ");

            if(t.equalsIgnoreCase(pairList.get(rint).getWord())){
                correctTries += 1;
                guess = true;
            }else{
                wrongTries += 1;
            }
        }while(!guess);
    }
}
