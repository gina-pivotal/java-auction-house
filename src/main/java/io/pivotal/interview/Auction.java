package io.pivotal.interview;

public class Auction {
    private int startingPrice;
    public Auction(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getBidderPrompt(String bidder, int currentBid){
        return "Bidder " + bidder + ": the minimum price is " + startingPrice + "; enter your bid";
    }
}
