
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

public class Aluksenosa implements Liikkuva {
    private int x;
    private int y;
    public boolean ehja;
    
    public Aluksenosa(int x, int y) {
        this.x = x;
        this.y = y;
        this.ehja = true;
    }
    
    public void asetaSijainti(int x, int y) {
        this.x = x;
        this.y = y;
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
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void liiku() {
        
    }
}
