
package battleship2000.ui.playback;
/**
 * The implementing classes handle the playback of an audio file.
 * 
 * @author Petri Kallio
 */
public interface Audible {
    
    /**
     * Plays the audio.
     */
    public void play();
    
    /**
     * Stops the audio.
     */
    public void stop();
    
    /**
     * Rewinds the audio to its starting point.
     */
    public void rewind();
}
