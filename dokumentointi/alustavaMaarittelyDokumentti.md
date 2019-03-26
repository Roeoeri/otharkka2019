# Vaativuusmäärittely <h1>

## Sovelluksen Tarkoitus <h2>
Työn tarkoituksena on luoda sovellus, jossa käyttäjät voivat pelata sudokua. Käyttäjiä voi olla useita ja heistä jokainen voi luoda nimimerkin sovellukseen pistelistojen tallentamista varten. Kuitenkin jokainen käyttäjä kuuluu samaan käyttäjäryhmään ja heillä on samat käyttäjäoikeudet.

## Käyttäjät <h2>
Sovelluksessa on vain yksi käyttäjärooli: normaali käyttäjä.

## Perusversion tarjoama toiminnallisuus <h2>
## Nimimerkin luominen ja kirjautuminen <h3>

- Käyttäjältä pyydetään lomakkeella nimimerkkiä.
- Nimimerkki voi olla vähintään yhden merkin pituinen ja enintään kahdeksan merkin pituinen.
- Nimimerkin luomisen jälkeen käyttäjä kirjautuu sovellukseen valitsemallaan nimimerkillä.

## Kirjautumisen jälkeen <h3>

- Käyttäjälle annetaan ratkaistavaksi jokin sovelluksen luoma sudoku.
- Sudokun aloittaminen käynnistää kellon, joka kertoo, kuinka kauan käyttäjä on yrittänyt sudokun ratkaisua. Kello on nähtävissä ruudulla sudokun vieressä ja kellon aikaa käytetään myöhemmin pistetaulukon muodostamisessa.
- Ratkaistavan sudokun vieressä käyttäjälle näytetään myös pistetaulukko, jossa näytetään enintään 10 sudokun nopeimmin ratkaissutta käyttäjää.
- Kun käyttäjä on saanut sudokun ratkaistua, hänen aikansa talletetaan pistetaulukkoon ja sovellus antaa hänelle uuden sudokun ratkaistavaksi.
- Käyttäjällä on myös koko ajan mahdollisuus lopettaa sudokujen ratkaiseminen ja kirjautua ulos sovelluksesta.

## Jatkokehitysideoita <h2>

- Kirjautumisen jälkeen käyttäjälle tarjotaan kaksi erilaista sudokua: helppo ja vaikea. 
- Helpossa sudokussa on huomattavasti enemmän valmiiksi täytettyjä ruutuja kuin vaikeassa.
- Helpolle ja vaikealle sudokulle on erikseen omat pistelistansa. 
- Jos käyttäjä ei keksi sudokulle ratkaisua, hänelle tarjotaan mahdollisuus luovuttaa. Mikäli käyttäjä luovuttaa, sovellus näyttää käyttäjälle sudokun ratkaisun.




