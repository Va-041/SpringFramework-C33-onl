package lesson29.homework.TaskOne;

import java.util.Scanner;

public class DIP {

    public static void startDIP() {

        System.out.println("\n========== Dependency Inversion Principle ==========\n");
        System.out.println("Принцип гласит: модули высокого уровня не должны зависеть от модулей низкого уровня. " +
                "Оба должны зависеть от абстракций. \nАбстракции не должны зависеть от деталей. Детали должны" +
                "зависеть от абстракций.\n");

        // Неправильный вариант
        System.out.println("=== Неправильная реализация ===");
        System.out.println("NotificationBad напрямую зависит от конкретной реализации EmailSenderBad, что затрудняет " +
                "замену способа отправки сообщений\n");

        NotificationBad badNotification = new NotificationBad();
        badNotification.notifyUser("Тестовое сообщение");

        // Правильный вариант
        System.out.println("\n=== Правильная реализация ===");
        System.out.println("NotificationGood зависит от абстракции MessageSenderGood");
        System.out.println("Теперь можно легко подменить способ отправки сообщений\n");

        MessageSenderGood emailSender = new EmailSenderGood();
        MessageSenderGood smsSender = new SmsSenderGood();

        NotificationGood goodNotification1 = new NotificationGood(emailSender);
        NotificationGood goodNotification2 = new NotificationGood(smsSender);

        goodNotification1.notifyUser("Тестовое сообщение через Email");
        goodNotification2.notifyUser("Тестовое сообщение через SMS");

        getRepeatMessage();
    }


    // Неправильная реализация
    static class EmailSenderBad {
        public void send(String message) {
            System.out.println("Отправка Email: " + message);
        }
    }

    static class NotificationBad {
        private EmailSenderBad emailSender;

        public NotificationBad() {
            this.emailSender = new EmailSenderBad(); // Жесткая зависимость
        }

        public void notifyUser(String message) {
            emailSender.send(message);
        }
    }

    // Правильная реализация
    interface MessageSenderGood {
        void send(String message);
    }

    static class EmailSenderGood implements MessageSenderGood {
        @Override
        public void send(String message) {
            System.out.println("Отправка Email: " + message);
        }
    }

    static class SmsSenderGood implements MessageSenderGood {
        @Override
        public void send(String message) {
            System.out.println("Отправка SMS: " + message);
        }
    }

    static class NotificationGood {
        private MessageSenderGood messageSender;

        public NotificationGood(MessageSenderGood messageSender) {
            this.messageSender = messageSender; // Внедрение зависимости
        }

        public void notifyUser(String message) {
            messageSender.send(message);
        }
    }


    public static void getRepeatMessage() {
        Scanner scanner = getInputScanner();
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it? Enter Y or N: ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startDIP();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from DIP...");
                break;
            } else {
                System.out.println("\nIncorrect input. Please enter Y or N.");
            }
        }
    }

    public static Scanner getInputScanner() {
        return new Scanner(System.in);
    }
}
