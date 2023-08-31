package startFolder.assetPanels;

import startFolder.StartController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AssetBackgroundPanel extends JPanel implements ActionListener {
    private StartController controller;
    private JButton exitButton;


    private Action spaceAction;


    private int count;
    private boolean showComponent;


    private JButton nextButton;
    private JButton backButton;

    public AssetBackgroundPanel(StartController controller) {
        this.controller = controller;
        setup();
    }

    /*
     * A private helper method used to set the swing components
     */
    private void setup() {
        count = 1;
        showComponent = true;
        setLayout(null);

        // exitButton
        ImageIcon exitIcon = new ImageIcon("buttons/exitButton.png");
        exitButton = new JButton("Exit", exitIcon);
        exitButton.setBounds(1350, 30, 100, 100);
        exitButton.addActionListener(this);
        add(exitButton);

        // nextButton
        ImageIcon nextIcon = new ImageIcon("buttons/nextAssetButton.png");
        nextButton = new JButton("Next", nextIcon);
        nextButton.setBounds(1265, 605, 185, 90);
        nextButton.addActionListener(this);
        add(nextButton);

        // backButton
        ImageIcon backIcon = new ImageIcon("buttons/backAssetButton.png");
        backButton = new JButton("Back", backIcon);
        backButton.setBounds(50, 605, 185, 90);
        backButton.addActionListener(this);
        backButton.setVisible(false);
        add(backButton);

        // spaceAction
        JLabel label = new JLabel();
        spaceAction = new SpaceAction();
        InputMap inputMap = label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(' '), "spaceAction");
        label.getActionMap().put("spaceAction", spaceAction);
        add(label);

        setSize(1500, 800);
        setVisible(true);
    }

    /*
     * When space is clicked, used to show/hide interface
     */
    public class SpaceAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(showComponent) {
                showComponent = false;
            } else {
                showComponent = true;
                setComponentVisible(true);
            }
            repaint();
        }
    }

    /*
     * A private helper method changes exit, next and back buttons visibility
     */
    private void setComponentVisible(boolean visible) {
        exitButton.setVisible(visible);
        nextButton.setVisible(visible);
        backButton.setVisible(visible);
    }

    /*
     * Paints the backgrounds
     */
    protected void paintComponent(Graphics g) {
        String backgroundStr = " ";
        if(count <= 0) {
            count = 1;
        } else if (count >= 9) {
            count = 8;
        }
        String titleText = "";
        String creditText = "";
        int creditXPos = 0;
        int titleXText = 640;
        switch (count) {
            case 1:
                backButton.setVisible(false);
                titleText = "Start Screen";
                creditText = "Credit: Fatallium";
                creditXPos = 600;
                titleXText = 637;
                backgroundStr = "backgrounds/startBackground.png";
                break;
            case 2:
                titleText = "Schoolgrounds";
                creditText = "Credit: Kimagure After";
                creditXPos = 585;
                titleXText = 637;
                backgroundStr = "backgrounds/schoolgroundsBackground.png";
                break;
            case 3:
                titleText = "Classroom";
                creditText = "Credit: Kimagure After";
                creditXPos = 580;
                titleXText = 665;
                backgroundStr = "backgrounds/classroomBackground.png";
                break;
            case 4:
                titleText = "Fade to Black";
                creditText = "Credit: fg-a.com";
                creditXPos = 615;
                backgroundStr = "backgrounds/blackBackground.jfif";
                break;
            case 5:
                titleText = "Library";
                creditText = "Credit: Kimagure After";
                titleXText = 695;
                creditXPos = 577;
                backgroundStr = "backgrounds/libraryBackground.png";
                break;
            case 6:
                titleText = "Hallway";
                creditText = "Credit: Kimagure After";
                titleXText = 695;
                creditXPos = 577;
                backgroundStr = "backgrounds/hallwayBackground.png";
                break;
            case 7:
                titleText = "Good Ending";
                creditText = "Credit: Fatallium";
                creditXPos = 610;
                backgroundStr = "backgrounds/goodEndingBackground.JPG"; //Now with Napoleon's uncensored face
                break;
            case 8:
                titleText = "Death Ending";
                creditText = "Credit: Fatallium";
                creditXPos = 610;
                nextButton.setVisible(false);
                backgroundStr = "backgrounds/deathEndingBackground.png";
                break;
            default:
                System.out.println("Unknown Background");
                break;
        }

        ImageIcon backgroundImage = new ImageIcon(backgroundStr);
        Image img = backgroundImage.getImage();
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

        if(showComponent) {
            g.setColor( Color.PINK );
            g.fillRect(250, 550, 1000, 200);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.BLACK);
            g2.drawRect(250, 550, 1000, 200);
            g.setFont(new Font("Courier", Font.BOLD, 35));

            g.drawString(titleText, titleXText, 600);
            g.drawString(creditText, creditXPos, 665);

            g.setFont(new Font("Courier", Font.BOLD, 30));
            g.drawString("Press space to hide/show interface", 500, 725);

        } else {
            setComponentVisible(false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;

            // exitButton
            if (button.getText().equals("Exit")) {
                if(showComponent) {
                    nextButton.setVisible(true);
                    exitButton.setVisible(true);
                    showComponent = true;
                    count = 1;
                    repaint();
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

            } else if (showComponent) {
                // nextButton
                if (button.getText().equals("Next")) {
                    count++;
                    backButton.setVisible(true);
                    if(count == 1) {
                        backButton.setVisible(false);
                    }
                    repaint();

                // backButton
                } else if (button.getText().equals("Back")) {
                    count--;
                    nextButton.setVisible(true);
                    if (count == 8) {
                        nextButton.setVisible(false);
                    }
                    repaint();
                }
            }

        }
    }


}

