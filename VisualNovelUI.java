import javax.swing.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.LayoutManager;

public class VisualNovelUI extends JFrame implements ActionListener{

    private JPanel visualNovelPanel;
    private JLabel dialogueLabel;
    private JLabel optionsLabel;
    private JTextField choiceTextField;

    public VisualNovelUI() {
        add(new JLabel("Visual"));
        createUIComponents();
        setContentPane(visualNovelPanel);
    }

    private void createUIComponents() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
