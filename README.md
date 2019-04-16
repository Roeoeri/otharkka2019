# Ohjelmistotekniikka 2019 kevät, Harjoitustyö <h1>
  Työn tarkoituksena on luoda sovellus, jossa käyttäjät voivat pelata sudokua. Käyttäjiä voi olla useita ja heistä jokainen voi luoda nimimerkin sovellukseen pistelistojen tallentamista varten. Sovellus toimii myös kevään 2019 Ohjelmistotekniikka-kurssin harjoitustyönä.

  ## Dokumentaatio <h2>
  
  [Vaatimusmäärittely](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/alustavaMaarittelyDokumentti.md)
  
  [Arkkitehtuurikuvaus](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/arkkitehtuuri.md)
  
  [Tuntikirjanpito](https://github.com/Roeoeri/otharkka2019/blob/master/dokumentointi/tuntikirjanpito.md)
  
  ## Releaset <h2>
  [Viikko5](https://github.com/Roeoeri/otharkka2019/releases)
  
  ## Oleellisest komentorivikomennot <h2>
  ### Testaus <h3>
  Testien ajaminen onnistuu komennolla:`mvn test`
  
  Testikattavuusraportin generointi onnistuu komennolla: `mvn jacoco:report`, jonka jälkeen raportti löytyy hakemistosta target/site/jacoco/index.html.
  
  ### Suoritettava Jar <h3>
  Jar luodaan komennolla `mvn package`, jonka jälkeen jar löytyy hakemistosta /target/Sudokupeli-1.0-SNAPSHOT-shaded.jar
  
  ### CheckStyle <h3>
  Checkstyleraportti saadaan komennolla ` mvn jxr:jxr checkstyle:checkstyle`, jonka jälkeen raportti on hakemistossa target/site/checkstyle.html

  
  
  
  
  
  
  
  
  


