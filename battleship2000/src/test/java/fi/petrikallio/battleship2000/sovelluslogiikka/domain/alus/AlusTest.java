
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;
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
    private Aluksenosa[] aluksenosat;
    private Pelikentta kentta;
    
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
        this.alus = new Alus(4);
        this.kentta = new Pelikentta(10, 10);
        this.alus.setPelikentta(kentta);
        this.aluksenosat = this.alus.getOsat();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunAluksenJonkaPituudeksiAnnetaanMinimiPituusPituusOnOikein() {
        Alus minimiPituusAlus = new Alus(Kokorajoitteet.aluksenVahimmaispituus());
        minimiPituusAlus.setPelikentta(kentta);
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), minimiPituusAlus.getAluksenPituus());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNolla() {
        Alus tyhjaAlus = new Alus(0);
        tyhjaAlus.setPelikentta(kentta);
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), tyhjaAlus.getAluksenPituus());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNegatiivinen() {
        Alus tyhjaAlus = new Alus(-1);
        tyhjaAlus.setPelikentta(kentta);
        assertEquals(Kokorajoitteet.aluksenVahimmaispituus(), tyhjaAlus.getAluksenPituus());
    }
    
    @Test
    public void alusVoiOllaaEnimmaismittainen() {
        Alus isoAlus = new Alus(Kokorajoitteet.aluksenEnimmaispituus());
        isoAlus.setPelikentta(kentta);
        assertEquals(Kokorajoitteet.aluksenEnimmaispituus(), isoAlus.getAluksenPituus());
    }
    
    @Test
    public void aluksenPituusEiVoiYlittaaEnimmaismittaa() {
        Alus isoAlus = new Alus(Kokorajoitteet.aluksenEnimmaispituus() + 1);
        isoAlus.setPelikentta(kentta);
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
 
    // REFAKTOROI!!
    
    @Test
    public void intParametritSaavaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = 2;
        int dy = 3;
        
        int ensimmaisenOsanX = alus.getOsat()[0].getX();
        int ensimmaisenOsanY = alus.getOsat()[0].getY();
        int toisenOsanX = alus.getOsat()[1].getX();
        int toisenOsanY = alus.getOsat()[1].getY();
        int kolmannenOsanX = alus.getOsat()[2].getX();
        int kolmannenOsanY = alus.getOsat()[2].getY();
        int neljannenOsanX = alus.getOsat()[3].getX();
        int neljannenOsanY = alus.getOsat()[3].getY();
        
        alus.liiku(dx, dy);
        
        if (!(ensimmaisenOsanX + dx == alus.getOsat()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == alus.getOsat()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == alus.getOsat()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == alus.getOsat()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == alus.getOsat()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == alus.getOsat()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == alus.getOsat()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == alus.getOsat()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = alus.getSuunta().getDx();
        int dy = alus.getSuunta().getDy();
        
        int ensimmaisenOsanX = alus.getOsat()[0].getX();
        int ensimmaisenOsanY = alus.getOsat()[0].getY();
        int toisenOsanX = alus.getOsat()[1].getX();
        int toisenOsanY = alus.getOsat()[1].getY();
        int kolmannenOsanX = alus.getOsat()[2].getX();
        int kolmannenOsanY = alus.getOsat()[2].getY();
        int neljannenOsanX = alus.getOsat()[3].getX();
        int neljannenOsanY = alus.getOsat()[3].getY();
        
        alus.liiku();
        
        if (!(ensimmaisenOsanX + dx == alus.getOsat()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == alus.getOsat()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == alus.getOsat()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == alus.getOsat()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == alus.getOsat()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == alus.getOsat()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == alus.getOsat()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == alus.getOsat()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleLanteen() {
        boolean kaikkiHyvin = true;
        
        int ensimmaisenOsanX = alus.getOsat()[0].getX();
        int ensimmaisenOsanY = alus.getOsat()[0].getY();
        int toisenOsanX = alus.getOsat()[1].getX();
        int toisenOsanY = alus.getOsat()[1].getY();
        int kolmannenOsanX = alus.getOsat()[2].getX();
        int kolmannenOsanY = alus.getOsat()[2].getY();
        int neljannenOsanX = alus.getOsat()[3].getX();
        int neljannenOsanY = alus.getOsat()[3].getY();
        
        alus.setSuunta(Suunta.LANSI);
        
        alus.liiku();
        
        if (aluksenosat[0].getX() != ensimmaisenOsanX 
                || aluksenosat[1].getX() != toisenOsanX
                || aluksenosat[2].getX() != kolmannenOsanX
                || aluksenosat[3].getX() != neljannenOsanX
                || aluksenosat[0].getY() != ensimmaisenOsanY
                || aluksenosat[1].getY() != toisenOsanY
                || aluksenosat[2].getY() != kolmannenOsanY
                || aluksenosat[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolellePohjoiseen() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.POHJOINEN);
        
        int ensimmaisenOsanX = alus.getOsat()[0].getX();
        int ensimmaisenOsanY = alus.getOsat()[0].getY();
        int toisenOsanX = alus.getOsat()[1].getX();
        int toisenOsanY = alus.getOsat()[1].getY();
        int kolmannenOsanX = alus.getOsat()[2].getX();
        int kolmannenOsanY = alus.getOsat()[2].getY();
        int neljannenOsanX = alus.getOsat()[3].getX();
        int neljannenOsanY = alus.getOsat()[3].getY();
        
        alus.liiku();
        
        if (aluksenosat[0].getX() != ensimmaisenOsanX 
                || aluksenosat[1].getX() != toisenOsanX
                || aluksenosat[2].getX() != kolmannenOsanX
                || aluksenosat[3].getX() != neljannenOsanX
                || aluksenosat[0].getY() != ensimmaisenOsanY
                || aluksenosat[1].getY() != toisenOsanY
                || aluksenosat[2].getY() != kolmannenOsanY
                || aluksenosat[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleItaan() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.ITA);
        alus.asetaSijainti(alus.getPelikentta().haeLeveys() - aluksenosat.length, 
                alus.getPelikentta().haeKorkeus()- aluksenosat.length);
        
        int ensimmaisenOsanX = alus.getOsat()[0].getX();
        int ensimmaisenOsanY = alus.getOsat()[0].getY();
        int toisenOsanX = alus.getOsat()[1].getX();
        int toisenOsanY = alus.getOsat()[1].getY();
        int kolmannenOsanX = alus.getOsat()[2].getX();
        int kolmannenOsanY = alus.getOsat()[2].getY();
        int neljannenOsanX = alus.getOsat()[3].getX();
        int neljannenOsanY = alus.getOsat()[3].getY();
        
        alus.liiku();
        
        if (aluksenosat[0].getX() != ensimmaisenOsanX 
                || aluksenosat[1].getX() != toisenOsanX
                || aluksenosat[2].getX() != kolmannenOsanX
                || aluksenosat[3].getX() != neljannenOsanX
                || aluksenosat[0].getY() != ensimmaisenOsanY
                || aluksenosat[1].getY() != toisenOsanY
                || aluksenosat[2].getY() != kolmannenOsanY
                || aluksenosat[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleEtelaan() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.ETELA);
        alus.asetaSijainti(alus.getPelikentta().haeLeveys() - aluksenosat.length, 
                alus.getPelikentta().haeKorkeus() - aluksenosat.length);
        
        int ensimmaisenOsanX = alus.getOsat()[0].getX();
        int ensimmaisenOsanY = alus.getOsat()[0].getY();
        int toisenOsanX = alus.getOsat()[1].getX();
        int toisenOsanY = alus.getOsat()[1].getY();
        int kolmannenOsanX = alus.getOsat()[2].getX();
        int kolmannenOsanY = alus.getOsat()[2].getY();
        int neljannenOsanX = alus.getOsat()[3].getX();
        int neljannenOsanY = alus.getOsat()[3].getY();
        
        alus.liiku();
        
        if (aluksenosat[0].getX() != ensimmaisenOsanX 
                || aluksenosat[1].getX() != toisenOsanX
                || aluksenosat[2].getX() != kolmannenOsanX
                || aluksenosat[3].getX() != neljannenOsanX
                || aluksenosat[0].getY() != ensimmaisenOsanY
                || aluksenosat[1].getY() != toisenOsanY
                || aluksenosat[2].getY() != kolmannenOsanY
                || aluksenosat[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaPOHJOINEN() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.POHJOINEN);
        
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanX != aluksenosat[i].getX() 
                    || ensimmaisenOsanY != aluksenosat[i].getY() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (!aluksenosat[0].onAluksenPaa() || aluksenosat[0].onAluksenPera()
                || aluksenosat[1].onAluksenPaa() || aluksenosat[1].onAluksenPera()
                || aluksenosat[2].onAluksenPaa() || aluksenosat[2].onAluksenPera()
                || aluksenosat[3].onAluksenPaa() || !aluksenosat[3].onAluksenPera()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaITA() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.ITA);
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanY != aluksenosat[i].getY() 
                    || ensimmaisenOsanX != aluksenosat[i].getX() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (aluksenosat[0].onAluksenPaa() || !aluksenosat[0].onAluksenPera()
                || aluksenosat[1].onAluksenPaa() || aluksenosat[1].onAluksenPera()
                || aluksenosat[2].onAluksenPaa() || aluksenosat[2].onAluksenPera()
                || !aluksenosat[3].onAluksenPaa() || aluksenosat[3].onAluksenPera()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaETELA() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.ETELA);
        
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanX != aluksenosat[i].getX() 
                    || ensimmaisenOsanY != aluksenosat[i].getY() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (aluksenosat[0].onAluksenPaa() || !aluksenosat[0].onAluksenPera()
                || aluksenosat[1].onAluksenPaa() || aluksenosat[1].onAluksenPera()
                || aluksenosat[2].onAluksenPaa() || aluksenosat[2].onAluksenPera()
                || !aluksenosat[3].onAluksenPaa() || aluksenosat[3].onAluksenPera()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaLANSI() {
        boolean kaikkiHyvin = true;
        
        alus.setSuunta(Suunta.LANSI);
        
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanY != aluksenosat[i].getY() 
                    || ensimmaisenOsanX != aluksenosat[i].getX() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (!aluksenosat[0].onAluksenPaa() || aluksenosat[0].onAluksenPera()
                || aluksenosat[1].onAluksenPaa() || aluksenosat[1].onAluksenPera()
                || aluksenosat[2].onAluksenPaa() || aluksenosat[2].onAluksenPera()
                || aluksenosat[3].onAluksenPaa() || !aluksenosat[3].onAluksenPera()) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void kunAlusLuodaanSeOnEhja() {
        assertTrue(this.alus.onEhja());
    }
    
    @Test
    public void kunAlusLuodaanSeEiOleTuhottu() {
        assertFalse(this.alus.onTuhottu());
    }
    
    @Test
    public void kunAluksenOsaanOsutaanAlusEiOleEhja() {
        alus.getOsat()[0].osuma();
        
        assertFalse(alus.onEhja());
    }
    
    @Test
    public void kunAluksenOsaanOsutaanAlusEiOleTuhottuJosSillaOnEhjiaOsia() {
        alus.getOsat()[0].osuma();
        
        assertFalse(alus.onTuhottu());
    }
    
    @Test
    public void kunAluksenKaikkiinOsiinOsutaanAlusOnTuhottu() {
        for (Aluksenosa osa : alus.getOsat()) {
            osa.osuma();
        }
        
        assertTrue(alus.onTuhottu());
    }
    
    @Test
    public void yksikaanAluksenosanGetXTaiGetYEiVoiOllaPienempiKuinNollaTaiSuurempiTaiYhtaSuuriKuinAluksenPelikentanKoko() {
        boolean kaikkiHyvin = true;
        alus.asetaSijainti(-1, -1);
        
        for (Aluksenosa aluksenosa : alus.getOsat()) {
            if (aluksenosa.getX() < 0 
                    || aluksenosa.getX() >= alus.getPelikentta().haeLeveys()
                    || aluksenosa.getY() < 0 
                    || aluksenosa.getY() >= alus.getPelikentta().haeKorkeus()) {
                kaikkiHyvin = false;
            }
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test 
    public void konstruktoriIlmanParametrejaLuoAluksenJonkaPituusOnKolme() {
        Alus parametritonKonstruktoriAlus = new Alus();
        parametritonKonstruktoriAlus.setPelikentta(kentta);
        assertEquals(3, parametritonKonstruktoriAlus.getOsat().length);
    }
    
    // TÄSTÄ ETEENPÄIN TESTIEN APUMETODIT
    
    private void kaannaAlustaMyotapaivaan(Alus alus, int kertaa) {
        for (int i = 0; i < kertaa; i++) {
            alus.kaannaMyotapaivaan();
        }
    }
    
    private void kaannaAlustaVastapaivaan(Alus alus, int kertaa) {
        for (int i = 0; i < kertaa; i++) {
            alus.kaannaVastapaivaan();
        }
    }
}