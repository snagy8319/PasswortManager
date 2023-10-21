package Rechner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Taschenrechner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double result;

        try {
            // Prompt the user to enter the first number and read it as a double
            System.out.println("Geben Sie die erste Zahl ein: ");
            double num1 = scanner.nextDouble();

            // Prompt the user to enter the second number and read it as a double
            System.out.println("Geben Sie die zweite Zahl ein: ");
            double num2 = scanner.nextDouble();

            System.out.println("Wähle eine Operation (+, -, *, /): ");
            char Operation = scanner.next().charAt(0);

            switch (Operation) {
                case '+':
                    result = addNumbers(num1, num2);
                    System.out.println("Ergebnis für Addition: " + result);
                    break;
                case '-':
                    result = subtractNumbers(num1, num2);
                    System.out.println("Ergebnis für Subtraktion: " + result);
                    break;
                case '*':
                    result = multiplyNumbers(num1, num2);
                    System.out.println("Ergebnis für Multiplikation: " + result);
                    break;
                case '/':
                    result = divideNumbers(num1, num2);
                    System.out.println("Ergebnis für Dividieren: " + result);
                    break;
                default:
                    System.out.println("Ungültige Operation: " + Operation);
            }

        } catch (InputMismatchException e) {
            System.out.println("Ungültige Eingabe: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetischer Fehler: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Adds two numbers and returns the sum.
     *
     * @param num1 the first number to add
     * @param num2 the second number to add
     * @return the sum of the two numbers
     */
    public static double addNumbers(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    /**
     * Subtracts the second number from the first number and returns the difference.
     *
     * @param firstNumber  the minuend
     * @param secondNumber the subtrahend
     * @return the difference of the two numbers
     */
    public static double subtractNumbers(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }

    /**
     * Multiplies two numbers and returns the product.
     *
     * @param firstNumber  the first factor
     * @param secondNumber the second factor
     * @return the product of the two numbers
     */
    public static double multiplyNumbers(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    /**
     * Divides two numbers and returns the integer quotient.
     * If the second number is zero, returns Integer.MIN_VALUE to indicate an error.
     *
     * @param firstNumber  the dividend
     * @param secondNumber the divisor
     * @return the integer quotient of the division, or Integer.MIN_VALUE if the
     *         divisor is zero
     */
    public static double divideNumbers(double firstNumber, double secondNumber) {
        if (secondNumber == 0) {
            throw new ArithmeticException("Division durch null nicht erlaubt");
        } else {
            return firstNumber / secondNumber;
        }
    }
}
