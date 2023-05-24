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

    private JButton enterButton;
    private JTextField guessField;
    private JLabel attemptLabel;
    private JLabel pictureLabel;

    private ArrayList<Character> list;

    private Character currentCharacter;



    public UI() {
        createUIComponents();
        initializeList();
//        loadQuestion();
    }

    private void createUIComponents() {
        setTitle("Guess that character (A game for those one and above)");
        setSize(550, 200);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        enterButton.addActionListener(this);
        setVisible(true);
    }

//    private void loadQuestion() {
////        String str = list.get(0).getIconSrc();
//        System.out.println(str);
//        try {
//            URL imageURL = new URL(str);
//            BufferedImage image = ImageIO.read(imageURL);
//            ImageIcon icon = new ImageIcon(image);
//            pictureLabel.setIcon(icon);
//        } catch (IOException e) { }
//    }

    private void clear() {
        guessField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Enter")) {
                enterButton.setText("Enter");
            }
        }
    }

    private void initializeList() {
        list = new ArrayList<>();
       list.add(new Character("Pikachu", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png", "Add website"));
       list.add(new Character("Bowser", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Link", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Phoenix Wright", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Sonic", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Sans", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Zagreus", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Isabelle", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Lady D", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Among Us", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
       list.add(new Character("Sephiroth", "https://en.wikipedia.org/wiki/Pikachu#/media/File:Pok%C3%A9mon_Pikachu_art.png", "Add website"));
    }


}
