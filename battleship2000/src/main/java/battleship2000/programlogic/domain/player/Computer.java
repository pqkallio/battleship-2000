package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.player.ai.ComputerGuessingPattern;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;
import java.util.List;
import java.util.Random;

/**
 * A computer player class that inherits the abstract Player class.
 * This player is the users rival in the game and uses a series of calculations
 * and randomness to place ships and choose user's game table's squares to bomb.
 * 
 * @author Petri Kallio
 */
public class Computer extends Player {
    private ComputerGuessingPattern cgp;
    
    public Computer() {
        
    }
    
    public Computer(BattleShipGame game) {
        super(game);
    }
    
    @Override
    public Square chooseASquare(Table table, boolean aSquareCanBeHitMultipleTimes) {
        if (table.allSquaresAreHit()) {
            return null;
        }
        
        Square chosen = null;
        
        while (chosen == null) {
            chosen = cgp.chooseASquare();
        }
        
        return chosen;
        
    }
    
    @Override
    public String toString() {
        return "COMPUTER";
    }

    @Override
    public void placeShipsOnTable() {
        List<Ship> ships = super.getShips();

        for (Ship ship : ships) {
            placeAShipOnTable(ship);
        }
    }

    private void placeAShipOnTable(Ship ship) {
        while (!ship.isOnTable()) {
            boolean canBePlacedOnTable = false;
            
            while (!canBePlacedOnTable) {
                int x = drawANumber(super.getTable().getWidth());
                int y = drawANumber(super.getTable().getHeight());
                
                if (ship.setPosition(x, y)) canBePlacedOnTable = true;
            }
            
            double directionRaffle = new Random().nextDouble();
            
            if (directionRaffle < 0.25) {
                ship.setDirection(ship.getPossibleDirections().get(0));
            } else if (directionRaffle < 0.5) {
                ship.setDirection(ship.getPossibleDirections().get(1));
            } else if (directionRaffle < 0.75) {
                ship.setDirection(ship.getPossibleDirections().get(2));
            } else {
                ship.setDirection(ship.getPossibleDirections().get(3));
            }
        }
    }

    private int drawANumber(int luku) {
        int luckyNumber = new Random().nextInt(luku);
        return luckyNumber;
    }
    
    @Override
    public void setGame(BattleShipGame game) {
        super.setGame(game);
        this.cgp = new ComputerGuessingPattern(game.getHuman(), game.aSquareCanBeHitMultipleTimes());
    }
}
