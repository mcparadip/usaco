// USACO 2015 February, Silver
// Problem 1. Censoring (Silver)
// **t**t*x*t*****

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class censor {

    public static void main(String args[]) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("censor.in"));

        String original = in.readLine();
        String replace = in.readLine();

        int length = original.length();

        while (true) {

            original = original.replaceAll(replace, "");

            int oldlength = length;
            length = original.length();

            if (oldlength == length) {
                break;
            }
        }

        PrintWriter writer = new PrintWriter("censor.out");
        writer.println(original);
        writer.close();

    }

}