package nl.hva.testworkshop;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class EvalCalculator {
    public static void main(String[] args) throws ScriptException {
        String userInput = getInput();
        System.out.println(calculateInput(userInput));
    }

    private static double calculateInput(String input) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return (double) engine.eval(input);
    }

    private static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer de som in (bijvoorbeeld: 2*3): ");
        return input.nextLine();
    }
}