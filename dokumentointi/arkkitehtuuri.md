
# Arkkitehtuuri <h1>

## Rakenne <h2>

Ohjelma noudattaa seuraavanlaista pakkausrakenetta:
![Pakkausrakenne](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/Pakkausrakenne.png)

Yhteys sudokupeli.ui:n ja sudokupeli.dao:n välillä johtuu siitä, että ui käyttää dao-luokan konstruktoria luodakseen daon, jonka se antaa parametrina domain pakkauksen luokalle. Jälkikäteen ajateltuna olisi ollut järkevämpää siirtää dao:n luominen suoraan domain-pakkaukseen, jolloin pakkausrakenteesta olisi saanut kolmikerroksisen ja näin eristää pois käyttöliittymäluokasta vastuuta jota ei sinne kuulu.

## Käyttöliittymä <h2>

Ohjelmassa ovat seuraavat näkymät:

- Aloitusvalikko
- Tilastot
- Pelin luominen
- Sudoku
- Sudoku ratkaistu

Jokaista näkymää vastaa oma Scene-olionsa, jotka piirretään vuorollaan Stage-luokan setScene() metodilla. Ui on saatu melko hyvin eristettyä sovelluslogiikasta ja käytänössä Ui:n ainoaksi tehtäväksi jää piirtää käyttöliittymää sovelluslogiikan antaman datan perusteella. 

## Sovelluslogiikka <h2>

Sovelluslogiikkaa hoitavat luokat Player, HighScoreList, Sudoku ja RandomNumbers. Karkealla tasolla luokkien väliset suhteet näyttävät tältä:

![Luokkakaavio](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/Luokkakaavio.png)

Luokan Sudoku vastuulla on Sudokuruudukon mallintaminen, luominen ja pelitilanteiden hoitaminen. Sudokujen luomiseen luokka käyttää luokkaa RandomNumbers, mikä tarjoaa sudokukäyttöön sopivia satunnaisia lukuja. 

HighscoreList-luokka pitää kirjaa pelaajien pisteistä käyttäen luokkaa Player, mikä pitää huolen siitä, että pelaajien tiedot tulevat tallennetuiksi sopivassa muodossa. 


## Tiedon tallentaminen <h2>

Sovellus käyttää FileHighScoreDao luokka pisteiden tallentamiseen. Luokan vastuulla on myös tallennettavan tiedon validointi, eli luokka pitää huolen siitä, että korkeintaan 10 pelaajan tiedot tallennetaan ja että pelaajat ovat lajiteltuna parhaimmasta huonoimpaan. Pelaajat tallennetaan tiedostoon highscores.txt muodossa: 

`pelaaja:pelaajanAika`

## Esimerkkikaavio nimimerkin luomisesta <h2>

### Nimimerkin luominen
![Sekvenssikaavio](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/sekvenssikaavioKirjautuminen.png)







