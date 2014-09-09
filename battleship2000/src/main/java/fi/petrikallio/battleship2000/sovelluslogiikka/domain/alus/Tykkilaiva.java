
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;

public class Tykkilaiva extends Alus {
    
    public Tykkilaiva() {
        super(3, 1);
    }
    
    @Override
    public String toString() {
        return "tykilaiva";
    }
}
