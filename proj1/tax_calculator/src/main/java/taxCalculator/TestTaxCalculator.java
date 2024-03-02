package taxCalculator;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.TreeMap;

/**
 * Tests by Xin Huang, Spring 2024
 */
public class TestTaxCalculator {
    @Test
    public void getTaxTest() {
        TaxCalculator taxCalculator = new TaxCalculator();
        assertEquals(taxCalculator.getTax(-32124), 0, 0);
        assertEquals(taxCalculator.getTax(0), 0, 0);
        assertEquals(taxCalculator.getTax(500), 0,0 );
        assertEquals(taxCalculator.getTax(3500), 0, 0);
        assertEquals(taxCalculator.getTax(3501), 1*0.05, 0);
        assertEquals(taxCalculator.getTax(4000), 500*0.05, 0);
        assertEquals(taxCalculator.getTax(4001), 500*0.05+1*0.1, 0);
        assertEquals(taxCalculator.getTax(5501), 500*0.05+1500*0.1+1*0.15, 0);
        assertEquals(taxCalculator.getTax(23500), 500*0.05+1500*0.1+3000*0.15+15000*0.2, 0);
        assertEquals(taxCalculator.getTax(30000), 500*0.05+1500*0.1+3000*0.15+15000*0.2+6500*0.25, 0);
        assertEquals(taxCalculator.getTax(99930000), 500*0.05+1500*0.1+3000*.15+15000*0.2+99906500*0.25, 0);
    }

    @Test
    public void setTaxPolicyTest() {
        TaxCalculator taxCalculator = new TaxCalculator();
        TreeMap<Double, Double> newIncome2taxRate = new TreeMap<>();
        newIncome2taxRate.put(100., 0.01);
        newIncome2taxRate.put(500., 0.02);
        newIncome2taxRate.put(2000., 0.4);
        newIncome2taxRate.put(10000., 0.7);
        newIncome2taxRate.put(Double.MAX_VALUE, 0.9);
        taxCalculator.setPolicy(5000, newIncome2taxRate);

        assertEquals(taxCalculator.getTax(-32124), 0, 0);
        assertEquals(taxCalculator.getTax(0), 0, 0);
        assertEquals(taxCalculator.getTax(50), 0,0 );
        assertEquals(taxCalculator.getTax(5000), 0, 0);
        assertEquals(taxCalculator.getTax(5001), 1*0.01, 0);
        assertEquals(taxCalculator.getTax(5500), 100*0.01+400*0.02, 0);
        assertEquals(taxCalculator.getTax(5701), 100*0.01+400*0.02+201*0.4, 0);
        assertEquals(taxCalculator.getTax(7001), 100*0.01+400*0.02+1500*0.4+1*0.7, 0);
        assertEquals(taxCalculator.getTax(23500), 100*0.01+400*0.02+1500*0.4+8000*0.7+8500*0.9, 0);
        assertEquals(taxCalculator.getTax(99923500.), 100*0.01+400*0.02+1500*0.4+8000*0.7+99908500*0.9, 0);
    }

    @Test
    public void checkFormatOfIncome2taxRatesTest() {
        TreeMap<Double, Double> mapping1 = new TreeMap<>();
        mapping1.put(100., 0.01);
        mapping1.put(200., 0.02);
        mapping1.put(2000., 0.4);
        mapping1.put(10000., 0.7);
        mapping1.put(Double.MAX_VALUE, 0.9);
        assertTrue(TaxCalculator.checkFormatOfIncome2taxRates(mapping1));

        TreeMap<Double, Double> mapping2 = new TreeMap<>();
        mapping2.put(100., 0.01);
        mapping2.put(500., 0.01);
        mapping2.put(2000., 0.4);
        mapping2.put(10000., 0.7);
        mapping2.put(Double.MAX_VALUE, 0.9);
        assertFalse(TaxCalculator.checkFormatOfIncome2taxRates(mapping2));

        TreeMap<Double, Double> mapping3 = new TreeMap<>();
        mapping3.put(100., 0.01);
        mapping3.put(500., 0.02);
        mapping3.put(2000., 0.4);
        mapping3.put(10000., 0.3);
        mapping3.put(Double.MAX_VALUE, 0.9);
        assertFalse(TaxCalculator.checkFormatOfIncome2taxRates( mapping3));

        TreeMap<Double, Double> mapping4 = new TreeMap<>();
        mapping4.put(100., 0.01);
        mapping4.put(500., 0.01);
        mapping4.put(20., 0.4);
        mapping4.put(10000., 0.7);
        mapping4.put(Double.MAX_VALUE, 0.9);
        assertFalse(TaxCalculator.checkFormatOfIncome2taxRates(mapping4));

    }
}
