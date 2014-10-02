
package battleship2000.programlogic.rules;

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

public class RulesTest {
    private Rules perussaannot;
    private Rules emptyRules;
    private final ShipType[] alustyypit = {ShipType.COMMANDER, 
                                             ShipType.CANNON_SHIP,
                                             ShipType.SUBMARINE,
                                             ShipType.MISSILE_SHIP,
                                             ShipType.AIRCARRIER};
    private final PlayerType[] pelaajatyypit = {PlayerType.HUMAN,
                                                   PlayerType.COMPUTER};
    
    public RulesTest() {
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
        this.emptyRules = new Rules();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saannotOlioLuodessaEiSisallaSaantoja() {
        assertEquals(0, emptyRules.getRules().size());
    }
    
    @Test
    public void kentanKokoTallentuuHashMapiinJossaKaksiAvainta() {
        assertEquals(2, this.perussaannot.getTableSize().size());
    }
    
    @Test
    public void haeKentanKokoMetodiPalauttaaHashMapinJokaSisaltaaAvaimenKENTAN_LEVEYS() {
        assertTrue(this.perussaannot.getTableSize().containsKey(Rule.TABLE_WIDTH));
    }
    
    @Test
    public void haeKentanKokoMetodiPalauttaaHashMapinJokaSisaltaaAvaimenKENTAN_KORKEUS() {
        assertTrue(this.perussaannot.getTableSize().containsKey(Rule.TABLE_HEIGHT));
    }
    
    @Test
    public void kentanLeveysTallentuuHashMapiinAvaimeenKENTAN_LEVEYS() {
        this.emptyRules.setTableSize(10, 15);
        assertEquals(10, (int)this.emptyRules.getTableSize().get(Rule.TABLE_WIDTH));
    }
    
    @Test
    public void kentanKorkeusTallentuuHashMapiinAvaimeenKENTAN_KORKEUS() {
        this.emptyRules.setTableSize(10, 15);
        assertEquals(15, (int)this.emptyRules.getTableSize().get(Rule.TABLE_HEIGHT));
    }
    
    @Test
    public void kentanLeveydenOllessaPienempiKuinSallittuVahimmaispituusLeveysOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.emptyRules.setTableSize(min-1, min-1);
        
        assertEquals(min, this.emptyRules.getTableWidth());
    }
    
    @Test
    public void kentanLeveydenOllessaSuurempiKuinSallittuEnimmaispituusLeveysOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.emptyRules.setTableSize(max+1, max+1);
        
