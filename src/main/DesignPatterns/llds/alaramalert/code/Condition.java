package llds.alaramalert.code;

import java.util.List;

public interface Condition {
    boolean evaluate(List<Metric> metrics);
}
