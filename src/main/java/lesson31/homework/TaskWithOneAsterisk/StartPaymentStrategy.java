package lesson31.homework.TaskWithOneAsterisk;

import java.util.Scanner;

public class StartPaymentStrategy {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskWithOneAsterisk() {
        System.out.println("\n===== Task with one asterisk =====\n");
        System.out.println("Выбор способа оплаты с помощью паттерна Стратегия.\n");

        PaymentProcessor processor = new PaymentProcessor();
        PaymentStrategy strategy = selectPaymentMethod();
        processor.setPaymentStrategy(strategy);

        double amount = enterPaymentAmount();
        processor.processPayment(amount);

        if (!getRepeatMessage()) {
            return;
        }
        startTaskWithOneAsterisk();
    }

    private static PaymentStrategy selectPaymentMethod() {
        int choice = -1;

        while (choice < 1 || choice > 3) {
            System.out.println("\nВыберите метод оплаты:");
            System.out.println("1. Банковская карта");
            System.out.println("2. PayPal");
            System.out.println("3. Криптовалюта");
            System.out.print("\nВаш выбор: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Ошибка: Введите число от 1 до 3.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    return new CreditCardPayment();
                case 2:
                    return new PayPalPayment();
                case 3:
                    return new CryptoPayment();
                default:
                    System.out.println("Ошибка: Введите число от 1 до 3.");
            }
        }
        return new CreditCardPayment();
    }

    private static double enterPaymentAmount() {
        double amount = -1;

        while (amount <= 0) {
            System.out.print("\nВведите сумму оплаты (BYN): ");

            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount <= 0) {
                    System.out.println("Ошибка: Сумма должна быть больше нуля.");
                }
            } else {
                System.out.println("Ошибка: Введите корректное число.");
                scanner.nextLine();
            }
        }

        System.out.println("Вы ввели сумму: " + amount + " BYN.");
        return amount;
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskWithOneAsterisk...");
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