        assertEquals(max, this.emptyRules.getTableWidth());
    }
    
    @Test
    public void kentanKorkeudenOllessaPienempiKuinSallittuVahimmaispituusKorkeusOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.emptyRules.setTableSize(min-1, min-1);
        
        assertEquals(min, this.emptyRules.getTableHeight());
    }
    
    @Test
    public void kentanKorkeudenOllessaSuurempiKuinSallittuEnimmaispituusKorkeusOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.emptyRules.setTableSize(max+1, max+1);
        
        assertEquals(max, this.emptyRules.getTableHeight());
    }
    
    @Test
    public void kentanLeveydenOllessaSallittuVahimmaispituusLeveysOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.emptyRules.setTableSize(min, min);
        
        assertEquals(min, this.emptyRules.getTableWidth());
    }
    
    @Test
    public void kentanLeveydenOllessaSallittuEnimmaispituusLeveysOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.emptyRules.setTableSize(max, max);
        
        assertEquals(max, this.emptyRules.getTableWidth());
    }
    
    @Test
    public void kentanKorkeudenOllessaSallittuVahimmaispituusKorkeusOnSallittuVahimmaispituus() {
        int min = SizeLimits.getMinimumTableWidth();
        this.emptyRules.setTableSize(min, min);
        
        assertEquals(min, this.emptyRules.getTableHeight());
    }
    
    @Test
    public void kentanKorkeudenOllessaSallittuEnimmaispituusKorkeusOnSallittuEnimmaispituus() {
        int max = SizeLimits.getMaximumTableWidth();
        this.emptyRules.setTableSize(max, max);
        
        assertEquals(max, this.emptyRules.getTableHeight());
    }
    
    @Test
    public void saantoihinTallentuuListaAlustyyppejaAvaimellaALUKSET() {
        assertEquals(ArrayList.class, perussaannot.getShipTypes().getClass());
    }
    
    @Test
    public void saantojenAlustyyppejaOnSamanVerranKuinParametrinaAnnettu() {
        List<ShipType> lisattavatAlustyypit = new ArrayList<>();
        Collections.addAll(lisattavatAlustyypit, alustyypit);
        emptyRules.setShipTypes(lisattavatAlustyypit);
        
        assertEquals(alustyypit.length, emptyRules.getShipTypes().size());
    }
    
    @Test
    public void saantojenAlustyypitOvatIdenttisetParametrinaAnnettujenKanssa() {
        boolean samat = true;
        
        List<ShipType> lisattavatAlustyypit = new ArrayList<>();
        Collections.addAll(lisattavatAlustyypit, alustyypit);
        emptyRules.setShipTypes(lisattavatAlustyypit);
        
        for (int i = 0; i < emptyRules.getShipTypes().size(); i++) {
            if (!(emptyRules.getShipTypes().get(i).getShipClass() == alustyypit[i].getShipClass())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
    
    @Test
    public void trueLiikkumisSaantoTallentuuOikeinAvaimellaALUKSET_LIIKKUVAT() {
        this.emptyRules.setShipsAreMovableRule(true);
        assertTrue(this.emptyRules.shipsAreMovable());
    }
    
    @Test
    public void falseLiikkumisSaantoTallentuuOikeinAvaimellaALUKSET_LIIKKUVAT() {
        this.emptyRules.setShipsAreMovableRule(false);
        assertFalse(this.emptyRules.shipsAreMovable());
    }
    
    @Test
    public void trueAlustenErikoistoimintoSaantoTallentuuOikeinAvaimellaALUSTEN_ERIKOISTOIMINNOT() {
        this.emptyRules.setSpecializedShips(true);
        assertTrue(this.emptyRules.shipsAreSpecialized());
    }
    
    @Test
    public void falseAlustenErikoistoimintoSaantoTallentuuOikeinAvaimellaALUSTEN_ERIKOISTOIMINNOT() {
        this.emptyRules.setSpecializedShips(false);
        assertFalse(this.emptyRules.shipsAreSpecialized());
    }
    
    @Test
    public void saantoihinTallentuuListaPelaajatyyppejaAvaimellaPELAAJAT() {
        assertEquals(ArrayList.class, perussaannot.getPlayerTypes().getClass());
    }
    
    @Test
    public void saantojenPelaajatyyppejaOnSamanVerranKuinParametrinaAnnettu() {
        List<PlayerType> lisattavatPelaajatyypit = new ArrayList<>();
        Collections.addAll(lisattavatPelaajatyypit, pelaajatyypit);
        emptyRules.setPlayerTypes(lisattavatPelaajatyypit);
        
        assertEquals(pelaajatyypit.length, emptyRules.getPlayerTypes().size());
    }
    
    @Test
    public void saantojenPelaajatyypitOvatIdenttisetParametrinaAnnettujenKanssa() {
        boolean samat = true;
        
        List<PlayerType> lisattavatPelaajatyypit = new ArrayList<>();
        Collections.addAll(lisattavatPelaajatyypit, pelaajatyypit);
        emptyRules.setPlayerTypes(lisattavatPelaajatyypit);
        
        for (int i = 0; i < emptyRules.getPlayerTypes().size(); i++) {
            if (!(emptyRules.getPlayerTypes().get(i).getPlayerClass() == pelaajatyypit[i].getPlayerClass())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
    
    @Test
    public void ifValidatedTableLengthMethodIsGivenTheMinimumWidthItReturnsTheMinimumWidth() {
        emptyRules.setTableSize(8, 8);
        assertEquals(8, emptyRules.getTableWidth());
    }
    
    @Test
    public void ifValidatedTableLengthMethodIsGivenLessThanMinimumWidthItReturnsTheMinimumWidth() {
        emptyRules.setTableSize(7, 7);
        assertEquals(8, emptyRules.getTableWidth());
    }
    
    @Test
    public void ifValidatedTableLengthMethodIsGivenTheMaximumWidthItReturnsTheMaximumWidth() {
        emptyRules.setTableSize(25, 25);
        assertEquals(25, emptyRules.getTableWidth());
    }
    
    @Test
    public void ifValidatedTableLengthMethodIsGivenMoreThanMaximumWidthItReturnsTheMaximumWidth() {
        emptyRules.setTableSize(26, 26);
        assertEquals(25, emptyRules.getTableWidth());
    }
    
}