package org.example.behavioral.observer.code.observer;

import org.example.behavioral.observer.code.observable.StockObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{
    String emailId;
    StockObservable observable;

    public EmailAlertObserverImpl(String emailId, StockObservable observable){
        this.observable = observable;
        this.emailId = emailId;
    }
    @Override
    public void update() {
        sendMail(emailId);
    }

    private void sendMail(String emailId){
        System.out.println("mail sent to : " + emailId);
        System.out.println("Products are in stock !!!");
    }
}
