
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Aluksenosa;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import java.util.Map;

public interface Kentta {
    public Map<Integer, Map<Integer, Ruutu>> haeKentta();
    public void asetaAlusKentalle(Alus alus);
    public void asetaAlusKentalle(Aluksenosa[] alus);
    public int haeKorkeus();
    public int haeLeveys();
    public void poistaOsienSijoitusKentalta(Aluksenosa[] aluksenosat);
    public void poistaOsienSijoitusKentalta(Alus alus);
}
