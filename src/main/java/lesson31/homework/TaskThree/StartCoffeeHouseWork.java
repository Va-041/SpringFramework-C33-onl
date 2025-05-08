package lesson31.homework.TaskThree;

import java.util.Scanner;

public class StartCoffeeHouseWork {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskThree() {
        System.out.println("\n===== Task Three =====\n");
        System.out.println("\nИмитация работы кофейни с помощью паттерна Декоратор.");

        CoffeeHouse coffeeHouseMatilda = new CoffeeHouse();
        coffeeHouseMatilda.startOrder();

        if (!getRepeatMessage()) {
            return;
        }
        startTaskThree();
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskThree...");
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
