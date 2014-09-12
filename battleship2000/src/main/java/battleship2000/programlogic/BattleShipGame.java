
package battleship2000.programlogic;

import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.programlogic.control.PlayTurn;
import java.util.ArrayList;
import java.util.List;

public class BattleShipGame {
    private List<Player> players;
    private List<Observer> observers;
    private boolean aSquareCanBeHitMultipleTimes;
    private boolean shipsAreMovable;
    private boolean shipsAreSpecialized;
    private boolean continues;
    private int turn;
    private Square chosenSquare;
    
    public BattleShipGame() {
        this.players = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.continues = true;
        this.turn = 0;
        this.chosenSquare = null;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }
    
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setShipsAreMovable(boolean shipsAreMovable) {
        this.shipsAreMovable = shipsAreMovable;
    }

    public void setShipsAreSpecialized(boolean shipsAreSpecialized) {
        this.shipsAreSpecialized = shipsAreSpecialized;
    }

    public void setASquareCanBeHitMultipleTimes(boolean aSquareCanBeHitMultipleTimes) {
        this.aSquareCanBeHitMultipleTimes = aSquareCanBeHitMultipleTimes;
    }
    
    public boolean aSquareCanBeHitMultipleTimes() {
        return this.aSquareCanBeHitMultipleTimes;
    }

    public boolean shipsAreMovable() {
        return shipsAreMovable;
    }

    public boolean shipsAreSpecialized() {
        return shipsAreSpecialized;
    }
    
    public void run() {
        while (continues) {
            this.turn++;
            
            Player player = getPlayer(turn);
            Player foe = getFoe(turn);
            
            chosenSquare = player.chooseASquare(foe.getTable(), 
                    this.aSquareCanBeHitMultipleTimes);
            
            continues = (boolean) new PlayTurn(player, foe, chosenSquare).execute();
            chosenSquare = null;
        }
    }

    private Player getPlayer(int turn) {
        if (turn % 2 != 0) return this.players.get(0);
        else return this.players.get(1);
    }

    private Player getFoe(int turn) {
        if (turn % 2 == 0) return this.players.get(0);
        else return this.players.get(1);
    }
}
