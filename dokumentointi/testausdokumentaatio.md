###Testausdokumentaatio
Siitä lähtien, kun peli on ollut pelattavassa kunnossa, on jokaisen muutoksen jälkeen tarkastettu peliä pelaamalla, että kaikki toimii.

Tietokoneen tekoälyä en ole lähtenyt automaattisesti testaamaan, vaan testaaminen on tapahtunut tarkkailemalla jokaista tietokoneen siirtoa pelatessa. Tämä tekniikka on auttanut myös löytämään aukkokohtia tekoälystä ja paikkaamaan niitä (esim. tietokonepelaaja miettii nyt kahteen kertaan ennen kuin valitsee ruudun, jonka viereen on jo pommitettu).
Samoin ohjausluokkaa _PlayOneRound_ en ole testannut, koska testaus on tapahtunut samalla lailla tarkkailemalla pelin kulkua.

Varsinaisia bugeja en ole pelissäni huomannut, mutta tietokoneen tekoälyyn jäi parantamisen varaa: jos tietokone on osunut ja pommittanut yhden suunnan loppuun tuhoamatta laivaa, saattaa se valita vastakkaisen suunnan sijaan jonkin toisen suunnan.
