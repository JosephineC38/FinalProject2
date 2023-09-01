package startFolder;

import visualNovelFolder.VisualNovelUI;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class StartPanel extends JPanel implements ActionListener {
    private StartController controller;
    private VisualNovelUI visualGamePanel;

    private JButton startButton;
    private JButton assetButton;
    private JButton quitButton;

    public StartPanel(StartController controller) {
        this.controller = controller;
        setLayout(null);
        setup();

    }

    /*
     * A private helper method that setups up the panel.
     */
    private void setup() {
        //assetButton
        ImageIcon assetButtonIcon = new ImageIcon("Assets/buttons/assetButton.png");
        assetButton = new JButton("ASSET", assetButtonIcon);
        assetButton.setBounds(1050,460,390,122);
        assetButton.addActionListener(this);
        add(assetButton);

        // startButton
        ImageIcon startButtonIcon = new ImageIcon("Assets/buttons/startButtonIcon.png");
        startButton = new JButton("Visual Novel Start", startButtonIcon);
        startButton.setBounds(1070,310,350,122);
        startButton.addActionListener(this);
        add(startButton);

        // quitButton
        ImageIcon quitButtonIcon = new ImageIcon("Assets/buttons/quitButtonIcon.png");
        quitButton = new JButton("Quit", quitButtonIcon);
        quitButton.setBounds(1100,610,285,122);
        quitButton.addActionListener(this);
        add(quitButton);

        setSize(1500, 800);
        setVisible(true);

    }

    //https://tips4java.wordpress.com/2008/10/12/background-panel/
    @Override
    protected void paintComponent(Graphics g)
    {
        ImageIcon startBackground = new ImageIcon("Assets/backgrounds/startBackground.png");
        Image img = startBackground.getImage();
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;

            // starts the game
            if (button.getText().equals("Visual Novel Start")) {
                controller.visualNovelStart();
                visualGamePanel = new VisualNovelUI();

            // goes to asset panel
            }  else if (button.getText().equals("ASSET")) {
                try {
                    controller.replaceCard("asset");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // exits from the program
            } else if (button.getText().equals("Quit")) {
                System.exit(0);
            }
        }
    }



}
