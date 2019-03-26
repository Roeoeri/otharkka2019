package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(100);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldAlussaOikein(){
        assertEquals("saldo: 1.0", kortti.toString());
        
    }
    
    @Test
    public void saldoPalauttaaSaldon(){
        int saldo = kortti.saldo();
        assertTrue(saldo == 100);
    }
    
    @Test 
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(150);
         assertEquals("saldo: 2.50", kortti.toString());
    }
    
    @Test 
    public void rahaaVoiVahentaaKunSaldoaTarpeeksi(){
        boolean palautus = kortti.otaRahaa(70);
        assertTrue(palautus);
        
    }
    
    @Test
    public void vahennysNakyyOikein(){
        kortti.otaRahaa(70);
        assertEquals("saldo: 0.30", kortti.toString());
        
    }
    
    @Test 
    public void rahaaEiVoiVahentaaKunSaldoaEiTarpeeksi(){
        boolean palautus = kortti.otaRahaa(150);
        assertFalse(palautus);
        
    }
    
    @Test 
    public void vahennysNakyyOikeinKunSaldoaEiTarpeeksi(){
        kortti.otaRahaa(150);
        assertEquals("saldo: 1.0", kortti.toString());
     
        
    }
}
