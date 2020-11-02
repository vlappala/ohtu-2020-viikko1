package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenKonstruktoriNegatiivinenTilavuus() {
        
        Varasto testi = new Varasto(-10);
        assertEquals(0, testi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
        @Test
    public void kaksoisKonstruktoriToimiiPositiivisellaTilavuudella() {
        
        Varasto testi = new Varasto(10, 8);
        assertEquals(10, testi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kaksoisKonstruktoriToimiiNegatiivisellaTilavuudella() {
        
        Varasto testi = new Varasto(-10, 8);
        assertEquals(0, testi.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kaksoisKonstruktoriToimiiNegatiivisellaSaldolla() {
        
        Varasto testi = new Varasto(-10, -8);
        assertEquals(0, testi.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataEnempaaKuinMahtuu() {
        varasto.lisaaVarastoon(16);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void NegatiivinenLisaysEiTeeMitaan() {
        
        Varasto testi = new Varasto(10);
        testi.lisaaVarastoon(-3);
        

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenPalauttaaNollan() {
        
        double saatuMaara = varasto.otaVarastosta(-10);         

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void liikaOttaminenTyhjentaa() {
        
        Varasto testi = new Varasto(10,10);
        
        double saatuMaara = varasto.otaVarastosta(12);         

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void toStringistaTuleeStringi() {
        
        
        String testi = varasto.toString();
        assertTrue(testi.contains("saldo"));
    }

}