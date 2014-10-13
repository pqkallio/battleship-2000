
package battleship2000.ui.playback;

import battleship2000.ui.BattleshipGui;
import java.util.Random;

public class AudioContent implements Runnable {
    private Audible explosion;
    private Audible setShip;
    private Audible takeShip;
    private Audible destruction;
    private Audible klik;
    private Audible klok;
    private Audible klikKlok;
    
    public AudioContent(BattleshipGui gui) {
        this.explosion = new AudioClip("/audio/explosion.wav", gui);
        this.setShip = new AudioClip("/audio/set_ship.wav", gui);
        this.takeShip = new AudioClip("/audio/take_ship.wav", gui);
        this.destruction = new AudioClip("/audio/destruction.wav", gui);
        this.klik = new AudioClip("/audio/klik.wav", gui);
        this.klok = new AudioClip("/audio/klok.wav", gui);
        this.klikKlok = new AudioClip("/audio/klik-klok.wav", gui);
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

    public Audible getDestruction() {
        return destruction;
    }

    public Audible getKlik() {
        return klik;
    }

    public Audible getKlok() {
        return klok;
    }

    public Audible getKlikKlok() {
        return klikKlok;
    }
    
    public Audible getKlikOrKlok() {
        double random = new Random().nextDouble();
        
        if (random < 0.5) {
            return klik;
        } else {
            return klok;
        }
    }

    @Override
    public void run() {
    }
}
