import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class UI extends JFrame implements ActionListener  {
    /*
    For the GUI
    */
    private JButton enterButton;
    private JTextField guessField;
    private JLabel attemptLabel;
    private JLabel pictureLabel;
    private JPanel mainPanel;
    private int characterIndex;
    private int questionsCorrect;
    private int count;

    //Creates a list of characters
    private ArrayList<Character> list;

    private Character currentCharacter;

    public UI() {
        characterIndex = 0;
        questionsCorrect = 0;
        count = 1;
        createUIComponents();
        initializeList();
        loadQuestion();
    }

    private void createUIComponents() {
        setContentPane(mainPanel);
        setTitle("Guess that character!!!");
        setSize(1000, 700);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        enterButton.addActionListener(this);
        setVisible(true);
    }

    private void loadQuestion() {
        if (list.size() == 0) {
            endScreen(questionsCorrect);
        }
        currentCharacter = list.get(characterIndex);
        String str = currentCharacter.getHiddenIcon();
        System.out.println(str);
        try {
            URL imageURL = new URL(str);
            BufferedImage image = ImageIO.read(imageURL);
            ImageIcon icon = new ImageIcon(image);
            pictureLabel.setIcon(icon);
        } catch (IOException e) { }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Enter")) {
                String guess = guessField.getText().toLowerCase();
                isQuestionCorrect(guess);
            } else if (button.getText().equals("Next")) {
                clear();
                loadQuestion();
                enterButton.setText("Enter");
            }
        }
    }

    public void isQuestionCorrect(String guess) {
       count++;
       attemptLabel.setText("Attempt " + count + " of of 3");
       if (guess.toLowerCase().equals(currentCharacter.getName().toLowerCase())) {
           questionsCorrect++;
           System.out.println("Correct");
           attemptLabel.setText("You are correct");
           enterButton.setText("Next");
           try {
               URL imageURL = new URL(currentCharacter.getIcon());
               BufferedImage image = ImageIO.read(imageURL);
               ImageIcon icon = new ImageIcon(image);
               pictureLabel.setIcon(icon);
           } catch (IOException e) { }
           characterIndex++;

       } else {
           if (count == 4) {
               attemptLabel.setText("The correct character is: " + currentCharacter.getName() + ".");
               enterButton.setText("Next");
               try {
                   URL imageURL = new URL(currentCharacter.getIcon());
                   BufferedImage image = ImageIO.read(imageURL);
                   ImageIcon icon = new ImageIcon(image);
                   pictureLabel.setIcon(icon);
               } catch (IOException e) { }
               characterIndex++;
           }
          System.out.println("Incorrect");
       }
    }

    public void endScreen(int guesses) {
        switch (guesses) {
            case 15:
                attemptLabel.setText("So cool!!! 15/15");
                break;
            default:
                attemptLabel.setText("Mid.");
                break;
        }
    }

    private void clear() {
        count = 1;
        guessField.setText("");
        attemptLabel.setText("Attempt " + count + " of of 3");
        }

    private void initializeList() {
       // String name, String icon, String hiddenIcon
       list = new ArrayList<>();
       list.add(new Character("Pikachu", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
       list.add(new Character("Bowser", "https://mario.wiki.gallery/images/thumb/6/6c/MP9_Bowser_Jr_Artwork.png/800px-MP9_Bowser_Jr_Artwork.png", "https://mario.wiki.gallery/images/thumb/7/7d/MSOGT_Bowser.png/300px-MSOGT_Bowser.png"));
       list.add(new Character("Link", "https://www.zeldadungeon.net/wiki/images/thumb/d/d8/Link_-_TotK_key_art_nobg.png/400px-Link_-_TotK_key_art_nobg.png", "https://www.zelda.com/links-awakening/assets/img/home/hero-char.png"));
       list.add(new Character("Phoenix Wright", "https://static.wikia.nocookie.net/aceattorney/images/b/bd/Phoenix_Wright_Portrait_AA6.png/revision/latest?cb=20200120030240", "https://ae01.alicdn.com/kf/S1d09cdfbe7924875aaec669c1e02b85aS/1761-1762-Ace-Attorney-Miles-Edgeworth-Phoenix-Wright-Change-Face-Movable-Joints-Anime-Figure-PVC-Decor.jpg_Q90.jpg_.webp"));
       list.add(new Character("Sonic", "https://static.wikia.nocookie.net/sonic/images/4/47/TheFrontiersSonic.png/revision/latest?cb=20220725073416", "https://static.wikia.nocookie.net/character-stats-and-profiles/images/2/27/SFModernSonicRender.png/revision/latest/scale-to-width-down/400?cb=20171007052143"));
       list.add(new Character("Sans", "https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Sans_undertale.jpg/220px-Sans_undertale.jpg", "https://imgs2.goodsmileus.com/image/cache/data/productimages/Plush/AceAttorney_PlushieDoll_PhoenixWright-MilesEdgeworth-MayaFey/01_2303301542021004-1200x1200.jpg"));
       list.add(new Character("Zagreus", "https://static.wikia.nocookie.net/hades_gamepedia_en/images/2/29/Zagreus.png/revision/latest?cb=20181210044005", "https://static.wikia.nocookie.net/vsbattles/images/2/29/Zagreus.png/revision/latest?cb=20210314181850"));
       list.add(new Character("Isabelle", "https://mario.wiki.gallery/images/thumb/2/2b/Isabelle_SSBU.png/1200px-Isabelle_SSBU.png", "https://static.wikia.nocookie.net/animalcrossing/images/9/95/Isabelle_NH.png/revision/latest?cb=20200610024111"));
       list.add(new Character("Lady Dimitrescu", "https://upload.wikimedia.org/wikipedia/en/e/e2/Lady_Dimitrescu.png", "https://images.pushsquare.com/af3fc586f42d7/lady-dimitrescu-resident-evil-village-ps5-1.large.jpg"));
       list.add(new Character("Among Us", "https://play-lh.googleusercontent.com/8ddL1kuoNUB5vUvgDVjYY3_6HwQcrg1K2fd_R8soD-e2QYj8fT9cfhfh3G0hnSruLKec", "https://www.citypng.com/public/uploads/preview/-41605243588a1dgdkbgqx.png"));
       list.add(new Character("Sephiroth", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "https://m.media-amazon.com/images/I/411M1-mQl6L._AC_UF894,1000_QL80_.jpg"));
    }


}
