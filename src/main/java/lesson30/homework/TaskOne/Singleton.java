package lesson30.homework.TaskOne;

import java.util.Scanner;

public class Singleton {
    private static final Scanner scanner = new Scanner(System.in); // Глобальный Scanner

    public static void startTaskOne() {
        System.out.println("\n===== Task One ====\n");

        System.out.println("\t  Первое получение экземпляра\n" +
                "---------------------------------------");
        Sun sun1 = Sun.getInstance();
        sun1.shine();
        sun1.printInfo();

        System.out.println("\n\t  Второе получение экземпляра\n" +
                "---------------------------------------");
        Sun sun2 = Sun.getInstance();
        sun2.shine();
        sun2.printInfo();

        System.out.println("\n\t\t  Проверка Singleton\n" +
                "---------------------------------------");
        System.out.println("sun1 и sun2 это один и тот же объект? " + (sun1 == sun2));
        System.out.println("Хэш sun1: " + sun1.hashCode());
        System.out.println("Хэш sun2: " + sun2.hashCode());

        getRepeatMessage();
    }

    public static class Sun {
        private static Sun instance;
        private final int id;

        private Sun() {
            this.id = (int)(Math.random() * 1000);
            System.out.println("Звезда Солнце родилась!         ID: " + id);
        }

        public static Sun getInstance() {
            if (instance == null) {
                instance = new Sun();
            }
            return instance;
        }

        public void shine() {
            System.out.println("Звезда Солнце излучает свет!    ID: " + id);
        }

        public void printInfo() {
            System.out.println("Информация о звезде Солнце:     ID: " + id + ", hash: " + this.hashCode());
        }
    }

    private static void getRepeatMessage() {
        char userInput;

        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                Sun.getInstance().shine();
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskOne...");
                break;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
