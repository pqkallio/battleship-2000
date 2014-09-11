
package battleship2000.programlogic.domain.player;

import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.control.CreateGame;
import battleship2000.programlogic.rules.BasicRules;
import battleship2000.programlogic.rules.Rules;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    private Player ihminen;
    private Rules saannot;
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
        this.saannot = new BasicRules(false);
        this.peli = (BattleShipGame) new CreateGame(saannot).execute();
        this.ihminen = this.peli.getPlayers().get(0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunPelaajanPisteetOvatNolla() {
        assertEquals(0, ihminen.getPoints());
    }
    
    @Test
    public void pelaajanPisteetEivatMuutuJosLisaaPisteetMetodilleAntaaNegatiivisenArvon() {
        this.ihminen.addPoints(-1);
        assertEquals(0, this.ihminen.getPoints());
    }
    
    @Test
    public void pelaajanPisteetOvatYksiJosLisaaPisteetMetodilleAntaaArvonYksi() {
        this.ihminen.addPoints(1);
        assertEquals(1, this.ihminen.getPoints());
    }
    
    @Test
    public void pelaajanPelikentanKorkeusOnSamaKuinSaannoissaMaaritetty() {
        int pelaajanKentanKorkeus = this.ihminen.getTable().getHeight();
        int saantojenKentanKorkeus = this.saannot.getTableHeight();
        assertEquals(saantojenKentanKorkeus, pelaajanKentanKorkeus);
    }
    
    @Test
    public void pelaajanPelikentanLeveysOnSamaKuinSaannoissaMaaritetty() {
        int pelaajanKentanLeveys = this.ihminen.getTable().getWidth();
        int saantojenKentanLeveys = this.saannot.getTableWidth();
        assertEquals(saantojenKentanLeveys, pelaajanKentanLeveys);
    }
    
    @Test
    public void pelaajallaOnYhtaMontaAlustaKuinSaannoissaOnMaaritetty() {
        assertEquals(this.saannot.getShipTypes().size(),
                this.ihminen.getShips().size());
    }
    
    @Test
    public void pelaajanAlustenAlustyypitOvatIdenttisetSaannoissaMMaaritettyjenKanssa() {
        boolean samat = true;
        List<ShipType> saantojenAlustyypit 
                = (ArrayList<ShipType>)this.saannot.getShipTypes();
        List<Ship> pelaajanAlukset = this.ihminen.getShips();
        
        for (int i = 0; i < pelaajanAlukset.size(); i++) {
            if (!(pelaajanAlukset.get(i).getClass() 
                    == saantojenAlustyypit.get(i).getShipClass())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
}