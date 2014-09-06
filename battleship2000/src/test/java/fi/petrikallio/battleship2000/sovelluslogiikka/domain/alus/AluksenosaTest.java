
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;
import fi.petrikallio.battleship2000.util.Kokonaislukutyokalut;
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
        this.osa = new Aluksenosa(0, 0);
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
        Aluksenosa osa2 = new Aluksenosa(-1, -1);
        assertEquals(0, osa2.getX());
    }
    
    @Test
    public void uudenAluksenosanYEiVoiOllaNegatiivinen() {
        Aluksenosa osa2 = new Aluksenosa(-1, -1);
        assertEquals(0, osa2.getY());
    }
    
    @Test
    public void uudenAluksenosanXEiVoiOllaEnemmanKuinKentanEnimmaisX() {
        int maxPlusYksi = Kokorajoitteet.kentanSivunEnimmaispituus();
        Aluksenosa osa2 = new Aluksenosa(maxPlusYksi, maxPlusYksi);
        assertEquals(maxPlusYksi - 1, osa2.getX());
    }
    
    @Test
    public void uudenAluksenosanYEiVoiOllaEnemmanKuinKentanEnimmaisY() {
        int maxPlusYksi = Kokorajoitteet.kentanSivunEnimmaispituus();
        Aluksenosa osa2 = new Aluksenosa(maxPlusYksi, maxPlusYksi);
        assertEquals(maxPlusYksi - 1, osa2.getY());
    }
    
    @Test
    public void uudenAluksenosanBooleanEhjaOnTrue() {
        assertTrue(this.osa.onkoEhja());
    }
    
    @Test
    public void kunAluksenosaanOsuuPalauttaaOnkoEhjaMetodiFalse() {
        this.osa.osuma();
        assertFalse(this.osa.onkoEhja());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaPositiivisellaArvollaJaXPaivittyyOikein() {
        this.osa.liiku(1, 0);
        assertEquals(1, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNegatiivisellaArvollaJaXPaivittyyOikein() {
        Aluksenosa osa2 = new Aluksenosa(1, 1);
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
        Aluksenosa osa2 = new Aluksenosa(1, 1);
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
}