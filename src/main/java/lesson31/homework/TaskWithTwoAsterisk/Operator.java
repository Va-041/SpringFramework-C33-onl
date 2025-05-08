package lesson31.homework.TaskWithTwoAsterisk;

public class Operator extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equalsIgnoreCase("Простой вопрос")) {
            System.out.println("Оператор отвечает: Перезапустите устройство.");
        } else {
            System.out.println("Оператор не может обработать запрос.");
            super.handleRequest(request);
        }
    }
}
