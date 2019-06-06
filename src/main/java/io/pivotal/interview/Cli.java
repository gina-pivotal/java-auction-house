package io.pivotal.interview;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cli implements Runnable {

    private final BufferedReader input;
    private final BufferedWriter output;

    public Cli(BufferedReader input, BufferedWriter output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        try {
            write("Seller: enter the auction type");
            String type = readString();
            write("Seller: enter the starting price");
            int startingPrice = readInt();
            int currentBid = startingPrice;
            write("Notice: created an auction of type " + type + " with starting price " + startingPrice);

            write("Bidders: enter your names (blank line to finish)");
            List<String> bidders = new ArrayList<>();
            String bidderOrBlank;
            while (!(bidderOrBlank = readString()).isEmpty()) {
                bidders.add(bidderOrBlank);
            }

            write("Notice: the bidders are " + String.join(", ", bidders));
            //call logic for auction type


            Map<String, Integer> bids = new HashMap<>();
            Auction auction;
            if(type.equalsIgnoreCase("english")) {
                auction = new EnglishAuction(startingPrice, bidders);
            }else {
                auction = new Auction(startingPrice);
            }

            for( String bidder: bidders){
                    write(auction.getBidderPrompt(bidder,currentBid));
                    //englishauction capture bid
                    int bid = readInt();
                    bids.put(bidder, bid);
                    currentBid = bid;
                }


            String winner = null;
            int winningBid = -1;
            for (Map.Entry<String, Integer> bidderAndBid : bids.entrySet()) {
                String bidder = bidderAndBid.getKey();
                int bid = bidderAndBid.getValue();
                if (bid > winningBid) {
                    winner = bidder;
                    winningBid = bid;
                }
            }

            write("Notice: " + winner + " has won the auction at a price of " + winningBid);
        } catch (IOException e) {
            if (!e.getMessage().equals("Stream closed")) {
                e.printStackTrace();
            }
        }
    }

    private void write(String prompt) throws IOException {
        output.write(prompt);
        output.write('\n');
        output.flush();
    }

    private String readString() throws IOException {
        return input.readLine();
    }

    private int readInt() throws IOException {
        return Integer.parseInt(input.readLine());
    }

    public static void main(String... args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        new Cli(input, output).run();
    }

}
