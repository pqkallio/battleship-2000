
package battleship2000.ui.playback;

import battleship2000.programlogic.StateChange;
import battleship2000.programlogic.observers.LogicObserver;
import battleship2000.ui.BattleshipGui;
import java.util.Random;

/**
 * Handles the loading and retrieving of the {@link battleship2000.ui.BattleshipGui}'s
 * audio content.
 * 
 * @author Petri Kallio
 */
public class AudioContent implements Runnable, LogicObserver {
    private Audible explosion;
    private Audible setShip;
    private Audible takeShip;
    private Audible destruction;
    private Audible klik;
    private Audible klok;
    private Audible klikKlok;
    private boolean updated;
    
    /**
     * Constructs a new instantiation of the class and creates the 
     * {@link battleship2000.ui.playback.Audible}s needed for the game.
     * 
     * @param gui   the graphical user interface the audio content is related to
     */
    public AudioContent(BattleshipGui gui) {
        this.explosion = new AudioClip("/audio/explosion.wav", gui);
        this.setShip = new AudioClip("/audio/set_ship.wav", gui);
        this.takeShip = new AudioClip("/audio/take_ship.wav", gui);
        this.destruction = new AudioClip("/audio/destruction.wav", gui);
        this.klik = new AudioClip("/audio/klik.wav", gui);
        this.klok = new AudioClip("/audio/klok.wav", gui);
        this.klikKlok = new AudioClip("/audio/klik-klok.wav", gui);
        
        this.updated = false;
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
    
    /**
     * Returns either the sound "klik" or "klok" based on raffle.
     * 
     * @return  a random Audible from the choices
     */
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

    @Override
    public void update(StateChange stateChange, Object... object) {
        if (stateChange.equals(StateChange.END_GAME)) {
            this.destruction.play();
        } else if (stateChange.equals(StateChange.SHIP_HIT)) {
            this.explosion.play();
        } else if (stateChange.equals(StateChange.SHOT_MISSED)) {
            this.setShip.play();
        } else if (stateChange.equals(StateChange.SET_SHIP)) {
            this.setShip.play();
        } else if (stateChange.equals(StateChange.TAKE_SHIP)) {
            this.takeShip.play();
        }
    }

    @Override
    public boolean isUpdated() {
        return this.updated;
    }
}
