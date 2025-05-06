package lesson30.homework.TaskFour;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreateDeveloperWithBuilder {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskFour() {
        while (true) {
            System.out.println("\n===== Task Four =====\n");

            Developer junior = new Developer.Builder("Джонни Синс", "Java").build();

            Developer senior = new Developer.Builder("Тадеуш Костюмчик", "Python")
                    .experienceYears(8)
                    .skills(Arrays.asList("Машинное обучение", "Дата-аналитика"))
                    .hasHigherEducation(true)
                    .specialization("AI/ML")
                    .build();

            List<String> skills = Arrays.asList("Spring", "Hibernate", "Docker");
            Developer javaDev = new Developer.Builder("Боби Джонсон", "Java")
                    .experienceYears(5)
                    .skills(skills)
                    .hasHigherEducation(true)
                    .build();

            System.out.println(junior);
            System.out.println(senior);
            System.out.println(javaDev);

            if (!getRepeatMessage()) {
                break;
            }
        }
        System.out.println("\nВыход из TaskFour...");
    }

    private static boolean getRepeatMessage() {
        while (true) {
            System.out.print("\n\nХотите повторить выполнение? Введите Y (Да) или N (Нет): ");
            char userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                return true;
            } else if (userInput == 'N' || userInput == 'n') {
                return false;
            } else {
                System.out.println("\nНекорректный ввод. Пожалуйста, введите Y или N.");
            }
        }
    }
}
