/*
ID: oliver.6
LANG: JAVA
TASK: crypt1
*/

// USACO Training, Chapter 1.4
// Problem 5. Prime Cryptarithm
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.IntStream;

public class crypt1 {

    private static int N;
    private static int[] digits;

    public static boolean isValid(String str) {
        for (char c : str.toCharArray()) {
            if (IntStream.of(digits).noneMatch(x -> x == Character.getNumericValue(c))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
        N = Integer.parseInt(in.readLine());

        digits = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            digits[i] = Integer.parseInt(st.nextToken());
        }

        in.close();

        int total = 0;

        for (int i : digits) {
            for (int j : digits) {
                for (int k : digits) {
                    for (int l : digits) {
                        for (int m : digits) {
                            int a = 100 * i + 10 * j + k;
                            int b = 10 * l + m;
                            int p1 = a * m;
                            int p2 = a * l;
                            int r = a * b;

                            boolean p1Valid = p1 / 1000 == 0 && isValid(Integer.toString(p1));
                            boolean p2Valid = p2 / 1000 == 0 && isValid(Integer.toString(p2));
                            boolean rValid = isValid(Integer.toString(r));

                            if (p1Valid && p2Valid && rValid) {
                                total += 1;
                            }
                        }
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter("crypt1.out");
        out.println(total);
        out.close();

    }

}