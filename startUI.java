import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Allows the user to change between the visual novel and quiz games.
 */
public class startUI extends JFrame implements ActionListener {

    private JPanel startPanel;
    private JLabel visualNovelLabel;
    private JButton visualNovelButton;
    private JButton quizButton;
    private QuizUI quizGamePanel;
    private VisualNovelUI visualGamePanel;
    private JLabel quizLabel;


    public startUI() {
        createUIComponents();
    }

    private void createUIComponents() {
        setSize(1000, 700);
        setTitle("Mini-games");
        add(startPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPanel.setBackground(Color.pink);
        setContentPane(startPanel);
        setLocation(450, 100);
        visualNovelButton.addActionListener(this);
        quizButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Visual Novel Start")) {
                visualGamePanel = new VisualNovelUI();
                this.dispose();
            } else if (button.getText().equals("Quiz Start")) {
                quizGamePanel = new QuizUI();
                this.dispose();
            }
        }
    }
}

