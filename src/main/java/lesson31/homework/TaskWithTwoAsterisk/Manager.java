package lesson31.homework.TaskWithTwoAsterisk;

public class Manager extends SupportHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("Менеджер отвечает: Ваш запрос будет рассмотрен в ближайшее время.");
    }
}
