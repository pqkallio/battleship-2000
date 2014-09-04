
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta;

import java.util.Map;

public interface Kentta {
    Map<Integer, Map<Integer, Ruutu>> haeKentta();
}
