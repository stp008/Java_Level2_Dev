package week3;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer(3);
        timer.addObserver(new Task("#1"));
        timer.addObserver(new Task("#2"));

        timer.start();
    }
}
