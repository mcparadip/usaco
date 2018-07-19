/*
ID: oliver.6
LANG: JAVA
TASK: combo
*/

// USACO Training, Chapter 1.4
// Problem 6. Combination Lock
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class combo {

    private static int N;
    private static int[][] combo;

    private static boolean doesWork(int[] code, int i, int j, int k) {
        return (Math.abs(i - code[0]) <= 2 || Math.abs(i - code[0]) >= N - 2) &&
                (Math.abs(j - code[1]) <= 2 || Math.abs(j - code[1]) >= N - 2) &&
                (Math.abs(k - code[2]) <= 2 || Math.abs(k - code[2]) >= N - 2);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("combo.in"));
        N = Integer.parseInt(in.readLine());

        combo = new int[2][3];
        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 3; j++) {
                combo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        in.close();

        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (doesWork(combo[0], i, j, k) || doesWork(combo[1], i, j, k)) {
                        total += 1;
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter("combo.out");
        out.println(total);
        out.close();

    }

}