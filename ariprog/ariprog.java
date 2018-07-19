/*
ID: oliver.6
LANG: JAVA
TASK: ariprog
*/

// USACO Training, Chapter 1.5
// Problem 2. Arithmetic Progressions
// **********

import java.io.*;

public class ariprog {

    private static int N;
    private static int M;

    private static boolean[] bisquare;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        in.close();

        bisquare = new boolean[M * M * 2 + 1];

        for (int p = 0; p <= M; p++) {
            for (int q = p; q <= M; q++) {
                bisquare[p * p + q * q] = true;
            }
        }

        PrintWriter out = new PrintWriter("ariprog.out");

        boolean any = false;

        for (int d = 1; d <= (M * M * 2) / (N - 1); d++) {
            for (int i = 0; i <= M * M; i++) {
                boolean found = true;

//                System.out.println(i + " " + d);

                for (int j = 0; j < N; j++) {
//                    System.out.println(i + j * d);
                    if (i + j * d > M * M * 2 || !bisquare[i + j * d]) {
                        found = false;
//                        System.out.println("OOF");
                        break;
                    }
                }

                if (found) {
                    out.println(i + " " + d);
//                    System.out.println("YES");
                    any = true;
                }
//                System.out.println();
            }
        }

        if (!any) {
            out.println("NONE");
        }

        out.close();

    }

}