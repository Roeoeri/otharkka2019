/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author htomi
 */
public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(500);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luotuKassapaateOnOlemassa() {
        assertTrue(paate != null);
    }

    @Test
    public void luotuKassapaatealustaaArvotOikein() {
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void kateismaksuToimiiEdullisessaKunMaksuOnRiittava() {
        int vaihtoraha = paate.syoEdullisesti(500);
        assertTrue(paate.kassassaRahaa() == 100000 + 240);
        assertTrue(vaihtoraha == 260);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);

    }

    @Test
    public void kateismaksuToimiiMaukkaassaKunMaksuOnRiittava() {
        int vaihtoraha = paate.syoMaukkaasti(500);
        assertTrue(paate.kassassaRahaa() == 100000 + 400);
        assertTrue(vaihtoraha == 100);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);

    }

    @Test
    public void kateismaksuToimiiEdullisessaKunMaksuEiRiittava() {
        int vaihtoraha = paate.syoEdullisesti(100);
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(vaihtoraha == 100);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);

    }

    @Test
    public void kateismaksuToimiiMaukkaassaKunMaksuEiRiittava() {
        int vaihtoraha = paate.syoMaukkaasti(100);
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(vaihtoraha == 100);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);

    }

    @Test
    public void korttiMaksuToimiiEdullisessaKunKortillaRahaa() {
        boolean palautus = paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 260);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
        assertTrue(palautus);

    }

    @Test
    public void korttiMaksuToimiiMaukkaassaKunKortillaRahaa() {
        boolean palautus = paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
        assertTrue(palautus);

    }

    @Test
    public void korttiMaksuToimiiEdullisessaKunKortillaEiRahaa() {
        kortti = new Maksukortti(100);
        boolean palautus = paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 100);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
        assertTrue(!palautus);

    }

    @Test
    public void korttiMaksuToimiiMaukkaassaKunKortillaEiRahaa() {
        kortti = new Maksukortti(100);
        boolean palautus = paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
        assertTrue(!palautus);

    }

    public void kortillaMaksaminenEiMuutaKassapaatteenRahamaaraa() {

        kortti = new Maksukortti(700);
        paate.syoEdullisesti(kortti);
        assertTrue(paate.kassassaRahaa() == 100000);

        paate.syoMaukkaasti(kortti);
        assertTrue(paate.kassassaRahaa() == 100000);

        paate.syoEdullisesti(kortti);
        assertTrue(paate.kassassaRahaa() == 100000);

        paate.syoMaukkaasti(kortti);
        assertTrue(paate.kassassaRahaa() == 100000);

    }

    @Test
    public void kortinLataaminenKasvattaaKortinJaKassanSaldoa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertTrue(paate.kassassaRahaa() == 100000 + 100);
        assertTrue(kortti.saldo() == 600);
    }

    @Test
    public void kortinLataaminenNegatiivisellaMaarallaEiKasvataSaldoja() {
        paate.lataaRahaaKortille(kortti, -100);
        assertTrue(paate.kassassaRahaa() == 100000);
        assertTrue(kortti.saldo() == 500);

    }

}
