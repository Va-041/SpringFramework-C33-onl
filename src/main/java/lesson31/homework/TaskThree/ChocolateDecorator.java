package lesson31.homework.TaskThree;

public class ChocolateDecorator extends CoffeeDecorator {
    public ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + шоколад";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.00;
    }
}

