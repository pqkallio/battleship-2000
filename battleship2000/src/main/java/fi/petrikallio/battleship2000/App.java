package fi.petrikallio.battleship2000;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaaja;
import fi.petrikallio.battleship2000.sovelluslogiikka.ohjaus.LuoPeli;
import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Perussaannot;

public class App 
{
    public static void main( String[] args )
    {
        BattleShipGame peli = (BattleShipGame) new LuoPeli(new Perussaannot(true)).suorita();
        for (Pelaaja pelaaja : peli.haePelaajat()) {
            System.out.println(pelaaja);
        }
        
        peli.haePelaajat().get(0).sijoitteleAluksetPelikentalle();
    }
}
