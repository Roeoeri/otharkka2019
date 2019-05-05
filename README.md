# Ohjelmistotekniikka 2019 kevät, Harjoitustyö <h1>
  Työn tarkoituksena on luoda sovellus, jossa käyttäjät voivat pelata sudokua. Käyttäjiä voi olla useita ja heistä jokainen voi luoda nimimerkin sovellukseen pistelistojen tallentamista varten. Sovellus toimii myös kevään 2019 Ohjelmistotekniikka-kurssin harjoitustyönä.

  ## Dokumentaatio <h2>
  [Käyttöohje](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/Kaytto.md)
  
  [Vaatimusmäärittely](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/Maarittelydokumentti.md)
  
  [Arkkitehtuurikuvaus](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/arkkitehtuuri.md)
  
  [Testaus](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/testaus.md)
  
  [Tuntikirjanpito](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/tuntikirjanpito.md)
  
  
  ## Releaset <h2>
  [Loppupalautus](https://github.com/Roeoeri/otharkka2019/releases/tag/Viikko7)
  
  [Viikko6](https://github.com/Roeoeri/otharkka2019/releases/tag/viikko6)
  
  [Viikko5](https://github.com/Roeoeri/otharkka2019/releases)
  
  ## Oleellisest komentorivikomennot <h2>
  
  ### Testaus <h3>
  Testien ajaminen onnistuu komennolla:`mvn test`
  
  Testikattavuusraportin luominen onnistuu komennolla: `mvn jacoco:report`, jonka jälkeen raportti löytyy polusta target/site/jacoco/index.html.
  
  ### Suoritettava Jar <h3>
  Jar luodaan komennolla `mvn package`, jonka jälkeen jar löytyy polusta /target/Sudokupeli-1.0-SNAPSHOT-shaded.jar.
  
  ### CheckStyle <h3>
  Checkstyleraportti saadaan komennolla ` mvn jxr:jxr checkstyle:checkstyle`, jonka jälkeen raportti on polulla target/site/checkstyle.html.
  
  ### JavaDoc <h3>
 Javadoc luodaan komennolla `mvn javadoc:javadoc` Tämän jälkeen javadoc on avattavissa polulla target/site/apidocs/index.html

  
  
  
  
  
  
  
  
  


