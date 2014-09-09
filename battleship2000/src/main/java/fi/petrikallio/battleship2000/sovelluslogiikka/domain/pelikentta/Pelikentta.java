
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Aluksenosa;
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

    @Override
    public int haeLeveys() {
        return this.pelikentta.get(0).size();
    }

    @Override
    public int haeKorkeus() {
        return this.pelikentta.size();
    }
    
    @Override
    public void asetaAlusKentalle(Alus alus) {
        asetaAlusKentalle(alus.getOsat());
    }

    @Override
    public void asetaAlusKentalle(Aluksenosa[] alus) {
        for (Aluksenosa osa : alus) {
            this.pelikentta.get(osa.getY()).get(osa.getX()).setAluksenosa(osa);
        }
    }

    @Override
    public void poistaOsienSijoitusKentalta(Aluksenosa[] aluksenosat) {
        for (Aluksenosa osa : aluksenosat) {
            if (osa.getX() > -1 && osa.getX() < haeLeveys()
                    && osa.getY() > -1 && osa.getY() < haeKorkeus()) {
                this.pelikentta.get(osa.getY()).get(osa.getX()).poistaAluksenosa();
            }
        }
    }

    @Override
    public void poistaOsienSijoitusKentalta(Alus alus) {
        poistaOsienSijoitusKentalta(alus.getOsat());
    }
}
