import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startUI extends JFrame implements ActionListener {
    private JLabel visualNovelLabel;
    private JButton visualNovelButton;
    private JButton quizButton;
    private JPanel startPanel;
    private JLabel quizLabel;


    public startUI() {
        createUIComponents();
    }

    private void createUIComponents() {
        startPanel.setBackground(Color.BLUE);
        setContentPane(startPanel);
        setTitle("Mini-games");
        startPanel.setBackground(Color.pink);
        setSize(1000, 700);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                // go to DATING SIM
            } else if (button.getText().equals("Quiz Start")) {
                QuizUI quiz = new QuizUI();
            }
        }
    }





}

