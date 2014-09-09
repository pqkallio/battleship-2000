
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Petri
 */
public class AluksenosaTest {
    private Aluksenosa osa;
    
    public AluksenosaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.osa = new Aluksenosa();
        this.osa.asetaSijainti(0, 0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void uudenAluksenosanXOnParametrinaAnnettu() {
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void uudenAluksenosanYOnParametrinaAnnettu() {
        assertEquals(0, this.osa.getY());
    }
    
    @Test
    public void uudenAluksenosanXEiVoiOllaNegatiivinen() {
        Aluksenosa osa2 = new Aluksenosa();
        osa2.asetaSijainti(-1, -1);
        assertEquals(0, osa2.getX());
    }
    
    @Test
    public void uudenAluksenosanYEiVoiOllaNegatiivinen() {
        Aluksenosa osa2 = new Aluksenosa();
        osa2.asetaSijainti(-1, -1);
        assertEquals(0, osa2.getY());
    }
    
    @Test
    public void uudenAluksenosanXEiVoiOllaEnemmanKuinKentanEnimmaisX() {
        int maxPlusYksi = Kokorajoitteet.kentanSivunEnimmaispituus();
        Aluksenosa osa2 = new Aluksenosa();
        osa2.asetaSijainti(maxPlusYksi, maxPlusYksi);
        assertEquals(maxPlusYksi - 1, osa2.getX());
    }
    
    @Test
    public void uudenAluksenosanYEiVoiOllaEnemmanKuinKentanEnimmaisY() {
        int maxPlusYksi = Kokorajoitteet.kentanSivunEnimmaispituus();
        Aluksenosa osa2 = new Aluksenosa();
        osa2.asetaSijainti(maxPlusYksi, maxPlusYksi);
        assertEquals(maxPlusYksi - 1, osa2.getY());
    }
    
    @Test
    public void uudenAluksenosanBooleanEhjaOnTrue() {
        assertTrue(this.osa.onEhja());
    }
    
    @Test
    public void kunAluksenosaanOsuuPalauttaaOnkoEhjaMetodiFalse() {
        this.osa.osuma();
        assertFalse(this.osa.onEhja());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaPositiivisellaArvollaJaXPaivittyyOikein() {
        this.osa.liiku(1, 0);
        assertEquals(1, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNegatiivisellaArvollaJaXPaivittyyOikein() {
        Aluksenosa osa2 = new Aluksenosa();
        osa2.asetaSijainti(1, 1);
        osa2.liiku(-1, 0);
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaPositiivisellaArvollaJaYPaivittyyOikein() {
        this.osa.liiku(0, 1);
        assertEquals(1, this.osa.getY());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaNegatiivisellaArvollaJaYPaivittyyOikein() {
        Aluksenosa osa2 = new Aluksenosa();
        osa2.asetaSijainti(1, 1);
        osa2.liiku(0, -1);
        assertEquals(0, this.osa.getY());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaPositiivisellaArvollaKentanRajanYliJaXPaivittyyOikein() {
        int maxPlusYksi = Kokorajoitteet.kentanSivunEnimmaispituus();
        this.osa.liiku(maxPlusYksi, 0);
        assertEquals(Kokorajoitteet.kentanSivunEnimmaispituus()-1, this.osa.getX());
    }
    
    @Test
    public void aluksenosaaLiikutetaanXAkselillaNegatiiviseenArvoonJaXEiAlitaNollaa() {
        this.osa.liiku(-1, 0);
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaPositiivisellaArvollaKentanRajanYliJaYPaivittyyOikein() {
        int maxPlusYksi = Kokorajoitteet.kentanSivunEnimmaispituus();
        this.osa.liiku(0, maxPlusYksi);
        assertEquals(Kokorajoitteet.kentanSivunEnimmaispituus()-1, this.osa.getY());
    }
    
    @Test
    public void aluksenosaaLiikutetaanYAkselillaNegatiiviseenArvoonJaYEiAlitaNollaa() {
        this.osa.liiku(0, -1);
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNollaJaXPysyySamana() {
        this.osa.liiku(0, 0);
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYNollaJaYPysyySamana() {
        this.osa.liiku(0, 0);
        assertEquals(0, this.osa.getY());
    }
    
    @Test
    public void metodiLiikuIlmanParametrejaEiMuutaXnArvoa() {
        int xEnnen = this.osa.getX();
        this.osa.liiku();
        int xJalkeen = this.osa.getX();
        
        assertTrue(xEnnen == xJalkeen);
    }
    
    @Test
    public void metodiLiikuIlmanParametrejaEiMuutaYnArvoa() {
        int yEnnen = this.osa.getY();
        this.osa.liiku();
        int yJalkeen = this.osa.getY();
        
        assertTrue(yEnnen == yJalkeen);
    }
    
    @Test
    public void liikkuminenSamanaikaisestiXJaYAkselillaMuuttaaMolempiaArvojaOikein() {
        boolean kaikkiHyvin = true;
        this.osa.liiku(2, 3);
        
        if (!(this.osa.getX() == 2) || !(this.osa.getY() == 3)) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void kunAlusLuodaanOnAluksenPaaMetodiPalauttaaFalse() {
        assertFalse(this.osa.onAluksenPaa());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPaaPalauttaaOnAluksenPaaMetodiTrue() {
        this.osa.asetaAluksenPaa();
        assertTrue(this.osa.onAluksenPaa());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPaaPalauttaaOnAluksenPeraMetodiFalse() {
        this.osa.asetaAluksenPaa();
        assertFalse(this.osa.onAluksenPera());
    }
    
    
    @Test
    public void kunAlusLuodaanOnAluksenPeraMetodiPalauttaaFalse() {
        assertFalse(this.osa.onAluksenPera());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPeraPalauttaaOnAluksenPeraMetodiTrue() {
        this.osa.asetaAluksenPera();
        assertTrue(this.osa.onAluksenPera());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPeraPalauttaaOnAluksenPaaMetodiFalse() {
        this.osa.asetaAluksenPera();
        assertFalse(this.osa.onAluksenPaa());
    }
    
    @Test
    public void josOsalleAsettaaNegatiivisenSijainninAsettuuSijainniksiNolla() {
        boolean kaikkiHyvin = false;
        this.osa.asetaSijainti(-1, -1);
        
        if (this.osa.getX() == 0 && this.osa.getY() == 0) {
            kaikkiHyvin = true;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void josOsalleAsettaaSijainninYliPelikentanEnimmaisKoonAsettuuSijainniksiMaksimiMiinusYksi() {
        boolean kaikkiHyvin = false;
        this.osa.asetaSijainti(Kokorajoitteet.kentanSivunEnimmaispituus(), 
                Kokorajoitteet.kentanSivunEnimmaispituus());
        
        if (this.osa.getX() == Kokorajoitteet.kentanSivunEnimmaispituus() - 1 
                && this.osa.getY() == Kokorajoitteet.kentanSivunEnimmaispituus() - 1) {
            kaikkiHyvin = true;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void metodiOnTuhottuPalauttaaFalseJosOnEhjaPalauttaaTrue() {
        assertFalse(this.osa.onTuhottu());
    }
    
    @Test
    public void metodiOnTuhottuPalauttaaTrueJosOnEhjaPalauuttaaFalse() {
        this.osa.osuma();
        assertTrue(this.osa.onTuhottu());
    }
}