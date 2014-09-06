
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;

public class Aluksenosa implements Liikkuva {
    private int x;
    private int y;
    public boolean ehja;
    
    public Aluksenosa(int x, int y) {
        this.x = tarkastettuSijainti(x);
        this.y = tarkastettuSijainti(y);
        this.ehja = true;
    }
    
    public void asetaSijainti(int x, int y) {
        this.x = tarkastettuSijainti(x);
        this.y = tarkastettuSijainti(y);
    }
    
    public void osuma() {
        this.ehja = false;
    }

    public boolean onkoEhja() {
        return ehja;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void liiku(int dx, int dy) {
        asetaSijainti(tarkastettuSijainti(this.x + dx),
                this.y = tarkastettuSijainti(this.y + dy));
    }

    @Override
    public void liiku() {}

    private int tarkastettuSijainti(int sijainti) {
        int max = Kokorajoitteet.kentanSivunEnimmaispituus() - 1;
        
        if (sijainti < 0) return 0;
        else if (sijainti > max) return max;
        else return sijainti;
    }
}
