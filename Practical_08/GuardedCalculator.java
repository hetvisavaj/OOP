import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        int attempts = 0;

        while (!success) {
            attempts++;
            try {
                System.out.print("Enter first number: ");
                double num1 = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter operator (+, -, *, /): ");
                String op = scanner.nextLine().trim();

                System.out.print("Enter second number: ");
                double num2 = Double.parseDouble(scanner.nextLine().trim());

                double result;
                switch (op) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            throw new DivideByZeroException("Division by zero is not allowed.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("Invalid operator: " + op);
                        continue;
                }

                System.out.println("Result: " + result);
                success = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please provide valid numeric inputs.");
            } catch (DivideByZeroException e) {
                System.out.println("Math Error: " + e.getMessage());
            } finally {
                System.out.println("[Log] Calculation attempt #" + attempts + " completed.");
            }
        }
        scanner.close();
    }
}
