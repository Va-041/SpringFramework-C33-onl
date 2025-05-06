package lesson30.homework.TaskThree;

import java.util.Scanner;

public class CreateCourseProgram {
    private static final Scanner scanner = new Scanner(System.in); // Глобальный Scanner

    public static void startTaskThree() {
        System.out.println("\n===== Task Three =====");
        System.out.println("Система создания учебных курсов");

        boolean running = true;
        while (running) {
            System.out.print("\nВведите название языка программирования (java/python) или 'exit' для выхода: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                continue;
            }

            CourseCreator.createCourse(input);

            if (!askForRepeat()) {
                running = false;
            }
        }

        System.out.println("\nВыход из TaskThree...");
    }

    private static boolean askForRepeat() {
        while (true) {
            System.out.print("\nХотите создать еще один курс? (Y/N): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Некорректный ввод. Пожалуйста, введите Y или N.");
        }
    }
}
