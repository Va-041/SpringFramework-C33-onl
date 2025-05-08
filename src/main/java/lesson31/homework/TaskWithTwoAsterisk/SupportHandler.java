package lesson31.homework.TaskWithTwoAsterisk;

public abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String request) {
        if (nextHandler != null) {
            System.out.println("Запрос '" + request + "' передаётся дальше...\n");
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Запрос '" + request + "' не удалось обработать.");
        }
    }
}
