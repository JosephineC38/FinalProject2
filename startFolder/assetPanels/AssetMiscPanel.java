package startFolder.assetPanels;
import startFolder.StartController;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AssetMiscPanel extends JPanel implements ActionListener  {
    private StartController controller;
    private JButton exitButton;
    private JLabel title;


    private JLabel burgundyLabel;
    private JLabel llamaLabel;
    private JLabel swordLabel;
    private JLabel cursorLabel;
    private JLabel clickedLabel;
    private JLabel selectLabel;
    private JLabel speakerLabel;
    private JLabel textLabel;


    public AssetMiscPanel(StartController controller) {
        this.controller = controller;
        setup();
    }

    /*
     * A private helper method used to set the swing components
     */
    private void setup() {
        setLayout(null);

        // title
        title = new JLabel("MISCELLANEOUS");
        title.setBounds(400,30,1000,100);
        title.setFont(new Font("Raleway", Font.PLAIN, 90));
        title.setForeground(Color.WHITE);
        add(title);

        // exitButton
        ImageIcon exitIcon = new ImageIcon("buttons/exitButton.png");
        exitButton = new JButton("Exit", exitIcon);
        exitButton.setBounds(1350,30,100,100);
        exitButton.addActionListener(this);
        add(exitButton);

        // swordLabel
        swordLabel = new JLabel("<html> Sword Gift <br/> Credit: Fatallium </html>");
        createLabel(swordLabel, "sword", 0, 0);

        // llamaLabel
        llamaLabel = new JLabel("<html> Llama Gift <br/> Credit: Fatallium </html>");
        createLabel(llamaLabel, "llamaPlushie", 466, 5);

        // burgundyLabel
        burgundyLabel = new JLabel("<html> Burgundy Gift <br/> Credit: Fatallium </html>");
        createLabel(burgundyLabel, "burgundy", 940, 0);

        // cursorLabel
        cursorLabel = new JLabel("<html> Pink Cursor <br/> Credit: wilted_wisteria </html>");
        createLabel(cursorLabel, "cursor", 20, 130);
        cursorLabel.setIconTextGap(17);

        // clickedLabel
        clickedLabel = new JLabel("<html> Baby Pink Pixel Cursor <br/> Credit: Custom Cursor </html>");
        createLabel(clickedLabel, "mouseClickedIcon", 465, 130);

        // selectLabel
        selectLabel = new JLabel("<html> Pink Text Select Cursor <br/> Credit: wilted_wisteria </html>");
        createLabel(selectLabel, "textSelect", 938, 130);

        // speakerLabel
        speakerLabel = new JLabel("<html> Deep Pink Speaker Icon <br/> Credit: ICONSDB </html>");
        createLabel(speakerLabel, "speakerIcon", 476, 260);
        speakerLabel.setIconTextGap(23);

        // textLabel
        Font font = new Font("Courier", Font.BOLD, 20);
        ImageIcon textIcon = new ImageIcon("miscellaneous/dialogueTextBox.png");
        textLabel = new JLabel(textIcon);
        textLabel.setFont(font);
        textLabel.setText("<html> Dialogue Text Box <br/> Credit: Cloud Novel </html>");
        textLabel.setBounds(-4 ,397, 1500, 500);
        add(textLabel);

        setSize(1500, 800);
        setBackground(Color.PINK);
        setVisible(true);
    }

    /*
     * A private helper method used to create labels
     */
    private void createLabel(JLabel label, String image, int x, int y) {
        Font font = new Font("Courier", Font.BOLD, 20);
        String imgStr = "";
        if(image.equals("sword") || image.equals("llamaPlushie") || image.equals("burgundy")) {
            imgStr = "gifts/" + image + "Transparent.png";
        } else {
            imgStr = "miscellaneous/" + image + ".png";
        }
        ImageIcon icon = new ImageIcon(imgStr);
        Image iconImg = icon.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        icon = new ImageIcon(iconImg);
        label.setIcon(icon);

        label.setFont(font);
        label.setBounds(60 + x, 139 + y, 456, 120);
        label.setIconTextGap(30);
        add(label);
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

