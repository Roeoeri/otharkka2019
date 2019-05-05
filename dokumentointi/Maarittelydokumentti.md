# Vaatimusmäärittely <h1>

## Sovelluksen Tarkoitus <h2>
Työn tarkoituksena on luoda sovellus, jossa käyttäjät voivat pelata sudokua. Käyttäjiä voi olla useita ja heistä jokainen voi luoda nimimerkin sovellukseen pistelistojen tallentamista varten. Kuitenkin jokainen käyttäjä kuuluu samaan käyttäjäryhmään ja heillä on samat käyttäjäoikeudet.

## Käyttäjät <h2>
Sovelluksessa on vain yksi käyttäjärooli: normaali käyttäjä.

## Perusversion tarjoama toiminnallisuus <h2>

### Aloitusvalikko <h3>
  - Aloitusvalikossa pelaajaa pyydetään valitsemaan, haluaako hän tarkastella muiden pelaajien pisteitä vai pelata sudokua.
  
### Pistelista <h3>
  - Jos käyttäjä valitsee pistelistan, hänelle näytetään listana 10 jonkin sudokun nopeimmin ratkaissutta pelaajaa
  - Pistelistasta on mahdollista palata takaisin päävalikkoon.
  
### Nimimerkin ja pelin luominen <h3>

- Käyttäjältä pyydetään lomakkeella nimimerkkiä.
- Nimimerkki voi olla vähintään yhden merkin pituinen ja enintään kahdeksan merkin pituinen.
- Käyttäjää pyydetään valitsemaan sudokun vaikeustaso liukukytkimellä.
- Nimimerkin luomisen ja vaikeustason valinnan jälkeen käyttäjä aloittaa sudokun valitsemallaan nimimerkillä.

### Sudoku <h3>

- Käyttäjälle annetaan ratkaistavaksi hänen antamansa vaikeustason mukaisesti jokin sovelluksen luoma sudoku.
- Sudokun aloittaminen käynnistää kellon, joka kertoo, kuinka kauan käyttäjä on yrittänyt sudokun ratkaisua. Kello on nähtävissä ruudulla sudokun vieressä ja kellon aikaa käytetään myöhemmin pistetaulukon muodostamisessa.
- Kun käyttäjä on saanut sudokun ratkaistua, hänen aikansa talletetaan pistetaulukkoon ja sovellus antaa hänelle uuden sudokun ratkaistavaksi.
- Käyttäjällä on myös koko ajan mahdollisuus lopettaa sudokujen ratkaiseminen ja palata aloitusruutuun.

## Jatkokehitysideoita <h2>

- Sudokuille omat pistelistat sudokun vaikeustason mukaan.
- Jos käyttäjä lopettaa sudokun ratkaisemisen, hänelle tarjotaan mahdollisuus nähdä sudokun oikea ratkaisu. 




