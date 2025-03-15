package llds.alaramalert.code;

import java.util.List;

public interface Builder {
    void setName();
    void setNotification(List<Notification> notification);
    void setAction(List<Action> action);
    void setCondition(Condition condition);
}
