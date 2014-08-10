package week3;

/**
 *
 */
public class Task implements Observer {
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void handleEvent() {
        System.out.println("Event handled by task: " + taskName);
    }
}
