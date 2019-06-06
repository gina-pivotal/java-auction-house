package io.pivotal.interview.acceptance;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

public class EnglishAuctionTest extends CliTestBase {

    private void assertBidderAndSuggestionThenBid(String bidder,int suggestedBid, int newBid) throws IOException
    {
        String prompt = prompts.read();
        assertThat(prompt, startsWith("Bidder " + bidder + ": "));
        //check suggested value
        assertThat(prompt, containsString(Integer.toString(suggestedBid)));
        //bid
        commands.write(newBid);
    }
    @Test
    public void shouldPromptForAuctionTypeAndStartingPrice() throws Exception {
        assertPrompt("Seller: enter the auction type");
        enterText("English");
        assertPrompt("Seller: enter the starting price");
        enterText(100);
        assertPrompt("Notice: created an auction of type English with starting price 100");
    }

    @Test
    public void shouldPromptForBidders() throws Exception {
        createAuction("English", 10);
        assertPrompt("Bidders: enter your names (blank line to finish)");
        enterText("Agatha");
        enterText("Bertram");
        enterText("Celia");
        enterText("");
        assertPrompt("Notice: the bidders are Agatha, Bertram, Celia");
    }

    @Test
    public void shouldPromptSuggestedPrice() throws Exception {

        String name = "firstname";
        String secondName = "secondName";
        Integer bid = 10;
        createAuction("English", bid);
        registerBidders(name,secondName);
//        assertPrompt(String.format("Bidder %s: The suggested Price is $%s; enter your bid",name, bid));
        assertBidderAndSuggestionThenBid(name,bid , bid * bid );
        assertBidderAndSuggestionThenBid(secondName,bid * bid + 1, bid * bid + 1);
    }

    @Test
    @Ignore
    public void shouldAskEachBidderMultipleTimes() throws Exception {
        createAuction("English", 10);
        registerBidders("Agatha", "Bertram", "Celia");

        assertAndBid("Agatha", 10);
        assertAndBid("Bertram", 11);
        assertAndBid("Celia", 12);

        assertAndBid("Agatha", 13);
        assertAndBid("Bertram", 14);
        assertAndBid("Celia", 15);
    }

}
