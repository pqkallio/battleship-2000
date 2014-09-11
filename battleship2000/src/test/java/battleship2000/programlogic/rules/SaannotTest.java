
package battleship2000.programlogic.rules;

import battleship2000.programlogic.rules.BasicRules;
import battleship2000.programlogic.rules.SizeLimits;
import battleship2000.programlogic.rules.Rules;
import battleship2000.programlogic.rules.Saanto;
import battleship2000.programlogic.domain.ship.ShipType;
import battleship2000.programlogic.domain.player.PlayerType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaannotTest {
    private Rules perussaannot;
    private Rules tyhjatSaannot;
    private final ShipType[] alustyypit = {ShipType.COMMANDER, 
                                             ShipType.CANNON_SHIP,
                                             ShipType.SUBMARINE,
                                             ShipType.MISSILE_SHIP,
                                             ShipType.AIRCARRIER};
    private final PlayerType[] pelaajatyypit = {PlayerType.HUMAN,
                                                   PlayerType.COMPUTER};
    
    public SaannotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.perussaannot = new BasicRules(true);
        this.tyhjatSaannot = new Rules();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saannotOlioLuodessaEiSisallaSaantoja() {
        assertEquals(0, tyhjatSaannot.haeSaannot().size());
    }
    
    @Test
    public void kentanKokoTallentuuHashMapiinJossaKaksiAvainta() {
        assertEquals(2, this.perussaannot.haeKentanKoko().size());
    }
    
    @Test
    public void haeKentanKokoMetodiPalauttaaHashMapinJokaSisaltaaAvaimenKENTAN_LEVEYS() {
        assertTrue(this.perussaannot.haeKentanKoko().containsKey(Saanto.KENTAN_LEVEYS));
    }
    
    @Test
    public void haeKentanKokoMetodiPalauttaaHashMapinJokaSisaltaaAvaimenKENTAN_KORKEUS() {
        assertTrue(this.perussaannot.haeKentanKoko().containsKey(Saanto.KENTAN_KORKEUS));
    }
    
    @Test
    public void kentanLeveysTallentuuHashMapiinAvaimeenKENTAN_LEVEYS() {
        this.tyhjatSaannot.asetaKentanKoko(10, 15);
        assertEquals(10, (int)this.tyhjatSaannot.haeKentanKoko().get(Saanto.KENTAN_LEVEYS));
    }
    
    @Test
    public void kentanKorkeusTallentuuHashMapiinAvaimeenKENTAN_KORKEUS() {
        this.tyhjatSaannot.asetaKentanKoko(10, 15);
        assertEquals(15, (int)this.tyhjatSaannot.haeKentanKoko().get(Saanto.KENTAN_KORKEUS));
    }
    
    @Test
    public void kentanLeveydenOllessaPienempiKuinSallittuVahimmaispituusLeveysOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(min-1, min-1);
        
        assertEquals(min, this.tyhjatSaannot.getTableWidth());
    }
    
    @Test
    public void kentanLeveydenOllessaSuurempiKuinSallittuEnimmaispituusLeveysOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(max+1, max+1);
        
        assertEquals(max, this.tyhjatSaannot.getTableWidth());
    }
    
    @Test
    public void kentanKorkeudenOllessaPienempiKuinSallittuVahimmaispituusKorkeusOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(min-1, min-1);
        
        assertEquals(min, this.tyhjatSaannot.getTableHeight());
    }
    
    @Test
    public void kentanKorkeudenOllessaSuurempiKuinSallittuEnimmaispituusKorkeusOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(max+1, max+1);
        
        assertEquals(max, this.tyhjatSaannot.getTableHeight());
    }
    
    @Test
    public void kentanLeveydenOllessaSallittuVahimmaispituusLeveysOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(min, min);
        
        assertEquals(min, this.tyhjatSaannot.getTableWidth());
    }
    
    @Test
    public void kentanLeveydenOllessaSallittuEnimmaispituusLeveysOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(max, max);
        
        assertEquals(max, this.tyhjatSaannot.getTableWidth());
    }
    
    @Test
    public void kentanKorkeudenOllessaSallittuVahimmaispituusKorkeusOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(min, min);
        
        assertEquals(min, this.tyhjatSaannot.getTableHeight());
    }
    
    @Test
    public void kentanKorkeudenOllessaSallittuEnimmaispituusKorkeusOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.tyhjatSaannot.asetaKentanKoko(max, max);
        
        assertEquals(max, this.tyhjatSaannot.getTableHeight());
    }
    
    @Test
    public void saantoihinTallentuuListaAlustyyppejaAvaimellaALUKSET() {
        assertEquals(ArrayList.class, perussaannot.getShipTypes().getClass());
    }
    
    @Test
    public void saantojenAlustyyppejaOnSamanVerranKuinParametrinaAnnettu() {
        List<ShipType> lisattavatAlustyypit = new ArrayList<>();
        Collections.addAll(lisattavatAlustyypit, alustyypit);
        tyhjatSaannot.createShips(lisattavatAlustyypit);
        
        assertEquals(alustyypit.length, tyhjatSaannot.getShipTypes().size());
    }
    
    @Test
    public void saantojenAlustyypitOvatIdenttisetParametrinaAnnettujenKanssa() {
        boolean samat = true;
        
        List<ShipType> lisattavatAlustyypit = new ArrayList<>();
        Collections.addAll(lisattavatAlustyypit, alustyypit);
        tyhjatSaannot.createShips(lisattavatAlustyypit);
        
        for (int i = 0; i < tyhjatSaannot.getShipTypes().size(); i++) {
            if (!(tyhjatSaannot.getShipTypes().get(i).getShipClass() == alustyypit[i].getShipClass())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
    
    @Test
    public void trueLiikkumisSaantoTallentuuOikeinAvaimellaALUKSET_LIIKKUVAT() {
        this.tyhjatSaannot.setShipsMoveRule(true);
        assertTrue(this.tyhjatSaannot.shipsAreMovable());
    }
    
    @Test
    public void falseLiikkumisSaantoTallentuuOikeinAvaimellaALUKSET_LIIKKUVAT() {
        this.tyhjatSaannot.setShipsMoveRule(false);
        assertFalse(this.tyhjatSaannot.shipsAreMovable());
    }
    
    @Test
    public void trueAlustenErikoistoimintoSaantoTallentuuOikeinAvaimellaALUSTEN_ERIKOISTOIMINNOT() {
        this.tyhjatSaannot.setSpecializedShips(true);
        assertTrue(this.tyhjatSaannot.shipsAreSpecialized());
    }
    
    @Test
    public void falseAlustenErikoistoimintoSaantoTallentuuOikeinAvaimellaALUSTEN_ERIKOISTOIMINNOT() {
        this.tyhjatSaannot.setSpecializedShips(false);
        assertFalse(this.tyhjatSaannot.shipsAreSpecialized());
    }
    
    @Test
    public void saantoihinTallentuuListaPelaajatyyppejaAvaimellaPELAAJAT() {
        assertEquals(ArrayList.class, perussaannot.getPlayerTypes().getClass());
    }
    
    @Test
    public void saantojenPelaajatyyppejaOnSamanVerranKuinParametrinaAnnettu() {
        List<PlayerType> lisattavatPelaajatyypit = new ArrayList<>();
        Collections.addAll(lisattavatPelaajatyypit, pelaajatyypit);
        tyhjatSaannot.createPlayers(lisattavatPelaajatyypit);
        
        assertEquals(pelaajatyypit.length, tyhjatSaannot.getPlayerTypes().size());
    }
    
    @Test
    public void saantojenPelaajatyypitOvatIdenttisetParametrinaAnnettujenKanssa() {
        boolean samat = true;
        
        List<PlayerType> lisattavatPelaajatyypit = new ArrayList<>();
        Collections.addAll(lisattavatPelaajatyypit, pelaajatyypit);
        tyhjatSaannot.createPlayers(lisattavatPelaajatyypit);
        
        for (int i = 0; i < tyhjatSaannot.getPlayerTypes().size(); i++) {
            if (!(tyhjatSaannot.getPlayerTypes().get(i).getPlayerClass() == pelaajatyypit[i].getPlayerClass())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
}