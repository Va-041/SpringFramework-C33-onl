package lesson31.homework.TaskWithThreeAsterisk;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {

    private List<Observer> subscribers = new ArrayList<>();

    public void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    public void publishNews(String news) {
        System.out.println("\nНовая новость: " + news);
        notifySubscribers(news);
    }

    private void notifySubscribers(String news) {
        for (Observer subscriber : subscribers) {
            subscriber.update(news);
        }
    }
}
