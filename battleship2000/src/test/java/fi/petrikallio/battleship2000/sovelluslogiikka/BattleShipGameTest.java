
package fi.petrikallio.battleship2000.sovelluslogiikka;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Ihminen;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaaja;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Tietokone;
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
    private final Pelaaja[] pelaajat = {new Ihminen(), new Tietokone()};
    
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
        
        assertEquals(this.peli.haePelaajat().size(), pelaajat.length);
    }
    
    @Test
    public void pelaajaListanPelaajatOvatSamatKuinParametrinaAnnettu() {
        asetaPeliinPelaajat();
        boolean identtiset = true;
        
        for (int i = 0; i < this.peli.haePelaajat().size(); i++) {
            if (!(this.peli.haePelaajat().get(i) == pelaajat[i])) {
                identtiset = false;
            }
        }
        
        assertTrue(identtiset);
    }
    
    @Test
    public void aluksetLiikkuvatMetodiPalauttaaTrueJokaMuuttujalleOnMaaritetty() {
        this.peli.asetaAluksetLiikkuvat(true);
        assertTrue(this.peli.aluksetLiikkuvat());
    }
    
    @Test
    public void aluksetLiikkuvatMetodiPalauttaaFalseJokaMuuttujalleOnMaaritetty() {
        this.peli.asetaAluksetLiikkuvat(false);
        assertFalse(this.peli.aluksetLiikkuvat());
    }
    
    @Test
    public void alustenErikoistoiminnotMetodiPalauttaaTrueJokaMuuttujalleOnMaaritetty() {
        this.peli.asetaAlustenErikoistoiminnot(true);
        assertTrue(this.peli.alustenErikoistoiminnot());
    }
    
    @Test
    public void alustenErikoistoiminnotMetodiPalauttaaFalseJokaMuuttujalleOnMaaritetty() {
        this.peli.asetaAlustenErikoistoiminnot(false);
        assertFalse(this.peli.alustenErikoistoiminnot());
    }

    private void asetaPeliinPelaajat() {
        List<Pelaaja> pelinPelaajat = new ArrayList<>();
        Collections.addAll(pelinPelaajat, pelaajat);
        this.peli.asetaPelaajat(pelinPelaajat);
    }
}