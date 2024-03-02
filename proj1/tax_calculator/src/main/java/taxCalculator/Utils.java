package taxCalculator;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * A handy set of tools to provide convenience on issues like users' interaction,
 * which is not relative to issues on tax calculator.
 * */
public class Utils {
    /**
     * Get input from user by terminal.
     * The input is confined to Integer.
     * */
    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
//        boolean invalidInput = true;
//        int inputInt = -1;
//        while (invalidInput) {
//            try {
//                String userInput = scanner.nextLine();
//                // if the parsing fails, a NumberFormatException will be thrown.
//                inputInt = Integer.parseInt(userInput);
//                invalidInput = false;
//            }
//            catch (Exception e) {
//                System.out.println("Invalid input. Please input a integer.");
//            }
//        }
//        return inputInt;
    }

    /**
     * Get input from user by terminal.
     * The input is confined to double.
     * */
    public static double getDoubleInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        boolean invalidInput = true;
        double inputDouble = -1;
        while (invalidInput) {
            try {
                // if the parsing fails, a NumberFormatException will be thrown.
                inputDouble = Double.parseDouble(userInput);
                invalidInput = false;
            }
            catch (Exception e) {
                System.out.println("Invalid input. Please input a integer.");
            }
        }
        return inputDouble;
    }

    /**
     * Get input from user by terminal.
     * Return true if user inputs "y", or false if user inputs "n".
     * If user input anything but "y" or "n", ask user to input once again.
     */
    public static boolean getYOrN() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        boolean userDecision = false;

        while (!validInput) {
            String userInput = scanner.nextLine();

            // 判断用户输入并设置标志
            if (userInput.equalsIgnoreCase("y")) {
                userDecision = true;
                validInput = true;
            } else if (userInput.equalsIgnoreCase("n")) {
//                userDecision = false;  // can be ignored with no influence.
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        scanner.close();
        return userDecision;
    }

    /**
     * Get input from user by terminal.
     * Return a Double to Double TreeMap constructed by entries user inputs.
     * If an input of entry is invalid, user will be warned and asked to input
     * once again.
     * */
    public static TreeMap<Double,Double> getDouble2DoubleTreeMap() {
        System.out.println("Enter q for ending.");
        TreeMap<Double, Double> double2DoubleTreeMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String[] inputParts = userInput.split("\\s+");
                if (inputParts.length != 2) {
                    throw new IllegalArgumentException("Invalid input.");
                }
                double income = Double.parseDouble(inputParts[0]);
                double rate = Double.parseDouble(inputParts[1]);
                double2DoubleTreeMap.put(income, rate);
            }
            catch (Exception e) {
                System.out.println("Invalid input.");
            }

        }
        scanner.close();
        return double2DoubleTreeMap;
    }
}
