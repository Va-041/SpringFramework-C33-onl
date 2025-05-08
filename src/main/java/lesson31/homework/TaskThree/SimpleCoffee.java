package lesson31.homework.TaskThree;

public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Обычный кофе";
    }

    @Override
    public double getCost() {
        return 3.50;
    }
}
