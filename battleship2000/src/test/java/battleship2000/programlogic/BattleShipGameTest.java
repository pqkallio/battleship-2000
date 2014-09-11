
package battleship2000.programlogic;

import battleship2000.programlogic.BattleShipGame;
import battleship2000.programlogic.domain.player.Human;
import battleship2000.programlogic.domain.player.Player;
import battleship2000.programlogic.domain.player.Computer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BattleShipGameTest {
    private BattleShipGame peli;
    private final Player[] pelaajat = {new Human(), new Computer()};
    
    public BattleShipGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.peli = new BattleShipGame();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void pelaajaListaOnSamanMittainenKuinAsetetutPelaajat() {
        asetaPeliinPelaajat();
        
        assertEquals(this.peli.getPlayers().size(), pelaajat.length);
    }
    
    @Test
    public void pelaajaListanPelaajatOvatSamatKuinParametrinaAnnettu() {
        asetaPeliinPelaajat();
        boolean identtiset = true;
        
        for (int i = 0; i < this.peli.getPlayers().size(); i++) {
            if (!(this.peli.getPlayers().get(i) == pelaajat[i])) {
                identtiset = false;
            }
        }
        
        assertTrue(identtiset);
    }
    
    @Test
    public void aluksetLiikkuvatMetodiPalauttaaTrueJokaMuuttujalleOnMaaritetty() {
        this.peli.setShipsAreMovable(true);
        assertTrue(this.peli.shipsAreMovable());
    }
    
    @Test
    public void aluksetLiikkuvatMetodiPalauttaaFalseJokaMuuttujalleOnMaaritetty() {
        this.peli.setShipsAreMovable(false);
        assertFalse(this.peli.shipsAreMovable());
    }
    
    @Test
    public void alustenErikoistoiminnotMetodiPalauttaaTrueJokaMuuttujalleOnMaaritetty() {
        this.peli.setShipsAreSpecialized(true);
        assertTrue(this.peli.shipsAreSpecialized());
    }
    
    @Test
    public void alustenErikoistoiminnotMetodiPalauttaaFalseJokaMuuttujalleOnMaaritetty() {
        this.peli.setShipsAreSpecialized(false);
        assertFalse(this.peli.shipsAreSpecialized());
    }

    private void asetaPeliinPelaajat() {
        List<Player> pelinPelaajat = new ArrayList<>();
        Collections.addAll(pelinPelaajat, pelaajat);
        this.peli.setPlayers(pelinPelaajat);
    }
}