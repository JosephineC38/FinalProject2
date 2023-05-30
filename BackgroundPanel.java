import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
//https://tips4java.wordpress.com/2008/10/12/background-panel/ is where I got the code from
public class BackgroundPanel extends JPanel
{
    private Image background;

    public BackgroundPanel(Image background)
    {
        this.background = background;
        setLayout( new BorderLayout() );
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // image scaled
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}