package startFolder.assetPanels;

import startFolder.StartController;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AssetButtonPanel extends JPanel implements ActionListener {
    private StartController controller;
    private JButton exitButton;
    private JLabel title;


    private JLabel startLabel;
    private JLabel assetLabel;
    private JLabel quitLabel;


    private JLabel nextAssetLabel;
    private JLabel backAssetLabel;
    private JLabel exitLabel;


    private JLabel enterNameLabel;
    private JLabel nextNovelLabel;
    private JLabel optionLabel;

    private JLabel goodEndLabel;
    private JLabel creditLabel;
    private JLabel badEndLabel;

    public AssetButtonPanel(StartController controller) {
        this.controller = controller;
        setup();
    }

    /*
     * A private helper method used to set the swing components
     */
    private void setup() {
        setLayout(null);

        // title
        title = new JLabel("BUTTONS");
        title.setBounds(520, 30, 750, 100);
        title.setFont(new Font("Raleway", Font.PLAIN, 100));
        title.setForeground(Color.WHITE);
        add(title);

        // exitButton
        ImageIcon exitIcon = new ImageIcon("Assets/buttons/exitButton.png");
        exitButton = new JButton("Exit", exitIcon);
        exitButton.setBounds(1350,30,100,100);
        exitButton.addActionListener(this);
        add(exitButton);

        // startButton
        ImageIcon startIcon = new ImageIcon("Assets/buttons/startButtonIcon.png");
        startLabel = new JLabel(startIcon);
        startLabel.setBounds(100,155,360,162);
        add(startLabel);

        // assetButton
        ImageIcon assetIcon = new ImageIcon("Assets/buttons/assetButton.png");
        assetLabel = new JLabel(assetIcon);
        assetLabel.setBounds(557,166,400,145);
        add(assetLabel);

        // quitButton
        ImageIcon quitIcon = new ImageIcon("Assets/buttons/quitButtonIcon.png");
        quitLabel = new JLabel(quitIcon);
        quitLabel.setBounds(1068,150,295,175);
        add(quitLabel);

        // nextAssetLabel
        ImageIcon nextAssetIcon = new ImageIcon("Assets/buttons/nextAssetButton.png");
        nextAssetLabel = new JLabel(nextAssetIcon);
        nextAssetLabel.setBounds(191,364,185,90);
        add(nextAssetLabel);

        // backAssetLabel
        ImageIcon backAssetIcon = new ImageIcon("Assets/buttons/backAssetButton.png");
        backAssetLabel = new JLabel(backAssetIcon);
        backAssetLabel.setBounds(658,364,185,90);
        add(backAssetLabel);

        // exitLabel
        exitLabel = new JLabel(exitIcon);
        exitLabel.setBounds(1166,360,100,100);
        add(exitLabel);

        // nextNovelLabel
        ImageIcon nextNovelIcon = new ImageIcon("Assets/buttons/nextButton.png");
        nextNovelLabel = new JLabel(nextNovelIcon);
        nextNovelLabel.setBounds(163,508,241, 109);
        add(nextNovelLabel);

        // enterNameLabel
        ImageIcon enterNameIcon = new ImageIcon("Assets/buttons/enterNameButtonIcon.png");
        enterNameLabel = new JLabel(enterNameIcon);
        enterNameLabel.setBounds(620,555,258, 55);
        add(enterNameLabel);

        // optionLabel
        ImageIcon optionIcon = new ImageIcon("Assets/buttons/optionButtonIcon.png");
        optionLabel = new JLabel(optionIcon);
        optionLabel.setBounds(1090,513,250, 100);
        add(optionLabel);

        // goodEndLabel
        ImageIcon goodEndIcon = new ImageIcon("Assets/buttons/restartButtonGood.png");
        goodEndLabel = new JLabel(goodEndIcon);
        goodEndLabel.setBounds(100,660,390, 100);
        add(goodEndLabel);

        // creditLabel
        creditLabel = new JLabel("Credit: wilted_wisteria");
        creditLabel.setBounds(540, 670, 750, 100);
        creditLabel.setFont(new Font("Raleway", Font.BOLD, 40));
        add(creditLabel);

        // badEndLabel
        ImageIcon badEndIcon = new ImageIcon("Assets/buttons/resetButtonDeath.png");
        badEndLabel = new JLabel(badEndIcon);
        badEndLabel.setBounds(1040,660,390, 100);
        add(badEndLabel);

        setSize(1500, 800);
        setBackground(Color.PINK);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;

            // exitButton
            if (button.getText().equals("Exit")) {
                try {
                    controller.replaceCard("assetBack");
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



