
package battleship2000.ui.playback;

import battleship2000.ui.BattleshipGui;

public class AudioContent {
    private Audible explosion;
    
    public AudioContent(BattleshipGui gui) {
        this.explosion = new AudioClip("src/main/java/battleship2000/media/audio/explosion.wav", gui);
    }

    public Audible getExplosion() {
        return explosion;
    }
}
