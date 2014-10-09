
package battleship2000.programlogic.domain.ship;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShipPartTest {
    private ShipPart part;
    
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
        this.part = new ShipPart();
        this.part.setPosition(0, 0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void uudenAluksenosanXOnParametrinaAnnettu() {
        assertEquals(0, this.part.getX());
    }
    
    @Test
    public void uudenAluksenosanYOnParametrinaAnnettu() {
        assertEquals(0, this.part.getY());
    }
    
    @Test
    public void uudenAluksenosanBooleanEhjaOnTrue() {
        assertTrue(this.part.isIntact());
    }
    
    @Test
    public void kunAluksenosaanOsuuPalauttaaOnkoEhjaMetodiFalse() {
        this.part.hit();
        assertFalse(this.part.isIntact());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaPositiivisellaArvollaJaXPaivittyyOikein() {
        this.part.move(1, 0);
        assertEquals(1, this.part.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNegatiivisellaArvollaJaXPaivittyyOikein() {
        ShipPart osa2 = new ShipPart();
        osa2.setPosition(1, 1);
        osa2.move(-1, 0);
        assertEquals(0, this.part.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaPositiivisellaArvollaJaYPaivittyyOikein() {
        this.part.move(0, 1);
        assertEquals(1, this.part.getY());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYAkselillaNegatiivisellaArvollaJaYPaivittyyOikein() {
        ShipPart osa2 = new ShipPart();
        osa2.setPosition(1, 1);
        osa2.move(0, -1);
        assertEquals(0, this.part.getY());
    }
    
    @Test
    public void aluksenosaanLiikutetaanXAkselillaNollaJaXPysyySamana() {
        this.part.move(0, 0);
        assertEquals(0, this.part.getX());
    }
    
    @Test
    public void aluksenosaanLiikutetaanYNollaJaYPysyySamana() {
        this.part.move(0, 0);
        assertEquals(0, this.part.getY());
    }
    
    @Test
    public void metodiLiikuIlmanParametrejaEiMuutaXnArvoa() {
        int xEnnen = this.part.getX();
        this.part.move();
        int xJalkeen = this.part.getX();
        
        assertTrue(xEnnen == xJalkeen);
    }
    
    @Test
    public void metodiLiikuIlmanParametrejaEiMuutaYnArvoa() {
        int yEnnen = this.part.getY();
        this.part.move();
        int yJalkeen = this.part.getY();
        
        assertTrue(yEnnen == yJalkeen);
    }
    
    @Test
    public void liikkuminenSamanaikaisestiXJaYAkselillaMuuttaaMolempiaArvojaOikein() {
        boolean kaikkiHyvin = true;
        this.part.move(2, 3);
        
        if (!(this.part.getX() == 2) || !(this.part.getY() == 3)) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void kunAlusLuodaanOnAluksenPaaMetodiPalauttaaFalse() {
        assertFalse(this.part.isShipsFront());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPaaPalauttaaOnAluksenPaaMetodiTrue() {
        this.part.setShipsFront();
        assertTrue(this.part.isShipsFront());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPaaPalauttaaOnAluksenPeraMetodiFalse() {
        this.part.setShipsFront();
        assertFalse(this.part.isShipsRear());
    }
    
    
    @Test
    public void kunAlusLuodaanOnAluksenPeraMetodiPalauttaaFalse() {
        assertFalse(this.part.isShipsRear());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPeraPalauttaaOnAluksenPeraMetodiTrue() {
        this.part.setShipsRear();
        assertTrue(this.part.isShipsRear());
    }
    
    @Test
    public void aluksenosanOllessaAluksenPeraPalauttaaOnAluksenPaaMetodiFalse() {
        this.part.setShipsRear();
        assertFalse(this.part.isShipsFront());
    }
    
    @Test
    public void metodiOnTuhottuPalauttaaFalseJosOnEhjaPalauttaaTrue() {
        assertFalse(this.part.isDestroyed());
    }
    
    @Test
    public void metodiOnTuhottuPalauttaaTrueJosOnEhjaPalauuttaaFalse() {
        this.part.hit();
        assertTrue(this.part.isDestroyed());
    }
    
    @Test
    public void moveMethodWithParametersReturnsTrue() {
        assertTrue(part.move(1, 0));
    }
    
    @Test
    public void moveMethodWithOutParametersReturnsTrue() {
        assertTrue(part.move());
    }
}