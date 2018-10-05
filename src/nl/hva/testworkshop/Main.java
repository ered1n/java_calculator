package nl.hva.testworkshop;

import java.util.*;

/**
 * Deze applicatie vraagt de gebruiker om een operator (+, -, *, /, %)
 * en 2 getallen in te voeren en toont vervolgens de som en het antwoord
 * op het scherm.
 *
 * @author R.G. Asciutto
 * @version 1.0
 */

public class Main {

    private static HashMap<Character, Integer> OPERATORS = new HashMap<>();
    private static ArrayList<Double[]> inputNumbers = new ArrayList<>();
    private static ArrayList<Character> inputOperators = new ArrayList<>();

    public static void main(String[] args) {

        OPERATORS.put('+', 2);
        OPERATORS.put('-', 2);
        OPERATORS.put('*', 1);
        OPERATORS.put('/', 1);
        OPERATORS.put('%', 1);



//        processInput();

//        if (processInput()) {
//            System.out.println("Successfully processed input");
//        } else {
//            System.out.println("Error: invalid operator used");
//        }

//        for (Double[] numberArray : inputNumbers) {
//            for (Double number : numberArray) {
//                System.out.println(number);
//            }
//        }
//
//        System.out.println(inputOperators);

//        System.out.println(inputOperators);

//        if (processInput()) {
//            if (isValidOperator(inputOperators)) {
//                System.out.println(inputOperators);
//                System.out.println(inputNumbers);
//            } else {
//                System.out.println("Error: wrong operator used");
//            }
//        } else {
//            System.out.println("Error: your input can not contain spaces");
//        }

//        OPERATORS.forEach(
//                (operator, priority) -> System.out.println(operator + " = " + priority)
//        );

//        if (isValidOperator(inputOperators)) {
////            for (String operator : inputOperators) {
////                System.out.println(operator);
////            }
//            for (int i = 0; i < inputOperators.size(); i++) {
////                System.out.println(inputOperators.get(i));
////                System.out.println(Arrays.asList(OPERATORS).indexOf(inputOperators.get(i)));
//            }
//        } else {
//            System.out.println("Error: wrong operator used");
//        }

    }

    private static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer de som in (bijvoorbeeld: 2*3): ");
        return input.nextLine();
    }

    private static void processInput() {
        String userInput = getInput();



//        if (isValidOperator(userInput)) {
            for (Character c : userInput.toCharArray()) {
                if (OPERATORS.containsKey(c)) {
                    inputNumbers.add(
                        new Double[]{
                            (double) Character.digit(userInput.charAt(userInput.indexOf(c) - 1), 10),
                            (double) Character.digit(userInput.charAt(userInput.indexOf(c) + 1), 10)
                        }
                    );
                    inputOperators.add(c);
                }
            }
//        } else {
//            return false;
//        }
//        return true;
    }

    private static boolean isValidOperator(String input) {
        for (Character c : input.toCharArray()) {
            if (!OPERATORS.containsKey(c) && !Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }
}