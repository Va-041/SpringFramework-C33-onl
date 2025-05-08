package lesson31.homework.TaskThree;


public class SyrupDecorator extends CoffeeDecorator {
    public SyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " + сироп";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.75;
    }
}

