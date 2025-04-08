package lesson29.homework.TaskOne;

import java.util.Scanner;

public class OCP {

    public static void startOCP() {
        System.out.println("\n========== Open-Closed Principle ==========\n");
        System.out.println("Принцип гласит: классы должны быть открыты для расширения, но закрыты для модификации\n");

        // Неправильный вариант
        System.out.println("=== Неправильная реализация ===");
        System.out.println("Класс AreaCalculatorBad при добавлении новой фигуры требует изменения своего кода " +
                "(добавление нового if-else)");
        System.out.println("Это нарушает OCP\n");

        AreaCalculatorBad badCalculator = new AreaCalculatorBad();
        System.out.println("Площадь круга: " + badCalculator.calculate(new CircleBad(5)));
        System.out.println("Площадь квадрата: " + badCalculator.calculate(new SquareBad(4)));

        // Правильный вариант
        System.out.println("\n=== Правильная реализация ===");
        System.out.println("Используем абстрактный класс ShapeGood с методом area()");
        System.out.println("Новые фигуры можно добавлять через наследование, не изменяя существующий код " +
                "AreaCalculatorGood\n");

        ShapeGood circle = new CircleGood(5);
        ShapeGood square = new SquareGood(4);
        System.out.println("Площадь круга: " + circle.area());
        System.out.println("Площадь квадрата: " + square.area());

        getRepeatMessage();
    }


    // Неправильная реализация
    static class CircleBad {
        public double radius;
        CircleBad(double r) { this.radius = r; }
    }

    static class SquareBad {
        public double side;
        SquareBad(double s) { this.side = s; }
    }

    static class AreaCalculatorBad {
        public double calculate(Object shape) {
            if (shape instanceof CircleBad) {
                CircleBad c = (CircleBad)shape;
                return Math.PI * c.radius * c.radius;
            }
            else if (shape instanceof SquareBad) {
                SquareBad s = (SquareBad)shape;
                return s.side * s.side;
            }
            throw new IllegalArgumentException("Unknown shape");
        }
    }

    // Правильная реализация
    abstract static class ShapeGood {
        public abstract double area();
    }

    static class CircleGood extends ShapeGood {
        private double radius;
        CircleGood(double r) { this.radius = r; }
        @Override public double area() { return Math.PI * radius * radius; }
    }

    static class SquareGood extends ShapeGood {
        private double side;
        SquareGood(double s) { this.side = s; }
        @Override public double area() { return side * side; }
    }


    public static void getRepeatMessage() {
        Scanner scanner = getInputScanner();
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it? Enter Y or N: ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startOCP();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from OCP...");
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