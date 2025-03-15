package org.example.behavioral.observer.code.observable;

import org.example.behavioral.observer.code.observer.NotificationAlertObserver;

public interface StockObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();
    public void setStockCount(int newStockCount);
    public int getStockCount();

}
