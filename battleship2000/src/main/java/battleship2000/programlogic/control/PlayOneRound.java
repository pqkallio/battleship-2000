
package battleship2000.programlogic.control;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.StateChange;
import battleship2000.programlogic.domain.player.Computer;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.points.Points;
import battleship2000.programlogic.domain.table.Square;

/**
 * This controller class is responsible for the actions of a single round,
 * where every player bombs an enemy square and is rewarded points for it.
 * 
 * @author Petri
 */
public class PlayOneRound implements Controller {
    private BattleShipGame game;
    private Square usersChoice;
    
    /**
     * In the constructor the user's choice of square to bomb is given as a 
     * parameter. Thus the user's choice must be already made before a new
     * PlayOneRound object can be instantiated.
     * 
     * @param game          the {@link battleship2000.programlogic.BattleShipGame}
     * @param usersChoice   the pre-made choice of which square to bomb
     */
    public PlayOneRound(BattleShipGame game, Square usersChoice) {
        this.game = game;
        this.usersChoice = usersChoice;
    }

    /**
     * Starts the round and performs needed actions.
     * <p>
     * One round consists of the following actions done for each of the game's players:
     * <ol>
     *      <li>The users choice of square is bombed, points added to the player</li>
     *      <li>The users hit or miss count is incremented according to the result of bombing the square</li>
     *      <li>The game's {@link battleship2000.programlogic.observers.LogicObserver}s are notified</li>
     *      <li>Check if the player won the game</li>
     *      <ul>
     *          <li>If the player won, the game ends</li>
     *          <li>Otherwise the game continues</li>
     *      </ul>
     * </ol>
     * <p>
     * <strong>Note!</strong> If one of the players wins the game, the game ends immediately and all
     * the remaining players' turns are not played.
     * 
     * @return      0 (irrelevant)
     */
    @Override
    public Object execute() {
        Human human = (Human)game.getHuman();
        
        int points = usersChoice.bomb();
        human.addPoints(points);
        
        addHitOrMiss(human, usersChoice);
        
        game.notifyObservers(StateChange.UPDATE_TABLE, game.getComputer());
        game.notifyObservers(StateChange.UPDATE_POINTS, 0);
        
        if (usersChoice.getSetShipPart() != null) {
            game.notifyObservers(StateChange.SHIP_HIT, 0);
        }
        
        if (game.getComputer().allShipsDestroyed()) {
            endGame(human);
            return 0;
        }
        
        computerMakeYourMove();

        if (game.getHuman().allShipsDestroyed()) {
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
        
        if (squareToHit.getSetShipPart() != null) {
            game.notifyObservers(StateChange.SHIP_HIT, 0);
        }
    }

    private void addHitOrMiss(Player player, Square square) {
        if (square.getSetShipPart() != null) {
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
