
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Pelikentta implements Kentta {
    private Map<Integer, Map<Integer, Ruutu>> pelikentta;
    private List<Alus> alukset;
    
    public Pelikentta(int leveys, int korkeus) {
        this.pelikentta = luoPelikentta(leveys, korkeus);
    }
            
    @Override
    public Map<Integer, Map<Integer, Ruutu>> haeKentta() {
        return this.pelikentta;
    }

    private Map<Integer, Map<Integer, Ruutu>> luoPelikentta(int leveys, int korkeus) {
        Map<Integer, Map<Integer, Ruutu>> kentta = new TreeMap<>();
        
        for (int i = 0; i < korkeus; i++) {
            kentta.put(i, new TreeMap<Integer, Ruutu>());
            
            for (int j = 0; j < leveys; j++) {
                kentta.get(i).put(j, new Ruutu(j, i));
            }
        }
        
        return kentta;
    }

    public int haeLeveys() {
        return this.pelikentta.get(0).size();
    }

    public int haeKorkeus() {
        return this.pelikentta.size();
    }
}
