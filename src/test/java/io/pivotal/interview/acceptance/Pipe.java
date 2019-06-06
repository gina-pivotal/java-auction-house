package io.pivotal.interview.acceptance;

import java.io.*;

public class Pipe implements Closeable {

    public final BufferedWriter input;
    public final BufferedReader output;

    public Pipe() throws IOException {
        PipedReader buffer = new PipedReader();
        this.output = new BufferedReader(buffer);
        this.input = new BufferedWriter(new PipedWriter(buffer));
    }

    @Override
    public void close() throws IOException {
        output.close();
    }

    public String read() throws IOException {
        String line = output.readLine();
        System.out.println(">> | " + line);
        if (line == null) throw new EOFException();
        return line;
    }

    public void write(String line) throws IOException {
        input.write(line);
        input.newLine();
        input.flush();
        System.out.println("<< | " + line);
    }

    public void write(int line) throws IOException {
        write(Integer.toString(line));
    }

}
