
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Kentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Ruutu;
import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;
import java.util.ArrayList;
import java.util.List;

public class Alus implements Liikkuva, Kaantyva, Osuttava {
    protected Suunta suunta;
    protected Aluksenosa[] osat;
    protected List<Suunta> mahdollisetSuunnat;
    protected Kentta pelikentta;
    protected int laukauksiaPerKayttokerta;
    protected boolean asetettu;
    
    public Alus() {
        this(3, 1);
    }
    
    public Alus(int toivottuPituus) {
        this(toivottuPituus, 1);
    }
    
    public Alus(int toivottuPituus, int laukauksiaPerKayttokerta) {
        this.mahdollisetSuunnat = new ArrayList<>();
        lisaaMahdollisetSuunnat();
        this.suunta = Suunta.ITA;
        luoAluksenOsat(toivottuPituus, this.suunta);
        this.laukauksiaPerKayttokerta = laukauksiaPerKayttokerta;
        this.asetettu = false;
    }

    public boolean isAsetettu() {
        return asetettu;
    }

    public void setAsetettu(boolean asetettu) {
        this.asetettu = asetettu;
    }
    
    public void setPelikentta(Pelikentta pelikentta) {
        this.pelikentta = pelikentta;
        
        maaritaOsienSijaintiPelikentalla(osat, 0, 0, suunta);
    }

    public Kentta getPelikentta() {
        return pelikentta;
    }
    
    public Aluksenosa[] getOsat() {
        return osat;
    }

    public Suunta getSuunta() {
        return suunta;
    }

    public void setSuunta(Suunta suunta) {
        if (mahdollisetSuunnat.contains(suunta)) {
            this.suunta = suunta;
            maaritaOsienSijaintiPelikentalla(osat, osat[0].getX(), 
                    osat[0].getY(), suunta);
        }
    }

    public List<Suunta> getMahdollisetSuunnat() {
        return mahdollisetSuunnat;
    }

    public int getLaukauksiaPerKayttokerta() {
        return laukauksiaPerKayttokerta;
    }
    
    @Override
    public void liiku(int dx, int dy) {
        for (Aluksenosa aluksenosa : osat) {
            aluksenosa.liiku(dx, dy);
        }
        
        tarkastaOsienSijaintiPelikentalla(osat);
    }
    
    public void asetaSijainti(int x, int y) {
        maaritaOsienSijaintiPelikentalla(osat, x, y, suunta);
    }
    
    @Override
    public void liiku() {
        boolean voiLiikkua = true;
        
        for (Aluksenosa aluksenosa : osat) {
            if (aluksenosa.getX() + suunta.getDx() < 0
                    || aluksenosa.getX() + suunta.getDx() > pelikentta.haeLeveys() - 1
                    || aluksenosa.getY() + suunta.getDy() < 0
                    || aluksenosa.getY() + suunta.getDy() > pelikentta.haeKorkeus() - 1) {
                voiLiikkua = false;
            }
        }
        
        if (voiLiikkua) liiku(this.suunta.getDx(), this.suunta.getDy());
    }

    private void luoAluksenOsat(int toivottuPituus, Suunta suunta) {
        int aluksenOsiaLuotava = palautaLopullinenPituus(toivottuPituus);
        
        Aluksenosa[] aluksenosat = new Aluksenosa[aluksenOsiaLuotava];
        
        for (int i = 0; i < aluksenosat.length; i++) {
            aluksenosat[i] = new Aluksenosa(this);
        }
        
        this.osat = aluksenosat;
    }
    
    public int getAluksenPituus() {
        return osat.length;
    }

    @Override
    public void kaannaMyotapaivaan() {
        this.suunta = haeSeuraavaSuunta(1);
        maaritaOsienSijaintiPelikentalla(osat, osat[0].getX(), osat[0].getY(), 
                suunta);
    }

    @Override
    public void kaannaVastapaivaan() {
        this.suunta = haeSeuraavaSuunta(-1);
        maaritaOsienSijaintiPelikentalla(osat, osat[0].getX(), osat[0].getY(), 
                suunta);
    }

    private void lisaaMahdollisetSuunnat() {
        for (Suunta ilmanSuunta : Suunta.values()) {
            if (ilmanSuunta.getKulma() % 90 == 0) {
                this.mahdollisetSuunnat.add(ilmanSuunta);
            }
        }
    }

    private int palautaLopullinenPituus(int toivottuPituus) {
        int min = Kokorajoitteet.aluksenVahimmaispituus();
        int max = Kokorajoitteet.aluksenEnimmaispituus();
        
        if (toivottuPituus >= min && toivottuPituus <= max) {
            return toivottuPituus;
        }
        
        if (toivottuPituus < min) return min;
        else return max;
    }

