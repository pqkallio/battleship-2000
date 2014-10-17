
package battleship2000.ui.playback;

import battleship2000.ui.BattleshipGui;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles the loading and playback of an audio file.
 * 
 * @author Petri Kallio
 */
public class AudioClip implements Audible {
    private Clip audio;
    
    /**
     * Constructs a new instantiation of the class.
     * 
     * @param fileName  the path and name of the audio file to be loaded
     * @param gui       the graphical user interface this AudioClip is related to
     */
    public AudioClip(String fileName, BattleshipGui gui) {
        try {
            InputStream is = this.getClass().getResourceAsStream(fileName);
            InputStream bufferedIs = new BufferedInputStream(is);
            AudioInputStream track = AudioSystem.getAudioInputStream(bufferedIs);
            this.audio = AudioSystem.getClip();
            this.audio.open(track);
        } catch (NullPointerException|IOException|UnsupportedAudioFileException
                |LineUnavailableException ex) {
            gui.alertException("Error loading audio", ex);
        }
    }
    
    @Override
    public void play() {
        rewind();
        this.audio.start();
    }
    
    @Override
    public void stop() {
        this.audio.stop();
    }

    @Override
    public void rewind() {
        this.audio.setFramePosition(0);
    }
}
