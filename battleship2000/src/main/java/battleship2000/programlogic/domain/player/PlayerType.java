
package battleship2000.programlogic.domain.player;

public enum PlayerType {
    HUMAN(Human.class),
    COMPUTER(Computer.class);
    
    private Class luokka;
    
    private PlayerType(Class luokka) {
        this.luokka = luokka;
    }

    public Class getPlayerClass() {
        return this.luokka;
    }
}
