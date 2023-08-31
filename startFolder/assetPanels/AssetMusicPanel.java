package startFolder.assetPanels;

import startFolder.StartController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AssetMusicPanel extends JPanel implements ActionListener  {
    private StartController controller;
    private JButton exitButton;
    private JLabel title;

    private Clip clip;

    private boolean isPlaying;
    private String name;


    // startBackgroundMusic
    private JButton startButton;
    private JLabel startLabel;

    // assetMusic
    private JButton assetButton;
    private JLabel assetLabel;

    // defaultMusic
    private JButton defaultButton;
    private JLabel defaultLabel;

    // louisxviMusic
    private JButton louisButton;
    private JLabel louisLabel;

    // misunderstandingMusic
    private JButton mistakeButton;
    private JLabel mistakeLabel;

    // confessionMusic
    private JButton confessButton;
    private  JLabel confessLabel;

    // napoleonFuriousMusic
    private JButton furiousButton;
    private JLabel furiousLabel;

    // goodEndingMusic
    private JButton goodEndButton;
    private JLabel goodEndLabel;

    // deathMusic
    private JButton deathEndButton;
    private JLabel deathEndLabel;



    public AssetMusicPanel(StartController controller) {
        this.controller = controller;
        try {
            setup();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * A private helper method used to set the swing components
     */
    private void setup() throws LineUnavailableException {
        isPlaying = false;
        name = "";
        clip = AudioSystem.getClip();
        setLayout(null);
        ImageIcon speakerIcon = new ImageIcon("miscellaneous/speakerIcon.png");

        // title
        title = new JLabel("BACKGROUND MUSIC");
        title.setBounds(200,30,1000,100);
        title.setFont(new Font("Raleway", Font.PLAIN, 90));
        title.setForeground(Color.WHITE);
        add(title);

        // exitButton
        ImageIcon exitIcon = new ImageIcon("buttons/exitButton.png");
        exitButton = new JButton("Exit", exitIcon);
        exitButton.setBounds(1350,30,100,100);
        exitButton.addActionListener(this);
        add(exitButton);

        // startBackgroundMusic
        startButton = new JButton("Start", speakerIcon);
        startLabel = new JLabel("<html> Doki Doki Literature Club! (Main Theme) " +
        "<br/> Credit: Doki Doki Literature Club! </html>");
        createButton(startButton, startLabel, 0, 0);

        // assetMusic
        assetButton = new JButton("Asset", speakerIcon);
        assetLabel = new JLabel("<html> It's Showtime! " +
        "<br/> Credit: Undertale </html>");
        createButton(assetButton, assetLabel, 100, 0);

        // defaultMusic
        defaultButton = new JButton("Default", speakerIcon);
        defaultLabel = new JLabel("<html> Fallen Down " +
        "<br/> Credit: Undertale </html>");
        createButton(defaultButton, defaultLabel, 200, 0);

        // louisxviMusic
        louisButton = new JButton("Louis", speakerIcon);
        louisLabel = new JLabel("<html> Queen " +
        "<br/> Credit: Deltarune Chapter 2 </html>");
        createButton(louisButton, louisLabel, 300, 0);

        // misunderstandingMusic
        mistakeButton = new JButton("Mistake", speakerIcon);
        mistakeLabel = new JLabel("<html> A Rose By Any Other Name..." +
        "<br/> Credit: OMORI </html>");
        createButton(mistakeButton, mistakeLabel, 0, 700);

        // confessionMusic
        confessButton = new JButton("Confess", speakerIcon);
        confessLabel = new JLabel("<html> How...tragic!" +
        "<br/> Credit: OMORI </html>");
        createButton(confessButton, confessLabel, 100, 700);

        // napoleonFuriousMusic
        furiousButton = new JButton("Furious", speakerIcon);
        furiousLabel = new JLabel("<html> The Only Thing They Fear Is You" +
        "<br/> Credit: DOOM Eternal </html>");
        createButton(furiousButton, furiousLabel, 200, 700);

        // goodEndingMusic
        goodEndButton = new JButton("GoodEnd", speakerIcon);
        goodEndLabel = new JLabel("<html> La Carmagnole" +
        "<br/> Credit: French Revolution </html>");
        createButton(goodEndButton, goodEndLabel, 300, 700);

        // deathMusic
        deathEndButton = new JButton("DeathEnd", speakerIcon);
        deathEndLabel = new JLabel("<html> Game Over" +
        "<br/> Credit: OMORI </html>");
        createButton(deathEndButton, deathEndLabel, 400, 450);

        setBackground(Color.PINK);
        setSize(1500, 800);
        setVisible(true);
    }

    /*
     * A private helper method used to create buttons
     */
    private void createButton(JButton button, JLabel label, int y, int x) {
        Font font = new Font("Courier", Font.BOLD, 20);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(150 + x,162 + y,75,75);
        button.addActionListener(this);
        add(button);

        label.setBounds(250 + x,162 + y,600,75);
        label.setFont(font);
        add(label);
    }

    /*
     * A private helper method that plays the background music
     * Clicking a button will turn that music on/off
     * Clicking another button will turn the previous music off
     */
    private void playBackgroundMusic(String music) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        if(music.equals(name)) {
            if(isPlaying) {
                isPlaying = false;
                clip.stop();
            } else {
                isPlaying = true;
                File file;
                file = new File(new String("backgroundMusic/" + music + "Music.wav"));
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } else {
            isPlaying = true;
            File file;
            file = new File(new String("backgroundMusic/" + music + "Music.wav"));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        name = music;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;

            // exitButton
            if (button.getText().equals("Exit")) {
                try {
                    isPlaying = false;
                    clip.stop();
                    controller.replaceCard("assetBackMusic");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // defaultButton
            } else if (button.getText().equals("Default")) {
                try {
                    playBackgroundMusic("default");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // goodEndButton
            } else if (button.getText().equals("GoodEnd")) {
                try {
                    playBackgroundMusic("goodEnding");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // deathButton
            } else if (button.getText().equals("DeathEnd")) {
                try {
                    playBackgroundMusic("death");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // confessButton
            } else if (button.getText().equals("Confess")) {
                try {
                    playBackgroundMusic("confession");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // louisButton
            } else if (button.getText().equals("Louis")) {
                try {
                    playBackgroundMusic("louisxvi");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // mistakeButton
            } else if (button.getText().equals("Mistake")) {
                try {
                    playBackgroundMusic("misunderstanding");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // furiousButton
            } else if (button.getText().equals("Furious")) {
                try {
                    playBackgroundMusic("napoleonFurious");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // startButton
            } else if (button.getText().equals("Start")) {
                try {
                    playBackgroundMusic("startBackground");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // assetButton
            } else if (button.getText().equals("Asset")) {
                try {
                    playBackgroundMusic("asset");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }



}
