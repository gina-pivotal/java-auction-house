package io.pivotal.interview;

import java.io.IOException;
import java.util.List;

public class EnglishAuction extends Auction{
    private Integer bid;
    private List<String> bidders;
    private boolean hasStarted = false;
    public EnglishAuction(Integer startingBid, List<String> startingBidders) {
        super(startingBid);
        bid = startingBid;
        bidders = startingBidders;
    }


    public String getBidderPrompt(String bidder, int currentBid)
    {
        int suggestedBid = hasStarted? (currentBid + 1) : currentBid;
        hasStarted = true;
        return String.format("Bidder %s: The suggested Price is $%s; enter your bid",bidder, suggestedBid);
    }
}
