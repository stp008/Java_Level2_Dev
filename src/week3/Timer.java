package week3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Timer implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private int delay;
    
    public Timer(int delay) {
        this.delay = delay;
    }

    public void start() {
        try {
            TimeUnit.SECONDS.sleep(delay);
            notifyObservers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.handleEvent();
        }
    }
}
