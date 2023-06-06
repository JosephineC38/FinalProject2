import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/*
 * Allows the user to change between the visual novel and quiz games.
 */
public class startUI extends JFrame implements ActionListener {

    private JFrame startFrame;

    private JLabel visualNovelLabel;

    private BackgroundPanel backgroundPanel;

    private JButton startButton;

    private JButton quitButton;

    private VisualNovelUI visualGamePanel;

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

    private void createUIComponents() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("startBackgroundMusic.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        // clip.loop(Clip.LOOP_CONTINUOUSLY); //Uncomment for music

        //cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("cursor.png");
        Point p = new Point(0,0);
        Cursor c = toolkit.createCustomCursor(cursorIcon, p, "cursor.png");
        startFrame.setCursor(c); //https://www.youtube.com/watch?v=UnzpZj77hYE


        // background
        ImageIcon startBackground = new ImageIcon("startBackground.JPG");
        backgroundPanel = new BackgroundPanel(startBackground.getImage());
        startFrame.setContentPane(backgroundPanel);


        // startButton
        ImageIcon startButtonIcon = new ImageIcon("startButtonIcon.png");
        startButton = new JButton(startButtonIcon);
        startButton.setText("Visual Novel Start");
        startButton.setBounds(1150,200,360,122);
        startButton.addActionListener(this);
        startFrame.add(startButton);

        // quitButton
        ImageIcon quitButtonIcon = new ImageIcon("quitButtonIcon.png");
        quitButton = new JButton(quitButtonIcon);
        quitButton.setText("Quit");
        quitButton.setBounds(1175,400,295,122);
        quitButton.addActionListener(this);
        startFrame.add(quitButton);

        // icon
        ImageIcon titleIcon = new ImageIcon("goodEndingIcon.JPG");
        startFrame.setIconImage(titleIcon.getImage());
        startFrame.setTitle("Win Napoleon's Heart! : Ready To Start");
        startFrame.setSize(1000, 700);
        startFrame.setLocation(450, 100);
        startFrame.setLayout(null);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Visual Novel Start")) {
                clip.stop();
                visualGamePanel = new VisualNovelUI();
                startFrame.dispose();
            } else if (button.getText().equals("Quit")) {
                System.exit(0);
            }
        }
    }
}

