package taxCalculator;

import java.util.Map;
import java.util.TreeMap;

/**
 *  A tax calculator Class to calculate the amount of tax for personal
 * income. It's also able to change the threshold of the personal income
 * tax and the tax rates of different amount of income.
 * */
public class TaxCalculator {
    /** The threshold of personal income tax. */
    private double taxThreshold;
    /** The mapping from income to rates of tax. */
    private TreeMap<Double, Double> income2taxRates;

    /** Default construction method. */
    public TaxCalculator() {
        taxThreshold = 3500;
        income2taxRates = new TreeMap<>();

        income2taxRates.put(500., 0.05);
        income2taxRates.put(2000., 0.1);
        income2taxRates.put(5000., 0.15);
        income2taxRates.put(20000., 0.2);
        income2taxRates.put(Double.MAX_VALUE, 0.25);
    }

    /**
     * Return the tax of the given income.
     * The only requirement is that given income must be double.
     * */
    public double getTax(double income) {
        double tax = 0;
        double lastIncomeLevel = 0;
        double income2tax = income - taxThreshold;

        if (income2tax <= 0) {
            return 0;
        }

        for (Map.Entry<Double, Double> entry : income2taxRates.entrySet()) {
            if (income2tax > entry.getKey()) {
                tax += (entry.getKey() - lastIncomeLevel) * entry.getValue();
                lastIncomeLevel = entry.getKey();
            } else {
                tax += (income2tax - lastIncomeLevel) * entry.getValue();
                return tax;
            }
        }
        return tax;
    }

    /** The only interface to set tax threshold or income2taxRates. */
    public void setPolicy(double threshold, TreeMap<Double, Double> income2taxRates) {
        if (taxThreshold < 0) {
            throw new IllegalArgumentException("Invalid tax threshold, which should not less than 0.");
        }
        if (!checkFormatOfIncome2taxRates(income2taxRates)) {
            throw new IllegalArgumentException("Invalid input. Please obey the format.");
        }
        setTaxThreshold(threshold);
        setIncome2taxRates(income2taxRates);
    }

    /** Reset the tax threshold by a given number. */
    private void setTaxThreshold(double taxThreshold) {
        if (taxThreshold < 0) {
            throw new IllegalArgumentException("Invalid tax threshold, which should not less than 0.");
        }
        this.taxThreshold = taxThreshold;
    }

    /** Reset the mapping from income to rates of tax by a given map. */
    private void setIncome2taxRates(TreeMap<Double, Double> Income2taxRates) {
        if (!TaxCalculator.checkFormatOfIncome2taxRates(Income2taxRates)) {
            throw new IllegalArgumentException("Invalid input. Please obey the format.");
        }
        this.income2taxRates = Income2taxRates;
    }

    /** Display the tax threshold and mapping from income to rates of tax. */
    public void displayConfigure() {
        System.out.println("Tax threshold: " + taxThreshold);
        System.out.println("Tax rate: ");
        for (Map.Entry<Double, Double> entry : income2taxRates.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    /**
     * Return true if the input of TreeMap follows the format of the mapping from
     * income to rate: First the amount of income and rates are increasing. Second
     * the rates must range in (0,1).
     * */
    public static boolean checkFormatOfIncome2taxRates(TreeMap<Double, Double> newIncome2taxRates) {
        if (newIncome2taxRates == null) {
            return false;
        }
        double income = 0.;
        double rate = 0.;
        for (Map.Entry<Double, Double> entry : newIncome2taxRates.entrySet()) {
            if (income >= entry.getKey() || rate >= entry.getValue()) {
                System.out.println("Invalid format. Income or Rate must be increasing.");
                return false;
            }
            income = entry.getKey();
            rate = entry.getValue();
            if (rate >= 1) {
                System.out.println("Invalid format. Rate is out of range(0,1).");
                return false;
            }
        }
        return true;
    }

    /** Display an example of the format of the mapping from income to rates of tax.  */
    public static void displayFormatOfIncome2taxRates() {
        System.out.println("""
                The amount of income and rates are increasing.
                And the rates must range in (0,1).
                For example:""");
        System.out.println("500.0 0.05");
        System.out.println("2000.0 0.1");
        System.out.println("5000.0 0.15");
        System.out.println("20000.0 0.2");
        System.out.println("infinite 0.25");
    }

}
