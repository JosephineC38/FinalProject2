import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class VisualNovelUI extends JFrame implements ActionListener {

    private JFrame visualNovelFrame;

    private JPanel dialoguePanel;

    private JPanel optionPanel;

    private JLabel dialogueText;

    private JLabel napoleonSprite;

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

    private JTextField nameTextField;

    /*
     * After the user enters their options, this button is used to submit them.
     */
    private JButton optionButton;

    private JButton nextButton;

    private JButton enterButton;

    private JButton resetButton;

    /*
     * Used to move the text forward.
     */
    private int count;

    private String text;

    private String speaker;

    private String playerName;

    private String gift;

    private int napoleonAffectionPoints;

    /*
     * Used to change the background of the frame and uses the BackgroundPanel class to do so.
     */
    private BackgroundPanel backgroundPanel;


    public VisualNovelUI() {
        visualNovelFrame = new JFrame();
        createUIComponents();
    }
    /*
     * A private helper method that sets up and initializes the visualNovelFrame.
     * It is used in the constructor.
     */
    private void createUIComponents() {
        // variables set up
        napoleonAffectionPoints = 0;
        playerName = "";
        count = 0;
        text = "Hi, I'm just a normal high-school student. One day, I hope to fall in love.";
        speaker = "playerName";
        gift = "";
        changeText();

        //cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("cursor.png");
        Point p = new Point(0,0);
        Cursor c = toolkit.createCustomCursor(cursorIcon, p, "cursor.png");
        visualNovelFrame.setCursor(c); //https://www.youtube.com/watch?v=UnzpZj77hYE

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
        nextButton.setBounds(1310, 750, 380, 134);
        nextButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        nextButton.addActionListener(this);
        visualNovelFrame.add(nextButton);

        // dialoguePanel and dialogueText
        dialoguePanel = new JPanel();
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        dialogueText = new JLabel(dialogueBoxIcon);
        Image newImage = dialogueBoxIcon.getImage().getScaledInstance(1500, 235, Image.SCALE_DEFAULT); //change dialogue box here
        ImageIcon dialogueBoxIconScaled = new ImageIcon(newImage);
        dialogueText = new JLabel(dialogueBoxIconScaled);
        dialoguePanel.setBackground(Color.PINK);
//      dialoguePanel.setBounds(0, 575, 1300, 235);
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
        ImageIcon optionButtonIcon = new ImageIcon("optionButtonIcon.png");
        optionButton = new JButton(optionButtonIcon);
        Font font = new Font("Courier", Font.BOLD, 25);
        optionLabel1.setFont(font);
        optionLabel2.setFont(font);
        optionLabel3.setFont(font);

        optionTextField = new JTextField();
        optionTextField.setFont(font);
        optionButton.setText("ENTER");


        optionLabel1.setBounds(0, 50, 500, 100);
        optionLabel2.setBounds(0, 250, 500, 100);
        optionLabel3.setBounds(0, 300, 500, 100);
        optionButton.setBounds(1450, 460, 250, 100);
        optionButton.addActionListener(this);
        optionTextField.setBounds(1200, 460, 250, 100);
        optionTextField.setVisible(false);

        // resetButton
        ImageIcon resetButtonIcon = new ImageIcon("resetButtonDeath.png");
        resetButton = new JButton(resetButtonIcon);
        resetButton.setBounds(700, 810, 455, 119);
        resetButton.setText("Restart");
        resetButton.setVisible(false);
        resetButton.addActionListener(this);
        visualNovelFrame.add(resetButton);


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
        nameTextField.setBounds(600,350, 400, 135);
        nameTextField.setVisible(true);
        visualNovelFrame.add(nameTextField);

        ImageIcon enterButtonIcon = new ImageIcon("enterButtonIcon.png");
        enterButton = new JButton(enterButtonIcon);
        enterButton.setFont(new Font("Calibri", Font.PLAIN, 24));
        enterButton.addActionListener(this);
        enterButton.setText("Enter Name");
        enterButton.setBounds(1010,390, 258, 55);
        visualNovelFrame.add(enterButton);
        dialoguePanel.setVisible(false);
        nextButton.setVisible(false);
        ImageIcon titleIcon = new ImageIcon("napoleonIcon.png");
        visualNovelFrame.setIconImage(titleIcon.getImage());
        visualNovelFrame.setTitle("Win Napoleon's Heart");
        visualNovelFrame.setSize(1000, 700);
        visualNovelFrame.setLocation(450, 100);
        visualNovelFrame.setLayout(null);
        visualNovelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualNovelFrame.setVisible(true);
    }

    /*
     * A private helper method that changes the dialogueText and the speaker
     */
    private void changeText() {
        ImageIcon dialogueBoxIcon = new ImageIcon("dialogueTextBox.png");
        Image newImage = dialogueBoxIcon.getImage().getScaledInstance(1300, 235, Image.SCALE_DEFAULT); // change dialogue box here
        ImageIcon dialogueBoxIconScaled = new ImageIcon(newImage);
        dialogueText = new JLabel(dialogueBoxIconScaled) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dialogueText.setFont(new Font("Courier", Font.BOLD, 25));
                g.drawString(text, 30, 150);
                g.drawString(speaker, 50, 37);
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
        } else if (background.equals("cafeteria")) {
            ImageIcon cafeteria = new ImageIcon("cafeteriaBackground.png");
            backgroundPanel.setImage(cafeteria.getImage());
        }
        visualNovelFrame.setContentPane(backgroundPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Restart")) {
                this.dispose();
                visualNovelFrame.dispose();
                visualNovelFrame = new VisualNovelUI();
                System.out.println("r");
            }

            if (button.getText().equals("NEXT")) {
                count++;
                String playerNameLowerCase = playerName.toLowerCase();
                if (playerNameLowerCase.equals("napoleon") || playerNameLowerCase.equals("louis")) {
                    uniqueName();
                } else {
                    try {
                        loadQuestion(count);
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else if (button.getText().equals("ENTER")) {
                String optionStr = optionTextField.getText();
                int option = 0;
                if (optionStr.equals("1") || optionStr.equals("2") || optionStr.equals("3")) {
                    option = Integer.parseInt(optionStr);
                }
                else {
                    optionTextField.setText("");
                }
                if (option == 1 || option == 2 || option == 3) {
                    try {
                        loadOption(count, option);
                    } catch (UnsupportedAudioFileException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (LineUnavailableException ex) {
                        throw new RuntimeException(ex);
                    }
                    nextButton.setVisible(true);
                } else {
                    optionTextField.setText("");
                }
            } else if (button.getText().equals("Enter Name")) {
                checkName();
            }
        }
    }

    /*
     * A private helper method that sets the inputted name of the player.
     */
    private void checkName() {
        String enteredName = nameTextField.getText();
        if(enteredName.length() > 9) {
            nameTextField.setText("");
        } else {
            if(enteredName.equals("")) {
                enteredName = "Yu";
            }

            playerName = enteredName;
            nameTextField.setVisible(false);
            enterButton.setVisible(false);
            dialoguePanel.setVisible(true);
            nextButton.setVisible(true);
            speaker = playerName;
        }


    }

    /*
     * A private helper method that sets napoleonSprite to the selected image.
     */
    private void setNapoleonSprite(String emotion) {
        if (emotion.equals("default")) {
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
     * A private helper method that plays sound once.
     */
    private void playSound(String sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("schoolbellSound.wav");
        if(sound.equals("schoolbell")) { //I don't know
            file = new File("schoolbellSound.wav");
        } else if (sound.equals("explosion")) {
            file = new File("explosionByQueen.wav");
        }
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    /*
     * A private helper method that plays the background music.
     */
    private void playBackgroundMusic(String music) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("schoolbellSound.wav");
        if(music.equals("death")) {
            file = new File("deathEndingBackgroundSound.wav");
        }
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        clip.stop();
    }

    /*
     * Goes directly to the death ending if the user has a unique name.
     */
    private void uniqueName() {
        String playerNameLowerCase = playerName.toLowerCase().strip();
        switch (playerNameLowerCase) {
            case "napoleon":
                switch (count) {
                    case 1:
                        text = "But right now, I'm running late to school.";
                        break;
                    case 2:
                        text = "Wait, why is the new transfer student heading towards me?";
                        break;
                    case 3:
                        speaker = "Napoleon Bonaparte";
                        text = "\"You..\"";
                        napoleonSprite.setVisible(true);
                        setNapoleonSprite("angry");
                        break;
                    case 4:
                        speaker = playerName;
                        text = "\"What?\"";
                        break;
                    case 5:
                        speaker = "Napoleon Bonaparte";
                        text = "\"It's an insult for you to have my name.\"";
                        break;
                    case 6:
                        text = "\"But to be partners for our project?\"";
                        break;
                    case 7:
                        text = "\"You don't know what's its like to be a true French man.\"";
                        break;
                    case 8:
                        text = "\"To live through the French Revolution and thrive.\"";
                        break;
                    case 9:
                        speaker = playerName;
                        text = "\"French Revolution? Wasn't that hundreds of years ago.\"";
                        break;
                    case 10:
                        text = "\"How could you live through it?\"";
                        break;
                    case 11:
                        text = "\"And what's wrong with working together?\"";
                        break;
                    case 12:
                        speaker = "Napoleon Bonaparte";
                        text = "\"Those are your final words? How pathetic.\"";
                        break;
                    case 13:
                        ending(2);
                    default:
                        System.out.println("Napoleon name error");
                        break;
                }
                break;
            case "louis":
                switch (count) {
                    case 1:
                        text = "But right now, I'm running late to school.";
                        break;
                    case 2:
                        text = "Wait, why is the new transfer student heading towards me?";
                        break;
                    case 3:
                        speaker = "Napoleon Bonaparte";
                        text = "\"Louis XVI...\"";
                        napoleonSprite.setVisible(true);
                        setNapoleonSprite("angry");
                        break;
                    case 4:
                        speaker = playerName;
                        text = "\"What? That isn't my na-\"";
                        break;
                    case 5:
                        speaker = "Napoleon Bonaparte";
                        text = "\"I could barely tolerate your existence.\"";
                        break;
                    case 6:
                        text = "\"But to have to work together for our project?\"";
                        break;
                    case 7:
                        text = "\"Your namesake was a disgraceful man who could never rule a country.\"";
                        break;
                    case 8:
                        text = "\"Thus, your existence sickens me.\"";
                        break;
                    case 9:
                        speaker = playerName;
                        text = "\"Wait, I was named after my cousin, I'm only the second Louis.\"";
                        break;
                    case 10:
                        text = "\"And why are you getting so upset over this?\"";
                        break;
                    case 11:
                        speaker = "Napoleon Bonaparte";
                        text = "\"I will not listen to your lies.\"";
                        break;
                    case 12:
                        speaker = "Napoleon Bonaparte";
                        text = "\"Now, let my beautiful face be the very last thing you see.\"";
                        break;
                    case 13:
                        ending(2);
                    default:
                        System.out.println("Louis name error");
                        break;
                }
                break;
            default:
                System.out.println("Unique name error");
                break;
        }
    }

    /*
     * A private helper method that displays the current text.
     */
    public void loadQuestion(int questionNum) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        switch(questionNum) {
            case -2:
                ending(2);
            case 1:
                speaker = playerName;
                text = "But right now, I'm running late to school.";
                break;
            case 2:
                text = "I wonder who will be my partner for my history project- OOF";
                break;
            case 3:
                speaker = "Napoleon";
                text = "\"Ugh! You peasant! How dare you bump into me???\"";
                break;
            case 4:
                speaker = playerName;
                text = "This is...";
                break;
            case 5:
                text = "Napoleon, the transfer student!";
                napoleonSprite.setVisible(true);
                setNapoleonSprite("angry");
                break;
            case 6:
                text = "\"O-oh, I'm so sorry! Are you okay?\"";
                break;
            case 7:
                speaker = "Napoleon";
                text = "\"I'm fine! Look where you're going next time, you fishcake.\"";
                setNapoleonSprite("default");
                break;
            case 8:
                text = "\"By the way, I heard we are partners in history class.\"";
                break;
            case 9:
                speaker = playerName;
                text = "Partners with Napoleon?";
                setUpOptions("Is this a dream come true?", "You birch tree.", "Wow, uh, cool...");
                break;
            case 10:
                speaker = playerName;
                text = "\"But wait, weren't the partners going to be announced today? How do you already know?\"";
                break;
            case 11:
                speaker = "Napoleon";
                text = "\"My persuasive skills are top notch, used to overthrow governments. Don't question it.\"";
                break;
            case 12:
                speaker = playerName;
                text = "\"That explains nothing!\"";
                break;
            case 13:
                text = "\"You know what, whatever. Maybe we should discuss what our history topic should be.\"";
                break;
            case 14:
                speaker = "Napoleon";
                text = "\"The French Revolution, of course! What other topic is as majestic as the fall of the monarchy?\"";
                setNapoleonSprite("happy");
                break;
            case 15:
                speaker = playerName;
                text = "\"...You seen very passionate about this.\"";
                break;
            case 16:
                speaker = "Napoleon";
                text = "\"Do you dare imply that the revolution is not worth your time. Why, as a young-\"";
                setNapoleonSprite("angry");
                break;
            case 17:
                playSound("schoolbell");
                text = "\"Is that the bell? We must head to class. I will not let you ruin my 666-day attendance streak.\"";
                setNapoleonSprite("default");
                break;
            case 18:
                speaker = playerName;
                text = "\"But you were only here for two days...?\"";
                break;
            case 19:
                speaker = "Mr. Miller";
                text = "\"Hello, everyone, thank God it's Friday!\"";
                switchBackground("classroom");
                break;
            case 20:
                text = "\"Today, we're gonna start working on our final project of the year!\"";
                break;
            case 21:
                speaker = playerName;
                text = "Woo-hoo...";
                break;
            case 22:
                speaker = "Mr. Miller";
                text = "\"And our first pair is Napoleon and " + playerName + "!\"";
                break;
            case 23:
                speaker = "Napoleon";
                text = "\"See, I told you! Did you know that Robespierre created the guillotine?\"";
                napoleonSprite.setVisible(false);
                break;
            case 24:
                speaker = playerName;
                text = "For the next two weeks, we somehow finished the history project with full marks...";
                break;
            case 25:
                // cafeteria
                text = "This stack of textbooks are so heavy... Why am I in the cafeteria- OOF";
                switchBackground("cafeteria");
                break;
            case 26:
                // explosion sound <- queen's acid drink
                text = "I fall on top of a broad-chested, dignified and strong narcissist.";
                playSound("explosion"); // <- You Get Me Kris / You Do Not Do Crazy Things Like "Have Opinions"
                break;
            case 27:
                napoleonSprite.setVisible(true);
                setNapoleonSprite("angry");
                speaker = "Napoleon";
                text = "\"Agh! You scoundrel! Do you wanna fight???\"";
                break;
            case 28:
                speaker = playerName;
                text = "\"Oh, I didn't see you, Napoleon-\"";
                setUpOptions("Sneeze", "Apologize", "Sneeze thrice");
                break;
            case 29:
                setNapoleonSprite("default");
                text = "Napoleon helps me out regardless... He's so charming..........";
                break;
            case 30:
                text = "Oh, he seems to be talking about something...";
                break;
            case 31:
                speaker = "Napoleon";
                text = "\"... and I absolutely loathe llamas!\"";
                break;
            case 32:
                text = "\"Anyway, I'm gonna head home now. See you later, " + playerName + ".\"";
                napoleonSprite.setVisible(false);
                break;
            case 33:
                speaker = playerName;
                text = "\"Okay, see you later, Napoleon!\"";
                break;
            case 34:
                text = "I watch Napoleon strut away in his 12-inch high-heels...";
                break;
            case 35:
                text = "Hmm... Maybe I should get him something as a thank-you for being so... Napoleon.";
                break;
            case 36:
                text = "What should I get him?";
                setUpOptions("A sword", "A llama stuffie", "A bottle of burgundy");
                break;
            case 37:
                text = "Alright, I'll be sure to surprise Napoleon with this gift!";
                break;
            case 38:
                speaker = "";
                text = "(The next day...)";
                break;
            case 39:
                switchBackground("schoolgrounds");
                napoleonSprite.setVisible(true);
                setNapoleonSprite("default");
                speaker = "Napoleon";
                text = "\"What did you need me for, you plebeian?\"";
                if(gift.equals("llama")) {
                    this.count = 42;
                } else if (gift.equals("burgundy")) {
                    this.count = 46;
                }
                break;
            case 40:
                speaker = playerName;
                text = "\"I wanted to thank you for being such a cool person, so here- a sword!\"";
                break;
            case 41:
                setNapoleonSprite("happy");
                speaker = "Napoleon";
                text = "\"A sword... just what this era needs! Thank-you, dear " + playerName + ".\"";
                break;
            case 42:
                speaker = playerName;
                text = "He seems really happy with the present...!";
                this.count = 50;
                break;
            case 43:
                speaker = playerName;
                text = "\"I wanted to thank you for being such a cool person, so here- a llama plushie!\"";
                break;
            case 44:
                setNapoleonSprite("angry");
                speaker = "Napoleon";
                text = "\"You wretched beast! Have I not said that I despised llamas!?\"";
                break;
            case 45:
                speaker = playerName;
                text = "\"O-oh... I totally forgot...\"";
                break;
            case 46:
                setNapoleonSprite("default");
                speaker = "Napoleon";
                text = "\"Whatever. I'm keeping it anyway. If there's nothing else, buh-bye!\"";
                this.count = 51;
                break;
            case 47:
                speaker = playerName;
                text = "\"I wanted to thank you for being such a cool person, so here- a bottle of burgundy!\"";
                break;
            case 48:
                setNapoleonSprite("happy");
                speaker = "Napoleon";
                text = "\"Ah, yes! Illegally-obtained alcohol is my favorite. Thank-you, " + playerName + ".\"";
                break;
            case 49:
                speaker = playerName;
                text = "He seems happy with the gift...!";
                break;
            case 50:
                setNapoleonSprite("default");
                speaker = "Napoleon";
                text = "\"If there's nothing else, see you later (again), " + playerName + ".\"";
                napoleonSprite.setVisible(false);
                break;
            case 51:
                speaker = playerName;
                napoleonSprite.setVisible(false);
                text = "I wave good-bye to Napoleon and watch his broad figure fade into the sunset...";
                break;
            case 52:
                speaker = playerName;
                switchBackground("schoolgrounds");
                napoleonSprite.setVisible(false);
                text = "The next day, my childhood friend, Louis XVI, asked me to help him propose to Marie Antoinette.";
                break;
            case 53:
                switchBackground("hallway");
                text = "\"Hey, Louis, isn't it too early for marriage?\"";
                break;
            case 54:
                speaker = "Louis XVI";
                text = "\"Miss Antoinette and I have been together for two months- that's more than enough!\"";
                break;
            case 55:
                speaker = playerName;
                text = "\"Okay... So, do I just stand here?\"";
                break;
            case 56:
                speaker = "Louis XVI";
                text = "\"Exactly. Ahem- oh, the love of my life, Juliet to my Romeo-!\"";
                break;
            case 57:
                text = "\"Nothing would make me happier than spending the rest of my life with you!\"";
                break;
            case 58:
                speaker = playerName;
                text = "\"So, will you marry me?\"";
                break;
            case 59:
                speaker = playerName;
                text = "Louis hands me a seven-layered cake.";
                break;
            case 60:
                speaker = playerName;
                text = "\"It's a bit much.\"";
                break;
            case 61:
                speaker = "Louis XVI";
                text = "\"No, I probably need another layer.\"";
                break;
            case 62:
                speaker = playerName;
                text = "Strangely enough, I could hear 12 inch stilettos running away outside the classroom.";
                break;
            case 63:
                text = "\"Good luck with your proposal, Louis.\"";
                break;
            case 64:
                //louis switch background music
                speaker = "Louis XVI";
                text = "\"Yes, thank you, friend. You'll be my first guest at the wedding!\"";
                break;
            case 65:
                switchBackground("schoolgrounds");
                speaker = playerName;
                text = "I wave good-bye and head out of school. I smack into a wall of pure, unadulterated, broad muscle-";
                break;
            case 66:
                napoleonSprite.setVisible(true);
                setNapoleonSprite("angry");
                text = "OOF- oh, is that Napoleon? He looks angry...";
                break;
            case 67:
                speaker = "Napoleon";
                text = "\"I can't believe that you gave your heart to Louis XVI instead of ME!!!\"";
                break;
            case 68:
                text = "\"I thought... I thought the two of us HAD something!!!\"";
                break;
            case 69:
                speaker = playerName;
                text = "\"What? What???\"";
                break;
            case 70:
                speaker = "Napoleon";
                text = "\"Do not play coy with me, " + playerName + "! I know you think of me lower than that sunny speck of GAS!\"";
                break;
            case 71:
                speaker = playerName;
                text = "\"What??? There must be a misunderstanding, Napoleon!\"";
                setUpOptions("Confess your feelings", "Sneeze", "You're dating Louis");
                break;
            // confess
            case 72:
                setNapoleonSprite("happy");
                speaker = "Napoleon";
                text = "\"Y-you... you WHAT. H-how dare you speak such blasphemy!!!\"";
                break;
            case 73:
                speaker = playerName;
                text = "\"I'm not lying! I truly do like you, more than bros!\"";
                break;
            case 74:
                speaker = "Napoleon";
                if (napoleonAffectionPoints == 50) {
                    text = "\"Well... I... I guess I like you a little as well, " + playerName + "...\"";
                }
                else {
                    text = "\"I know you're PLAYING WITH ME!!! Meet your E N D!\"";
                    count = -3;
                }
                break;
            case 75:
                speaker = playerName;
                text = "\"aight, can we kiss now\"";
                break;
            case 76:
                ending(1);
                break;
            case 77:
                speaker = "Napoleon";
                text = "\"Stop that! Stop sneezing!!!\"";
                break;
            case 78:
                speaker = "Napoleon";
                text = "\"I've had enough with your rudeness! i'm gonna ghost you fr now\"";
                break;
            case 79:
                speaker = playerName;
                text = "\"Napoleon, please don't go!!! I can feel my soul leaving my body, I'll stop sneezing...!\"";
                count = -3;
                break;
            case 80:
                speaker = "Napoleon";
                text = "\"YOU'RE W H A T.\"";
                break;
            case 81:
                speaker = "Napoleon";
                text = "\"How dare you play my feelings like a fiddle you... you...!\"";
                break;
            case 82:
                text = "\"My heart... oh, it aches...! Curse you, " + playerName + "!!\"";
                break;
            case 83:
                speaker = playerName;
                text = "\"Wait, Napoleon! I can explain-!\"";
                break;
            case 84:
                speaker = "Napoleon";
                text = "\"Save your explanation for my BLADE!!!\"";
                count = -3;
                break;
            default:
                System.out.println("Oh no, a dialogue problem");
        }
        repaint();
    }

    /*
     * A private helper method that changes the text based on the user's choice.
     */
    private void loadOption(int count, int choice) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        optionTextField.setText("");
        setOptionsVisible(false);
        optionTextField.setVisible(false);
        switch (count) {
            //Partners with Napoleon?
            case 9:
                switch (choice) {
                    case 1:
                        speaker = "Napoleon";
                        text = "\"Of course your dreams have very high standards! Many men and women dream of me!\"";
                        break;
                    case 2:
                        speaker = "Napoleon";
                        text = "\"Birch tree? You couldn't think of any other insult?\"";
                        break;
                    case 3:
                        speaker = "Napoleon";
                        text = "\"I see you are in awe of my omniscient knowledge. You are not the first.\"";
                        break;
                    default:
                        System.out.println("LOAD OPTION ERROR 2.");
                }
                break;
            //What??? There must be a misunderstanding, Napoleon!
            case 28:
                switch (choice) {
                    case 1:
                        addPoints(5, true);
                        speaker = "Napoleon";
                        text = "\"How DARE you sneeze on me AGAIN!!!\"";
                        break;
                    case 2:
                        addPoints(10, true);
                        speaker = "Napoleon";
                        text = "\"Hmph! Apology accepted. I shall help you carry these books.\"";
                        break;
                    case 3:
                        addPoints(5, false);
                        speaker = "Napoleon";
                        text = "\"Jesus, do you have allergies or something??? Stop that!!!\"";
                        break;
                    default:
                        System.out.println("LOAD OPTION ERROR 2.");
                        break;
                }
                break;
            case 36:
                switch (choice) {
                    case 1:
                        addPoints(20, true);
                        text = "I think he would appreciate a good broadsword... Yes, I'll gift Napoleon a sword!";
                        break;
                    case 2:
                        addPoints(10, false);
                        text = "I think he would appreciate a good llama plushie! Yes, that's a great idea, me.";
                        gift = "llama";
                        break;
                    case 3:
                        addPoints(10, true);
                        text = "I think he would appreciate some good... burgundy drink...?";
                        gift = "burgundy";
                        break;
                    default:
                        System.out.println("LOAD OPTION ERROR 2.");
                }
                break;
            case 71:
                switch (choice) {
                    case 1:
                        addPoints(20, true);
                        speaker = playerName;
                        text = "\"Napoleon! The truth is... I... I like you!\"";
                        repaint();
                        this.count = 71;
                        break;
                    case 2:
                        addPoints(5, false);
                        text = "\"A-ACHOO!!!\"";
                        repaint();
                        this.count = 77;
                        break;
                    case 3:
                        addPoints(20, false);
                        text = "\"The truth is... I... I'm dating Louis XVI!!!\"";
                        repaint();
                        this.count = 80;
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
                // change reset button to happy color
                ImageIcon goodEnding = new ImageIcon("goodEndingBackground.JPG");
                ImageIcon resetButtonGood = new ImageIcon("restartButtonGood.png");
                resetButton.setIcon(resetButtonGood);
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
                try {
                    playBackgroundMusic("death");
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
                ImageIcon deathIcon = new ImageIcon("deathIcon.png");
                visualNovelFrame.setIconImage(deathIcon.getImage());
                visualNovelFrame.setTitle("You won... death. But at least Napoleon gave you flowers for your grave.");
                napoleonSprite.setVisible(false);
                break;
            default:
                break;
        }
        resetButton.setVisible(true);
        setOptionsVisible(false);
        dialoguePanel.setVisible(false);
        nextButton.setVisible(false);
    }

    private void addPoints (int points, boolean add) {
        if (add) {
            if (napoleonAffectionPoints + points > 50) {
                napoleonAffectionPoints = 50;
            } else {
                napoleonAffectionPoints += points;
            }
        }
        if (!add) {
            if (napoleonAffectionPoints - points < 0) {
                napoleonAffectionPoints = 0;
            }
            else {
                napoleonAffectionPoints-= points;
            }
        }
    }
}





