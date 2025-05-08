package lesson31.homework.TaskWithThreeAsterisk;

import java.util.Scanner;

public class StartNewsPublisher {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskWithTwoAsterisk() {
        System.out.println("\n===== Task with two asterisk =====\n");
        System.out.println("Имитация системы подписки на новости с помощью паттерна Наблюдатель.");

        NewsPublisher publisher = new NewsPublisher();

        Observer subscriber1 = new Subscriber("Алексей");
        Observer subscriber2 = new Subscriber("Мария");

        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        publisher.publishNews("Новая версия Java вышла!");

        publisher.unsubscribe(subscriber1);

        publisher.publishNews("Скоро ожидается крупное обновление Spring Framework!");


        if (!getRepeatMessage()) {
            return;
        }
        startTaskWithTwoAsterisk();
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskWithTwoAsterisk...");
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
