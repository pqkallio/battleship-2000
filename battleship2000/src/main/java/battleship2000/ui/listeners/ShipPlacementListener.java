package battleship2000.ui.listeners;

import battleship2000.programlogic.control.ShipPlacement;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.table.Square;
import battleship2000.ui.panes.VisualSquare;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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
            if (square.getVisualGameTable().getChosenShip() == null) {
                if (square.getSquare().getSetShipPart() == null) {
                    return;
                } else {
                    rechoosePlacedShip();
                }
            } else if (square.getVisualGameTable().getGameCommands().setShipOnTable(this.square.getVisualGameTable().getChosenShip(), square.getSquare().getX(), square.getSquare().getY())) {
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
        if (square.getVisualGameTable().getChosenShip() == null) {
            return;
        }
        
        Ship ship = this.square.getVisualGameTable().getChosenShip();
        if (!ship.isOnTable()) {
            highlight(ship);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (square.getVisualGameTable().getChosenShip() == null) {
            return;
        }
        
        removeHighlight();
    }
    
    private void removeHighlight() {
        if (shipInSquarePartsPositioning != null) {
            for (VisualSquare tableSquare : square.getVisualGameTable().getSquares()) {
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
        boolean redden = false;
        
        if (!positioning.setShipsPosition(square.getSquare().getX(), square.getSquare().getY())) {
            redden = true;
        }
        
        positioning.addFrontAndRear();
        ShipPart[] parts = ship.getParts();
        shipInSquarePartsPositioning = positioning.getPositions();
        int i = 0;

        for (VisualSquare tableSquare : square.getVisualGameTable().getSquares()) {
            for (Position position : shipInSquarePartsPositioning) {
                if (tableSquare.getSquare().getX() == position.getX() 
                        && tableSquare.getSquare().getY() == position.getY()) {
                    tableSquare.getSquare().floatAPiece(parts[i]);
                    if (redden) tableSquare.redden();
                    else tableSquare.makeGray();
                    i++;
                }
            }
        }
    }

    private void rechoosePlacedShip() {
        Ship ship = square.getSquare().getSetShipPart().getMotherShip();
        ship.setIsOnTable(false);
        square.getVisualGameTable().setChosenShip(ship);
        square.getVisualGameTable().setOkToBeginGame(false);
        highlight(ship);
        
        square.getVisualGameTable().repaintAll();
    }

    private void placeShipOnTable() {
        removeHighlight();
        
        for (VisualSquare tableSquare : square.getVisualGameTable().getSquares()) {
            tableSquare.repaint();
        }
        
        square.getVisualGameTable().setChosenShip(null);

        checkIfAllShipsArePlaced();
    }

    private void checkIfAllShipsArePlaced() {
        if (square.getVisualGameTable().getPlayer().shipsArePlaced()) {
            square.getVisualGameTable().setOkToBeginGame(true);
        }
    }

    private void turnTheShip() {
        Ship ship = this.square.getVisualGameTable().getChosenShip();
            removeHighlight();
            ship.turnClockwise();
            highlight(ship);
    }
}
