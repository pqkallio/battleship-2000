
package battleship2000.programlogic.domain.ship;

import battleship2000.programlogic.domain.ship.ShipPart;
import battleship2000.programlogic.domain.ship.Direction;
import battleship2000.programlogic.domain.ship.Ship;
import battleship2000.programlogic.domain.table.GameTable;
import battleship2000.programlogic.rules.SizeLimits;
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
    private Ship alus;
    private ShipPart[] aluksenosat;
    private GameTable kentta;
    
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
        this.alus = new Ship(4);
        this.kentta = new GameTable(10, 10);
        this.alus.setTable(kentta);
        this.aluksenosat = this.alus.getParts();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunAluksenJonkaPituudeksiAnnetaanMinimiPituusPituusOnOikein() {
        Ship minimiPituusAlus = new Ship(SizeLimits.getShipsMinimumSize());
        minimiPituusAlus.setTable(kentta);
        assertEquals(SizeLimits.getShipsMinimumSize(), minimiPituusAlus.getShipLength());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNolla() {
        Ship tyhjaAlus = new Ship(0);
        tyhjaAlus.setTable(kentta);
        assertEquals(SizeLimits.getShipsMinimumSize(), tyhjaAlus.getShipLength());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNegatiivinen() {
        Ship tyhjaAlus = new Ship(-1);
        tyhjaAlus.setTable(kentta);
        assertEquals(SizeLimits.getShipsMinimumSize(), tyhjaAlus.getShipLength());
    }
    
    @Test
    public void alusVoiOllaaEnimmaismittainen() {
        Ship isoAlus = new Ship(SizeLimits.getShipsMaximumSize());
        isoAlus.setTable(kentta);
        assertEquals(SizeLimits.getShipsMaximumSize(), isoAlus.getShipLength());
    }
    
    @Test
    public void aluksenPituusEiVoiYlittaaEnimmaismittaa() {
        Ship isoAlus = new Ship(SizeLimits.getShipsMaximumSize() + 1);
        isoAlus.setTable(kentta);
        assertEquals(SizeLimits.getShipsMaximumSize(), isoAlus.getShipLength());
    }
    
    @Test
    public void luodunAluksenSuuntaOnIta() {
        assertEquals(Direction.EAST, alus.getDirection());
    }
    
    @Test
    public void luodunAluksenOsatOvatPerakkaisissaRuuduissa() {
        int ekanOsanX = alus.getParts()[0].getX();
        int ekanOsanY = alus.getParts()[0].getY();
        
        boolean ovatPerakkain = true;
        
        for (int i = 1; i < alus.getParts().length; i++) {
            if (ekanOsanX + i != alus.getParts()[i].getX()
                    || ekanOsanY != alus.getParts()[i].getY()) {
                ovatPerakkain = false;
            }
        }
        
        assertTrue(ovatPerakkain);
    }
    
    @Test
    public void aluksenMahdollisiaSuuntiaOnNelja() {
        assertEquals(4, alus.possibleDirections.size());
    }
    
    @Test
    public void kunAlustaKaantaaKerranMyotapaivaanOnSuuntaEtela() {
        kaannaAlustaMyotapaivaan(this.alus, 1);
        assertEquals(Direction.SOUTH, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKaksiKertaaMyotapaivaanOnSuuntaLansi() {
        kaannaAlustaMyotapaivaan(this.alus, 2);
        assertEquals(Direction.WEST, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKolmeKertaaMyotapaivaanOnSuuntaPohjoinen() {
        kaannaAlustaMyotapaivaan(this.alus, 3);
        assertEquals(Direction.NORTH, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaNeljaKertaaMyotapaivaanOnSuuntaIta() {
        kaannaAlustaMyotapaivaan(this.alus, 4);
        assertEquals(Direction.EAST, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKerranVastapaivaanOnSuuntaPohjoinen() {
        kaannaAlustaVastapaivaan(this.alus, 1);
        assertEquals(Direction.NORTH, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKaksiKertaaVastapaivaanOnSuuntaLansi() {
        kaannaAlustaVastapaivaan(this.alus, 2);
        assertEquals(Direction.WEST, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKolmeKertaaVastapaivaanOnSuuntaEtela() {
        kaannaAlustaVastapaivaan(this.alus, 3);
        assertEquals(Direction.SOUTH, this.alus.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaNeljaKertaaVastapaivaanOnSuuntaIta() {
        kaannaAlustaVastapaivaan(this.alus, 4);
        assertEquals(Direction.EAST, this.alus.getDirection());
    }
    
    @Test
    public void setSuuntaMuuttaaAluksenSuunnanJosSuuntaOnMahdollistenSuuntienListassa() {
        this.alus.setDirection(Direction.NORTH);
        this.alus.setDirection(Direction.SOUTH);
        
        assertEquals(Direction.SOUTH, this.alus.getDirection());
    }
    
    @Test
    public void setSuuntaEiMuutaAluksenSuuntaaJosSuuntaEiOleMahdollistenSuuntienListassa() {
        this.alus.setDirection(Direction.NORTH);
        this.alus.setDirection(Direction.SOUTHWEST);
        
        assertEquals(Direction.NORTH, this.alus.getDirection());
    }
    
    @Test
    public void getMahdollisetSuunnatPalauttaaArrayListinJossaOnNeljaPaailmansuuntaa() {
        assertEquals(4, this.alus.getPossibleDirections().size());
    }
 
    // REFAKTOROI!!
    
    @Test
    public void intParametritSaavaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = 2;
        int dy = 3;
        
        int ensimmaisenOsanX = alus.getParts()[0].getX();
        int ensimmaisenOsanY = alus.getParts()[0].getY();
        int toisenOsanX = alus.getParts()[1].getX();
        int toisenOsanY = alus.getParts()[1].getY();
        int kolmannenOsanX = alus.getParts()[2].getX();
        int kolmannenOsanY = alus.getParts()[2].getY();
        int neljannenOsanX = alus.getParts()[3].getX();
        int neljannenOsanY = alus.getParts()[3].getY();
        
        alus.move(dx, dy);
        
        if (!(ensimmaisenOsanX + dx == alus.getParts()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == alus.getParts()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == alus.getParts()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == alus.getParts()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == alus.getParts()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == alus.getParts()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == alus.getParts()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == alus.getParts()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = alus.getDirection().getDx();
        int dy = alus.getDirection().getDy();
        
        int ensimmaisenOsanX = alus.getParts()[0].getX();
        int ensimmaisenOsanY = alus.getParts()[0].getY();
        int toisenOsanX = alus.getParts()[1].getX();
        int toisenOsanY = alus.getParts()[1].getY();
        int kolmannenOsanX = alus.getParts()[2].getX();
        int kolmannenOsanY = alus.getParts()[2].getY();
        int neljannenOsanX = alus.getParts()[3].getX();
        int neljannenOsanY = alus.getParts()[3].getY();
        
        alus.move();
        
        if (!(ensimmaisenOsanX + dx == alus.getParts()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == alus.getParts()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == alus.getParts()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == alus.getParts()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == alus.getParts()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == alus.getParts()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == alus.getParts()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == alus.getParts()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleLanteen() {
        boolean kaikkiHyvin = true;
        
        int ensimmaisenOsanX = alus.getParts()[0].getX();
        int ensimmaisenOsanY = alus.getParts()[0].getY();
        int toisenOsanX = alus.getParts()[1].getX();
        int toisenOsanY = alus.getParts()[1].getY();
        int kolmannenOsanX = alus.getParts()[2].getX();
        int kolmannenOsanY = alus.getParts()[2].getY();
        int neljannenOsanX = alus.getParts()[3].getX();
        int neljannenOsanY = alus.getParts()[3].getY();
        
        alus.setDirection(Direction.WEST);
        
        alus.move();
        
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
        
        alus.setDirection(Direction.NORTH);
        
        int ensimmaisenOsanX = alus.getParts()[0].getX();
        int ensimmaisenOsanY = alus.getParts()[0].getY();
        int toisenOsanX = alus.getParts()[1].getX();
        int toisenOsanY = alus.getParts()[1].getY();
        int kolmannenOsanX = alus.getParts()[2].getX();
        int kolmannenOsanY = alus.getParts()[2].getY();
        int neljannenOsanX = alus.getParts()[3].getX();
        int neljannenOsanY = alus.getParts()[3].getY();
        
        alus.move();
        
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
        
        alus.setDirection(Direction.EAST);
        alus.setPosition(alus.getTable().getWidth() - aluksenosat.length, 
                alus.getTable().getHeight()- aluksenosat.length);
        
        int ensimmaisenOsanX = alus.getParts()[0].getX();
        int ensimmaisenOsanY = alus.getParts()[0].getY();
        int toisenOsanX = alus.getParts()[1].getX();
        int toisenOsanY = alus.getParts()[1].getY();
        int kolmannenOsanX = alus.getParts()[2].getX();
        int kolmannenOsanY = alus.getParts()[2].getY();
        int neljannenOsanX = alus.getParts()[3].getX();
        int neljannenOsanY = alus.getParts()[3].getY();
        
        alus.move();
        
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
        
        alus.setDirection(Direction.SOUTH);
        alus.setPosition(alus.getTable().getWidth() - aluksenosat.length, 
                alus.getTable().getHeight() - aluksenosat.length);
        
        int ensimmaisenOsanX = alus.getParts()[0].getX();
        int ensimmaisenOsanY = alus.getParts()[0].getY();
        int toisenOsanX = alus.getParts()[1].getX();
        int toisenOsanY = alus.getParts()[1].getY();
        int kolmannenOsanX = alus.getParts()[2].getX();
        int kolmannenOsanY = alus.getParts()[2].getY();
        int neljannenOsanX = alus.getParts()[3].getX();
        int neljannenOsanY = alus.getParts()[3].getY();
        
        alus.move();
        
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
        
        alus.setDirection(Direction.NORTH);
        
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanX != aluksenosat[i].getX() 
                    || ensimmaisenOsanY != aluksenosat[i].getY() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (!aluksenosat[0].isShipsFront() || aluksenosat[0].isShipsRear()
                || aluksenosat[1].isShipsFront() || aluksenosat[1].isShipsRear()
                || aluksenosat[2].isShipsFront() || aluksenosat[2].isShipsRear()
                || aluksenosat[3].isShipsFront() || !aluksenosat[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaITA() {
        boolean kaikkiHyvin = true;
        
        alus.setDirection(Direction.EAST);
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanY != aluksenosat[i].getY() 
                    || ensimmaisenOsanX != aluksenosat[i].getX() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (aluksenosat[0].isShipsFront() || !aluksenosat[0].isShipsRear()
                || aluksenosat[1].isShipsFront() || aluksenosat[1].isShipsRear()
                || aluksenosat[2].isShipsFront() || aluksenosat[2].isShipsRear()
                || !aluksenosat[3].isShipsFront() || aluksenosat[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaETELA() {
        boolean kaikkiHyvin = true;
        
        alus.setDirection(Direction.SOUTH);
        
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanX != aluksenosat[i].getX() 
                    || ensimmaisenOsanY != aluksenosat[i].getY() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (aluksenosat[0].isShipsFront() || !aluksenosat[0].isShipsRear()
                || aluksenosat[1].isShipsFront() || aluksenosat[1].isShipsRear()
                || aluksenosat[2].isShipsFront() || aluksenosat[2].isShipsRear()
                || !aluksenosat[3].isShipsFront() || aluksenosat[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaLANSI() {
        boolean kaikkiHyvin = true;
        
        alus.setDirection(Direction.WEST);
        
        int ensimmaisenOsanX = aluksenosat[0].getX();
        int ensimmaisenOsanY = aluksenosat[0].getY();
        
        for (int i = 1; i < aluksenosat.length; i++) {
            if (ensimmaisenOsanY != aluksenosat[i].getY() 
                    || ensimmaisenOsanX != aluksenosat[i].getX() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (!aluksenosat[0].isShipsFront() || aluksenosat[0].isShipsRear()
                || aluksenosat[1].isShipsFront() || aluksenosat[1].isShipsRear()
                || aluksenosat[2].isShipsFront() || aluksenosat[2].isShipsRear()
                || aluksenosat[3].isShipsFront() || !aluksenosat[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void kunAlusLuodaanSeOnEhja() {
        assertTrue(this.alus.isIntact());
    }
    
    @Test
    public void kunAlusLuodaanSeEiOleTuhottu() {
        assertFalse(this.alus.isDestroyed());
    }
    
    @Test
    public void kunAluksenOsaanOsutaanAlusEiOleEhja() {
        alus.getParts()[0].hit();
        
        assertFalse(alus.isIntact());
    }
    
    @Test
    public void kunAluksenOsaanOsutaanAlusEiOleTuhottuJosSillaOnEhjiaOsia() {
        alus.getParts()[0].hit();
        
        assertFalse(alus.isDestroyed());
    }
    
    @Test
    public void kunAluksenKaikkiinOsiinOsutaanAlusOnTuhottu() {
        for (ShipPart osa : alus.getParts()) {
            osa.hit();
        }
        
        assertTrue(alus.isDestroyed());
    }
    
    @Test
    public void yksikaanAluksenosanGetXTaiGetYEiVoiOllaPienempiKuinNollaTaiSuurempiTaiYhtaSuuriKuinAluksenPelikentanKoko() {
        boolean kaikkiHyvin = true;
        alus.setPosition(-1, -1);
        
        for (ShipPart aluksenosa : alus.getParts()) {
            if (aluksenosa.getX() < 0 
                    || aluksenosa.getX() >= alus.getTable().getWidth()
                    || aluksenosa.getY() < 0 
                    || aluksenosa.getY() >= alus.getTable().getHeight()) {
                kaikkiHyvin = false;
            }
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test 
    public void konstruktoriIlmanParametrejaLuoAluksenJonkaPituusOnKolme() {
        Ship parametritonKonstruktoriAlus = new Ship();
        parametritonKonstruktoriAlus.setTable(kentta);
        assertEquals(3, parametritonKonstruktoriAlus.getParts().length);
    }
    
    // TÄSTÄ ETEENPÄIN TESTIEN APUMETODIT
    
    private void kaannaAlustaMyotapaivaan(Ship alus, int kertaa) {
        for (int i = 0; i < kertaa; i++) {
            alus.turnClockwise();
        }
    }
    
    private void kaannaAlustaVastapaivaan(Ship alus, int kertaa) {
        for (int i = 0; i < kertaa; i++) {
            alus.turnCounterClockwise();
        }
    }
}