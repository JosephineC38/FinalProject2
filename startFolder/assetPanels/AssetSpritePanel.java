package startFolder.assetPanels;

import startFolder.StartController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AssetSpritePanel extends JPanel implements ActionListener {
    private StartController controller;
    private JButton exitButton;
    private JLabel title;

    private JLabel louisLabel;
    private JLabel napAngryLabel;
    private JLabel napDefaultLabel;
    private JLabel napHappyLabel;
    private JLabel napFuriousLabel;
    private JButton switchButton;

    public AssetSpritePanel(StartController controller) {
        this.controller = controller;
        setup();

    }

    /*
     * A private helper method used to set the swing components
     */
    private void setup() {
        setLayout(null);

        // title
        title = new JLabel("SPRITES");
        title.setBounds(570,30,750,100);
        title.setFont(new Font("Raleway", Font.PLAIN, 100));
        title.setForeground(Color.WHITE);
        add(title);

        // exitButton
        ImageIcon exitIcon = new ImageIcon("Assets/buttons/exitButton.png");
        exitButton = new JButton("Exit", exitIcon);
        exitButton.setBounds(1350,30,100,100);
        exitButton.addActionListener(this);
        add(exitButton);

        // switchButton
        ImageIcon nextIcon = new ImageIcon("Assets/buttons/nextAssetButton.png");
        switchButton = new JButton("Next", nextIcon);
        switchButton.setBounds(1275, 600, 185, 90);
        switchButton.addActionListener(this);
        add(switchButton);

        // screenOne labels

        // louisLabel
        louisLabel = new JLabel("<html> Portrait of Louis XVI <br/> Credit: Antoine-François Callet </html>");
        setLabel(louisLabel, "louisSpriteIcon");
        louisLabel.setBounds(100, 150, 350, 600);

        // napDefaultLabel
        napDefaultLabel = new JLabel("<html> Default Napoleon <br/> Credit: Fatallium </html>");
        setLabel(napDefaultLabel, "napoleonDefaultSprite");
        napDefaultLabel.setBounds(500, 100, 350, 650);

        // napHappyLabel
        napHappyLabel = new JLabel("<html> Happy Napoleon <br/> Credit: Fatallium </html>");
        setLabel(napHappyLabel, "napoleonHappySprite");
        napHappyLabel.setBounds(900, 100, 350, 650);

        // screenTwo labels

        // napAngryLabel
        napAngryLabel = new JLabel("<html> Angry Napoleon <br/> Credit: Fatallium </html>");
        setLabel(napAngryLabel, "napoleonAngrySprite");
        napAngryLabel.setBounds(300, 100, 350, 650);

        // napFuriousLabel
        napFuriousLabel = new JLabel("<html> Furious Napoleon <br/> Credit: Fatallium </html>");
        setLabel(napFuriousLabel, "napoleonFuriousJojoSprite");
        napFuriousLabel.setBounds(800, 100, 350, 650);

        screenTwo(false);
        setSize(1500, 800);
        setBackground(Color.PINK);
        setVisible(true);
    }

    /*
     * A private helper method used to create labels
     */
    private void setLabel(JLabel label, String iconString) {
        ImageIcon icon = new ImageIcon("Assets/sprites/" + iconString + ".png");
        label.setIcon(icon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setFont(new Font("Courier", Font.PLAIN,22));
        label.setIconTextGap(5);
        add(label);
    }

    /*
     * A private helper method used to change the visibility of screenOne labels
     */
    private void screenOne (boolean visible) {
        louisLabel.setVisible(visible);
        napDefaultLabel.setVisible(visible);
        napHappyLabel.setVisible(visible);
    }

    /*
     * A private helper method used to change the visibility of screenTwo labels
     */
    private void screenTwo(boolean visible) {
        napAngryLabel.setVisible(visible);
        napFuriousLabel.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;

            // exitButton
            if (button.getText().equals("Exit")) {
                try {
                    switchButton.setText("Next");
                    switchButton.setBounds(1290, 600, 150, 100);
                    screenOne(true);
                    screenTwo(false);
                    controller.replaceCard("assetBack");
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            // switch to switchTwo
            } else if (button.getText().equals("Next")) {
                ImageIcon backIcon = new ImageIcon("Assets/buttons/backAssetButton.png");
                switchButton.setText("Back");
                switchButton.setIcon(backIcon);
                switchButton.setBounds(35, 600, 185, 90);
                screenOne(false);
                screenTwo(true);

            // switch to switchOne
            } else if (button.getText().equals("Back")) {
                ImageIcon nextIcon = new ImageIcon("Assets/buttons/nextAssetButton.png");
                switchButton.setText("Back");
                switchButton.setIcon(nextIcon);
                switchButton.setText("Next");
                switchButton.setBounds(1275, 600, 185, 90);
                screenOne(true);
                screenTwo(false);
            }
        }
    }

}
