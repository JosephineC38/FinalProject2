import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class startUI extends JFrame implements ActionListener {

    private JPanel startPanel;
    private JLabel visualNovelLabel;
    private JButton visualNovelButton;
    private JButton quizButton;
    private QuizUI quizGamePanel;
    private VisualNovelUI visualGamePanel;
    private JLabel quizLabel;
    private CardLayout cards;


    public startUI() {
        cards = new CardLayout();
        startPanel = new JPanel(cards);
        quizGamePanel = new QuizUI();
        visualGamePanel = new VisualNovelUI();
        startPanel.add(visualGamePanel, "Visual");
        startPanel.add(quizGamePanel, "Quiz");
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
        add(startPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object actionSource = e.getSource();
        if (actionSource instanceof JButton) {
            JButton button = (JButton) actionSource;
            if (button.getText().equals("Visual Novel Start")) {
                replaceScreen("Visual");
            } else if (button.getText().equals("Quiz Start")) {
                replaceScreen("Quiz");
            }
        }
    }

    public void replaceScreen(String screen) {
        switch (screen) {
            case "Visual":
                cards.show(startPanel, "Visual");
                break;
            case "Quiz":
                cards.show(startPanel, "Quiz");
                break;
            default:
                System.out.println("ERROR");
        }

    }
}

