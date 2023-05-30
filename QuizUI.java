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
        ImageIcon titleIcon = new ImageIcon("piegonIcon.png");
        setIconImage(titleIcon.getImage());
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
        list.add(new Character("Pikachu", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-blue-version/8/89/Pikachu.jpg", "https://w7.pngwing.com/pngs/248/960/png-transparent-pikachu-pokemon-go-silhouette-drawing-pikachu-dog-like-mammal-fictional-character-black.png"));
        list.add(new Character("Bowser", "https://static.wikia.nocookie.net/neoencyclopedia/images/e/ec/Bowser_-_New_Super_Mario_Bros_2.png/revision/latest/zoom-crop/width/500/height/500?cb=20121026104906", "https://www.pngitem.com/pimgs/m/398-3987614_king-koopa-for-png-transparent-clipart-for-download.png"));
        list.add(new Character("Link", "https://www.zelda.com/links-awakening/assets/img/home/hero-char.png", "https://prod-cdn-08.storenvy.com/product_photos/84818917/file_3154c168c3_original.png"));
        list.add(new Character("Phoenix Wright", "https://upload.wikimedia.org/wikipedia/en/d/d8/PhoenixWright.png", "https://ih1.redbubble.net/image.875019704.1525/flat,750x1000,075,f.jpg"));
        list.add(new Character("Sonic", "https://static.wikia.nocookie.net/character-stats-and-profiles/images/1/1f/Sonic_Speed_in_Sonic_X_by_Strunton.png/revision/latest/scale-to-width-down/400?cb=20170811050412", "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/323faa6e-45f9-4189-826b-caa11bffdc4d/da49sgl-a166af20-53cf-4280-8b89-6b46b0cb69c3.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzMyM2ZhYTZlLTQ1ZjktNDE4OS04MjZiLWNhYTExYmZmZGM0ZFwvZGE0OXNnbC1hMTY2YWYyMC01M2NmLTQyODAtOGI4OS02YjQ2YjBjYjY5YzMucG5nIn1dXS" +
                "wiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.sE60qt--tMbX18VlkobfvbOZnSST9Y3sP9Kr1SCsLLs"));
        list.add(new Character("Sans", "https://upload.wikimedia.org/wikipedia/en/thumb/0/01/Sans_undertale.jpg/220px-Sans_undertale.jpg", "https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/64e3ba9c82b55af.png"));
        list.add(new Character("Zagreus", "https://www.voquent.com/blog/wp-content/uploads/2023/02/Zagreus500.jpg", "https://preview.redd.it/0305jlf8wom61.png?width=640&crop=smart&auto=webp&s=7e3de572faea5ba6df9e3f44e2632fb184e917d7"));
        list.add(new Character("Isabelle", "https://ssb.wiki.gallery/images/2/26/Isabelle_AF.png", "https://cdn.discordapp.com/attachments/414922651392540674/1112093684998086746/Isabelle_AF2.png"));
        list.add(new Character("Lady Dimitrescu", "https://www.sideshow.com/cdn-cgi/image/width=500,quality=80,f=auto/https://www.sideshow.com/storage/product-images/911462/lady-dimitrescu_resident-evil_silo_md.png", "https://cdn.discordapp.com/attachments/414922651392540674/1112094332137250948/lady-dimitrescu_resident-evil_silo_md2.png"));
        list.add(new Character("Among Us", "https://static.wikia.nocookie.net/among-us-wiki/images/3/31/Red.png/revision/latest?cb=20230410034616", "https://www.citypng.com/public/uploads/preview/-41605243588a1dgdkbgqx.png"));
        list.add(new Character("Sephiroth", "https://cdn.mos.cms.futurecdn.net/URoTVKfVpgMdB2mtFnAY5o-320-80.jpg", "https://m.media-amazon.com/images/I/411M1-mQl6L._SR600%2C315_PIWhiteStrip%2CBottomLeft%2C0%2C35_SCLZZZZZZZ_FMpng_BG255%2C255%2C255.jpg"));
    }
}
