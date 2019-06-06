package io.pivotal.interview.acceptance;

import org.junit.Test;

public class BlindAuctionTest extends CliTestBase {

    @Test
    public void shouldPromptForAuctionTypeAndStartingPrice() throws Exception {
        assertPrompt("Seller: enter the auction type");
        enterText("Blind");
        assertPrompt("Seller: enter the starting price");
        enterText(100);
        assertPrompt("Notice: created an auction of type Blind with starting price 100");
    }

    @Test
    public void shouldPromptForBidders() throws Exception {
        createAuction("Blind", 10);
        assertPrompt("Bidders: enter your names (blank line to finish)");
        enterText("Agatha");
        enterText("Bertram");
        enterText("Celia");
        enterText("");
        assertPrompt("Notice: the bidders are Agatha, Bertram, Celia");
    }

    @Test
    public void shouldAskEachBidderForABid() throws Exception {
        createAuction("Blind", 10);
        registerBidders("Agatha", "Bertram", "Celia");
        assertPrompt("Bidder Agatha: the minimum price is 10; enter your bid");
        enterText(20);
        assertPrompt("Bidder Bertram: the minimum price is 10; enter your bid");
        enterText(25);
        assertPrompt("Bidder Celia: the minimum price is 10; enter your bid");
        enterText(30);
    }

    @Test
    public void shouldDeclareTheHighestBidderTheWinner() throws Exception {
        createAuction("Blind", 10);
        registerBidders("Agatha", "Bertram", "Celia");

        assertAndBid("Agatha", 11);
        assertAndBid("Bertram", 12);
        assertAndBid("Celia", 13);

        assertWinner("Celia", 13);
    }

}
