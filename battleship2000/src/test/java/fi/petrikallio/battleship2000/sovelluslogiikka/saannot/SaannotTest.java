
package fi.petrikallio.battleship2000.sovelluslogiikka.saannot;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alustyyppi;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja.Pelaajatyyppi;
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
    private Saannot perussaannot;
    private Saannot tyhjatSaannot;
    private final Alustyyppi[] alustyypit = {Alustyyppi.TULENJOHTOLAIVA, 
                                             Alustyyppi.TYKKILAIVA,
                                             Alustyyppi.SUKELLUSVENE,
                                             Alustyyppi.OHJUSALUS,
                                             Alustyyppi.LENTOTUKIALUS};
    private final Pelaajatyyppi[] pelaajatyypit = {Pelaajatyyppi.IHMINEN,
                                                   Pelaajatyyppi.TIETOKONE};
    
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
        this.perussaannot = new Perussaannot(true);
        this.tyhjatSaannot = new Saannot();
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
        int min = Kokorajoitteet.kentanSivunVahimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(min-1, min-1);
        
        assertEquals(min, this.tyhjatSaannot.haeKentanLeveys());
    }
    
    @Test
    public void kentanLeveydenOllessaSuurempiKuinSallittuEnimmaispituusLeveysOnSallittuEnimmaispituus() {
        int max = Kokorajoitteet.kentanSivunEnimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(max+1, max+1);
        
        assertEquals(max, this.tyhjatSaannot.haeKentanLeveys());
    }
    
    @Test
    public void kentanKorkeudenOllessaPienempiKuinSallittuVahimmaispituusKorkeusOnSallittuVahimmaispituus() {
        int min = Kokorajoitteet.kentanSivunVahimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(min-1, min-1);
        
        assertEquals(min, this.tyhjatSaannot.haeKentanKorkeus());
    }
    
    @Test
    public void kentanKorkeudenOllessaSuurempiKuinSallittuEnimmaispituusKorkeusOnSallittuEnimmaispituus() {
        int max = Kokorajoitteet.kentanSivunEnimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(max+1, max+1);
        
        assertEquals(max, this.tyhjatSaannot.haeKentanKorkeus());
    }
    
    @Test
    public void kentanLeveydenOllessaSallittuVahimmaispituusLeveysOnSallittuVahimmaispituus() {
        int min = Kokorajoitteet.kentanSivunVahimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(min, min);
        
        assertEquals(min, this.tyhjatSaannot.haeKentanLeveys());
    }
    
    @Test
    public void kentanLeveydenOllessaSallittuEnimmaispituusLeveysOnSallittuEnimmaispituus() {
        int max = Kokorajoitteet.kentanSivunEnimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(max, max);
        
        assertEquals(max, this.tyhjatSaannot.haeKentanLeveys());
    }
    
    @Test
    public void kentanKorkeudenOllessaSallittuVahimmaispituusKorkeusOnSallittuVahimmaispituus() {
        int min = Kokorajoitteet.kentanSivunVahimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(min, min);
        
        assertEquals(min, this.tyhjatSaannot.haeKentanKorkeus());
    }
    
    @Test
    public void kentanKorkeudenOllessaSallittuEnimmaispituusKorkeusOnSallittuEnimmaispituus() {
        int max = Kokorajoitteet.kentanSivunEnimmaispituus();
        this.tyhjatSaannot.asetaKentanKoko(max, max);
        
        assertEquals(max, this.tyhjatSaannot.haeKentanKorkeus());
    }
    
    @Test
    public void saantoihinTallentuuListaAlustyyppejaAvaimellaALUKSET() {
        assertEquals(ArrayList.class, perussaannot.haeAlustyypit().getClass());
    }
    
    @Test
    public void saantojenAlustyyppejaOnSamanVerranKuinParametrinaAnnettu() {
        List<Alustyyppi> lisattavatAlustyypit = new ArrayList<>();
        Collections.addAll(lisattavatAlustyypit, alustyypit);
        tyhjatSaannot.asetaAlukset(lisattavatAlustyypit);
        
        assertEquals(alustyypit.length, tyhjatSaannot.haeAlustyypit().size());
    }
    
    @Test
    public void saantojenAlustyypitOvatIdenttisetParametrinaAnnettujenKanssa() {
        boolean samat = true;
        
        List<Alustyyppi> lisattavatAlustyypit = new ArrayList<>();
        Collections.addAll(lisattavatAlustyypit, alustyypit);
        tyhjatSaannot.asetaAlukset(lisattavatAlustyypit);
        
        for (int i = 0; i < tyhjatSaannot.haeAlustyypit().size(); i++) {
            if (!(tyhjatSaannot.haeAlustyypit().get(i).haeLuokka() == alustyypit[i].haeLuokka())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
    
    @Test
    public void trueLiikkumisSaantoTallentuuOikeinAvaimellaALUKSET_LIIKKUVAT() {
        this.tyhjatSaannot.asetaLiikkumissaanto(true);
        assertTrue(this.tyhjatSaannot.liikkuvatkoAlukset());
    }
    
    @Test
    public void falseLiikkumisSaantoTallentuuOikeinAvaimellaALUKSET_LIIKKUVAT() {
        this.tyhjatSaannot.asetaLiikkumissaanto(false);
        assertFalse(this.tyhjatSaannot.liikkuvatkoAlukset());
    }
    
    @Test
    public void trueAlustenErikoistoimintoSaantoTallentuuOikeinAvaimellaALUSTEN_ERIKOISTOIMINNOT() {
        this.tyhjatSaannot.asetaAlustenErikoistoiminnot(true);
        assertTrue(this.tyhjatSaannot.aluksillaErikoistoiminnot());
    }
    
    @Test
    public void falseAlustenErikoistoimintoSaantoTallentuuOikeinAvaimellaALUSTEN_ERIKOISTOIMINNOT() {
        this.tyhjatSaannot.asetaAlustenErikoistoiminnot(false);
        assertFalse(this.tyhjatSaannot.aluksillaErikoistoiminnot());
    }
    
    @Test
    public void saantoihinTallentuuListaPelaajatyyppejaAvaimellaPELAAJAT() {
        assertEquals(ArrayList.class, perussaannot.haePelaajatyypit().getClass());
    }
    
    @Test
    public void saantojenPelaajatyyppejaOnSamanVerranKuinParametrinaAnnettu() {
        List<Pelaajatyyppi> lisattavatPelaajatyypit = new ArrayList<>();
        Collections.addAll(lisattavatPelaajatyypit, pelaajatyypit);
        tyhjatSaannot.asetaPelaajat(lisattavatPelaajatyypit);
        
        assertEquals(pelaajatyypit.length, tyhjatSaannot.haePelaajatyypit().size());
    }
    
    @Test
    public void saantojenPelaajatyypitOvatIdenttisetParametrinaAnnettujenKanssa() {
        boolean samat = true;
        
        List<Pelaajatyyppi> lisattavatPelaajatyypit = new ArrayList<>();
        Collections.addAll(lisattavatPelaajatyypit, pelaajatyypit);
        tyhjatSaannot.asetaPelaajat(lisattavatPelaajatyypit);
        
        for (int i = 0; i < tyhjatSaannot.haePelaajatyypit().size(); i++) {
            if (!(tyhjatSaannot.haePelaajatyypit().get(i).haeLuokka() == pelaajatyypit[i].haeLuokka())) {
                samat = false;
            }
        }
        
        assertTrue(samat);
    }
}