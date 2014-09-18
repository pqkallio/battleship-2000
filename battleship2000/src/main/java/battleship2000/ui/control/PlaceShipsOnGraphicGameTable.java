
//package battleship2000.ui.control;
//
//import battleship2000.programlogic.LogicObserver;
//import battleship2000.programlogic.control.HumanPlaceShips;
//import battleship2000.programlogic.control.SetShipsPosition;
//import battleship2000.programlogic.domain.position.Position;
//import battleship2000.programlogic.domain.ship.Ship;
//import battleship2000.programlogic.domain.table.Table;
//import battleship2000.ui.observers.GameTableObserver;
//import battleship2000.ui.observers.ObserverCode;
//import battleship2000.ui.panes.GraphicGameTable;
//import battleship2000.ui.panes.TableSquare;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class PlaceShipsOnGraphicGameTable {
//    private TableSquare square;
//    private int tick;
//    private boolean isSet;
//    private GraphicGameTable visualTable;
//    
//    public PlaceShipsOnGraphicGameTable(GraphicGameTable visualTable) {
//        this.visualTable = visualTable;
//        this.tick = 0;
//    }
//    
//    @Override
//    public void update(Object object) {
//        if (object.getClass() == HumanPlaceShips.class) {
//            try {
//                placeShips((HumanPlaceShips) object);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(PlaceShipsOnGraphicGameTable.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private void placeShips(HumanPlaceShips humanPlaceShips) throws InterruptedException {
//        List<Ship> ships = humanPlaceShips.getPlayer().getShips();
//        Table table = humanPlaceShips.getPlayer().getTable();
//        
//        for (int i = 0; i < ships.size(); i++) {
//            placeTheShipOnTheTable(table, ships.get(i));
//        }
//    }
//
//    private void placeTheShipOnTheTable(Table table, Ship ship) throws InterruptedException {
//        isSet = false;
//        
//        while (!isSet) {
//            int currentTick = this.tick;
//            
//            while (this.tick == currentTick) {
//                Thread.sleep(100);
//            }
//            
//            if (this.square != null) {
//                int x = this.square.getSquare().getX();
//                int y = this.square.getSquare().getY();
//                SetShipsPosition positioning = new SetShipsPosition(ship);
//                Position[] positions = positioning.getPositions();
//                Map<Integer, Map<Integer, TableSquare>> tableSquares = visualTable.getTableSquares();
//                
//                if (positioning.setShipsPosition(x, y)) {
//                    for (int i = 0; i < positions.length; i++) {
//                        tableSquares.get(positions[i].getY()).get(positions[i].getX()).makeGray();
//                    }
//                } else {
//                    for (int i = 0; i < positions.length; i++) {
//                        tableSquares.get(positions[i].getY()).get(positions[i].getX()).redden();
//                    }
//                }
//                
//                
//            }
//        }
//    }
//
//    @Override
//    public void updateSquare(Object object) {
//        if (object.getClass() == TableSquare.class) {
//            this.square = (TableSquare)object;
//        } else if (object.getClass() == ObserverCode.class) {
//            if ((ObserverCode) object == ObserverCode.SET_SHIP) {
//                isSet = true;
//            }
//        }
//        incrementTick();
//    }
//
//    private void incrementTick() {
//        if (tick < Integer.MAX_VALUE) {
//            tick++;
//        } else {
//            tick = 0;
//        }
//    }
//}
