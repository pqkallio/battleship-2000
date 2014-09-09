
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

public class Aluksenosa implements Liikkuva, Osuttava {
    private Alus emoalus;
    private int x;
    private int y;
    private boolean ehja;
    private boolean aluksenPaa;
    private boolean aluksenPera;
    
    public Aluksenosa(Alus emoalus) {
        this.ehja = true;
        this.aluksenPaa = false;
        this.aluksenPera = false;
        this.emoalus = emoalus;
        this.x = 0;
        this.y = 0;
    }
    
    public Aluksenosa() {
        this(null);
    }
    
    public void asetaSijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void osuma() {
        this.ehja = false;
    }

    @Override
    public boolean onEhja() {
        return ehja;
    }

    public void asetaAluksenPaa() {
        this.aluksenPaa = true;
        this.aluksenPera = false;
    }

    public void asetaAluksenPera() {
        this.aluksenPaa = false;
        this.aluksenPera = true;
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
        asetaSijainti(this.x + dx,
                this.y + dy);
    }

    @Override
    public void liiku() {}

    private int tarkastettuSijaintiX(int sijainti) {
        int max = emoalus.getPelikentta().haeLeveys() - 1;
        
        if (sijainti < 0) return 0;
        else if (sijainti > max) return max;
        else return sijainti;
    }

    @Override
    public boolean onTuhottu() {
        return !this.ehja;
    }

    public Alus getEmoalus() {
        return emoalus;
    }

    private int tarkastettuSijaintiY(int sijainti) {
        int max = emoalus.getPelikentta().haeKorkeus()- 1;
        
        if (sijainti < 0) return 0;
        else if (sijainti > max) return max;
        else return sijainti;
    }
}