    private Suunta haeSeuraavaSuunta(int i) {
        int nykyisenSuunnanIndeksi = this.mahdollisetSuunnat.indexOf(this.suunta);
        
        if (nykyisenSuunnanIndeksi + i >= 0 &&
                nykyisenSuunnanIndeksi + i < this.mahdollisetSuunnat.size()) {
            return this.mahdollisetSuunnat.get(nykyisenSuunnanIndeksi + i);
        }
        
        if (nykyisenSuunnanIndeksi + i < 0) {
            return this.mahdollisetSuunnat.get(this.mahdollisetSuunnat.size()-1);
        }
        
        return this.mahdollisetSuunnat.get(0);
    }

    @Override
    public boolean onEhja() {
        for (Aluksenosa aluksenosa : osat) {
            if (!(aluksenosa.onEhja())) return false;
        }
        
        return true;
    }

//    private Aluksenosa[] sijoitaOsat(int osienLukumaara, Suunta suunta, 
//            int x, int y) {
////        Aluksenosa[] aluksenosat = new Aluksenosa[osienLukumaara];
////        
////        for (int i = 0; i < aluksenosat.length; i++) {
////            aluksenosat[i] = new Aluksenosa(x, y);
////        }
//        
//        maaritaOsienSijaintiPelikentalla(aluksenosat, x, y, suunta);
//        
//        return aluksenosat;
//    }

    private void maaritaOsienSijaintiPelikentalla(Aluksenosa[] aluksenosat, 
            int x, int y, Suunta suunta) {
        
        this.pelikentta.poistaOsienSijoitusKentalta(aluksenosat);
        
        if (suunta == Suunta.ITA || suunta == Suunta.LANSI) {
            for (int i = 0; i < aluksenosat.length; i++) {
                aluksenosat[i].asetaSijainti(x + i, y);
            }
        } else {
            for (int i = 0; i < aluksenosat.length; i++) {
                aluksenosat[i].asetaSijainti(x, y + i);
            }
        }
        
        if (suunta == Suunta.LANSI || suunta == Suunta.POHJOINEN) {
            aluksenosat[0].asetaAluksenPaa();
            aluksenosat[aluksenosat.length - 1].asetaAluksenPera();
        } else {
            aluksenosat[0].asetaAluksenPera();
            aluksenosat[aluksenosat.length - 1].asetaAluksenPaa();
        }
        
        tarkastaOsienSijaintiPelikentalla(aluksenosat);
    }

    @Override
    public boolean onTuhottu() {
        for (Aluksenosa aluksenosa : osat) {
            if (aluksenosa.onEhja()) return false;
        }
        
        return true;
    }

    private void tarkastaOsienSijaintiPelikentalla(Aluksenosa[] aluksenosat) {
        int ekaX = aluksenosat[0].getX();
        int ekaY = aluksenosat[0].getY();
        int vikaX = aluksenosat[aluksenosat.length - 1].getX();
        int vikaY = aluksenosat[aluksenosat.length - 1].getY();
        int vikaIndeksi = aluksenosat.length - 1;
        int kentanVikaX = this.pelikentta.haeLeveys() - 1;
        int kentanVikaY = this.pelikentta.haeKorkeus() - 1;
        
        if (vikaX > kentanVikaX) {
            asetaSijainti(vikaX - (vikaX - kentanVikaX) - vikaIndeksi, vikaY);
        } else if (vikaY > kentanVikaY) {
            asetaSijainti(vikaX, vikaY - (vikaY - kentanVikaY) - vikaIndeksi);
        } else if (ekaX < 0) {
            asetaSijainti((-1 * ekaX) + ekaX, vikaY);
        } else if (ekaY < 0) {
            asetaSijainti(0, (-1 * vikaY) + vikaY);
        } else {
            this.pelikentta.asetaAlusKentalle(this);
        }
        
//        if (aluksenosat[aluksenosat.length - 1].getX() > this.pelikentta.haeLeveys() - 1) {
//            liiku((aluksenosat[aluksenosat.length - 1].getX() - this.pelikentta.haeLeveys()), 0);
//        } else if (aluksenosat[aluksenosat.length - 1].getY() > this.pelikentta.haeKorkeus()) {
//            liiku(0, (aluksenosat[aluksenosat.length - 1].getY() - this.pelikentta.haeKorkeus()));
//        } else if (aluksenosat[0].getX() < 0) {
//            liiku(-1 * (aluksenosat[0].getX()), 0);
//        } else if (aluksenosat[0].getY() < 0) {
//            liiku(0, -1 * (aluksenosat[0].getY()));
//        } else {
//            this.pelikentta.asetaAlusKentalle(this);
//        }
    }

    public List<Ruutu> getPommitettavatRuudut(Ruutu ruutu) {
        List<Ruutu> pommitettavatRuudut = new ArrayList<>();
        pommitettavatRuudut.add(ruutu);
        return pommitettavatRuudut;
    }
}
