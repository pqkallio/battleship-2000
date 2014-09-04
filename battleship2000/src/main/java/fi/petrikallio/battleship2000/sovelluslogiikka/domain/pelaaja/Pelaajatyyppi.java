
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

public enum Pelaajatyyppi {
    IHMINEN(Ihminen.class),
    TIETOKONE(Tietokone.class);
    
    private Class luokka;
    
    private Pelaajatyyppi(Class luokka) {
        this.luokka = luokka;
    }

    public Class haeLuokka() {
        return this.luokka;
    }
}
