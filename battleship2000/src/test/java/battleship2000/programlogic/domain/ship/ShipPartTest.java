
package battleship2000.programlogic.domain.ship;

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
public class ShipPartTest {
    private ShipPart osa;
    
    public ShipPartTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.osa = new ShipPart();
        this.osa.setPosition(0, 0);
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
    
//    @Test
//    public void uudenAluksenosanXEiVoiOllaNegatiivinen() {
//        ShipPart osa2 = new ShipPart();
//        osa2.setPosition(-1, -1);
//        assertEquals(0, osa2.getX());
//    }
    
//    @Test
//    public void uudenAluksenosanYEiVoiOllaNegatiivinen() {
//        ShipPart osa2 = new ShipPart();
//        osa2.setPosition(-1, -1);
//        assertEquals(0, osa2.getY());
//    }
    
//    @Test
//    public void uudenAluksenosanXEiVoiOllaEnemmanKuinKentanEnimmaisX() {
//        int maxPlusYksi = SizeLimits.kentanSivunEnimmaispituus();
//        ShipPart osa2 = new ShipPart();
//        osa2.setPosition(maxPlusYksi, maxPlusYksi);
//        assertEquals(maxPlusYksi - 1, osa2.getX());
//    }
    
//    @Test
//    public void uudenAluksenosanYEiVoiOllaEnemmanKuinKentanEnimmaisY() {
//        int maxPlusYksi = SizeLimits.kentanSivunEnimmaispituus();
//        ShipPart osa2 = new ShipPart();
//        osa2.setPosition(maxPlusYksi, maxPlusYksi);
//        assertEquals(maxPlusYksi - 1, osa2.getY());
//    }
    
    @Test
    public void uudenAluksenosanBooleanEhjaOnTrue() {
        assertTrue(this.osa.isIntact());
    }
    
    @Test
    public void kunAluksenosaanOsuuPalauttaaOnkoEhjaMetodiFalse() {
        this.osa.hit();
        assertFalse(this.osa.isIntact());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaPositiivisellaArvollaJaXPaivittyyOikein() {
        this.osa.move(1, 0);
        assertEquals(1, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNegatiivisellaArvollaJaXPaivittyyOikein() {
        ShipPart osa2 = new ShipPart();
        osa2.setPosition(1, 1);
        osa2.move(-1, 0);
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaPositiivisellaArvollaJaYPaivittyyOikein() {
        this.osa.move(0, 1);
        assertEquals(1, this.osa.getY());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaNegatiivisellaArvollaJaYPaivittyyOikein() {
        ShipPart osa2 = new ShipPart();
        osa2.setPosition(1, 1);
        osa2.move(0, -1);
        assertEquals(0, this.osa.getY());
    }
    
//    @Test
//    public void aluksenosaanLiikutetaanXAkselillaPositiivisellaArvollaKentanRajanYliJaXPaivittyyOikein() {
//        int maxPlusYksi = SizeLimits.kentanSivunEnimmaispituus();
//        this.osa.move(maxPlusYksi, 0);
//        assertEquals(SizeLimits.kentanSivunEnimmaispituus()-1, this.osa.getX());
//    }
    
//    @Test
//    public void aluksenosaaLiikutetaanXAkselillaNegatiiviseenArvoonJaXEiAlitaNollaa() {
//        this.osa.move(-1, 0);
//        assertEquals(0, this.osa.getX());
//    }
    
//    @Test
//    public void aluksenosaanLiikutetaanYAkselillaPositiivisellaArvollaKentanRajanYliJaYPaivittyyOikein() {
//        int maxPlusYksi = SizeLimits.kentanSivunEnimmaispituus();
//        this.osa.move(0, maxPlusYksi);
//        assertEquals(SizeLimits.kentanSivunEnimmaispituus()-1, this.osa.getY());
//    }
    
//    @Test
//    public void aluksenosaaLiikutetaanYAkselillaNegatiiviseenArvoonJaYEiAlitaNollaa() {
//        this.osa.move(0, -1);
//        assertEquals(0, this.osa.getX());
//    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNollaJaXPysyySamana() {
        this.osa.move(0, 0);
        assertEquals(0, this.osa.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYNollaJaYPysyySamana() {
        this.osa.move(0, 0);
        assertEquals(0, this.osa.getY());
    }
    
    @Test
    public void metodiLiikuIlmanParametrejaEiMuutaXnArvoa() {
        int xEnnen = this.osa.getX();
        this.osa.move();
        int xJalkeen = this.osa.getX();
        
        assertTrue(xEnnen == xJalkeen);
    }
    
    @Test
    public void metodiLiikuIlmanParametrejaEiMuutaYnArvoa() {
        int yEnnen = this.osa.getY();
        this.osa.move();
        int yJalkeen = this.osa.getY();
        
        assertTrue(yEnnen == yJalkeen);
    }
    
    @Test
    public void liikkuminenSamanaikaisestiXJaYAkselillaMuuttaaMolempiaArvojaOikein() {
        boolean kaikkiHyvin = true;
        this.osa.move(2, 3);
        
        if (!(this.osa.getX() == 2) || !(this.osa.getY() == 3)) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void kunAlusLuodaanOnAluksenPaaMetodiPalauttaaFalse() {
        assertFalse(this.osa.isShipsFront());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPaaPalauttaaOnAluksenPaaMetodiTrue() {
        this.osa.setShipsFront();
        assertTrue(this.osa.isShipsFront());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPaaPalauttaaOnAluksenPeraMetodiFalse() {
        this.osa.setShipsFront();
        assertFalse(this.osa.isShipsRear());
    }
    
    
    @Test
    public void kunAlusLuodaanOnAluksenPeraMetodiPalauttaaFalse() {
        assertFalse(this.osa.isShipsRear());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPeraPalauttaaOnAluksenPeraMetodiTrue() {
        this.osa.setShipsRear();
        assertTrue(this.osa.isShipsRear());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPeraPalauttaaOnAluksenPaaMetodiFalse() {
        this.osa.setShipsRear();
        assertFalse(this.osa.isShipsFront());
    }
    
//    @Test
//    public void josOsalleAsettaaNegatiivisenSijainninAsettuuSijainniksiNolla() {
//        boolean kaikkiHyvin = false;
//        this.osa.setPosition(-1, -1);
//        
//        if (this.osa.getX() == 0 && this.osa.getY() == 0) {
//            kaikkiHyvin = true;
//        }
//        
//        assertTrue(kaikkiHyvin);
//    }
    
//    @Test
//    public void josOsalleAsettaaSijainninYliPelikentanEnimmaisKoonAsettuuSijainniksiMaksimiMiinusYksi() {
//        boolean kaikkiHyvin = false;
//        this.osa.setPosition(SizeLimits.kentanSivunEnimmaispituus(), 
//                SizeLimits.kentanSivunEnimmaispituus());
//        
//        if (this.osa.getX() == SizeLimits.kentanSivunEnimmaispituus() - 1 
//                && this.osa.getY() == SizeLimits.kentanSivunEnimmaispituus() - 1) {
//            kaikkiHyvin = true;
//        }
//        
//        assertTrue(kaikkiHyvin);
//    }
    
    @Test
    public void metodiOnTuhottuPalauttaaFalseJosOnEhjaPalauttaaTrue() {
        assertFalse(this.osa.isDestroyed());
    }
    
    @Test
    public void metodiOnTuhottuPalauttaaTrueJosOnEhjaPalauuttaaFalse() {
        this.osa.hit();
        assertTrue(this.osa.isDestroyed());
    }
}