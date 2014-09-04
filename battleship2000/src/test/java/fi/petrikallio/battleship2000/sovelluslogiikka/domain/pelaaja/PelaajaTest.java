
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alustyyppi;
import fi.petrikallio.battleship2000.sovelluslogiikka.ohjaus.LuoPeli;
import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Perussaannot;
import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Saannot;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    private Pelaaja ihminen;
    private Saannot saannot;
    private BattleShipGame peli;
    
    public PelaajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.saannot = new Perussaannot(false);
        this.peli = (BattleShipGame) new LuoPeli(saannot).suorita();
        this.ihminen = this.peli.haePelaajat().get(0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunPelaajanPisteetOvatNolla() {
        assertEquals(0, ihminen.haePisteet());
    }
    
    @Test
    public void pelaajanPisteetEivatMuutuJosLisaaPisteetMetodilleAntaaNegatiivisenArvon() {
        this.ihminen.lisaaPisteet(-1);
        assertEquals(0, this.ihminen.haePisteet());
    }
    
    @Test
    public void pelaajanPisteetOvatYksiJosLisaaPisteetMetodilleAntaaArvonYksi() {
        this.ihminen.lisaaPisteet(1);
        assertEquals(1, this.ihminen.haePisteet());
    }
    
    @Test
    public void pelaajanPelikentanKorkeusOnSamaKuinSaannoissaMaaritetty() {
        int pelaajanKentanKorkeus = this.ihminen.haePelikentta().haeKorkeus();
        int saantojenKentanKorkeus = (int)this.saannot.haeKentanKoko().get("y");
        assertEquals(saantojenKentanKorkeus, pelaajanKentanKorkeus);
    }
    
    @Test
    public void pelaajanPelikentanLeveysOnSamaKuinSaannoissaMaaritetty() {
        int pelaajanKentanLeveys = this.ihminen.haePelikentta().haeLeveys();
        int saantojenKentanLeveys = (int)this.saannot.haeKentanKoko().get("x");
        assertEquals(saantojenKentanLeveys, pelaajanKentanLeveys);
    }
    
    @Test
    public void pelaajallaOnYhtaMontaAlustaKuinSaannoissaOnMaaritetty() {
        assertEquals(this.saannot.haeAlustyypit().size(),
                this.ihminen.haeAlukset().size());
    }
    
    @Test
    public void pelaajanAlustenAlustyypitOvatIdenttisetSaannoissaMMaaritettyjenKanssa() {
        boolean samat = true;
        List<Alustyyppi> saantojenAlustyypit 
                = (ArrayList<Alustyyppi>)this.saannot.haeAlustyypit();
        List<Alus> pelaajanAlukset = this.ihminen.haeAlukset();
        
        for (int i = 0; i < pelaajanAlukset.size(); i++) {
            if (!(pelaajanAlukset.get(i).getClass() 
                    == saantojenAlustyypit.get(i).haeLuokka())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
}