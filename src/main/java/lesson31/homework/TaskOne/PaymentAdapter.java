package lesson31.homework.TaskOne;

public class PaymentAdapter implements PaymentService {
    private OldPaymentSystem oldPaymentSystem;

    public PaymentAdapter(OldPaymentSystem oldPaymentSystem) {
        this.oldPaymentSystem = oldPaymentSystem;
    }

    @Override
    public void pay(double amount) {
        oldPaymentSystem.makePayment(amount); // Адаптация вызова
    }
}

