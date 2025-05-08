package lesson31.homework.TaskWithOneAsterisk;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата банковской картой на сумму: " + amount + " BYN");
    }
}
