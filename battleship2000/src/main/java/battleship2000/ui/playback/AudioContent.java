
package battleship2000.ui.playback;

import battleship2000.ui.BattleshipGui;

public class AudioContent implements Runnable {
    private Audible explosion;
    private Audible setShip;
    private Audible takeShip;
    
    public AudioContent(BattleshipGui gui) {
        this.explosion = new AudioClip("/audio/explosion.wav", gui);
        this.setShip = new AudioClip("/audio/set_ship.wav", gui);
        this.takeShip = new AudioClip("/audio/take_ship.wav", gui);
    }

    public Audible getExplosion() {
        return explosion;
    }

    public Audible getSetShip() {
        return setShip;
    }

    public Audible getTakeShip() {
        return takeShip;
    }

    @Override
    public void run() {
    }
}
