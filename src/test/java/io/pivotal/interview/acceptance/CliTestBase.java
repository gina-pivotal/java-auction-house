package io.pivotal.interview.acceptance;

import io.pivotal.interview.Cli;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public abstract class CliTestBase {

    @Rule
    public Timeout timeout = new Timeout(5, TimeUnit.SECONDS);

    protected Pipe prompts;
    protected Pipe commands;
    private Cli cli;
    private Thread cliThread;

    @Before
    public final void startApplication() throws Exception {
        System.out.println("\n---+---------------------------------------------");
        prompts = new Pipe();
        commands = new Pipe();
        cli = new Cli(commands.output, prompts.input);
        cliThread = new Thread(cli);
        cliThread.start();
    }

    @After
    public final void stopApplication() throws Exception {
        commands.close();
        cliThread.join(1000);
        prompts.close();
    }

    protected void createAuction(String type, int startingPrice) throws IOException {
        prompts.read();
        commands.write(type);
        prompts.read();
        commands.write(Integer.toString(startingPrice));
        prompts.read();
    }

    protected void registerBidders(String... bidders) throws IOException {
        prompts.read();
        for (String bidder : bidders) {
            commands.write(bidder);
        }
        commands.write("");
        prompts.read();
    }

    protected void assertAndBid(String bidder, int bid) throws IOException {
        assertThat(prompts.read(), startsWith("Bidder " + bidder + ": "));
        commands.write(bid);
    }

    protected void assertAndPass(String bidder) throws IOException {
        assertThat(prompts.read(), startsWith("Bidder " + bidder + ": "));
        commands.write("");
    }

    protected void assertWinner(String bidder, int amount) throws IOException {
        assertThat(prompts.read(), equalTo("Notice: " + bidder + " has won the auction at a price of " + amount));
    }

    protected void assertPrompt(String message) throws IOException {
        assertThat(prompts.read(), equalTo(message));
    }

    protected void enterText(int line) throws IOException {
        commands.write(line);
    }

    protected void enterText(String english) throws IOException {
        commands.write(english);
    }

}
