package llds.alaramalert.code;

import llds.alaramalert.code.impl.EmailNotification;
import llds.alaramalert.code.impl.TerminateInstanceAction;
import llds.alaramalert.code.impl.ThresholdCondition;

public class Demo {
    public static void main(String[] args) {
        Alarm alarm = new Alarm.AlarmBuilder("1", "High CPU Usage")
                .addMetric(new Metric("CPU", 85))  // Add CPU metric
                .addNotification(new EmailNotification("user@example.com"))  // Email alert
                .addAction(new TerminateInstanceAction("i-1234567890"))  // Terminate instance
                .setCondition(new ThresholdCondition("CPU", 80, true))  // Condition: CPU > 80%
                .build();
        alarm.evaluate();  // Evaluates alarm and triggers actions if condition met
    }
}
