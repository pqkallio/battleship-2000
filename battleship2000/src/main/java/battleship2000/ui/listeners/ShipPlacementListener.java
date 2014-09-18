
package battleship2000.ui.listeners;

import battleship2000.programlogic.control.GameCommands;
import battleship2000.programlogic.control.SetShipsPosition;
import battleship2000.programlogic.domain.position.Position;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.ui.panes.TableSquare;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShipPlacementListener implements MouseListener {
    private TableSquare square;
    private Position[] shipInSquarePartsPositioning;
    
    public ShipPlacementListener(TableSquare square) {
        this.square = square;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {
            removeHighlight();
            square.getPartOf().getGameCommands().setShipOnTable(this.square.getPartOf().getPlayer().getShips().get(0), square.getSquare().getX(), square.getSquare().getY());
            for (TableSquare tableSquare : square.getPartOf().getSquares()) {
                tableSquare.repaint();
            }
        } else if (me.getButton() == MouseEvent.BUTTON3) {
            Ship ship = this.square.getPartOf().getPlayer().getShips().get(0);
            removeHighlight();
            ship.turnClockwise();
            highlight(ship);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        Ship ship = this.square.getPartOf().getPlayer().getShips().get(0);
        if (!ship.isOnTable()) {
            highlight(ship);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        removeHighlight();
    }
    
    private void removeHighlight() {
        if (shipInSquarePartsPositioning != null) {
            for (TableSquare tableSquare : square.getPartOf().getSquares()) {
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
        SetShipsPosition positioning = new SetShipsPosition(ship);
        if (positioning.setShipsPosition(square.getSquare().getX(), square.getSquare().getY())) {
            shipInSquarePartsPositioning = positioning.getPositions();
            for (TableSquare tableSquare : square.getPartOf().getSquares()) {
                for (Position position : shipInSquarePartsPositioning) {
                    if (tableSquare.getSquare().getX() == position.getX() 
                            && tableSquare.getSquare().getY() == position.getY()) {
                        tableSquare.makeGray();
                    }
                }
            }
        } else {
            shipInSquarePartsPositioning = positioning.getPositions();
            for (TableSquare tableSquare : square.getPartOf().getSquares()) {
                for (Position position : shipInSquarePartsPositioning) {
                    if (tableSquare.getSquare().getX() == position.getX() 
                            && tableSquare.getSquare().getY() == position.getY()) {
                        tableSquare.redden();
                    }
                }
            }
        }
    }
}
