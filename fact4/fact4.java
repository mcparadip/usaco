/*
ID: oliver.6
LANG: JAVA
TASK: fact4
*/

// USACO Training, Chapter 3.2
// Problem 2. Factorials
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class fact4 {

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("fact4.in"));
        N = Integer.parseInt(in.readLine());
        in.close();

        int r = 1;

        for (int i = 1; i <= N; i++) {
            r *= i;
            // System.out.print(r + " ");
            while (r % 10 == 0) r /= 10;
            r %= 100000;
            // System.out.println(r);
        }

        PrintWriter out = new PrintWriter("fact4.out");
        out.println(r % 10);
        out.close();

    }

}