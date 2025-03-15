package org.example.behavioral.observer.code;

import org.example.behavioral.observer.code.observable.IphoneObserableImpl;
import org.example.behavioral.observer.code.observable.StockObservable;
import org.example.behavioral.observer.code.observer.EmailAlertObserverImpl;
import org.example.behavioral.observer.code.observer.NotificationAlertObserver;

public class Demo {
    public static void main(String[] a){
        StockObservable iphone = new IphoneObserableImpl();

        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("xyz@abc.com", iphone);
        NotificationAlertObserver observer2 = new EmailAlertObserverImpl("xy@pqrs.com" , iphone);

        iphone.add(observer1);
        iphone.add(observer2);

        iphone.setStockCount(15);
    }
}
