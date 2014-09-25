package battleship2000.programlogic.domain.player;

/**
 * The PlayerType enumerator is used to pass different player types from the
 * abstract Rules class to the CreateGame control class used to create a new game.
 * <p>
 * Note! The ERRONEUS_PLAYER enumerator is solely used by the tests to track the 
 * behavior of the game when an InstantiationException is thrown while creating
 * new players for the game while the CreateGame class is executed.
 * 
 * @author Petri Kallio
 */
public enum PlayerType {
    HUMAN(Human.class),
    COMPUTER(Computer.class),
    ERRONEOUS_PLAYER(Player.class);
    
    private Class luokka;
    
    private PlayerType(Class luokka) {
        this.luokka = luokka;
    }

    public Class getPlayerClass() {
        return this.luokka;
    }
}
