package llds.alaramalert.code.impl;

import llds.alaramalert.code.Notification;

public class SMSNotification implements Notification {
    private String phoneNumber;
    public SMSNotification(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send() {
        System.out.println("Notify sent to phone number " + phoneNumber);
    }
}
