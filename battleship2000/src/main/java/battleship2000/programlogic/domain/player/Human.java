/**
 * A user-controlled player class that inherits the abstract Player class.
 * Controlled by the user, this player class contains the player's choices
 * in placing the ships and choosing
 */
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.control.HumanChooseASquareToBomb;
import battleship2000.programlogic.control.HumanPlaceShips;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.Table;
import battleship2000.programlogic.domain.table.Square;

public class Human extends Player {
    
    public Human() {
        
    }
    
    public Human(BattleShipGame game) {
        super(game);
    }
    
    @Override
    public Square chooseASquare(Table table, boolean aSquareCanBeHitMultipleTimes) {
        HumanChooseASquareToBomb chooseASquare = new HumanChooseASquareToBomb(this, table);
        return (Square)chooseASquare.execute();
//        Scanner reader = new Scanner(System.in);
//        Square square = null;
//        
//        while (square == null) {
//            System.out.println("Choose a square to bomb!");
//            System.out.print("x: ");
//            int x = Integer.parseInt(reader.nextLine());
//            System.out.print("y: ");
//            int y = Integer.parseInt(reader.nextLine());
//
//            if (x > -1 && x < table.getWidth() - 1 && y > - 1 && y < table.getHeight()) {
//                if (!table.getTable().get(y).get(x).isHit() 
//                        || aSquareCanBeHitMultipleTimes) {
//                    square = table.getTable().get(y).get(x);
//                }
//            }
//        }
//        
//        return square;
    }
    
    @Override
    public String toString() {
        return "human";
    }

    @Override
    public void placeShipsOnTable() {
        HumanPlaceShips shipPlacementControl = new HumanPlaceShips(this);
        shipPlacementControl.execute();
    
//        Scanner reader = new Scanner(System.in);
//        List<Ship> ships = super.getShips();
//        while (true) {
//            System.out.println("choose a ship, any other key ends:");
//            for (int i = 0; i < ships.size(); i++) {
//                String placed = "";
//                if (ships.get(i).isOnTable()) {
//                    placed = "Ok!";
//                }
//                
//                System.out.println(i + 1 + ". " + ships.get(i) + " " + placed);
//            }
//            String choice = reader.nextLine();
//
//            if (choice.equals("1")) {
//                placeAShipOnTable(ships.get(0));
//            } else if (choice.equals("2")) {
//                placeAShipOnTable(ships.get(1));
//            } else if (choice.equals("3")) {
//                placeAShipOnTable(ships.get(2));
//            } else if (choice.equals("4")) {
//                placeAShipOnTable(ships.get(3));
//            } else if (choice.equals("5")) {
//                placeAShipOnTable(ships.get(4));
//            } else {
//                boolean allSet = true;
//                for (Ship ship : ships) {
//                    if (!ship.isOnTable()) {
//                        allSet = false;
//                    }
//                }
//                if (!allSet) {
//                    System.out.println("Please place all the ships!");
//                } else {
//                    System.out.println("Ok!");
//                    break;
//                }
//            }
//        }
    }

    private void placeAShipOnTable(Ship ship) {
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Place ship " + ship);
//        while (true) {
//            System.out.println("Give coordinates");
//            System.out.print("x: ");
//            int x = Integer.parseInt(reader.nextLine());
//            System.out.print("y: ");
//            int y = Integer.parseInt(reader.nextLine());
//            if (ship.setPosition(x, y)) {
//                System.out.print("Position set, are you happy? (y/n) ");
//                String ok = reader.nextLine();
//                if (ok.equals("y")) {
//                    break;
//                }
//            } else {
//                System.out.println("Collision!");
//            }
//        }
//        
//        System.out.println("");
//        printTable();
//        System.out.println("");
//        
//        while (true) {
//            System.out.println("Ship's direction is now " + ship.getDirection());
//            System.out.print("Do you want to change the direction? (y/n) ");
//            String will = reader.nextLine();
//            if (will.equals("y")) {
//                System.out.println("Turn the ship clockwise or counter clockwise");
//                System.out.print("(m/v)");
//                String direction = reader.nextLine();
//                
//                if (direction.equals("m")) {
//                    if (!ship.turnClockwise()) {
//                        System.out.println("Collision!");
//                    }
//                } else if (direction.equals("v")) {
//                     if (!ship.turnCounterClockwise()) {
//                         System.out.println("Collision!");
//                     }
//                }
//            }
//            System.out.print("Is ok? (y/n) ");
//            String ok = reader.nextLine();
//            
//            if (ok.equals("y")) {
//                break;
//            }
//        }
//        
//        System.out.println("");
//        super.printTable();
//        System.out.println("");
    }
}
