
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.GameTable;
import battleship2000.programlogic.domain.table.Square;
import java.util.List;
import java.util.Scanner;

public class Human extends Player {
    
    public Human() {
        
    }
    
    public Human(BattleShipGame game) {
        super(game);
    }
    
    @Override
    public Square chooseASquare(Table table, boolean aSquareCanBeHitMultipleTimes) {
        
        
        return null;
    }
    
    @Override
    public String toString() {
        return "human";
    }

    @Override
    public void placeShipsOnTable() {
        Scanner reader = new Scanner(System.in);
        List<Ship> ships = super.getShips();
        while (true) {
            System.out.println("choose a ship, any other key ends:");
            for (int i = 0; i < ships.size(); i++) {
                String placed = "";
                if (ships.get(i).isOnTable()) {
                    placed = "Ok!";
                }
                
                System.out.println(i + 1 + ". " + ships.get(i) + " " + placed);
            }
            String choice = reader.nextLine();

            if (choice.equals("1")) {
                placeAShipOnTable(ships.get(0));
            } else if (choice.equals("2")) {
                placeAShipOnTable(ships.get(1));
            } else if (choice.equals("3")) {
                placeAShipOnTable(ships.get(2));
            } else if (choice.equals("4")) {
                placeAShipOnTable(ships.get(3));
            } else if (choice.equals("5")) {
                placeAShipOnTable(ships.get(4));
            } else {
                boolean allSet = true;
                for (Ship ship : ships) {
                    if (!ship.isOnTable()) {
                        allSet = false;
                    }
                }
                if (!allSet) {
                    System.out.println("Please place all the ships!");
                } else {
                    System.out.println("Ok!");
                    break;
                }
            }
        }
    }

    private void placeAShipOnTable(Ship ship) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Place ship " + ship);
        while (true) {
            System.out.println("Give coordinates");
            System.out.print("x: ");
            int x = Integer.parseInt(reader.nextLine());
            System.out.print("y: ");
            int y = Integer.parseInt(reader.nextLine());
            if (ship.setPosition(x, y)) {
                ship.setIsOnTable(true);
                System.out.print("Position set, are you happy? (y/n) ");
                String ok = reader.nextLine();
                if (ok.equals("y")) {
                    ship.setPosition(x, y);
                    break;
                }
            } else {
                System.out.println("Collision!");
            }
        }
        
        System.out.println("");
        printTable();
        System.out.println("");
        
        while (true) {
            System.out.println("Ship's direction is now " + ship.getHeading());
            System.out.print("Do you want to change the direction? (y/n) ");
            String will = reader.nextLine();
            if (will.equals("y")) {
                System.out.println("Turn the ship clockwise or counter clockwise");
                System.out.print("(m/v)");
                String direction = reader.nextLine();
                
                if (direction.equals("m")) {
                    if (!ship.turnClockwise()) {
                        System.out.println("Collision!");
                    }
                } else if (direction.equals("v")) {
                     if (!ship.turnCounterClockwise()) {
                         System.out.println("Collision!");
                     }
                }
            }
            System.out.print("Is ok? (y/n) ");
            String ok = reader.nextLine();
            
            if (ok.equals("y")) {
                break;
            }
        }
        
        System.out.println("");
        printTable();
        System.out.println("");
    }

    private void printTable() {
        Table table = super.getTable();
        
        for (Integer y : table.getTable().keySet()) {
            for (Square square : table.getTable().get(y).values()) {
                if (square.getShipPart() == null) {
                    System.out.print("O");
                } else {
                    if (square.getShipPart().isShipsFront()) {
                        System.out.print("F");
                    } else if (square.getShipPart().isShipsRear()) {
                        System.out.print("R");
                    } else {
                        System.out.print("X");
                    }
                }
            }
            System.out.println("");
        }
    }
}
