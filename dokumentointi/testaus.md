# Testidokumentti <h1>

Ohjelmaa on testattu yksikkö ja integraatiotasolla Junittia hyödyntäem ja manuaalisesti järjestelmätasolla ohjemaa käyttäen. 

## Yksikkö- ja integraatiotestaus <h2>

### Sovelluslogiikka <h3>

Pakkauksen sudokupeli.domain luokkia Sudoku, RandomNumbers ja Player on testattu pääasiassa yksikkötestein. Näitä luokkia oli mahdollista testata kattavasti yksikkötestein, sillä niiden käyttötapaukset ohjelman toiminnan aikana eivät ole suuresti riippuvaisia muiden luokkien toiminnasta. Tietenkin esimerkiksi Sudoku-luokan testit kattavat samalla myös ainakin osaksi RandomNumbers-luokan testit, mutta siltikin yksikkötestien kirjoittaminen näille luokille tuntui luontevalta. 

Integraatiotestillä HighscoreListTest on testattu HighscoreList ja FileHighScoreDao. Testeissä on erityisesti pyritty ottamaan huomioon käyttöliittymän asettamat käyttötapaukset. Integraatiotestissä daolle on lisäksi asetettu oma HighScoreListTest.txt- tekstitiedostona, jota dao käyttää testien aikana.

### Testauskattavuus <h3>

Käyttöliittymäluokkaa lukuunottamatta testien rivikattavuus on 93 % ja haaraumakattavuus 96%. Testaamatta jäivät käytännössä vain Dao-luokan mahdolliset poikkeustilanteet.


## Järjestelmätestaus <h2>

Testauksessa sovellus on asennettu käyttöohjeen mukaisesti ja sitä on käytetty Linux-ympäristössä. Sovellusta on käytetty tilanteissa, jossa sen vaatimia tekstitiedostoja ei ole valmiiksi suoritushakemistossa, jolloin sovellus on luonut ne itse. Testaamatta on kuitenkin jäänyt tilanne, jossa sovelluksella ei ole kirjoitusoikeuksia suorituskansioonsa. Sovelluksen käytön aikana on myös yritetty käydä kattavasti lävitse määrittelydokumentin käyttötapaukset.
 

## Jääneet laatuongelmat <h2>

- Sovelluksen käyttäytyminen tilanteessa, jossa sillä ei ole kirjoitusoikeuksia, on jäänyt selvittämättä. Käyttöliittymässä tilanteeseen ollaan varauduttu omalla ilmoituksellaan, mutta asiaa ei testattu.

- Joissakin tilanteissa pelin vaikeustason valitseva liukukytkin ei anna samaa arvoa, kuin ruudulla näkisi.



