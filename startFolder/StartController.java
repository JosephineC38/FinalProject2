package startFolder;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class StartController {
    private StartFrame startFrame;
    private Clip clip;


    public StartController() {
        try {
            start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * A public helper method used to set up the frame and music
     */
    public void start() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        startFrame = new StartFrame(this);
        clip = AudioSystem.getClip();
        startBackgroundMusic("startBackground");
    }

    /*
     * A public helper method that starts the game
     */
    public void visualNovelStart() {
        startFrame.dispose();
        clip.stop();
    }

    /*
     * A public helper method that changes the panel card and title.
     */
    public void replaceCard(String screen) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (screen) {
            case "start":
                startFrame.setTitle("Win Napoleon's Heart! : Ready To Start");
                startBackgroundMusic("startBackground");
                break;
            case "asset":
                startFrame.setTitle("Win Napoleon's Heart! : The Details behind the Man");
                startBackgroundMusic("asset");
                break;
            case "assetBack":
                startFrame.setTitle("Win Napoleon's Heart! : The Details behind the Man");
                screen = "asset";
                break;
            case "assetBackMusic": // for AssetMusic and AssetSound
                startBackgroundMusic("asset");
                startFrame.setTitle("Win Napoleon's Heart! : The Details behind the Man");
                screen = "asset";
                break;
            case "assetSprite":
                startFrame.setTitle("Win Napoleon's Heart! : Napoleon's Portraits (And Louis)");
                break;
            case "assetButton":
                startFrame.setTitle("Win Napoleon's Heart! : Button Mayhem");
                break;
            case "assetMusic":
                startFrame.setTitle("Win Napoleon's Heart! : The Music that Often Accompanies Napoleon");
                clip.stop();
                break;
            case "assetBackground":
                startFrame.setTitle("Win Napoleon's Heart! : Background Havoc");
                break;
            case "assetSound":
                startFrame.setTitle("Win Napoleon's Heart! : Napoleon's Stunning Stab (And other strange sounds)");
                clip.stop();
                break;
            case "assetMisc":
                startFrame.setTitle("Win Napoleon's Heart! : Miscellaneous Moments");
                break;
            default:
                System.out.println("Unaccounted panel");
                break;
        }
        startFrame.replaceCard(screen);
    }

    /*
     * A private helper method that starts the background music.
     */
    private void startBackgroundMusic(String music) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        File file = new File(new String("Assets/backgroundMusic/" + music + "Music.wav"));
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
