# Käyttöohje <h1>
  
## Lataaminen <h2>

[Pelin voi ladata täälä](https://github.com/Roeoeri/otharkka2019/releases/tag/viikko6)

## Konfigurointi <h2>

Peli olettaa, että sen suoritushakemistosta löytyy tiedosto highscores.txt. Mikäli tiedostoa ei ole, sovelluksen pitäisi luoda se itse. Mikäli näin ei kuitenkaan tapahdu, voi tiedoston luoda manuaalisesti vaikka seuraavalla komennolla:

`touch highscores.txt`

## Pelin käynnistäminen <h2>
Kun peli on ladattu ja tallennettu haluttuun paikkaan, navigoi komentorivillä hakemistoon, johon peli on tallennettu. Tämän jälkeen pelin avaaminen onnistuu komennolla: 
  
  `java -jar SudokupeliViikko6.jar`
  
## Aloitusvalikko <h2>
  Pelin käynnistymisen jälkeen avautuu valikko, jossa on kaksi nappia "Pelaa" ja "Tilastot".
  
## Tilastot <h2>
  Painamalla nappia "Tilastot", käyttäjälle avautuu näkymä, johon on listattu enintään 10 sudokun nopeimmin ratkaissutta pelaajaa, sekä heidän aikansa ja sijoituksensa taulukossa. Näkymästä pääsee pois painamalla nappia "Takaisin"
  
## Pelin aloittaminen <h2>

Kun käyttäjä on painanut "Pelaa" nappia aloitusvalikosta, pelin voi aloittaa syöttämällä avautuvassa ikkunassa haluamansa nimimerkin ja valitsemalla halutun sudokun vaikeustason ikkunassa näkyvällä liukukytkimellä. Tämän jälkeen pelin voi aloittaa painamalla nappia ”Sudokuun!”.

## Pelin pelaaminen <h2>
Peliä pelataan klikkaamalla ruutuja, joissa ei ole mustia numeroita. Mikä ruudussa ei ole numeroa, ensimmäisen klikkauksen yhteydessä ruutuun asetetaan sininen numero yksi. Klikkaamalla sinistä numeroa, numeron arvo kasvaa yhdellä, paitsi jos numero on yhdeksän, jolloin numeron arvoksi asetetaan yksi. Käyttäjä voi tarkistaa, onko hänen ratkaisunsa oikea painamalla sudokun alla olevaa nappulaa ”Tarkista”. Mikäli sudoku on oikein, käyttäjälle näytetään onnistumisesta kertova näkymä, ja painamalla näkymän ainutta nappia, käyttäjä pääsee takaisin pelin luomiseen. Pelin aikana käyttäjällä on myös mahdollisuus painaa nappia "Luovuta", mikä ohjaa hänet takaisin aloitusvalikkoon.
