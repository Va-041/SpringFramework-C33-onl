package lesson29.homework.TaskOne;

import java.util.Scanner;

public class SRP {

    public static void startSRP() {
        System.out.println("\n========== Single Responsibility Principle ==========\n");
        System.out.println("Принцип гласит: класс должен отвечать только за одну операцию (т.е. иметь только одну" +
                " ответственность)\n");

        // Неправильный вариант
        System.out.println("=== Неправильная реализация ===");
        System.out.println("Класс TextProcessorBad выполняет две обязанности:");
        System.out.println("1. Модификация текста");
        System.out.println("2. Печать текста");
        System.out.println("Это нарушает SRP, так как при изменении логики печати или модификации придется изменять " +
                "один и тот же класс\n");

        TextProcessorBad badProcessor = new TextProcessorBad();
        String badResult = badProcessor.appendText("Hello World", "!");
        badProcessor.printText(badResult);

        // Правильный вариант
        System.out.println("\n=== Правильная реализация ===");
        System.out.println("Разделили ответственности на два класса:");
        System.out.println("1. TextManipulatorGood - только модификация текста");
        System.out.println("2. TextPrinterGood - только печать текста");
        System.out.println("Теперь каждый класс имеет одну ответственность и одну причину для изменения\n");

        TextManipulatorGood manipulator = new TextManipulatorGood();
        TextPrinterGood printer = new TextPrinterGood();
        String goodResult = manipulator.appendText("Hello World", "!");
        printer.printText(goodResult);

        getRepeatMessage();
    }


    // Неправильный класс
    static class TextProcessorBad {
        public String appendText(String text, String toAppend) {
            return text + toAppend;
        }

        public void printText(String text) {
            System.out.println("Печатаем: " + text);
        }
    }

    // Правильные классы
    static class TextManipulatorGood {
        public String appendText(String text, String toAppend) {
            return text + toAppend;
        }
    }

    static class TextPrinterGood {
        public void printText(String text) {
            System.out.println("Печатаем: " + text);
        }
    }


    public static void getRepeatMessage() {
        Scanner scanner = getInputScanner();
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it? Enter Y or N: ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startSRP();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from SRP...");
                break;
            } else {
                System.out.println("\nIncorrect input. Please enter Y or N.");
            }
        }
    }

    public static Scanner getInputScanner() {
        return new Scanner(System.in);
    }
}