package lesson29.homework.TaskOne;

import java.util.Scanner;

public class ISP {

    public static void startISP() {
        System.out.println("\n========== Interface Segregation Principle ==========\n");
        System.out.println("Принцип гласит: клиенты не должны зависеть от интерфейсов, которые они не используют. " +
                "\nЛучше много специализированных интерфейсов, чем один универсальный\n");

        // Неправильный вариант
        System.out.println("=== Неправильная реализация ===");
        System.out.println("Интерфейс MultiFunctionDeviceBad объединяет слишком много методов, заставляя классы " +
                "реализовывать ненужные функции\n");

        MultiFunctionDeviceBad advancedDevice = new AdvancedDeviceBad();
        advancedDevice.print();
        advancedDevice.scan();

        MultiFunctionDeviceBad simplePrinter = new SimplePrinterBad();
        simplePrinter.print(); // Работает
        // simplePrinter.scan(); // Выбросит исключение - нарушение ISP

        // Правильный вариант
        System.out.println("\n=== Правильная реализация ===");
        System.out.println("Разделили интерфейсы на PrinterGood и ScannerGood");
        System.out.println("Теперь классы реализуют только нужные им интерфейсы\n");

        PrinterGood goodPrinter = new SimplePrinterGood();
        PrinterGood photocopier = new PhotocopierGood();
        ScannerGood scanner = new PhotocopierGood();

        goodPrinter.print();
        photocopier.print();
        scanner.scan();

        getRepeatMessage();
    }

    // Неправильная реализация
    interface MultiFunctionDeviceBad {
        void print();
        void scan();
        void fax();
    }

    static class AdvancedDeviceBad implements MultiFunctionDeviceBad {
        @Override public void print() { System.out.println("Печатаем документ"); }
        @Override public void scan() { System.out.println("Сканируем документ"); }
        @Override public void fax() { System.out.println("Отправляем факс"); }
    }

    static class SimplePrinterBad implements MultiFunctionDeviceBad {
        @Override public void print() { System.out.println("Печатаем документ"); }
        @Override public void scan() { throw new UnsupportedOperationException(); }
        @Override public void fax() { throw new UnsupportedOperationException(); }
    }

    // Правильная реализация
    interface PrinterGood {
        void print();
    }

    interface ScannerGood {
        void scan();
    }

    static class SimplePrinterGood implements PrinterGood {
        @Override public void print() { System.out.println("Печатаем документ"); }
    }

    static class PhotocopierGood implements PrinterGood, ScannerGood {
        @Override public void print() { System.out.println("Печатаем документ"); }
        @Override public void scan() { System.out.println("Сканируем документ"); }
    }


    public static void getRepeatMessage() {
        Scanner scanner = getInputScanner();
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it? Enter Y or N: ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startISP();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from ISP...");
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