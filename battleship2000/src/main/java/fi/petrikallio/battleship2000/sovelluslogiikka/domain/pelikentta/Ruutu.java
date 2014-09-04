
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Aluksenosa;

public class Ruutu implements Comparable<Ruutu> {
    private int x;
    private int y;
    private Aluksenosa aluksenosa;
    
    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setAluksenosa(Aluksenosa aluksenosa) {
        this.aluksenosa = aluksenosa;
    }

    public Aluksenosa getAluksenosa() {
        return aluksenosa;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
