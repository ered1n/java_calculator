package nl.hva.testworkshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Deze applicatie vraagt de gebruiker om een som in te voeren (bijvoorbeeld: 1 + 2 * 3)
 * met de volgende operators +, -, *, /, %. Vervolgens wordt de som verwerkt en het resultaat
 * in de console geprint.
 *
 * @author R.G. Asciutto
 * @version 1.0
 */

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
    private static String userInput;
    private static ArrayList<Double> inputNumbers = new ArrayList<>();
    private static ArrayList<Character> inputOperators = new ArrayList<>();

    public static void main(String[] args) {
        // Het programma blijft loopen totdat de gebruiker "S" invoert
        do {
            userInput = getInput();

            // Als het verwerken van de input true returned dan wordt de som uitgerekend
            if (processInput(userInput)) {
                processSum();
                System.out.println(userInput.replaceAll("\\s+","") + " = " + String.format("%.2f", inputNumbers.get(0)));
            // Als het verwerken van de input fout is gegaan en deze geen "S" is, wordt er een foutmelding weergeven
            } else {
                if (!userInput.equals("S")) {
                    System.out.println("Probleem met het verwerken van de input");
                }
            }

            inputNumbers.clear();
            inputOperators.clear();
        } while (!userInput.equals("S"));
    }

    /**
     * Input van de gebruiker opvragen
     * @return String
     */
    private static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer de som in (bijvoorbeeld: 1 + 2 * 3), S = stoppen: ");
        userInput = input.nextLine();
        return userInput;
    }

    /**
     * Verwerkt de input van de gebruiker en zet deze om in getallen en operators
     * @param input
     * @return boolean
     */
    private static boolean processInput(String input) {
        StringBuilder tempNumberString = new StringBuilder();
        char previousChar = '\0';

        for (Character character : input.toCharArray()) {
            // Kijken of de character een operator is
            if (OPERATORS.containsKey(character)) {
                // Als er nog geen andere characters bekend zijn of de vorige character een operator is,
                // dan gaat het om een negatief getal en moet de "-" toegevoegd worden aan de number string
                if (tempNumberString.length() == 0 || OPERATORS.containsKey(previousChar)) {
                    tempNumberString.append(character);
                } else {
                    // Spatie toevoegen aan de number string om de losse getallen te onderscheiden
                    tempNumberString.append(' ');
                    inputOperators.add(character);
                }
            } else {
                // Kijken of de character een getal is of een "."
                if (Character.isDigit(character) || character == '.') {
                    tempNumberString.append(character);
                // Als de character ook geen spatie is, dan is het geen geldige input
                } else if (character != ' ') {
                    return false;
                }
            }

            if (character != ' ') {
                previousChar = character;
            }
        }

        // De number string omzetten in een array
        String[] numberStringSplit = tempNumberString.toString().split("\\s+");

        // Door de number array heen loopen en deze toevoegen aan inputNumbers als Double
        for (String number : numberStringSplit) {
            try {
                inputNumbers.add(Double.parseDouble(number));
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verwerkt de som. Hiervoor wordt gebruik gemaakt van de priority keys in de OPERATORS HashMap.
     * Deze priority keys worden gebruikt om ervoor te zorgen dat operators met een hogere prioriteit
     * eerst worden berekend
     */
    private static void processSum() {
        // Loopt totdat er geen operators meer in de array zitten (gehele som is verwerkt)
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

    /**
     * Voert de berekening uit en vervangt de uitkomst met de huidige getallen in inputNumbers
     * @param depth
     */
    private static void executeCalculation(int depth) {
        inputNumbers.set(1 + depth, calculateSum(inputNumbers.get(depth), inputNumbers.get(1 + depth), inputOperators.get(depth)));
        inputNumbers.remove(depth);
        inputOperators.remove(depth);
    }

    /**
     * Berekent de som op basis van 2 getallen en een operator
     * @param firstNumber
     * @param secondNumber
     * @param operator
     * @return double
     */
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