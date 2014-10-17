##Battleship 2000 - rakennekuvaus
####Graafisen käyttöliittymän ja sovelluslogiikan suhde
Käyttöliittymän sekä sovelluslogiikan suhde on minimoitu käyttämällä hyväksi tarkkailija-suunnittelumallia.
Käyttöliittymän luokat toteuttavat sovelluslogiikka-pakkauksessa olevan _LogicObserver_-rajapinnan, jonka avulla
sovelluslogiikka voi pyytää tarvittaessa käyttöliittymää tarkkailijoita päivittämään tilansa.
Sovelluslogiikka on täysin riippumaton käyttöliittymästä, joka puolestaan kommunikoi sovelluslogiikan
kanssa ainoastaan _GameCommands_-luokan avulla antaen sovelluslogiikalle käskyäj esim. luoda uusi peli tai
aloittaa peli.

####Sovelluslogiikka
Sovelluslogiikan puolella annetaan _Rules_-luokan ilmentymä parametrina _CreateGame_-ohjausluokalle,
jonka avulla luodaan uusi BattleShipGame-luokan ilmentymä. Rules-luokan määrittelyt määräävät pelin pelaajat,
heidän pelikenttänsä koon sekä laivojen määrän. Tietokonepelaajalle luodaan tekoäly _ComputerGuessingPattern_-luokan
ilmentymän muodossa. _PlayOneRound_-luokkaa käytetään yksittäisen pelivuoron alusta loppuun ohjaamiseen.

####Graafinen käyttöliittymä
Käyttöliittymä antaa sovelluslogiikan luokkien visuaaliset vastineet sekä lisää peliin äänitehosteet. Käyttöliittymän
kautta annetaan sovelluslogiikalle komentoja.