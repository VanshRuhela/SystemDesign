package org.example.behavioral.mediator.code;

public class Demo {

    public static void main(String[]a){
        AuctionMediator auctionManager = new Auction();
        Colleague bidder1 = new Bidder("Rubella" , auctionManager);
        Colleague bidder2 = new Bidder("Bebop", auctionManager);
        Colleague bidder3 = new Bidder("Shubha", auctionManager);

        bidder1.placeBid(20);
        bidder2.placeBid(30);
        bidder1.placeBid(10);
        bidder3.placeBid(25);
    }
}
