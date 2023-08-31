package startFolder.assetPanels;

import startFolder.StartController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class AssetPanel extends JPanel implements ActionListener {
    private StartController controller;
    private JButton exitButton;
    private JLabel title;


    private JButton musicButton;
    private JButton buttonButton; //The ultimate button
    private JButton soundButton;
    private JButton miscButton;
    private JButton spriteButton;
    private JButton backgroundButton;

    public AssetPanel(StartController controller) {
        this.controller = controller;
        setup();
    }

    /*
     * A private helper method used to set the swing components
     */
    private void setup() {
        setLayout(null);

        // title
        title = new JLabel("ASSETS");
        title.setBounds(70,30,750,100);
        title.setFont(new Font("Raleway", Font.PLAIN, 90));
        title.setForeground(Color.WHITE);
        add(title);

        // exitButton
        exitButton = new JButton("Exit");
        createButton(exitButton, 450, 135);
        exitButton.setFont(new Font("Raleway", Font.BOLD, 60));

        // backgroundButton
        backgroundButton = new JButton("Backgrounds");
        createButton(backgroundButton, 0, 0);

        // spriteButton
        spriteButton = new JButton("Sprites");
        createButton(spriteButton, 67, 120);

        // musicButton
        musicButton = new JButton("Music");
        createButton(musicButton, 134, 135);

        // soundButton
        soundButton = new JButton("Sounds");
        createButton(soundButton, 201, 110);

        // buttonButton
        buttonButton = new JButton("Buttons");
        createButton(buttonButton, 268, 100);

        // miscButton
        miscButton = new JButton("Misc");
        createButton(miscButton, 335, 160);

        // napLabel
        ImageIcon napIcon = new ImageIcon("sprites/napoleonDefaultSprite.png");
        Image iconImg = napIcon.getImage().getScaledInstance(549, 750, Image.SCALE_DEFAULT);
        napIcon = new ImageIcon(iconImg);
        JLabel napLabel = new JLabel(napIcon);
        napLabel.setBounds(900, 50, 549, 750);
        add(napLabel);

        setSize(1500, 800);
        setVisible(true);
    }

    /*
     * A private helper method used to create buttons
     */
    private void createButton(JButton button, int y, int w) {
        Font font = new Font("Raleway", Font.BOLD, 40);
        button.setFont(font);
        button.setBounds(50,200 + y,290 - w,47);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        add(button);
    }

    //https://tips4java.wordpress.com/2008/10/12/background-panel/
    @Override
    protected void paintComponent(Graphics g)
    {
        ImageIcon startBackground = new ImageIcon("backgrounds/schoolgroundsBackground.png");
        Image img = startBackground.getImage();
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.pink);
        Area a1 = new Area(new Rectangle2D.Double(0, 0, 600, 800));
        Area a2 = new Area(new Ellipse2D.Double(400, -100, 800, 950));
        a1.subtract(a2);
        g2d.fill(a1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;

            // exitButton
            if (button.getText().equals("Exit")) {
                try {
                    controller.replaceCard("start");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // spriteButton
            } else if (button.getText().equals("Sprites")) {
                try {
                    controller.replaceCard("assetSprite");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // buttonButton
            } else if (button.getText().equals("Buttons")) {
                try {
                    controller.replaceCard("assetButton");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // backgroundButton
            } else if (button.getText().equals("Backgrounds")) {
                try {
                    controller.replaceCard("assetBackground");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // musicButton
            } else if (button.getText().equals("Music")) {
                try {
                    controller.replaceCard("assetMusic");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // soundButton
            } else if (button.getText().equals("Sounds")) {
                try {
                    controller.replaceCard("assetSound");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // miscButton
            } else if (button.getText().equals("Misc")) {
                try {
                    controller.replaceCard("assetMisc");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

}
