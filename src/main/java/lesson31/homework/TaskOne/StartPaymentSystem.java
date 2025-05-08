package lesson31.homework.TaskOne;

import java.util.Scanner;

public class StartPaymentSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskOne() {
        System.out.println("\n===== Task One =====\n");

        System.out.println("\nPaymentService - основной общий интерфейс." +
                "\nOldPaymentSystem работает иначе чем новая функция, но PaymentAdapter даёт ему совместимость с " +
                "\n новым интерфейсом, что позволяет использовать оба варианта через PaymentService.");

        PaymentService newPayment = new NewPaymentService();
        newPayment.pay(99.89);

        PaymentService adaptedOldPayment = new PaymentAdapter(new OldPaymentSystem());
        adaptedOldPayment.pay(200.75);

        if (!getRepeatMessage()) {
            return;
        }
        startTaskOne();
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskOne...");
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
