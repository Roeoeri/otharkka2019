# Käyttöohje <h1>
  
## Lataaminen <h2>

[Pelin voi ladata täälä](https://github.com/Roeoeri/otharkka2019/releases/tag/viikko6)

## Konfigurointi <h2>

Peli olettaa, että sen suoritushakemistosta löytyy tiedosto highscores.txt. Mikäli näin ei kuitenkaan ole, luo sovellus sellaisen itse.

## Pelin käynnistäminen <h2>
Kun peli on ladattu ja tallennettu haluttuun paikkaan, onnistuu sen käynnistäminen komennolla:
  `java -jar SudokupeliViikko6.jar`

## Pelin aloittaminen <h2>

Pelin voi aloittaa syöttämällä sovelluksen käynnistyksen jälkeen avautuvassa ikkunassa haluamansa nimimerkin ja valitsemalla halutun sudokun vaikeustason ikkunassa näkyvällä liukukytkimellä. Tämän jälkeen pelin voi aloittaa painamalla nappia ”Sudokuun!”.

## Pelin pelaaminen <h2>
Peliä pelataan klikkaamalla ruutuja, joissa ei ole punaisia numeroita. Mikä ruudussa ei ole numeroa, ensimmäisen klikkauksen yhteydessä ruutuun asetetaan musta numero yksi. Klikkaamalla mustaa numeroa, numeron arvo kasvaa yhdellä, paitsi jos numero on yhdeksän, jolloin numeron arvoksi asetetaan yksi. Käyttäjä voi tarkistaa, onko hänen ratkaisunsa oikea painamalla sudokun alla olevaa nappulaa ”Tarkista”.
