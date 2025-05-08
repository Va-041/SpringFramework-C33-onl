package lesson31.homework.TaskWithOneAsterisk;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Ошибка: Метод оплаты не выбран!");
            return;
        }
        paymentStrategy.pay(amount);
    }
}

