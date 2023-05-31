import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class VisualNovelUI extends JFrame implements ActionListener {

    private JFrame visualNovelFrame;

    private JPanel dialoguePanel;

    private JLabel dialogueText;

    private JLabel napoleonSprite;


    private JLabel optionLabel1;

    private JLabel optionLabel2;

    private JLabel optionLabel3;

    private JTextField optionTextField;

    private JButton optionButton;


    private JButton nextButton;

    private int count;

    private String text;

    private String speaker;


    public VisualNovelUI() {
        createUIComponents();
    }

    public void createUIComponents() {
        visualNovelFrame = new JFrame();
        count = 0;
        text = "Hi, I'm Yu, a normal high-schooler. One day, I hope to fall in love.";
        speaker = "Yu";
        changeText();

        //Setting the background
        ImageIcon background = new ImageIcon("testBackground.png");
        BackgroundPanel backgroundPanel = new BackgroundPanel(background.getImage());
        visualNovelFrame.setContentPane(backgroundPanel);


        //Setting up Napoleon Sprite
        napoleonSprite = new JLabel();
        napoleonSprite.setBounds(0,0,474, 627);
        ImageIcon napoleonSpriteIcon = new ImageIcon("napoleonSprite.jfif");
        napoleonSprite.setIcon(napoleonSpriteIcon);
        napoleonSprite.setVisible(false);
        visualNovelFrame.add(napoleonSprite);

        //Setting up the button
        ImageIcon nextButtonIcon = new ImageIcon("nextButton.png");
        nextButton = new JButton(nextButtonIcon);
        nextButton.setText("NEXT");
        nextButton.setBounds(1200, 750, 250, 100);
        nextButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        nextButton.addActionListener(this);
        visualNovelFrame.add(nextButton);

        //Setting up the dialogue
        dialoguePanel = new JPanel();
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        dialogueText = new JLabel(dialogueBoxIcon);
        dialoguePanel.setBackground(Color.PINK);
        dialoguePanel.setBounds(0, 700, 1200, 235);
        dialoguePanel.setLayout(new BorderLayout());
        changeText();
        dialoguePanel.add(dialogueText);
        visualNovelFrame.add(dialoguePanel);

        // Setting the options
        JPanel optionPanel = new JPanel();

        optionPanel.setBackground(Color.PINK);
        optionPanel.setLayout(new BorderLayout());
        visualNovelFrame.add(optionPanel);
        visualNovelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualNovelFrame.setLayout(null);
        visualNovelFrame.setSize(1000, 700);
        visualNovelFrame.setLocation(450, 100);

        optionPanel.setBounds(1200, 60, 500, 400);
        optionLabel1 = new JLabel();
        optionLabel2 = new JLabel();
        optionLabel3 = new JLabel();
        optionButton = new JButton();
        optionTextField = new JTextField();
        optionLabel1.setBackground(Color.BLUE);
        optionLabel2.setBackground(Color.RED);
        optionLabel3.setBackground(Color.GREEN);
        optionButton.setText("ENTER");
        optionLabel1.setBounds(0, 50, 500, 100);
        optionLabel2.setBounds(0, 150, 500, 100);
        optionLabel3.setBounds(0, 300, 500, 100);

        optionButton.setBounds(1450, 460, 250, 100);
        optionButton.addActionListener(this);
        optionTextField.setBounds(1200, 460, 100, 100);
        optionTextField.setVisible(false);

        optionPanel.add(optionLabel1);
        optionPanel.add(optionLabel2);
        optionPanel.add(optionLabel3);
        visualNovelFrame.add(optionButton);
        visualNovelFrame.add(optionTextField);

        //Setting up the frame background
        ImageIcon titleIcon = new ImageIcon("napoleonIcon.png");
        visualNovelFrame.setIconImage(titleIcon.getImage());
        visualNovelFrame.setTitle("Win Napoleon's Heart");
        visualNovelFrame.setVisible(true);
    }

    public void changeText() {
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        dialogueText = new JLabel(dialogueBoxIcon) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dialogueText.setFont(new Font("Calibri", Font.PLAIN, 30));
                g.drawString(text, 100, 150); //these are x and y positions
                if (speaker.equals("Yu")) {
                    g.drawString("Yu", 50, 37);
                } else if (speaker.equals("Napoleon")) {
                    g.drawString("Napoleon", 50, 37);
                }
            }
        };
    }

    public void setUpOptions(String o1, String o2, String o3) {
        optionLabel1.setText("Option 1: " + o1);
        optionLabel2.setText("Option 2: " + o2);
        optionLabel3.setText("Option 3: " + o3);
        optionTextField.setVisible(true);
        setOptionsVisible(true);
        nextButton.setVisible(false);
    }

    public void setOptionsVisible(boolean statement) {
        optionLabel1.setVisible(statement);
        optionLabel2.setVisible(statement);
        optionLabel3.setVisible(statement);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("NEXT")) {
                count++;
                loadQuestion(count);
            } else if (button.getText().equals("ENTER")) {
                loadOption(count, Integer.parseInt(optionTextField.getText()));
            }
        }
    }


    public void loadQuestion(int questionNum) {
        switch(questionNum) {
            case 1:
                speaker = "Yu";
                text = "But right now, I'm running late to school";
                setUpOptions("DRINK ORANGE JUICE", "COMBUST", "smile");
                break;
            case 2:
                text = "I still have to start that project but I wonder when--OOF";
                break;
            case 3:
                speaker = "Napoleon";
                text = "Are you alright?";
                break;
            case 4:
                speaker = "Yu";
                text = "This is...";
                setUpOptions("TEST1", "TEST2", "TEST3");
                break;
            case 5:
                text = "Napoleon, the transfer student";
                napoleonSprite.setVisible(true);
                break;
            case 6:
                text = "Of course, I'm alright Napoleon. Th-thanks for watching after me";
                break;
            case 7:
                speaker = "Napoleon";
                text = "It was my pleasure.";
                break;
            case 8:
                text = "By the way, I heard we were partners for the history project";
                break;
            case 9:
                speaker = "Yu";
                text = "Partners with Napoleon?";

                break;
            default:
                System.out.println("Oh no, a code problem");
        }
        repaint();
    }

    public void loadOption(int count, int choice) {
        switch (count) {
            case 1:
                switch (choice) {
                    case 1:
                        text = "drinking orange juice...";
                        break;
                    case 2:
                        text = "combusting...";
                        break;
                    case 3:
                        text = "smiling...";
                        break;
                    default:
                        System.out.println("LOAD OPTION ERROR 2.");
                }
                break;
            case 4:
                break;
            default:
                System.out.println("LOAD OPTION ERROR 1.");
                break;
        }
    }

}





