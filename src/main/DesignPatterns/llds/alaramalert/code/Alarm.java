package llds.alaramalert.code;
import java.util.ArrayList;
import java.util.List;

public class Alarm {
    private String id;
    private String name;
    private List<Metric> metrics;
    private AlarmState state;
    private List<Notification> notifications;
    private List<Action> actions;
    private Condition condition;

    // Private constructor for Builder pattern
    private Alarm(AlarmBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.metrics = builder.metrics;
        this.state = AlarmState.OK;  // Default state
        this.notifications = builder.notifications;
        this.actions = builder.actions;
        this.condition = builder.condition;
    }

    // Method to evaluate alarm condition
    public void evaluate() {
        if (condition.evaluate(metrics)) {
            state = AlarmState.ALARM;
            notifySubscribers();
            executeActions();
        } else {
            state = AlarmState.OK;
        }
    }

    private void notifySubscribers() {
        for (Notification notification : notifications) {
            notification.send();
        }
    }

    private void executeActions() {
        for (Action action : actions) {
            action.execute();
        }
    }

    // Getter methods (optional)
    public String getId() { return id; }
    public String getName() { return name; }
    public AlarmState getState() { return state; }

    // **Alarm Builder Class**
    public static class AlarmBuilder {
        private String id;
        private String name;
        private List<Metric> metrics = new ArrayList<>();
        private List<Notification> notifications = new ArrayList<>();
        private List<Action> actions = new ArrayList<>();
        private Condition condition;

        public AlarmBuilder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public AlarmBuilder addMetric(Metric metric) {
            this.metrics.add(metric);
            return this;
        }

        public AlarmBuilder addNotification(Notification notification) {
            this.notifications.add(notification);
            return this;
        }

        public AlarmBuilder addAction(Action action) {
            this.actions.add(action);
            return this;
        }

        public AlarmBuilder setCondition(Condition condition) {
            this.condition = condition;
            return this;
        }

        public Alarm build() {
            return new Alarm(this);
        }
    }
}