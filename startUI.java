import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/*
 * The start menu which allows the user to play the game or exit out.
 */
public class startUI extends JFrame implements ActionListener {

    private JFrame startFrame;

    /*
     * A BackgroundPanel used to start the panel.
     */
    private BackgroundPanel backgroundPanel;

    /*
     * A JButton used to start the visual novel.
     */
    private JButton startButton;

    private VisualNovelUI visualGamePanel;

    /*
     * A JButton used to quit the program.
     */
    private JButton quitButton;

    /*
     * A clip used to play background music.
     */
    private Clip clip;


    public startUI() {
        startFrame = new JFrame();
        try {
            createUIComponents();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * A private helper method to create the frame and its components.
     */
    private void createUIComponents() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        // background music
        File file = new File("startUIResources/startBackgroundMusic.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY); //Uncomment for music

        // cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("miscellaneous/cursor.png");
        Point p = new Point(0,0);
        Cursor c = toolkit.createCustomCursor(cursorIcon, p, "miscellaneous/cursor.png");
        startFrame.setCursor(c); //https://www.youtube.com/watch?v=UnzpZj77hYE


        // background
        ImageIcon startBackground = new ImageIcon("startUIResources/startBackground.JPG");
        backgroundPanel = new BackgroundPanel(startBackground.getImage());
        startFrame.setContentPane(backgroundPanel);


        // startButton
        ImageIcon startButtonIcon = new ImageIcon("startUIResources/startButtonIcon.png");
        startButton = new JButton(startButtonIcon);
        startButton.setText("Visual Novel Start");
        startButton.setBounds(1500,200,360,122);
        startButton.addActionListener(this);
        startFrame.add(startButton);

        // quitButton
        ImageIcon quitButtonIcon = new ImageIcon("startUIResources/quitButtonIcon.png");
        quitButton = new JButton(quitButtonIcon);
        quitButton.setText("Quit");
        quitButton.setBounds(1525,400,295,122);
        quitButton.addActionListener(this);
        startFrame.add(quitButton);

        // icon
        ImageIcon titleIcon = new ImageIcon("titleIcons/goodEndingIcon.JPG");
        startFrame.setIconImage(titleIcon.getImage());
        startFrame.setTitle("Win Napoleon's Heart! : Ready To Start");

        // startFrame set-up
        startFrame.setSize(1000, 700);
        startFrame.setLocation(450, 100);
        startFrame.setLayout(null);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);
    }

    /*
     * Used to listen for startButton and quitButton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            // starts the game
            if (button.getText().equals("Visual Novel Start")) {
                clip.stop();
                visualGamePanel = new VisualNovelUI();
                startFrame.dispose();
                // exits from the program
            } else if (button.getText().equals("Quit")) {
                System.exit(0);
            }
        }
    }
}
