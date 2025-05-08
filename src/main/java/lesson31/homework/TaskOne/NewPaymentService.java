package lesson31.homework.TaskOne;

public class NewPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("\nОплата через новую систему: " + amount + " USD");
    }
}

