package battleship2000.ui.listeners;

import battleship2000.programlogic.control.ShipPlacement;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.ui.panes.VisualSquare;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A mouse listener used for placing and setting a ship on the table.
 *
 * @author Petri Kallio
 */
public class ShipPlacementListener implements MouseListener {
    private VisualSquare square;
    private Position[] shipInSquarePartsPositioning;
    
    public ShipPlacementListener(VisualSquare square) {
        this.square = square;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
        if (me.getButton() == MouseEvent.BUTTON1) {
            if (square.getPartOf().getChosenShip() == null) {
                if (square.getSquare().getShipPart() == null) {
                    return;
                } else {
                    rechoosePlacedShip();
                }
            } else if (square.getPartOf().getGameCommands().setShipOnTable(this.square.getPartOf().getChosenShip(), square.getSquare().getX(), square.getSquare().getY())) {
                placeShipOnTable();
            }
        } else if (me.getButton() == MouseEvent.BUTTON3) {
            turnTheShip();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (square.getPartOf().getChosenShip() == null) {
            return;
        }
        
        Ship ship = this.square.getPartOf().getChosenShip();
        if (!ship.isOnTable()) {
            highlight(ship);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (square.getPartOf().getChosenShip() == null) {
            return;
        }
        
        removeHighlight();
    }
    
    private void removeHighlight() {
        if (shipInSquarePartsPositioning != null) {
            for (VisualSquare tableSquare : square.getPartOf().getSquares()) {
                for (Position position : shipInSquarePartsPositioning) {
                    if (tableSquare.getSquare().getX() == position.getX() 
                            && tableSquare.getSquare().getY() == position.getY()) {
                        tableSquare.removeHighlight();
                    }
                }
            }

            shipInSquarePartsPositioning = null;
        }
    }

    private void highlight(Ship ship) {
        ShipPlacement positioning = new ShipPlacement(ship);
        if (positioning.setShipsPosition(square.getSquare().getX(), square.getSquare().getY())) {
            shipInSquarePartsPositioning = positioning.getPositions();
            for (VisualSquare tableSquare : square.getPartOf().getSquares()) {
                for (Position position : shipInSquarePartsPositioning) {
                    if (tableSquare.getSquare().getX() == position.getX() 
                            && tableSquare.getSquare().getY() == position.getY()) {
                        tableSquare.makeGray();
                    }
                }
            }
        } else {
            shipInSquarePartsPositioning = positioning.getPositions();
            for (VisualSquare tableSquare : square.getPartOf().getSquares()) {
                for (Position position : shipInSquarePartsPositioning) {
                    if (tableSquare.getSquare().getX() == position.getX() 
                            && tableSquare.getSquare().getY() == position.getY()) {
                        tableSquare.redden();
                    }
                }
            }
        }
    }

    private void rechoosePlacedShip() {
        Ship ship = square.getSquare().getShipPart().getMotherShip();
        ship.setIsOnTable(false);
        square.getPartOf().setChosenShip(ship);
        square.getPartOf().setOkToBeginGame(false);
        highlight(ship);
        square.getPartOf().repaintAll();
    }

    private void placeShipOnTable() {
        removeHighlight();
        
        for (VisualSquare tableSquare : square.getPartOf().getSquares()) {
            tableSquare.repaint();
        }
        
        square.getPartOf().setChosenShip(null);

        checkIfAllShipsArePlaced();
    }

    private void checkIfAllShipsArePlaced() {
        if (square.getPartOf().getPlayer().shipsArePlaced()) {
            square.getPartOf().setOkToBeginGame(true);
        }
    }

    private void turnTheShip() {
        Ship ship = this.square.getPartOf().getChosenShip();
            removeHighlight();
            ship.turnClockwise();
            highlight(ship);
    }
}
