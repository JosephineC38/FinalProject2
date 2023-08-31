package startFolder.assetPanels;
import startFolder.StartController;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class AssetSoundPanel extends JPanel implements ActionListener {
    private StartController controller;
    private JButton exitButton;
    private JLabel title;

    private Clip clip;

    private boolean isPlaying;
    private String name;


    // trip
    private JButton tripButton;
    private JLabel tripLabel;

    // schoolbell
    private JButton bellButton;
    private JLabel bellLabel;

    // explosionByQueen
    private JButton explodeButton;
    private JLabel explodeLabel;

    // sneeze
    private JButton sneezeButton;
    private JLabel sneezeLabel;

    // napoleonWalking
    private JButton walkButton;
    private JLabel walkLabel;

    // stab
    private JButton stabButton;
    private JLabel stabLabel;

    // sneezeThrice
    private JButton thriceButton;
    private JLabel thriceLabel;

    public AssetSoundPanel(StartController controller) {
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
        ImageIcon speakerIcon = new ImageIcon("miscellaneous/speakerIcon.png");
        clip = AudioSystem.getClip();
        setLayout(null);

        // title
        title = new JLabel("SOUNDS");
        title.setBounds(510, 30, 750, 100);
        title.setFont(new Font("Raleway", Font.PLAIN, 100));
        title.setForeground(Color.WHITE);
        add(title);

        // exitButton
        ImageIcon exitIcon = new ImageIcon("buttons/exitButton.png");
        exitButton = new JButton("Exit", exitIcon);
        exitButton.setBounds(1350, 30, 100, 100);
        exitButton.addActionListener(this);
        add(exitButton);

        // trip
        tripButton = new JButton("Trip", speakerIcon);
        tripLabel = new JLabel("<html> Tripping " +
        "<br/> Credit: [To Be Found] </html>");
        createButton(tripButton, tripLabel, 0, 0);

        // schoolbell
        bellButton = new JButton("Bell", speakerIcon);
        bellLabel = new JLabel("<html> School Bell " +
        "<br/> Credit: Ioannis IGI </html>");
        createButton(bellButton, bellLabel, 0, 125);

        // explosionByQueen
        explodeButton = new JButton("Explode", speakerIcon);
        explodeLabel = new JLabel("<html> Queen Explosion " +
        "<br/> Credit: Deltarune Chapter 2 </html>");
        createButton(explodeButton, explodeLabel, 0, 250);

        // Sneeze
        sneezeButton = new JButton("Sneeze", speakerIcon);
        sneezeLabel = new JLabel("<html> Sneeze " +
        "<br/> Credit: Double Slits </html>");
        createButton(sneezeButton, sneezeLabel, 700, 0);

        // napoleonWalking
        walkButton = new JButton("Walk", speakerIcon);
        walkLabel = new JLabel("<html> Napoleon Walking Away" +
        "<br/> Credit: Sound Laboratory </html>");
        createButton(walkButton, walkLabel, 700, 125);

        // stab
        stabButton = new JButton("Stab", speakerIcon);
        stabLabel = new JLabel("<html> The Sound of Death" +
        "<br/> Credit: Sound Library </html>");
        createButton(stabButton, stabLabel, 700, 250);

        // sneezeThrice
        thriceButton = new JButton("Thrice", speakerIcon);
        thriceLabel = new JLabel("<html> Sneezing Thrice" +
        "<br/> Credit: Double Slits </html>");
        createButton(thriceButton, thriceLabel, 380, 375);

        setSize(1500, 800);
        setBackground(Color.PINK);
        setVisible(true);
    }

    /*
     * A private helper method used to create buttons
     */
    private void createButton(JButton button, JLabel label, int x, int y) {
        Font font = new Font("Courier", Font.BOLD, 20);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(200 + x, 175 + y, 75, 75);
        button.addActionListener(this);
        add(button);

        label.setBounds(300 + x, 175 + y, 600, 75);
        label.setFont(font);
        add(label);
    }


    /*
     * A private helper method that plays sound once
     * Clicking a button will turn that music on/off
     * Clicking another button will turn the previous music off
     */
    private void playSound(String music) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        if(music.equals(name)) {
            if(isPlaying) {
                isPlaying = false;
                clip.stop();
            } else {
                isPlaying = true;
                File file = new File(new String("Sounds/" + music + ".wav"));
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl volume = (FloatControl) clip.getControl(MASTER_GAIN);
                volume.setValue(6.0f);
                clip.start();
            }

        } else {
            isPlaying = true;
            File file = new File(new String("Sounds/" + music + ".wav"));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl volume = (FloatControl) clip.getControl(MASTER_GAIN);
            volume.setValue(6.0f);
            clip.start();
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
                    clip.stop();
                    controller.replaceCard("assetBackMusic");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // tripbutton
            } else if (button.getText().equals("Trip")) {
                try {
                    playSound("trip");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // bellButton
            } else if (button.getText().equals("Bell")) {
                try {
                    playSound("schoolBell");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // sneezeButton
            } else if (button.getText().equals("Sneeze")) {
                try {
                    playSound("sneeze");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // explodeButton
            } else if (button.getText().equals("Explode")) {
                try {
                    playSound("explosionByQueen");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // walkButton
            } else if (button.getText().equals("Walk")) {
                try {
                    playSound("napoleonWalking");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // stabButton
            } else if (button.getText().equals("Stab")) {
                try {
                    playSound("stab");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            // thriceButton
            } else if (button.getText().equals("Thrice")) {
                try {
                    playSound("sneezeThrice");
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
