package llds.alaramalert.code.impl;

import llds.alaramalert.code.Condition;
import llds.alaramalert.code.Metric;

import java.util.List;

public class ThresholdCondition implements Condition {
    private String metricName;
    private double threshold;
    private boolean isGreater;

    public ThresholdCondition(String metricName, double threshold, boolean isGreater) {
        this.metricName = metricName;
        this.threshold = threshold;
        this.isGreater = isGreater;
    }

    @Override
    public boolean evaluate(List<Metric> metrics) {
        for (Metric metric : metrics) {
            if (metric.getName().equals(metricName)) {
                return isGreater ? metric.getValue() > threshold : metric.getValue() < threshold;
            }
        }
        return false;
    }
}
