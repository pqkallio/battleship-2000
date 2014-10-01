
package battleship2000.programlogic.control;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.StateChange;
import battleship2000.programlogic.domain.player.Computer;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.points.Points;
import battleship2000.programlogic.domain.table.Square;

public class PlayOneRound implements Controller {
    private BattleShipGame game;
    private Square usersChoice;
    
    public PlayOneRound(BattleShipGame game, Square usersChoice) {
        this.game = game;
        this.usersChoice = usersChoice;
    }

    @Override
    public Object execute() {
        Human human = (Human)game.getHuman();
        
        int points = usersChoice.bomb();
        human.addPoints(points);
        
        addHitOrMiss(human, usersChoice);
        
        game.notifyObservers(StateChange.UPDATE_TABLE, game.getComputer());
        game.notifyObservers(StateChange.UPDATE_POINTS, 0);
        
        if (game.getComputer().allShipsDestroyed()) {
            endGame(human);
            return 0;
        }

        computerMakeYourMove();

        if (game.getComputer().allShipsDestroyed()) {
            endGame(game.getComputer());
        }
        
        return 0;
    }
    
    private void computerMakeYourMove() {
        Human human = (Human)game.getHuman();
        Computer computer = (Computer)game.getComputer();
        Square squareToHit = computer.chooseASquare(human.getTable(), game.aSquareCanBeHitMultipleTimes());
        
        int points = squareToHit.bomb();
        computer.addPoints(points);
        
        addHitOrMiss(computer, squareToHit);
        
        game.notifyObservers(StateChange.UPDATE_TABLE, game.getHuman());
        game.notifyObservers(StateChange.UPDATE_POINTS, 0);
    }

    private void addHitOrMiss(Player player, Square square) {
        if (square.getShipPart() != null) {
            player.addShotsHit(1);
        } else {
            player.addShotsMissed(1);
        }
    }

    private void endGame(Player player) {
        player.addPoints(Points.WIN.getPoints());
        game.notifyObservers(StateChange.UPDATE_POINTS, 0);
        game.notifyObservers(StateChange.END_GAME, player);
    }
}