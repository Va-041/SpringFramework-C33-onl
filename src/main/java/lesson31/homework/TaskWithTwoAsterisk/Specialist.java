package lesson31.homework.TaskWithTwoAsterisk;

public class Specialist extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equalsIgnoreCase("Технический вопрос")) {
            System.out.println("Специалист отвечает: Проверьте соединение.");
        } else {
            System.out.println("Специалист не может обработать запрос.");
            super.handleRequest(request);
        }
    }
}
