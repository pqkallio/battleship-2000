
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Aluksenosa;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pistelasku.Pisteet;

public class Ruutu implements Comparable<Ruutu> {
    private int x;
    private int y;
    private Aluksenosa aluksenosa;
    private boolean pommitettu;
    
    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.pommitettu = false;
    }
    
    public void setAluksenosa(Aluksenosa aluksenosa) {
        this.aluksenosa = aluksenosa;
    }

    public Aluksenosa getAluksenosa() {
        return aluksenosa;
    }
    
    public void poistaAluksenosa() {
        this.aluksenosa = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPommitettu() {
        return pommitettu;
    }
    
    public int pommita() {
        this.pommitettu = true;
        
        if (aluksenosa != null) {
            if (aluksenosa.getEmoalus().onTuhottu()) {
                return Pisteet.TUHOSI.getPisteet();
            } else {
                return Pisteet.OSUI.getPisteet();
            }
        }
        
        return Pisteet.OHI.getPisteet();
    }
    
    @Override
    public int compareTo(Ruutu verrattavaRuutu) {
        if (this.x < verrattavaRuutu.getX()) return -1;
        else if (this.x > verrattavaRuutu.getX()) return 1;
        else {
            if (this.y < verrattavaRuutu.getY()) return -1;
            else if (this.y > verrattavaRuutu.getY()) return 1;
            else return 0;
        }
    }
}
