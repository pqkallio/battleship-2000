package fi.petrikallio.battleship2000;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.ohjaus.LuoPeli;
import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Perussaannot;

public class App 
{
    public static void main( String[] args ) 
            throws InstantiationException, IllegalAccessException
    {
        BattleShipGame peli = (BattleShipGame) new LuoPeli(new Perussaannot(false)).suorita();
    }
}
