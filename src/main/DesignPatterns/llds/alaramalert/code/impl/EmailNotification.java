package llds.alaramalert.code.impl;

import llds.alaramalert.code.Notification;

public class EmailNotification implements Notification {
    private String email;
    public EmailNotification(String email){
        this.email = email;
    }

    @Override
    public void send() {
        System.out.println("Sending email notification to " + email);
    }
}
