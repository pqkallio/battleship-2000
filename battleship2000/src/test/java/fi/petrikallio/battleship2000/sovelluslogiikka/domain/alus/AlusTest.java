
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.saannot.Kokorajoitteet;
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
public class AlusTest {
    private Alus alus;
    private Alus tykkilaiva;
    private Alus lentotukialus;
    private Alus ohjusalus;
    private Alus sukellusvene;
    private Alus tulenjohtolaiva;
    
    public AlusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.alus = new Alus(1);
        this.tykkilaiva = new Tykkilaiva();
        this.lentotukialus = new Lentotukialus();
        this.ohjusalus = new Ohjusalus();
        this.sukellusvene = new Sukellusvene();
        this.tulenjohtolaiva = new Tulenjohtolaiva();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunAluksenJonkaPituudeksiAnnetaanMinimiPituusPituusOnOikein() {
        Alus minimiPituusAlus = new Alus(Kokorajoitteet.aluksenVahimmaispituus());
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), alus.getAluksenPituus());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNolla() {
        Alus tyhjaAlus = new Alus(0);
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), tyhjaAlus.getAluksenPituus());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNegatiivinen() {
        Alus tyhjaAlus = new Alus(-1);
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), tyhjaAlus.getAluksenPituus());
    }
    
    @Test
    public void alusVoiOllaaEnimmaismittainen() {
        Alus isoAlus = new Alus(Kokorajoitteet.aluksenEnimmaispituus());
        assertEquals(Kokorajoitteet.aluksenEnimmaispituus(), isoAlus.getAluksenPituus());
    }
    
    @Test
    public void aluksenPituusEiVoiYlittaaEnimmaismittaa() {
        Alus isoAlus = new Alus(Kokorajoitteet.aluksenEnimmaispituus() + 1);
        assertEquals(Kokorajoitteet.aluksenEnimmaispituus(), isoAlus.getAluksenPituus());
    }
    
    @Test
    public void luodunAluksenSuuntaOnIta() {
        assertEquals(Suunta.ITA, alus.getSuunta());
    }
    
    @Test
    public void aluksenMahdollisiaSuuntiaOnNelja() {
        assertEquals(4, alus.mahdollisetSuunnat.size());
    }
    
    @Test
    public void kunAlustaKaantaaKerranMyotapaivaanOnSuuntaEtela() {
        kaannaAlustaMyotapaivaan(this.alus, 1);
        assertEquals(Suunta.ETELA, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaKaksiKertaaMyotapaivaanOnSuuntaLansi() {
        kaannaAlustaMyotapaivaan(this.alus, 2);
        assertEquals(Suunta.LANSI, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaKolmeKertaaMyotapaivaanOnSuuntaPohjoinen() {
        kaannaAlustaMyotapaivaan(this.alus, 3);
        assertEquals(Suunta.POHJOINEN, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaNeljaKertaaMyotapaivaanOnSuuntaIta() {
        kaannaAlustaMyotapaivaan(this.alus, 4);
        assertEquals(Suunta.ITA, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaKerranVastapaivaanOnSuuntaPohjoinen() {
        kaannaAlustaVastapaivaan(this.alus, 1);
        assertEquals(Suunta.POHJOINEN, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaKaksiKertaaVastapaivaanOnSuuntaLansi() {
        kaannaAlustaVastapaivaan(this.alus, 2);
        assertEquals(Suunta.LANSI, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaKolmeKertaaVastapaivaanOnSuuntaEtela() {
        kaannaAlustaVastapaivaan(this.alus, 3);
        assertEquals(Suunta.ETELA, this.alus.getSuunta());
    }
    
    @Test
    public void kunAlustaKaantaaNeljaKertaaVastapaivaanOnSuuntaIta() {
        kaannaAlustaVastapaivaan(this.alus, 4);
        assertEquals(Suunta.ITA, this.alus.getSuunta());
    }
    
    @Test
    public void aluksenOsatVastaavatParametrinaSyotettyjaOsia() {
        
    }
    
    private void kaannaAlustaMyotapaivaan(Alus alus, int kertaa) {
        for (int i = 0; i < kertaa; i++) {
            this.alus.kaannaMyotapaivaan();
        }
    }
    
    private void kaannaAlustaVastapaivaan(Alus alus, int kertaa) {
        for (int i = 0; i < kertaa; i++) {
            this.alus.kaannaVastapaivaan();
        }
    }
}