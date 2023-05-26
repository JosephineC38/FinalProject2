import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class QuizUI extends JFrame implements ActionListener  {
    /*
     * The main Panel.
     */
    private JPanel mainPanel;

    /*
     * Used to enter guesses submitted by the guessField
    */
    private JButton enterButton;

    /*
     * Used to type in guesses.
    */
    private JTextField guessField;

    /*
      * Displays how many attempts the user has left.
      * Each question will have three attempts.
   */
    private JLabel attemptLabel;

    /*
     * Displays both the hidden and true icon of the character
    */
    private JLabel pictureLabel;

    /*
     * Displays the number of questions left.
     * There will be eleven questions.
     */
    private JLabel questionNumLabel;

    /*
     * Used to get the character index in the list.
     */
    private int characterIndex;

    /*
     * Used to get the character index in the list.
     */
    private int questionsCorrect;

    /*
     * Keeps track of the number of attempts the user has left.
     * Displayed to the user in attemptLabel.
     */
    private int count;

    /*
     * Keeps track of the current character the user must guess.
     */
    private Character currentCharacter;

    /*
     * Keeps track of the number of questions the user has left.
     * Displayed to the user in questionNumLabel.
     */
    private int questionNum;

    /*
     * Creates an array list of characters.
     */
    private ArrayList<Character> list;

    public QuizUI() {
        add(new JLabel("Quiz"));
        list = new ArrayList<>();
        questionNum = 1;
        questionsCorrect = 0;
        count = 1;
        characterIndex = (int) (Math.random() * list.size() - 1);
        createUIComponents();
        initializeList();
        loadQuestion();

    }

    private void createUIComponents() {
        mainPanel.setBackground(Color.PINK);
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
            System.out.println(characterIndex);
            endScreen(questionsCorrect);
        }
        else {
            characterIndex = (int) (Math.random() * list.size() - 1);
            System.out.println(characterIndex);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Enter")) {
                String guess = guessField.getText().toLowerCase();
                isQuestionCorrect(guess);
              //When the user has guessed correctly or ran out of attempts.
            } else if (button.getText().equals("Next")) {
                list.remove(characterIndex);
                clear();
                loadQuestion();
                enterButton.setText("Enter");
            }
        }
    }

    private void isQuestionCorrect(String guess) {
        count++;
        //If the user's case-insensitive answer is correct.
        if (guess.toLowerCase().equals(currentCharacter.getName().toLowerCase())) {
            questionsCorrect++;
            questionNum++;
            System.out.println("Correct");
            attemptLabel.setText("You are correct");
            questionNumLabel.setText("");
            enterButton.setText("Next");
            try {
                URL imageURL = new URL(currentCharacter.getIcon());
                BufferedImage image = ImageIO.read(imageURL);
                ImageIcon icon = new ImageIcon(image);
                pictureLabel.setIcon(icon);
            } catch (IOException e) { }
        } else {
            attemptLabel.setText("Attempt " + count + " of of 3");
            //If the user ran out of attempts.
            if (count == 4) {
                questionNum++;
                attemptLabel.setText("The correct character is: " + currentCharacter.getName() + ".");
                questionNumLabel.setText("");
                enterButton.setText("Next");
                try {
                    URL imageURL = new URL(currentCharacter.getIcon());
                    BufferedImage image = ImageIO.read(imageURL);
                    ImageIcon icon = new ImageIcon(image);
                    pictureLabel.setIcon(icon);
                } catch (IOException e) { }
            }
            System.out.println("Incorrect");

        }
    }

    /*
     * A helper method that displays the endScreen regardless of whatever or not the user won.
     */
    private void endScreen(int guesses) {
        enterButton.setVisible(false);
        guessField.setVisible(false);
        questionNumLabel.setText("");
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
        questionNumLabel.setText("Question " + questionNum + " out of 11 questions");
    }


    /*
     * A helper method used in the constructor. It will create a list containing eleven characters.
     */
    private void initializeList() {
        list.add(new Character("Pikachu", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
        list.add(new Character("Bowser", "https://mario.wiki.gallery/images/thumb/6/6c/MP9_Bowser_Jr_Artwork.png/800px-MP9_Bowser_Jr_Artwork.png", "https://mario.wiki.gallery/images/thumb/7/7d/MSOGT_Bowser.png/300px-MSOGT_Bowser.png"));
        list.add(new Character("Link", "https://www.zeldadungeon.net/wiki/images/thumb/d/d8/Link_-_TotK_key_art_nobg.png/400px-Link_-_TotK_key_art_nobg.png", "https://www.zelda.com/links-awakening/assets/img/home/hero-char.png"));
        list.add(new Character("Phoenix Wright", "https://static.wikia.nocookie.net/aceattorney/images/b/bd/Phoenix_Wright_Portrait_AA6.png/revision/latest?cb=20200120030240", "https://ae01.alicdn.com/kf/S1d09cdfbe7924875aaec669c1e02b85aS/1761-1762-Ace-Attorney-Miles-Edgeworth-Phoenix-Wright-Change-Face-Movable-Joints-Anime-Figure-PVC-Decor.jpg_Q90.jpg_.webp"));
        list.add(new Character("Sonic", "https://images.unsplash.com/photo-1472457897821-70d3819a0e24?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c21hbGx8ZW58MHx8MHx8fDA%3D&w=1000&q=80", "https://static.wikia.nocookie.net/character-stats-and-profiles/images/2/27/SFModernSonicRender.png/revision/latest/scale-to-width-down/400?cb=20171007052143"));
        list.add(new Character("Sans", "https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Sans_undertale.jpg/220px-Sans_undertale.jpg", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg"));
        list.add(new Character("Zagreus", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
        list.add(new Character("Isabelle", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
        list.add(new Character("Lady Dimitrescu", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
        list.add(new Character("Among Us", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
        list.add(new Character("Sephiroth", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://static.wikia.nocookie.net/pokemon/images/6/6c/Char-pikachu.png/revision/latest/scale-to-width/360?cb=20190430034300"));
    }
}
