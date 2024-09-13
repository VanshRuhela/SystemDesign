package org.example.behavioral.mediator.code;

public interface Colleague {
    void placeBid(int bidAmount);
    void recieveBidNotification(int bidAmount);
    String getName();
}
