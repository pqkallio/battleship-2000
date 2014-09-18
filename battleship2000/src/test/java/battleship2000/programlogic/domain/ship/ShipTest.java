
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
public class ShipTest {
    private Ship ship;
    private ShipPart[] shipParts;
    private GameTable table;
    
    public ShipTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ship = new Ship(4);
        this.table = new GameTable(10, 10);
        this.ship.setTable(table);
        this.ship.setPosition(0, 0);
        this.shipParts = this.ship.getParts();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunAluksenJonkaPituudeksiAnnetaanMinimiPituusPituusOnOikein() {
        Ship minimiPituusAlus = new Ship(SizeLimits.getShipsMinimumSize());
        minimiPituusAlus.setTable(table);
        assertEquals(SizeLimits.getShipsMinimumSize(), minimiPituusAlus.getShipLength());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNolla() {
        Ship tyhjaAlus = new Ship(0);
        tyhjaAlus.setTable(table);
        assertEquals(SizeLimits.getShipsMinimumSize(), tyhjaAlus.getShipLength());
    }
    
    @Test
    public void aluksenPituusEiVoiOllaNegatiivinen() {
        Ship tyhjaAlus = new Ship(-1);
        tyhjaAlus.setTable(table);
        assertEquals(SizeLimits.getShipsMinimumSize(), tyhjaAlus.getShipLength());
    }
    
    @Test
    public void alusVoiOllaaEnimmaismittainen() {
        Ship isoAlus = new Ship(SizeLimits.getShipsMaximumSize());
        isoAlus.setTable(table);
        assertEquals(SizeLimits.getShipsMaximumSize(), isoAlus.getShipLength());
    }
    
    @Test
    public void aluksenPituusEiVoiYlittaaEnimmaismittaa() {
        Ship isoAlus = new Ship(SizeLimits.getShipsMaximumSize() + 1);
        isoAlus.setTable(table);
        assertEquals(SizeLimits.getShipsMaximumSize(), isoAlus.getShipLength());
    }
    
    @Test
    public void luodunAluksenSuuntaOnIta() {
        assertEquals(Direction.EAST, ship.getDirection());
    }
    
    @Test
    public void luodunAluksenOsatOvatPerakkaisissaRuuduissa() {
        int ekanOsanX = ship.getParts()[0].getX();
        int ekanOsanY = ship.getParts()[0].getY();
        
        boolean ovatPerakkain = true;
        
        for (int i = 1; i < ship.getParts().length; i++) {
            if (ekanOsanX + i != ship.getParts()[i].getX()
                    || ekanOsanY != ship.getParts()[i].getY()) {
                ovatPerakkain = false;
            }
        }
        
        assertTrue(ovatPerakkain);
    }
    
    @Test
    public void aluksenMahdollisiaSuuntiaOnNelja() {
        assertEquals(4, ship.possibleDirections.size());
    }
    
    @Test
    public void kunAlustaKaantaaKerranMyotapaivaanOnSuuntaEtela() {
        kaannaAlustaMyotapaivaan(this.ship, 1);
        assertEquals(Direction.SOUTH, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKaksiKertaaMyotapaivaanOnSuuntaLansi() {
        kaannaAlustaMyotapaivaan(this.ship, 2);
        assertEquals(Direction.WEST, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKolmeKertaaMyotapaivaanOnSuuntaPohjoinen() {
        kaannaAlustaMyotapaivaan(this.ship, 3);
        assertEquals(Direction.NORTH, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaNeljaKertaaMyotapaivaanOnSuuntaIta() {
        kaannaAlustaMyotapaivaan(this.ship, 4);
        assertEquals(Direction.EAST, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKerranVastapaivaanOnSuuntaPohjoinen() {
        kaannaAlustaVastapaivaan(this.ship, 1);
        assertEquals(Direction.NORTH, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKaksiKertaaVastapaivaanOnSuuntaLansi() {
        kaannaAlustaVastapaivaan(this.ship, 2);
        assertEquals(Direction.WEST, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaKolmeKertaaVastapaivaanOnSuuntaEtela() {
        kaannaAlustaVastapaivaan(this.ship, 3);
        assertEquals(Direction.SOUTH, this.ship.getDirection());
    }
    
    @Test
    public void kunAlustaKaantaaNeljaKertaaVastapaivaanOnSuuntaIta() {
        kaannaAlustaVastapaivaan(this.ship, 4);
        assertEquals(Direction.EAST, this.ship.getDirection());
    }
    
    @Test
    public void setSuuntaMuuttaaAluksenSuunnanJosSuuntaOnMahdollistenSuuntienListassa() {
        this.ship.setDirection(Direction.NORTH);
        this.ship.setDirection(Direction.SOUTH);
        
        assertEquals(Direction.SOUTH, this.ship.getDirection());
    }
    
    @Test
    public void setSuuntaEiMuutaAluksenSuuntaaJosSuuntaEiOleMahdollistenSuuntienListassa() {
        this.ship.setDirection(Direction.NORTH);
        this.ship.setDirection(Direction.SOUTHWEST);
        
        assertEquals(Direction.NORTH, this.ship.getDirection());
    }
    
    @Test
    public void getMahdollisetSuunnatPalauttaaArrayListinJossaOnNeljaPaailmansuuntaa() {
        assertEquals(4, this.ship.getPossibleDirections().size());
    }
 
    // REFAKTOROI!!
    
    @Test
    public void intParametritSaavaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = 2;
        int dy = 3;
        
        int ensimmaisenOsanX = ship.getParts()[0].getX();
        int ensimmaisenOsanY = ship.getParts()[0].getY();
        int toisenOsanX = ship.getParts()[1].getX();
        int toisenOsanY = ship.getParts()[1].getY();
        int kolmannenOsanX = ship.getParts()[2].getX();
        int kolmannenOsanY = ship.getParts()[2].getY();
        int neljannenOsanX = ship.getParts()[3].getX();
        int neljannenOsanY = ship.getParts()[3].getY();
        
        ship.move(dx, dy);
        
        if (!(ensimmaisenOsanX + dx == ship.getParts()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == ship.getParts()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == ship.getParts()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == ship.getParts()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == ship.getParts()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == ship.getParts()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == ship.getParts()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == ship.getParts()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiLiikuttaaKaikkiaAluksenOsiaSamanVerranSamaanSuuntaan() {
        boolean kaikkiHyvin = true;
        int dx = ship.getDirection().getDx();
        int dy = ship.getDirection().getDy();
        
        int ensimmaisenOsanX = ship.getParts()[0].getX();
        int ensimmaisenOsanY = ship.getParts()[0].getY();
        int toisenOsanX = ship.getParts()[1].getX();
        int toisenOsanY = ship.getParts()[1].getY();
        int kolmannenOsanX = ship.getParts()[2].getX();
        int kolmannenOsanY = ship.getParts()[2].getY();
        int neljannenOsanX = ship.getParts()[3].getX();
        int neljannenOsanY = ship.getParts()[3].getY();
        
        ship.move();
        
        if (!(ensimmaisenOsanX + dx == ship.getParts()[0].getX())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanX + dx == ship.getParts()[1].getX())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanX + dx == ship.getParts()[2].getX())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanX + dx == ship.getParts()[3].getX())) {
            kaikkiHyvin = false;
        }
        if (!(ensimmaisenOsanY + dy == ship.getParts()[0].getY())) {
            kaikkiHyvin = false;
        }
        if (!(toisenOsanY + dy == ship.getParts()[1].getY())) {
            kaikkiHyvin = false;
        }
        if (!(kolmannenOsanY + dy == ship.getParts()[2].getY())) {
            kaikkiHyvin = false;
        }
        if (!(neljannenOsanY + dy == ship.getParts()[3].getY())) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleLanteen() {
        boolean kaikkiHyvin = true;
        
        int ensimmaisenOsanX = ship.getParts()[0].getX();
        int ensimmaisenOsanY = ship.getParts()[0].getY();
        int toisenOsanX = ship.getParts()[1].getX();
        int toisenOsanY = ship.getParts()[1].getY();
        int kolmannenOsanX = ship.getParts()[2].getX();
        int kolmannenOsanY = ship.getParts()[2].getY();
        int neljannenOsanX = ship.getParts()[3].getX();
        int neljannenOsanY = ship.getParts()[3].getY();
        
        ship.setDirection(Direction.WEST);
        
        ship.move();
        
        if (shipParts[0].getX() != ensimmaisenOsanX 
                || shipParts[1].getX() != toisenOsanX
                || shipParts[2].getX() != kolmannenOsanX
                || shipParts[3].getX() != neljannenOsanX
                || shipParts[0].getY() != ensimmaisenOsanY
                || shipParts[1].getY() != toisenOsanY
                || shipParts[2].getY() != kolmannenOsanY
                || shipParts[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolellePohjoiseen() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.NORTH);
        
        int ensimmaisenOsanX = ship.getParts()[0].getX();
        int ensimmaisenOsanY = ship.getParts()[0].getY();
        int toisenOsanX = ship.getParts()[1].getX();
        int toisenOsanY = ship.getParts()[1].getY();
        int kolmannenOsanX = ship.getParts()[2].getX();
        int kolmannenOsanY = ship.getParts()[2].getY();
        int neljannenOsanX = ship.getParts()[3].getX();
        int neljannenOsanY = ship.getParts()[3].getY();
        
        ship.move();
        
        if (shipParts[0].getX() != ensimmaisenOsanX 
                || shipParts[1].getX() != toisenOsanX
                || shipParts[2].getX() != kolmannenOsanX
                || shipParts[3].getX() != neljannenOsanX
                || shipParts[0].getY() != ensimmaisenOsanY
                || shipParts[1].getY() != toisenOsanY
                || shipParts[2].getY() != kolmannenOsanY
                || shipParts[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleItaan() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.EAST);
        ship.setPosition(ship.getTable().getWidth() - shipParts.length, 
                ship.getTable().getHeight()- shipParts.length);
        
        int ensimmaisenOsanX = ship.getParts()[0].getX();
        int ensimmaisenOsanY = ship.getParts()[0].getY();
        int toisenOsanX = ship.getParts()[1].getX();
        int toisenOsanY = ship.getParts()[1].getY();
        int kolmannenOsanX = ship.getParts()[2].getX();
        int kolmannenOsanY = ship.getParts()[2].getY();
        int neljannenOsanX = ship.getParts()[3].getX();
        int neljannenOsanY = ship.getParts()[3].getY();
        
        ship.move();
        
        if (shipParts[0].getX() != ensimmaisenOsanX 
                || shipParts[1].getX() != toisenOsanX
                || shipParts[2].getX() != kolmannenOsanX
                || shipParts[3].getX() != neljannenOsanX
                || shipParts[0].getY() != ensimmaisenOsanY
                || shipParts[1].getY() != toisenOsanY
                || shipParts[2].getY() != kolmannenOsanY
                || shipParts[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void aluksenSuuntaanPerustuvaLiikuMetodiEiLiikutaAluksenOsiaPelikentanUlkopuolelleEtelaan() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.SOUTH);
        ship.setPosition(ship.getTable().getWidth() - shipParts.length, 
                ship.getTable().getHeight() - shipParts.length);
        
        int ensimmaisenOsanX = ship.getParts()[0].getX();
        int ensimmaisenOsanY = ship.getParts()[0].getY();
        int toisenOsanX = ship.getParts()[1].getX();
        int toisenOsanY = ship.getParts()[1].getY();
        int kolmannenOsanX = ship.getParts()[2].getX();
        int kolmannenOsanY = ship.getParts()[2].getY();
        int neljannenOsanX = ship.getParts()[3].getX();
        int neljannenOsanY = ship.getParts()[3].getY();
        
        ship.move();
        
        if (shipParts[0].getX() != ensimmaisenOsanX 
                || shipParts[1].getX() != toisenOsanX
                || shipParts[2].getX() != kolmannenOsanX
                || shipParts[3].getX() != neljannenOsanX
                || shipParts[0].getY() != ensimmaisenOsanY
                || shipParts[1].getY() != toisenOsanY
                || shipParts[2].getY() != kolmannenOsanY
                || shipParts[3].getY() != neljannenOsanY) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaPOHJOINEN() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.NORTH);
        
        int ensimmaisenOsanX = shipParts[0].getX();
        int ensimmaisenOsanY = shipParts[0].getY();
        
        for (int i = 1; i < shipParts.length; i++) {
            if (ensimmaisenOsanX != shipParts[i].getX() 
                    || ensimmaisenOsanY != shipParts[i].getY() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (!shipParts[0].isShipsFront() || shipParts[0].isShipsRear()
                || shipParts[1].isShipsFront() || shipParts[1].isShipsRear()
                || shipParts[2].isShipsFront() || shipParts[2].isShipsRear()
                || shipParts[3].isShipsFront() || !shipParts[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaITA() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.EAST);
        int ensimmaisenOsanX = shipParts[0].getX();
        int ensimmaisenOsanY = shipParts[0].getY();
        
        for (int i = 1; i < shipParts.length; i++) {
            if (ensimmaisenOsanY != shipParts[i].getY() 
                    || ensimmaisenOsanX != shipParts[i].getX() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (shipParts[0].isShipsFront() || !shipParts[0].isShipsRear()
                || shipParts[1].isShipsFront() || shipParts[1].isShipsRear()
                || shipParts[2].isShipsFront() || shipParts[2].isShipsRear()
                || !shipParts[3].isShipsFront() || shipParts[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaETELA() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.SOUTH);
        
        int ensimmaisenOsanX = shipParts[0].getX();
        int ensimmaisenOsanY = shipParts[0].getY();
        
        for (int i = 1; i < shipParts.length; i++) {
            if (ensimmaisenOsanX != shipParts[i].getX() 
                    || ensimmaisenOsanY != shipParts[i].getY() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (shipParts[0].isShipsFront() || !shipParts[0].isShipsRear()
                || shipParts[1].isShipsFront() || shipParts[1].isShipsRear()
                || shipParts[2].isShipsFront() || shipParts[2].isShipsRear()
                || !shipParts[3].isShipsFront() || shipParts[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
            
        
        assertTrue(kaikkiHyvin);
    }
    
    // REFAKTOROI!!
    
    @Test
    public void maaritaOsienSijaintiPelikentallaMetodiJarjestaaOsatOikeinKunSuuntaLANSI() {
        boolean kaikkiHyvin = true;
        
        ship.setDirection(Direction.WEST);
        
        int ensimmaisenOsanX = shipParts[0].getX();
        int ensimmaisenOsanY = shipParts[0].getY();
        
        for (int i = 1; i < shipParts.length; i++) {
            if (ensimmaisenOsanY != shipParts[i].getY() 
                    || ensimmaisenOsanX != shipParts[i].getX() - i) {
                kaikkiHyvin = false;
            }
        }
        
        if (!shipParts[0].isShipsFront() || shipParts[0].isShipsRear()
                || shipParts[1].isShipsFront() || shipParts[1].isShipsRear()
                || shipParts[2].isShipsFront() || shipParts[2].isShipsRear()
                || shipParts[3].isShipsFront() || !shipParts[3].isShipsRear()) {
            kaikkiHyvin = false;
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test
    public void kunAlusLuodaanSeOnEhja() {
        assertTrue(this.ship.isIntact());
    }
    
    @Test
    public void kunAlusLuodaanSeEiOleTuhottu() {
        assertFalse(this.ship.isDestroyed());
    }
    
    @Test
    public void kunAluksenOsaanOsutaanAlusEiOleEhja() {
        ship.getParts()[0].hit();
        
        assertFalse(ship.isIntact());
    }
    
    @Test
    public void kunAluksenOsaanOsutaanAlusEiOleTuhottuJosSillaOnEhjiaOsia() {
        ship.getParts()[0].hit();
        
        assertFalse(ship.isDestroyed());
    }
    
    @Test
    public void kunAluksenKaikkiinOsiinOsutaanAlusOnTuhottu() {
        for (ShipPart osa : ship.getParts()) {
            osa.hit();
        }
        
        assertTrue(ship.isDestroyed());
    }
    
    @Test
    public void yksikaanAluksenosanGetXTaiGetYEiVoiOllaPienempiKuinNollaTaiSuurempiTaiYhtaSuuriKuinAluksenPelikentanKoko() {
        boolean kaikkiHyvin = true;
        ship.setPosition(-1, -1);
        
        for (ShipPart aluksenosa : ship.getParts()) {
            if (aluksenosa.getX() < 0 
                    || aluksenosa.getX() >= ship.getTable().getWidth()
                    || aluksenosa.getY() < 0 
                    || aluksenosa.getY() >= ship.getTable().getHeight()) {
                kaikkiHyvin = false;
            }
        }
        
        assertTrue(kaikkiHyvin);
    }
    
    @Test 
    public void konstruktoriIlmanParametrejaLuoAluksenJonkaPituusOnKolme() {
        Ship parametritonKonstruktoriAlus = new Ship();
        parametritonKonstruktoriAlus.setTable(table);
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