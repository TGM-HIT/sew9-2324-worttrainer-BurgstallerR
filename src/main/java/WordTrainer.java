import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    public String gui(String url) {
        URL imgURL;
        try {
            imgURL = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        Image originalImage = null;
        try {
            originalImage = ImageIO.read(imgURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        JTextField input = new JTextField(20);
        String statisticText = "CorrectTries: " +correctTries +"\nWrongTries: " +wrongTries;

        Object[] components = {icon, statisticText, "Input:", input};

        int auswahl = JOptionPane.showConfirmDialog(null, components, "Wortspiel", JOptionPane.OK_CANCEL_OPTION);

        if (auswahl == JOptionPane.OK_OPTION) {
            String eingabe = input.getText();
            return eingabe;
        }
        return "";
    }

    public void training() {
        Random r = new Random();
        boolean running = true;
        do {
            boolean guess = false;
            int rint = r.nextInt(pairList.size());
            String currentURL = pairList.get(rint).getUrl();
            String t;
            do {
                t = gui(currentURL);
                if (pairList.get(rint).compare(t, pairList.get(rint).getUrl())) {
                    correctTries += 1;
                    guess = true;
                } else {
                    wrongTries += 1;
                }
                if(t.equals("")) {
                    running = false;
                }
            }while(!guess && running);
        }while(running);
    }
}
