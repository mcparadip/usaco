/*
ID: oliver.6
LANG: JAVA
TASK: skidesign
*/

// USACO Training, Chapter 1.4
// Problem 8. Ski Course Design
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class skidesign {

    private static int N;
    private static int[] hills;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
        N = Integer.parseInt(in.readLine());

        hills = new int[N];

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < N; i++) {
            hills[i] = Integer.parseInt(in.readLine());
            min = Math.min(min, hills[i]);
            max = Math.max(max, hills[i]);
        }
        in.close();

        int result = Integer.MAX_VALUE;

        for (int i = min; i <= max - 17; i++) {

            int cost = 0;

//            System.out.println(i + " " + (i + 17));

            for (int j = 0; j < N; j++) {
                int x = 0;

                if (hills[j] < i) {
                    x = i - hills[j];
                } else if (hills[j] > i + 17) {
                    x += hills[j] - i - 17;
                }

//                System.out.println(j + ": " + x + " = " + x * x);
                cost += x * x;
            }

//            System.out.println(cost);
//            System.out.println();

            result = Math.min(result, cost);

        }

        PrintWriter out = new PrintWriter("skidesign.out");
        out.println(result);
        out.close();

    }

}