/*
ID: oliver.6
LANG: JAVA
TASK: numtri
*/

// USACO Training, Chapter 1.6
// Problem 2. Number Triangles
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class numtri {

    private static int R;
    private static int[][] tri;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
        R = Integer.parseInt(in.readLine());
        tri = new int[R][R];
        for (int i = 0; i < R; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j <= i; j++) {
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();

        for (int i = R - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
            }
        }

        PrintWriter out = new PrintWriter("numtri.out");
        out.println(tri[0][0]);
        out.close();

    }

}