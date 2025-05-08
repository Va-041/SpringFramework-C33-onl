package lesson31.homework.TaskWithOneAsterisk;

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата через PayPal на сумму: " + amount + " BYN");
    }
}
