/*
ID: oliver.6
LANG: JAVA
TASK: palsquare
*/

// USACO Training, Chapter 1.3
// Problem 5. Palindromic Squares
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class palsquare {

    private static int B;

    private static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
        B = Integer.parseInt(in.readLine());
        in.close();

        PrintWriter out = new PrintWriter("palsquare.out");

        for (int i = 1; i < 300; i++) {
            String num = Integer.toString(i, B).toUpperCase();
            String square = Integer.toString(i * i, B).toUpperCase();

            if (isPalindrome(square)) {
                out.println(num + " " + square);
            }
        }

        out.close();

    }

}
