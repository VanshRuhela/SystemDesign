package org.example.behavioral.mediator.code;

public class Bidder implements Colleague{
    String name;
    AuctionMediator auctionMediator;

    Bidder(String name , AuctionMediator mediator){
        this.name = name;
        this.auctionMediator = mediator;
        auctionMediator.addBidder(this);
    }
    @Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this, bidAmount);
    }

    @Override
    public void recieveBidNotification(int bidAmount) {
        System.out.println("Bidder: " + name + " got the notification :: Someone placed bid of :" +bidAmount);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
