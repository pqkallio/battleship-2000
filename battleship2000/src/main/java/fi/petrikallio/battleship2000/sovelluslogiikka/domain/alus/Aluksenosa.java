
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;

public class Aluksenosa implements Liikkuva, Osuttava {
    private int x;
    private int y;
    private boolean ehja;
    private boolean aluksenPaa;
    private boolean aluksenPera;
    
    public Aluksenosa(int x, int y) {
        this.x = tarkastettuSijainti(x);
        this.y = tarkastettuSijainti(y);
        this.ehja = true;
        this.aluksenPaa = false;
        this.aluksenPera = false;
    }
    
    public void asetaSijainti(int x, int y) {
        this.x = tarkastettuSijainti(x);
        this.y = tarkastettuSijainti(y);
    }
    
    public void osuma() {
        this.ehja = false;
    }

    @Override
    public boolean onEhja() {
        return ehja;
    }

    public void asetaAluksenPaa(boolean aluksenPaa) {
        this.aluksenPaa = aluksenPaa;
    }

    public void asetaAluksenPera(boolean aluksenPera) {
        this.aluksenPera = aluksenPera;
    }
    
    public boolean onAluksenPaa() {
        return aluksenPaa;
    }

    public boolean onAluksenPera() {
        return aluksenPera;
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

    @Override
    public boolean onTuhottu() {
        return !this.ehja;
    }
}
