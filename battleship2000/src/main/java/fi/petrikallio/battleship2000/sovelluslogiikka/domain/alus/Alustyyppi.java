
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

public enum Alustyyppi {
    LENTOTUKIALUS(Lentotukialus.class),
    OHJUSALUS(Ohjusalus.class),
    SUKELLUSVENE(Sukellusvene.class),
    TULENJOHTOLAIVA(Tulenjohtolaiva.class),
    TYKKILAIVA(Tykkilaiva.class);
    
    private Class luokka;
    
    private Alustyyppi(Class luokka) {
        this.luokka = luokka;
    }

    public Class haeLuokka() {
        return luokka;
    }
}
