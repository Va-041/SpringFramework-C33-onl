package lesson31.homework.TaskTwo;

import java.util.Scanner;

public class StartComputerFacade {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskTwo() {

        System.out.println("\n===== Task Two =====");

        System.out.println("\nИмитация запуска ПК с помощью паттерна Фасад.");

        ComputerFacade newComputer = new ComputerFacade();
        newComputer.startComputer();

        if (!getRepeatMessage()) {
            return;
        }
        startTaskTwo();
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskTwo...");
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
