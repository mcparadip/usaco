/*
ID: oliver.6
LANG: JAVA
TASK: concom
*/

// USACO Training, Chapter 2.3
// Problem 5. Controlling Companies
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class concom {

    private static int N;
    private static int[][] owns;
    private static boolean[][] controls;

    private static void addController(int i, int j) {

        if (controls[i][j]) return;

        controls[i][j] = true;

        for (int k = 0; k < 100; k++) {
            owns[i][k] += owns[j][k];
        }

        for (int k = 0; k < 100; k++) {
            if (controls[k][i]) {
                addController(k, j);
            }
        }

        for (int k = 0; k < 100; k++) {
            if (owns[i][k] > 50) {
                addController(i, k);
            }
        }

    }

    private static void addOwner(int i, int j, int p) {
        for (int k = 0; k < 100; k++) {
            if (controls[k][i]) {
                owns[k][j] += p;
            }
        }

        for (int k = 0; k < 100; k++) {
            if (owns[k][j] > 50) {
                addController(k, j);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("concom.in"));
        N = Integer.parseInt(in.readLine());

        owns = new int[100][100];
        controls = new boolean[100][100];

        for (int i = 0; i < 100; i++) {
            addController(i, i);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            addOwner(a, b, Integer.parseInt(st.nextToken()));
        }
        
        in.close();

        PrintWriter out = new PrintWriter("concom.out");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i != j && controls[i][j]) {
                    out.println((i + 1) + " " + (j + 1));
                }
            }
        }
        out.close();

    }

}