package nl.hva.testworkshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Calculator {

    private static final HashMap<Character, Integer> OPERATORS = new HashMap<Character, Integer>() {
        {
            put('+', 2);
            put('-', 2);
            put('*', 1);
            put('/', 1);
            put('%', 1);
        }
    };
    private static ArrayList<Double> inputNumbers = new ArrayList<>();
    private static ArrayList<Character> inputOperators = new ArrayList<>();

    public static void main(String[] args) {
        if (processInput(getInput())) {
            System.out.println("\nNummers: " + inputNumbers + "\n" + "Operators: " + inputOperators + "\n");
            processSum();
            System.out.println("De uitkomst is: " + inputNumbers.get(0));
        } else {
            System.out.println("Problem processing the input, invalid character used");
        }
    }

    private static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer de som in (bijvoorbeeld: 1 + 2 * 3): ");
        return input.nextLine();
    }

    private static boolean processInput(String input) {
        StringBuilder tempNumberString = new StringBuilder();

        for (Character c : input.toCharArray()) {
            if (OPERATORS.containsKey(c)) {
                tempNumberString.append(' ');
                inputOperators.add(c);
            } else {
                if (Character.isDigit(c) || c == '.') {
                    tempNumberString.append(c);
                } else if (c != ' ') {
                    return false;
                }
            }
        }

        String[] numberStringSplit = tempNumberString.toString().split("\\s+");

        for (String number : numberStringSplit) {
            try {
                inputNumbers.add(Double.parseDouble(number));
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private static void processSum() {
        while (inputOperators.size() > 0) {
            for (int priority = 1; priority <= 2; priority++) {
                int depth = 0;
                for (Character operator : inputOperators) {
                    if (OPERATORS.get(operator) == priority) {
                        executeCalculation(depth);
                        processSum();
                        return;
                    }
                    depth++;
                }
            }
        }
    }

    private static void executeCalculation(int depth) {
        inputNumbers.set(1 + depth, calculateSum(inputNumbers.get(depth), inputNumbers.get(1 + depth), inputOperators.get(depth)));
        inputNumbers.remove(depth);
        inputOperators.remove(depth);
    }

    private static double calculateSum(double firstNumber, double secondNumber, Character operator) {
        switch (operator) {
            case '+':
                return firstNumber + secondNumber;
            case '-':
                return firstNumber - secondNumber;
            case '*':
                return firstNumber * secondNumber;
            case '/':
                return firstNumber / secondNumber;
            case '%':
                return firstNumber % secondNumber;
        }

        return 0;
    }

}