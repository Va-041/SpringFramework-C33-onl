package lesson31.homework.TaskWithOneAsterisk;

public class CryptoPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата криптовалютой на сумму: " + amount + " BYN");
    }
}
