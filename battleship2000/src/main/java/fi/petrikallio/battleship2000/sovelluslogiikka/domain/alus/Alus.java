
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;
import java.util.ArrayList;
import java.util.List;

public class Alus implements Liikkuva, Kaantyva {
    protected Suunta suunta;
    protected Aluksenosa[] osat;
    protected List<Suunta> mahdollisetSuunnat;
    
    public Alus(int toivottuPituus) {
        luoAluksenOsat(toivottuPituus);
        this.mahdollisetSuunnat = new ArrayList<>();
        lisaaMahdollisetSuunnat();
        this.suunta = Suunta.ITA;
    }
    
    public Aluksenosa[] getOsat() {
        return osat;
    }

    public void setOsat(Aluksenosa[] osat) {
        this.osat = osat;
    }

    public Suunta getSuunta() {
        return suunta;
    }

    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
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

    private void luoAluksenOsat(int toivottuPituus) {
        int aluksenOsiaLuotava = palautaLopullinenPituus(toivottuPituus);
        
        this.osat = new Aluksenosa[aluksenOsiaLuotava];
        
        for (int i = 0; i < this.osat.length; i++) {
            this.osat[i] = new Aluksenosa(i, 0);
        }
    }
    
    public int getAluksenPituus() {
        return osat.length;
    }

    @Override
    public void kaannaMyotapaivaan() {
        this.suunta = haeSeuraavaSuunta(1);
    }

    @Override
    public void kaannaVastapaivaan() {
        this.suunta = haeSeuraavaSuunta(-1);
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
    
    private
}
