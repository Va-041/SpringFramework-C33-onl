package lesson31.homework.TaskWithTwoAsterisk;



import java.util.Scanner;

public class StartSupportWork {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskWithThreeAsterisk() {
        System.out.println("\n===== Task with three asterisk =====\n");
        System.out.println("Имитация обрабоки запросов техподдержки с помощью паттерна Цепочка обязанностей.");

        SupportHandler operator = new Operator();
        SupportHandler specialist = new Specialist();
        SupportHandler manager = new Manager();

        operator.setNextHandler(specialist);
        specialist.setNextHandler(manager);

        System.out.println("\nЗапрос 1:");
        operator.handleRequest("Простой вопрос");

        System.out.println("\nЗапрос 2:");
        operator.handleRequest("Технический вопрос");

        System.out.println("\nЗапрос 3:");
        operator.handleRequest("Сложная проблема");

        if (!getRepeatMessage()) {
            return;
        }
        startTaskWithThreeAsterisk();
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nВыход из TaskWithThreeAsterisk...");
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
