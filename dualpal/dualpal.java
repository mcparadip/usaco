/*
ID: oliver.6
LANG: JAVA
TASK: dualpal
*/

// USACO Training, Chapter 1.3
// Problem 6. Dual Palindromes
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class dualpal {

    private static int N;
    private static int S;

    private static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        in.close();

        PrintWriter out = new PrintWriter("dualpal.out");

        int total = 0;
        for (int i = S + 1; total < N; i++) {

            int count = 0;
            for (int b = 2; b <= 10 && count < 2; b++) {
                String num = Integer.toString(i, b);
                if (isPalindrome(num)) count++;
            }

            if (count == 2) {
                out.println(i);
                total++;
            }

        }

        out.close();

    }

}
