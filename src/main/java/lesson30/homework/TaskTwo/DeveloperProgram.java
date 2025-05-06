package lesson30.homework.TaskTwo;

import java.util.Scanner;

public class DeveloperProgram {
    private static final Scanner scanner = new Scanner(System.in); // Глобальный Scanner

    public static void startTaskTwo() {
        System.out.println("\n===== Task Two ====\n");

        DeveloperFactory factory = selectLanguage();
        Developer developer = factory.createDeveloper();
        developer.writeCode();

        getRepeatMessage();
    }

    private static DeveloperFactory selectLanguage() {
        while (true) {
            System.out.println("Выберите язык программирования:");
            System.out.println("1 - Java");
            System.out.println("2 - Python");
            System.out.println("3 - C++");
            System.out.print("\nВаш выбор: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очищаем ввод после nextInt(), чтобы избежать проблем с nextChar()

                switch (choice) {
                    case 1:
                        return new JavaDeveloperFactory();
                    case 2:
                        return new PythonDeveloperFactory();
                    case 3:
                        return new CppDeveloperFactory();
                    default:
                        System.out.println("Некорректный выбор! Пожалуйста, введите число от 1 до 3.\n");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Пожалуйста, введите число.");
                scanner.nextLine(); // Очистка буфера после ошибки
            }
        }
    }

    private static void getRepeatMessage() {
        char userInput;

        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startTaskTwo();
                break;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskTwo...");
                break;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
