/*
ID: oliver.6
LANG: JAVA
TASK: preface
*/

// USACO Training, Chapter 2.2
// Problem 3. Preface Numbering
// ********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class preface {

    private static final String[] ref = {"I", "V", "X", "L", "C", "D", "M"};
    private static int[] digits = new int[8];

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("preface.in"));
        N = Integer.parseInt(in.readLine());
        in.close();

        for (int i = 1; i <= N; i++) {
            int j = i;
            int p = 0;

            while (j > 0) {
                int v = j % 10;

                if (v % 5 == 4) {
                    digits[p + v / 5 + 1]++;
                    digits[p]++;
                } else {
                    digits[p] += v % 5;
                    digits[p + 1] += v / 5;
                }

                j /= 10;
                p += 2;
            }
        }

        PrintWriter out = new PrintWriter("preface.out");
        for (int i = 0; i < 7; i++) {
            if (digits[i] == 0) break;
            out.println(ref[i] + " " + digits[i]);
        }
        out.close();

    }

}