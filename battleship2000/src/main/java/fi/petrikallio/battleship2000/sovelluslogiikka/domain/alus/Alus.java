
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;
import java.util.ArrayList;
import java.util.List;

public class Alus implements Liikkuva, Kaantyva, Osuttava {
    protected Suunta suunta;
    protected Aluksenosa[] osat;
    protected List<Suunta> mahdollisetSuunnat;
    
    public Alus(int toivottuPituus) {
        this.mahdollisetSuunnat = new ArrayList<>();
        lisaaMahdollisetSuunnat();
        this.suunta = Suunta.ITA;
        luoAluksenOsat(toivottuPituus, this.suunta);
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

    @Override
    public void liiku(int dx, int dy) {
        for (Aluksenosa aluksenosa : osat) {
            aluksenosa.liiku(dx, dy);
        }
    }
    
    @Override
    public void liiku() {
        liiku(this.suunta.getDx(), this.suunta.getDy());
    }

    private void luoAluksenOsat(int toivottuPituus, Suunta suunta) {
        int aluksenOsiaLuotava = palautaLopullinenPituus(toivottuPituus);
        
        this.osat = sijoitaOsat(aluksenOsiaLuotava, suunta, 0, 0);
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

    private Aluksenosa[] sijoitaOsat(int osienLukumaara, Suunta suunta, 
            int x, int y) {
        Aluksenosa[] aluksenosat = new Aluksenosa[osienLukumaara];
        
        for (int i = 0; i < aluksenosat.length; i++) {
            aluksenosat[i] = new Aluksenosa(x, y);
        }
        
        maaritaOsienSijaintiPelikentalla(aluksenosat, x, y, suunta);
        
        return aluksenosat;
    }

    private void maaritaOsienSijaintiPelikentalla(Aluksenosa[] aluksenosat, 
            int x, int y, Suunta suunta) {
        if (suunta == Suunta.ITA || suunta == Suunta.LANSI) {
            for (int i = 0; i < aluksenosat.length; i++) {
                aluksenosat[i].asetaSijainti(x + i, y);
            }
        } else {
            for (int i = 0; i < aluksenosat.length; i++) {
                aluksenosat[i].asetaSijainti(x, y + 1);
            }
        }
        
        if (suunta == Suunta.LANSI || suunta == Suunta.POHJOINEN) {
            aluksenosat[0].asetaAluksenPaa(true);
            aluksenosat[aluksenosat.length - 1].asetaAluksenPera(true);
        } else {
            aluksenosat[0].asetaAluksenPera(true);
            aluksenosat[aluksenosat.length - 1].asetaAluksenPaa(true);
        }
    }

    @Override
    public boolean onTuhottu() {
        for (Aluksenosa aluksenosa : osat) {
            if (aluksenosa.onEhja()) return false;
        }
        
        return true;
    }
}
