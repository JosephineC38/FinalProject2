import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class VisualNovelUI extends JFrame implements ActionListener {

    private JFrame visualNovelFrame;

    private JPanel dialoguePanel;

    private JPanel optionPanel;

    private JLabel dialogueText;

    private JLabel napoleonSprite;

    private JLabel louisSprite;

    /*
     * The optionLabel for each of the three choices.
     */
    private JLabel optionLabel1;

    private JLabel optionLabel2;

    private JLabel optionLabel3;

    /*
     * The user uses this to input their choices.
     */
    private JTextField optionTextField;

    /*
     * After the user enters their options, this button is used to submit them.
     */
    private JButton optionButton;

    private JButton nextButton;

    /*
     * Used to move the text forward.
     */
    private int count;

    private String text;

    private String speaker;

    private int napoleonAffectionPoints;

    /*
     * Used to change the background of the frame and uses the BackgroundPanel class to do so.
     */
    private BackgroundPanel backgroundPanel;


    public VisualNovelUI() {
        visualNovelFrame = new JFrame();
        try {
           createUIComponents();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * A private helper method that sets up and initializes the visualNovelFrame.
     * It is used in the constructor.
     */
    private void createUIComponents() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // variables set up
        napoleonAffectionPoints = 0;
        count = 0;
        text = "Hi, I'm Yu, a normal high-school student. One day, I hope to fall in love.";
        speaker = "Yu";
        changeText();


//        File file = new File("schoolbellSound.wav");
//        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioStream);
//        clip.start(); //test it works

        // backgroundPanel
        ImageIcon schoolgrounds = new ImageIcon("schoolgroundsBackground.png");
        backgroundPanel = new BackgroundPanel(schoolgrounds.getImage());
        visualNovelFrame.setContentPane(backgroundPanel);


        // napoleonSprite
        napoleonSprite = new JLabel();
        napoleonSprite.setBounds(0,0,720, 960);
        setNapoleonSprite("default");
        napoleonSprite.setVisible(false);
        visualNovelFrame.add(napoleonSprite);

        // nextButton
        ImageIcon nextButtonIcon = new ImageIcon("nextButton.png");
        nextButton = new JButton(nextButtonIcon);
        nextButton.setText("NEXT");
        nextButton.setBounds(1310, 750, 200, 134);
        nextButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        nextButton.addActionListener(this);
        visualNovelFrame.add(nextButton);

        // dialoguePanel and dialogueText
        dialoguePanel = new JPanel();
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        dialogueText = new JLabel(dialogueBoxIcon);
        Image newImage = dialogueBoxIcon.getImage().getScaledInstance(1400, 235, Image.SCALE_DEFAULT); //change dialouge box here
        ImageIcon dialogueBoxIconScaled = new ImageIcon(newImage);
        dialogueText = new JLabel(dialogueBoxIconScaled);
        dialoguePanel.setBackground(Color.PINK);
        dialoguePanel.setBounds(0, 700, 1300, 235); // for school coumpters
        //dialoguePanel.setBounds(0, 575, 1300, 235);
        dialoguePanel.setLayout(new BorderLayout());
        changeText();
        dialoguePanel.add(dialogueText);
        visualNovelFrame.add(dialoguePanel);

        // optionPanel
        optionPanel = new JPanel();
        optionPanel.setBackground(Color.PINK);
        optionPanel.setLayout(new BorderLayout());
        visualNovelFrame.add(optionPanel);


        // optionPanel, optionLabels, optionButton, and optionTextField
        optionPanel.setBounds(1200, 60, 500, 400);
        optionLabel1 = new JLabel();
        optionLabel2 = new JLabel();
        optionLabel3 = new JLabel();
        optionButton = new JButton();
        optionTextField = new JTextField();
        optionButton.setText("ENTER");
        optionLabel1.setBounds(0, 50, 500, 100);
        optionLabel2.setBounds(0, 250, 500, 100);
        optionLabel3.setBounds(0, 300, 500, 100);

        optionButton.setBounds(1450, 460, 250, 100);
        optionButton.addActionListener(this);
        optionTextField.setBounds(1200, 460, 100, 100);
        optionTextField.setVisible(false);

        // adding the option panel/button/text-field
        optionPanel.add(optionLabel1);
        optionPanel.add(optionLabel2);
        optionPanel.add(optionLabel3);
        visualNovelFrame.add(optionButton);
        visualNovelFrame.add(optionTextField);
        setOptionsVisible(false);

        // enter name
        nameTextField = new JTextField();
        nameTextField.setText("Maximum 9 characters");
        nameTextField.setBounds(100,150, 400, 135);
        nameTextField.setVisible(true);
        visualNovelFrame.add(nameTextField);

        enterButton = new JButton();
        enterButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        enterButton.addActionListener(this);
        enterButton.setText("Enter Name");
        enterButton.setBounds(550,150, 150, 40);
        visualNovelFrame.add(enterButton);

        ImageIcon titleIcon = new ImageIcon("napoleonIcon.png");
        visualNovelFrame.setIconImage(titleIcon.getImage());
        visualNovelFrame.setTitle("Win Napoleon's Heart");
        visualNovelFrame.setSize(1000, 700);
        visualNovelFrame.setLocation(450, 100);
        visualNovelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualNovelFrame.setLayout(null);
        visualNovelFrame.setVisible(true);
    }

    /*
     * A private helper method that changes the dialogueText and the speaker
     */
    private void changeText() {
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        Image newImage = dialogueBoxIcon.getImage().getScaledInstance(1300, 235, Image.SCALE_DEFAULT); //change dialouge box here
        ImageIcon dialogueBoxIconScaled = new ImageIcon(newImage);
        dialogueText = new JLabel(dialogueBoxIconScaled) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dialogueText.setFont(new Font("Calibri", Font.BOLD, 30));
                g.drawString(text, 30, 150);
                if (speaker.equals("Yu")) {
                    g.drawString("Yu", 50, 37);
                } else if (speaker.equals("Napoleon")) {
                    g.drawString("Napoleon", 50, 37);
                } else if (speaker.equals("Mr. Miller")) { //hi there Mr. Miller
                    g.drawString("Mr. Miller", 50, 37);
                }
            }
        };
    }

    /*
     * A private helper method that sets up the options and disables the next buttons.
     * optionLabels' 2 and 3 being switched is a feature not a bug.
     */
    private void setUpOptions(String o1, String o2, String o3) {
        optionLabel1.setText("Option 1: " + o1);
        optionLabel3.setText("Option 2: " + o2);
        optionLabel2.setText("Option 3: " + o3);
        setOptionsVisible(true);
        nextButton.setVisible(false);
    }

    /*
     * A private helper method that changes the option labels, panel and button visibility.
     */
    private void setOptionsVisible(boolean statement) {
        optionPanel.setVisible(statement);
        optionButton.setVisible(statement);
        optionTextField.setVisible(statement);
        optionLabel1.setVisible(statement);
        optionLabel2.setVisible(statement);
        optionLabel3.setVisible(statement);
    }

    /*
     * A private helper method that takes in a String to switch the background.
     */
    private void switchBackground(String background) {
        if(background.equals("schoolgrounds")) {
            ImageIcon schoolgrounds = new ImageIcon("schoolgroundsBackground.png");
            backgroundPanel.setImage(schoolgrounds.getImage());
        } else if (background.equals("hallway")) {
            ImageIcon hallway = new ImageIcon("hallwayBackground.png");
            backgroundPanel.setImage(hallway.getImage());
        } else if (background.equals("classroom")) {
            ImageIcon classroom = new ImageIcon("classroomBackground.JPG");
            backgroundPanel.setImage(classroom.getImage());
        }
        visualNovelFrame.setContentPane(backgroundPanel);
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

                int option = Integer.parseInt(optionTextField.getText());
                if (option == 1 || option == 2 || option == 3) {
                    loadOption(count, option);
                    nextButton.setVisible(true);
                } else {
                    optionTextField.setText("");
                }
            }
        }
    }

    private void checkName() {
        String enteredName = nameTextField.getText();
        while(enteredName.length() <= 9) {
            nameTextField.setText("");
        }
    }

    /*
     * A private helper method that sets napoleonSprite to the selected image.
     */
    private void setNapoleonSprite(String emotion) {
        if(emotion.equals("default")) {
            ImageIcon napoleonDefaultSprite = new ImageIcon("napoleonDefaultSprite.JPG");
            napoleonSprite.setIcon(napoleonDefaultSprite);
        } else if (emotion.equals("happy")) {
            ImageIcon napoleonHappySprite = new ImageIcon("napoleonHappySprite.JPG");
            napoleonSprite.setIcon(napoleonHappySprite);
        } else if (emotion.equals("angry")) {
            ImageIcon napoleonAngrySprite = new ImageIcon("napoleonAngrySprite.JPG");
            napoleonSprite.setIcon(napoleonAngrySprite);
        }
    }

    /*
     * A private helper method that displays the current text.
     */
    public void loadQuestion(int questionNum) {
        switch(questionNum) {
            case 1:
                speaker = "Yu";
                text = "But right now, I'm running late to school.";
                break;
            case 2:
                text = "I wonder who will be my partner for my pr- OOF";
                break;
            case 3:
                speaker = "Napoleon";
                text = "Ugh! You peasant! How dare you bump into me???";
                break;
            case 4:
                speaker = "Yu";
                text = "This is...";
                break;
            case 5:
                text = "Napoleon, the transfer student!";
                napoleonSprite.setVisible(true);
                setNapoleonSprite("angry");
                break;
            case 6:
                text = "O-oh, I'm so sorry! Are you okay?";
                break;
            case 7:
                speaker = "Napoleon";
                text = "I'm fine! Look where you're going next time, you fishcake.";
                setNapoleonSprite("default");
                break;
            case 8:
                text = "By the way, I heard we were partners for the history project in Mr. Miller's class.";
                break;
            case 9:
                speaker = "Yu";
                text = "Partners with Napoleon?";
                setUpOptions("Is this a dream come true?", "You disgusting birch tree.", "Wow, uh, cool...");
                break;
            case 10:
                speaker = "Yu";
                text = "But wait, weren't the partners were going to be announced today, how do you already know?";
                break;
            case 11:
                speaker = "Napoleon";
                text = "My persuasive skills are top notch, used to overthrow governments before.";
                break;
            case 12:
                speaker = "Yu";
                text = "That explains nothing!";
                break;
            case 13:
                speaker = "Yu";
                text = "You know what, whatever. Maybe we should discuss what our history topic should be.";
                break;
            case 14:
                speaker = "Napoleon";
                text = "The French Revolution, of course! What other topic is as majestic as the fall of the monarchy?";
                setNapoleonSprite("happy");
                break;
            case 15:
                speaker = "Yu";
                text = "...You seen very passionate about this.";
                break;
            case 16:
                speaker = "Napoleon";
                text = "Do you dare imply that the revolution is not worth your time. Why, as a youn-";
                setNapoleonSprite("angry");
                break;
            case 17:
                speaker = "Napoleon";
                text = "Is that the bell? We must head to class. I will not let you ruin my 666-day attendance streak.";
                setNapoleonSprite("default");
                break;
            case 18:
                speaker = "Yu";
                text = "But you were only here for two days.";
                break;
            case 19:
                speaker = "Mr. Miller";
                text = "Mr. Miller welcomes the class";
                switchBackground("classroom");
                break;
            case 20:
                text = "Today, we're gonna start working on our final project of the year!";
                break;
            case 21:
                speaker = "Yu";
                text = "Tomorrow's the last day of school... ";
                break;
            case 22:
                speaker = "Mr. Miller";
                text = "And our first pair is Napoleon and Yu";
                break;
//            case 23:
//                text = "Oh, is that Napoleon? He looks angry...";
//                break;
//            case 24:
//                speaker = "Napoleon";
//                text = "Yu!";
//                break;
//            case 25:
//                text = "I can't believe that you gave your chocolate to Louis XVI instead of ME!!!";
//                break;
//            case 26:
//                text = "I thought... I thought the two of us HAD something!!!";
//                break;
//            case 27:
//                speaker = "Yu";
//                text = "What? What chocolate???";
//                break;
//            case 28:
//                speaker = "Napoleon";
//                text = "Do not play coy with me, Yu!!! I know you think of me lower than that sunny speck of GAS!";
//                break;
//            case 29:
//                speaker = "Yu";
//                text = "What??? There must be a misunderstanding, Napoleon!";
//                setUpOptions("Confess your feelings", "Sneeze", "You're dating Louis");
//                break;
//            case 40:
//                speaker = "Napoleon";
//                text = "Y-you... you WHAT. H-how dare you speak such blasphemy!!!";
//                break;
//            case 41:
//                speaker = "Yu";
//                text = "I'm not lying! I truly do like you, more than bros!";
//                break;
//            case 42:
//                speaker = "Napoleon";
//                text = "Well... I... I guess I like you a little bit as well, Yu...";
//                break;
//            case 43:
//                speaker = "Yu";
//                text = "aight, can we kiss now";
//                count = 48;
//                break;
//            case 48:
//                ending(1);
//                text = "";
//                break;
//            case 50:
//                speaker = "Napoleon";
//                text = "Stop that! Stop sneezing!!!";
//                break;
//            case 51:
//                text = "I've had enough with your rudeness! i'm gonna ghost you fr now";
//                break;
//            case 52:
//                speaker = "Yu";
//                text = "Napoleon!!! Please don't go!!! I'll stop sneezing...!";
//                ending(2);
//                break;
//            case 60:
//                speaker = "Napoleon";
//                text = "YOU'RE W H A T.";
//                break;
//            case 61:
//                text = "How dare you play my feelings like a fiddle you... you...!";
//                break;
//            case 62:
//                text = "My heart... oh, it aches...! Curse you, Yu!!!";
//                break;
//            case 63:
//                speaker = "Yu";
//                text = "Wait, Napoleon! I can explain-!";
//                break;
//            case 64:
//                speaker = "Napoleon";
//                text = "Save your explanation for my BLADE!!!";
//                ending(2);
//                break;
            default:
                System.out.println("Oh no, a code problem");
        }
        repaint();
    }

    /*
     * A private helper method that changes the text based on the user's choice.
     */
    private void loadOption(int count, int choice) {
        optionTextField.setText("");
        setOptionsVisible(false);
        optionTextField.setVisible(false);
        switch (count) {
            //Partners with Napoleon?
            case 9:
                switch (choice) {
                    case 1:
                        speaker = "Napoleon";
                        text = "Of course your dreams have very high standards! Many men and women dream of me!";
                        break;
                    case 2:
                        speaker = "Napoleon";
                        text = "Birch tree? You couldn't think of any other insult?";
                        break;
                    case 3:
                        speaker = "Napoleon";
                        text = "I see you are in awe of my omniscient knowledge. You are not the first.";
                        break;
                    default:
                        System.out.println("LOAD OPTION ERROR 2.");
                }
                break;
            //What??? There must be a misunderstanding, Napoleon!
            case 29:
                switch (choice) {
                    case 1:
                        speaker = "Yu";
                        text = "Napoleon! The truth is... I... I like you!";
                        this.count = 40;
                        break;
                    case 2:
                        text = "A-ACHOO!!!";
                        this.count = 50;
                        break;
                    case 3:
                        text = "The truth is... I... I'm dating Louis XVI!!!";
                        this.count = 60;
                        break;
                    default:
                        System.out.println("LOAD OPTION ERROR 2.");
                        break;
                }
                break;
            default:
                System.out.println("LOAD OPTION ERROR 1.");
                break;
        }
    }

    /*
     * A private helper method that sets up the endings.
     */
    private void ending(int ending) {
        switch (ending) {
            // ending 1, good ending
            case 1:
                ImageIcon goodEnding = new ImageIcon("goodEndingBackground.JPG");
                backgroundPanel.setImage(goodEnding.getImage());
                visualNovelFrame.setContentPane(backgroundPanel);

                ImageIcon goodEndingIcon = new ImageIcon("goodEndingIcon.JPG");
                visualNovelFrame.setIconImage(goodEndingIcon.getImage());
                visualNovelFrame.setTitle("You won Napoleon's heart! You have no life purpose anymore.");
                napoleonSprite.setVisible(false);
                break;
            // ending 2, death ending
            case 2:
                ImageIcon death = new ImageIcon("DEATH.png");
                backgroundPanel.setImage(death.getImage());
                visualNovelFrame.setContentPane(backgroundPanel);

                ImageIcon deathIcon = new ImageIcon("deathIcon.png");
                visualNovelFrame.setIconImage(deathIcon.getImage());
                visualNovelFrame.setTitle("You won...death. But at least Napoleon gave you flowers.");
                napoleonSprite.setVisible(false);
                break;
            default:
                break;
        }
    }
}





