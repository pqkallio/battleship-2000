
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunAluksenJonkaPituudeksiAnnetaanMinimiPituusPituusOnOikein() {
        Alus minimiPituusAlus = new Alus(Kokorajoitteet.aluksenVahimmaispituus());
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), minimiPituusAlus.getAluksenPituus());
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
    public void luodunAluksenOsatOvatPerakkaisissaRuuduissa() {
        int ekanOsanX = alus.getOsat()[0].getX();
        int ekanOsanY = alus.getOsat()[0].getY();
        
        boolean ovatPerakkain = true;
        
        for (int i = 1; i < alus.getOsat().length; i++) {
            if (ekanOsanX + i != alus.getOsat()[i].getX()
                    || ekanOsanY != alus.getOsat()[i].getY()) {
                ovatPerakkain = false;
            }
        }
        
        assertTrue(ovatPerakkain);
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
    public void setSuuntaMuuttaaAluksenSuunnanJosSuuntaOnMahdollistenSuuntienListassa() {
        this.alus.setSuunta(Suunta.POHJOINEN);
        this.alus.setSuunta(Suunta.ETELA);
        
        assertEquals(Suunta.ETELA, this.alus.getSuunta());
    }
    
    @Test
    public void setSuuntaEiMuutaAluksenSuuntaaJosSuuntaEiOleMahdollistenSuuntienListassa() {
        this.alus.setSuunta(Suunta.POHJOINEN);
        this.alus.setSuunta(Suunta.LOUNAS);
        
        assertEquals(Suunta.POHJOINEN, this.alus.getSuunta());
    }
    
    @Test
    public void getMahdollisetSuunnatPalauttaaArrayListinJossaOnNeljaPaailmansuuntaa() {
        assertEquals(4, this.alus.getMahdollisetSuunnat().size());
    }
    
    @Test
    public void intParametritSaavaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = 2;
        int dy = 3;
        Alus alus2 = new Alus(4);
        
        int ensimmaisenOsanX = alus2.getOsat()[0].getX();
        int ensimmaisenOsanY = alus2.getOsat()[0].getY();
        int toisenOsanX = alus2.getOsat()[1].getX();
        int toisenOsanY = alus2.getOsat()[1].getY();
        int kolmannenOsanX = alus2.getOsat()[2].getX();
        int kolmannenOsanY = alus2.getOsat()[2].getY();
        int neljannenOsanX = alus2.getOsat()[3].getX();
        int neljannenOsanY = alus2.getOsat()[3].getY();
        
        alus2.liiku(dx, dy);
        
        if (!(ensimmaisenOsanX + dx == alus2.getOsat()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == alus2.getOsat()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == alus2.getOsat()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == alus2.getOsat()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == alus2.getOsat()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == alus2.getOsat()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == alus2.getOsat()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == alus2.getOsat()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        Alus alus2 = new Alus(4);
        int dx = alus2.getSuunta().getDx();
        int dy = alus2.getSuunta().getDy();
        
        int ensimmaisenOsanX = alus2.getOsat()[0].getX();
        int ensimmaisenOsanY = alus2.getOsat()[0].getY();
        int toisenOsanX = alus2.getOsat()[1].getX();
        int toisenOsanY = alus2.getOsat()[1].getY();
        int kolmannenOsanX = alus2.getOsat()[2].getX();
        int kolmannenOsanY = alus2.getOsat()[2].getY();
        int neljannenOsanX = alus2.getOsat()[3].getX();
        int neljannenOsanY = alus2.getOsat()[3].getY();
        
        alus2.liiku();
        
        if (!(ensimmaisenOsanX + dx == alus2.getOsat()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == alus2.getOsat()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == alus2.getOsat()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == alus2.getOsat()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == alus2.getOsat()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == alus2.getOsat()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == alus2.getOsat()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == alus2.getOsat()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // TÄSTÄ ETEENPÄIN TESTIEN APUMETODIT
    
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