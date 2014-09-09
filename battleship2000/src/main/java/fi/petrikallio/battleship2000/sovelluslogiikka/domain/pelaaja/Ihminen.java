
package fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelaaja;

import fi.petrikallio.battleship2000.sovelluslogiikka.BattleShipGame;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.alus.Alus;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Kentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Pelikentta;
import fi.petrikallio.battleship2000.sovelluslogiikka.domain.pelikentta.Ruutu;
import java.util.Scanner;

public class Ihminen extends Pelaaja {
    
    public Ihminen() {
        
    }
    
    public Ihminen(BattleShipGame peli) {
        super(peli);
    }
    
    @Override
    public Ruutu valitseRuutu(Kentta pelikentta, boolean ruutuaVoiPommittaaUseasti) {
        
        
        return null;
    }
    
    @Override
    public String toString() {
        return "ihminen";
    }

    @Override
    public void sijoitteleAluksetPelikentalle() {
        for (Alus alus : super.haeAlukset()) {
            sijoitaAlusPelikentalle(alus);
        }
    }

    private void sijoitaAlusPelikentalle(Alus alus) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Sijoita alus " + alus);
        while (true) {
            System.out.println("Anna koordinaatit");
            System.out.print("x: ");
            int x = Integer.parseInt(lukija.nextLine());
            System.out.print("y: ");
            int y = Integer.parseInt(lukija.nextLine());
            alus.asetaSijainti(x, y);
            System.out.print("Sijainti asetettu, haluatko muuttaa? (k/e) ");
            String ok = lukija.nextLine();
            if (ok.equals("k")) {
                alus.asetaSijainti(x, y);
                break;
            }
        }
        
        System.out.println("");
        tulostaPelikentta();
        System.out.println("");
        
        while (true) {
            System.out.println("Aluksen suunta on nyt " + alus.getSuunta());
            System.out.print("Haluatko muuttaa suuntaa? (k/e) ");
            String halu = lukija.nextLine();
            if (!halu.equals("e")) {
                System.out.println("Käännä alusta myötaä- tai vastapäivään");
                System.out.print("(m/v)");
                String suunta = lukija.nextLine();
                
                if (suunta.equals("m")) {
                    alus.kaannaMyotapaivaan();
                } else if (suunta.equals("v")) {
                    alus.kaannaVastapaivaan();
                }
            }
            System.out.print("Onko ok? (k/e) ");
            String ok = lukija.nextLine();
            
            if (ok.equals("k")) {
                break;
            }
        }
        
        System.out.println("");
        tulostaPelikentta();
        System.out.println("");
    }

    private void tulostaPelikentta() {
        Pelikentta kentta = super.haePelikentta();
        
        for (Integer y : kentta.haeKentta().keySet()) {
            for (Ruutu ruutu : kentta.haeKentta().get(y).values()) {
                if (ruutu.getAluksenosa() == null) {
                    System.out.print("O");
                } else {
                    if (ruutu.getAluksenosa().onAluksenPaa()) {
                        System.out.print("H");
                    } else if (ruutu.getAluksenosa().onAluksenPera()) {
                        System.out.print("T");
                    } else {
                        System.out.print("X");
                    }
                }
            }
            System.out.println("");
        }
    }
}
