
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus;

public enum Suunta {
    POHJOINEN(0.0, 0, -1),
    POHJOIS_KOILLINEN(22.5, 1, -2),
    KOILLINEN(45.0, 1, -1),
    ITA_KOILLINEN(67.5, 2, -1),
    ITA(90.0, 1, 0),
    ITA_KAAKKO(112.5, 2, 1),
    KAAKKO(135, 1, 1),
    ETELA_KAAKKO(157.5, 1, 2),
    ETELA(180, 0, 1),
    ETELA_LOUNAS(202.5, -1, 2),
    LOUNAS(225, -1, 1),
    LANSI_LOUNAS(247.5, -2, 1),
    LANSI(270.0, -1, 0),
    LANSI_LUODE(292.5, -2, -1),
    LUODE(315.0, -1, -1),
    POHJOIS_LUODE(337.5, -1, -2);
    
    private double kulmaPohjoisestaMyotapaivaan;
    private int dx;
    private int dy;
    
    private Suunta(double kulmaPohjoisestaMyotapaivaan, int dx, int dy) {
        this.kulmaPohjoisestaMyotapaivaan = kulmaPohjoisestaMyotapaivaan;
        this.dx = dx;
        this.dy = dy;
    }

    public double getKulma() {
        return kulmaPohjoisestaMyotapaivaan;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
