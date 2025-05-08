package lesson31.homework.TaskThree;

import java.util.Scanner;

public class CoffeeHouse {

    private static final Scanner scanner = new Scanner(System.in);

    public void startOrder() {
        System.out.println("\nДобро пожаловать в Кофейню!");
        Coffee coffee = selectCoffeeType();
        coffee = addIngredients(coffee);
        printReceipt(coffee);
    }

    private Coffee selectCoffeeType() {
        int choice = -1;

        while (choice < 1 || choice > 3) {
            System.out.println("\nВыберите ваш кофе:");
            System.out.println("1. Обычный кофе");
            System.out.println("2. Кофе с молоком");
            System.out.println("3. Кофе с шоколадом");
            System.out.print("\nВаш выбор: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Ошибка: Введите число от 1 до 3.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    return new SimpleCoffee();
                case 2:
                    return new MilkDecorator(new SimpleCoffee());
                case 3:
                    return new ChocolateDecorator(new SimpleCoffee());
                default:
                    System.out.println("Ошибка: Введите число от 1 до 3.");
            }
        }
        return new SimpleCoffee();
    }

    private Coffee addIngredients(Coffee coffee) {
        int milkCount = 0, chocolateCount = 0, syrupCount = 0, totalIngredients = 0;

        while (true) {
            System.out.println("\nХотите добавить ингредиенты? (" + totalIngredients + "/4)");
            System.out.println("1. Добавить молоко (" + milkCount + "/2)");
            System.out.println("2. Добавить шоколад (" + chocolateCount + "/2)");
            System.out.println("3. Добавить сироп (" + syrupCount + "/2)");
            System.out.println("4. Нет, спасибо.");
            System.out.print("\nВаш выбор: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите число от 1 до 4.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (totalIngredients >= 4 && choice != 4) {
                System.out.println("Вы достигли лимита ингредиентов (4/4).");
                return coffee;
            }

            switch (choice) {
                case 1:
                    if (milkCount < 2) {
                        coffee = new MilkDecorator(coffee);
                        milkCount++;
                        totalIngredients++;
                    } else {
                        System.out.println("Вы уже добавили максимум молока (2/2).");
                    }
                    break;
                case 2:
                    if (chocolateCount < 2) {
                        coffee = new ChocolateDecorator(coffee);
                        chocolateCount++;
                        totalIngredients++;
                    } else {
                        System.out.println("Вы уже добавили максимум шоколада (2/2).");
                    }
                    break;
                case 3:
                    if (syrupCount < 2) {
                        coffee = new SyrupDecorator(coffee);
                        syrupCount++;
                        totalIngredients++;
                    } else {
                        System.out.println("Вы уже добавили максимум сиропа (2/2).");
                    }
                    break;
                case 4:
                    return coffee;
                default:
                    System.out.println("Ошибка: Введите число от 1 до 4.");
            }
        }
    }

    private void printReceipt(Coffee coffee) {
        System.out.println("\nВаш заказ: " + coffee.getDescription());
        System.out.println("Итоговая цена: " + coffee.getCost() + " BYN");
        System.out.println("Спасибо за заказ!");
    }
}
