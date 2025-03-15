package llds.alaramalert.code;

public class Metric {
    private String name;
    private double value;

    public Metric(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Object getName() {
        return name;
    }
}
