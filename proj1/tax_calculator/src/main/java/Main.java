import java.util.TreeMap;

import taxCalculator.TaxCalculator;
import static taxCalculator.Utils.*;

public class Main {
    /**
     *  This program is a terminal to interact with users.
     *  With a tax calculator instance, this program provide the ability for
     * users to adjusting to tax policy and calculate their personal income tax.
     * */
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator();
        run(taxCalculator);
    }

    /**
     * Base logic of user interaction.
     * */
    public static void run(TaxCalculator taxCalculator){
        System.out.println("===");
        System.out.println("Welcome to Tax Calculator!");
        while (true) {
            // Display commands
            displayCommand();

            // User input command
            String command = getStringInput();
            switch (command) {
                // Quit
                case "0":
                    System.out.println("Tax calculator program quit.");
                    return;

                // Display the current tax policy.
                case "1":
                    System.out.println("The current personal income tax policy:");
                    taxCalculator.displayConfigure();
                    break;

                // Calculate the tax for given personal income.
                case "2":
                    System.out.print("Please input the personal income to be calculated:");
                    double income = getDoubleInput();
                    double tax = taxCalculator.getTax(income);
                    System.out.println("Tax: " + tax);
                    break;

                // Adjust the current tax policy.
                case "3":
                    System.out.print("Please input the new tax threshold:");
                    double threshold = getDoubleInput();

                    System.out.println("Please input the new mapping from income to tax rates.");
                    TaxCalculator.displayFormatOfIncome2taxRates();
                    TreeMap<Double, Double> newIncome2taxRates = getDouble2DoubleTreeMap();

                    System.out.println("Save this policy? (y or n)");
                    boolean is2save = getYOrN();
                    if (is2save) {
                        try {
                            taxCalculator.setPolicy(threshold, newIncome2taxRates);
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "":
                    break;
                default:
                    System.out.println("Invalid input. Please input integer from 0 to 3.");
            }
            System.out.println();
        }
    }

    public static void displayCommand() {
        System.out.println("===");
        System.out.println("0. Exit the program.");
        System.out.println("1. Display the current tax policy.");
        System.out.println("2. Calculate the tax for given personal income.");
        System.out.println("3. Adjust the current tax policy.");
        System.out.println();
    }
}