package startFolder;

import startFolder.assetPanels.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class StartFrame extends JFrame implements MouseListener {
    private StartController controller;

    private Cursor defaultCursor;

    private CardLayout layout;
    private JPanel panelCards;

    private StartPanel startPanel;
    private AssetPanel assetPanel;
    private AssetSpritePanel assetSpritePanel;
    private AssetButtonPanel assetButtonPanel;
    private AssetBackgroundPanel assetBackgroundPanel;
    private AssetMusicPanel assetMusicPanel;
    private AssetSoundPanel assetSoundPanel;
    private AssetMiscPanel assetMiscPanel;

    public StartFrame(StartController controller) throws LineUnavailableException {
        this.controller = controller;
        setup();
    }

    /*
     * A private helper method that setups up the cardLayout and frame.
     */
    private void setup()  {
        startPanel = new StartPanel(controller);
        assetPanel = new AssetPanel(controller);
        assetSpritePanel = new AssetSpritePanel(controller);
        assetButtonPanel = new AssetButtonPanel(controller);
        assetBackgroundPanel =  new AssetBackgroundPanel(controller);
        assetMusicPanel = new AssetMusicPanel(controller);
        assetSoundPanel = new AssetSoundPanel(controller);
        assetMiscPanel = new AssetMiscPanel(controller);

        panelCards = new JPanel();
        layout = new CardLayout();

        //panelCards
        add(panelCards);
        panelCards.setLayout(layout);
        panelCards.add(startPanel, "start");
        panelCards.add(assetPanel, "asset");
        panelCards.add(assetSpritePanel, "assetSprite");
        panelCards.add(assetButtonPanel, "assetButton");
        panelCards.add(assetBackgroundPanel, "assetBackground");
        panelCards.add(assetMusicPanel, "assetMusic");
        panelCards.add(assetSoundPanel, "assetSound");
        panelCards.add(assetMiscPanel, "assetMisc");
        layout.show(panelCards, "start");

        // cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("Assets/miscellaneous/mouseClickedIcon.png");
        Point p = new Point(0,0);
        defaultCursor = toolkit.createCustomCursor(cursorIcon, p, "Assets/miscellaneous/mouseClickedIcon.png");
        setCursor(defaultCursor); //https://www.youtube.com/watch?v=UnzpZj77hYE

        // icon
        ImageIcon titleIcon = new ImageIcon("Assets/titleIcons/goodEndingIcon.JPG");
        setIconImage(titleIcon.getImage());

        setTitle("Win Napoleon's Heart! : Ready To Start");
        addMouseListener(this);
        setSize(1500, 800);
        setLocation(25, 25);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /*
     * A public method used by StartController to replace the card.
     */
    public void replaceCard(String screen) {
        layout.show(panelCards, screen);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorIcon = toolkit.getImage("Assets/miscellaneous/cursor.png");
        Point p = new Point(0,0);
        Cursor c = toolkit.createCustomCursor(cursorIcon, p, "Assets/miscellaneous/cursor.png");
        setCursor(c);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(defaultCursor);
    }

    // unused methods
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
