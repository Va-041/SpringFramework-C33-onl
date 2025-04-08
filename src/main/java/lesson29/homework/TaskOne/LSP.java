package lesson29.homework.TaskOne;

import java.util.Scanner;

public class LSP {
    public static void startLSP() {

        System.out.println("\n========== Liskov Substitution Principle ==========\n");
        System.out.println("Принцип гласит: объекты должны быть заменяемыми экземплярами своих родительских классов " +
                "без нарушения работы программы\n");

        // Неправильный вариант
        System.out.println("=== Неправильная реализация ===");
        System.out.println("Класс SquareBad наследуется от RectangleBad, но изменяет поведение");
        System.out.println("setWidth/setHeight, что нарушает LSP - нельзя использовать SquareBad вместо RectangleBad\n");

        RectangleBad rect = new SquareBad();
        rect.setWidth(5);
        rect.setHeight(4);
        System.out.println("Ожидаемая площадь прямоугольника 5x4: 20");
        System.out.println("Фактическая площадь: " + rect.getArea() + " (нарушение LSP)");

        // Правильный вариант
        System.out.println("\n=== Правильная реализация ===");
        System.out.println("Создаем общий интерфейс ShapeGood с методом getArea()");
        System.out.println("Теперь RectangleGood и SquareGood независимы и могут использоваться вместо ShapeGood " +
                "без нарушения поведения\n");

        ShapeGood goodRect = new RectangleGood(5, 4);
        ShapeGood goodSquare = new SquareGood(5);
        System.out.println("Площадь прямоугольника: " + goodRect.getArea());
        System.out.println("Площадь квадрата: " + goodSquare.getArea());

        getRepeatMessage();
    }


    // Неправильная реализация
    static class RectangleBad {
        protected int width, height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getArea() {
            return width * height;
        }
    }

    static class SquareBad extends RectangleBad {
        @Override
        public void setWidth(int width) {
            super.setWidth(width);
            super.setHeight(width); // Нарушение LSP!
        }

        @Override
        public void setHeight(int height) {
            super.setWidth(height); // Нарушение LSP!
            super.setHeight(height);
        }
    }

    // Правильная реализация
    interface ShapeGood {
        int getArea();
    }

    static class RectangleGood implements ShapeGood {
        private int width, height;

        RectangleGood(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public int getArea() {
            return width * height;
        }
    }

    static class SquareGood implements ShapeGood {
        private int side;

        SquareGood(int side) {
            this.side = side;
        }

        @Override
        public int getArea() {
            return side * side;
        }
    }


    public static void getRepeatMessage() {
        Scanner scanner = getInputScanner();
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it? Enter Y or N: ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startLSP();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from LSP...");
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