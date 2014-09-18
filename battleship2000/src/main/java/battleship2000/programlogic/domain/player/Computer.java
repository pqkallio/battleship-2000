
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;
import java.util.List;
import java.util.Random;

public class Computer extends Player {

    public Computer() {
        
    }
    
    public Computer(BattleShipGame game) {
        super(game);
    }
    
    @Override
    public Square chooseASquare(Table table, boolean aSquareCanBeHitMultipleTimes) {
        int x = 0;
        int y = 0;
        
        while (true) {
            x = drawANumber(table.getWidth());
            y = drawANumber(table.getHeight());
            
            if (aSquareCanBeHitMultipleTimes 
                    || !table.getTable().get(y).get(x).isHit() ) {
                break;
            }
        }
        
        return table.getTable().get(y).get(x);
    }
    
    @Override
    public String toString() {
        return "computer";
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
            int x = drawANumber(super.getTable().getWidth());
            int y = drawANumber(super.getTable().getHeight());

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
            
            ship.setPosition(x, y);
        }
    }

    private int drawANumber(int luku) {
        int luckyNumber = new Random().nextInt(luku);
        return luckyNumber;
    }
}
