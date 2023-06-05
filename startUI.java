import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Allows the user to change between the visual novel and quiz games.
 */
public class startUI extends JFrame implements ActionListener {

    private JFrame startFrame;

    private JPanel startPanel;
    private JLabel visualNovelLabel;
    private JButton visualNovelButton;
    private VisualNovelUI visualGamePanel;


    public startUI() {
        createUIComponents();
    }

    private void createUIComponents() {
//        startFrame = new JFrame();

        setSize(1000, 700);
        setTitle("Win Napoleon's Heart! : Ready To Start");
        add(startPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPanel.setBackground(Color.pink);
        setContentPane(startPanel);
        setLocation(450, 100);
        visualNovelButton.addActionListener(this);
        setVisible(true);
//        startFrame.add(startPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Visual Novel Start")) {
                visualGamePanel = new VisualNovelUI();
                this.dispose();
            }
        }
    }
}

