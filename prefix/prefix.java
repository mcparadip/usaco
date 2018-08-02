/*
ID: oliver.6
LANG: JAVA
TASK: prefix
*/

// USACO Training, Chapter 2.3
// Problem 1. Longest Prefix
// **********

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class prefix {

    private static List<String> prefixes;
    private static String sequence;

    private static boolean[] dp;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("prefix.in"));
        prefixes = new ArrayList<String>();
        while (in.hasNext()) {
            String token = in.next();
            if (token.equals(".")) break;
            prefixes.add(token);
        }
        StringBuilder seqbuilder = new StringBuilder();
        while (in.hasNext()) {
            seqbuilder.append(in.next());
        }
        sequence = seqbuilder.toString();
        in.close();

        dp = new boolean[sequence.length() + 1];
        dp[0] = true;

        int max = 0;

        for (int i = 1; i <= sequence.length(); i++) {
            for (String prefix : prefixes) {
                if (prefix.length() <= i && sequence.substring(i-prefix.length(), i).equals(prefix) && dp[i - prefix.length()]) {
                    dp[i] = true;
                    max = i;
                    break;
                }
            }
        }

        PrintWriter out = new PrintWriter("prefix.out");
        out.println(max);
        out.close();

    }

}