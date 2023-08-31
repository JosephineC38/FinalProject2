package unusedResources;

import visualNovelFolder.BackgroundPanel;
import visualNovelFolder.VisualNovelUI;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/*
 * The start menu which allows the user to play the game or exit out.
 */
public class startUI extends JFrame implements ActionListener, MouseListener {

    private JFrame startFrame;

    /*
     * A visualNovelFolder.BackgroundPanel used to start the panel.
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

    private Cursor defaultCursor;


    public startUI() {
        startFrame = new JFrame("Win Napoleon's Heart! : Ready To Start");
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
        File file = new File("backgroundMusic/startBackgroundMusic.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        // cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("miscellaneous/cursor.png");
        Point p = new Point(0,0);
        defaultCursor = toolkit.createCustomCursor(cursorIcon, p, "miscellaneous/cursor.png");
        startFrame.setCursor(defaultCursor); //https://www.youtube.com/watch?v=UnzpZj77hYE

        // background
        ImageIcon startBackground = new ImageIcon("backgrounds/startBackground.png");
        backgroundPanel = new BackgroundPanel(startBackground.getImage());
        startFrame.setContentPane(backgroundPanel);

        // startButton
        ImageIcon startButtonIcon = new ImageIcon("buttons/startButtonIcon.png");
        startButton = new JButton(startButtonIcon);
        startButton.setText("Visual Novel Start");
        startButton.setBounds(1050,450,360,122);

        startButton.addActionListener(this);
        startButton.addMouseListener(this);
        startFrame.add(startButton);

        // quitButton
        ImageIcon quitButtonIcon = new ImageIcon("buttons/quitButtonIcon.png");
        quitButton = new JButton(quitButtonIcon);
        quitButton.setText("Quit");
        quitButton.setBounds(1075,600,295,122);
        quitButton.addActionListener(this);
        quitButton.addMouseListener(this);
        startFrame.add(quitButton);

        // icon
        ImageIcon titleIcon = new ImageIcon("titleIcons/goodEndingIcon.JPG");
        startFrame.setIconImage(titleIcon.getImage());


        // startFrame set-up
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(1500, 800);
        startFrame.setLocation(25, 25);
        startFrame.setLayout(null);
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



    @Override
    public void mouseEntered(MouseEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("miscellaneous/mouseClickedIcon.png");
        Point p = new Point(0,0);
        Cursor c = toolkit.createCustomCursor(cursorIcon, p, "miscellaneous/mouseClickedIcon.png");
        startFrame.setCursor(c);
    }

    //In order for mouseListener to work, I have to add these methods here, but I don't have a use for them.
    @Override
    public void mouseExited(MouseEvent e) {
        startFrame.setCursor(defaultCursor);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }






}


