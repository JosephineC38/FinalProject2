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


    private JLabel optionLabel;

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
        text = "Hi, I'm Yu, a normal highschooler. One day, I hope to fall in love";
        speaker = "Yu";
        changeText();

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
        nextButton = new JButton();
        nextButton.setText("NEXT");
        nextButton.setBounds(1225, 650, 300, 100);
        nextButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        nextButton.addActionListener(this);
        visualNovelFrame.add(nextButton);

        //Setting up the dialogue
        dialoguePanel = new JPanel();
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        dialogueText = new JLabel(dialogueBoxIcon);
        dialoguePanel.setBackground(Color.PINK);
        dialoguePanel.setBounds(0, 575, 1200, 235);
        dialoguePanel.setLayout(new BorderLayout());
        changeText();
        dialoguePanel.add(dialogueText);
        visualNovelFrame.add(dialoguePanel);


        //Setting the options
        JPanel optionPanel = new JPanel();
        optionPanel.setBounds(1200, 59, 400, 400);
        optionPanel.setBackground(Color.PINK);
        optionPanel.setLayout(new BorderLayout());
        visualNovelFrame.add(optionPanel);

        visualNovelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualNovelFrame.setLayout(null);
        visualNovelFrame.setSize(1000, 700);
        visualNovelFrame.setLocation(450, 100);


        //Setting up the frame background
        ImageIcon titleIcon = new ImageIcon("napoleonIcon.png");
        visualNovelFrame.setIconImage(titleIcon.getImage());
        visualNovelFrame.setTitle("Win Napoleon's Heart");
        visualNovelFrame.getContentPane().setBackground(Color.CYAN);
        visualNovelFrame.setVisible(true);


    }



    public void changeText() {
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        dialogueText = new JLabel(dialogueBoxIcon) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                dialogueText.setFont(new Font("Calibri", Font.PLAIN, 30));
                g.drawString(text, 100, 150); //these are x and y positions
                if(speaker.equals("Yu")) {
                    g.drawString("Yu", 50, 37);
                } else if (speaker.equals("Napoleon")) {
                    g.drawString("Napoleon", 50, 37);
                }
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("NEXT")) {
                System.out.println("hello");
                count++;
                loadQuestion(count);
            }
        }
    }


    public void loadQuestion(int questionNum) {

        switch(questionNum) {
            case 1:
                speaker = "Yu";
                text = "But right now, I'm running late to school";
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

}





