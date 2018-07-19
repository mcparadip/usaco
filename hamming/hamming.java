/*
ID: oliver.6
LANG: JAVA
TASK: hamming
*/

// USACO Training, Chapter 2.1
// Problem 7. Hamming Codes
// ***********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class hamming {

    private static int N;
    private static int B;
    private static int D;

    private static int hammingdist(int a, int b) {
        return Integer.bitCount(a ^ b);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        in.close();

        List<Integer> res = new ArrayList<>(N);

        mainLoop:
        for (int i = 0; i < 1 << B && res.size() < N; i++) {
            for (int cw : res) {
                if (hammingdist(cw, i) < D) {
                    continue mainLoop;
                }
            }
            res.add(i);
        }

        PrintWriter out = new PrintWriter("hamming.out");
        out.print(res.get(0));
        for (int i = 1; i < N; i++) {
            out.print(i % 10 == 0 ? "\n" : " ");
            out.print(res.get(i));
        }
        out.println();
        out.close();

    }

}