/*
ID: oliver.6
LANG: JAVA
TASK: ttwo
*/

// USACO Training, Chapter 2.4
// Problem 2. The Tamworth Two
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ttwo {

    private static boolean[][] board;
    private static int sfx;
    private static int sfy;
    private static int sfd;
    private static int scx;
    private static int scy;
    private static int scd;
    private static int fx;
    private static int fy;
    private static int fd;
    private static int cx;
    private static int cy;
    private static int cd;

    private static final int[][] key = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    //  0
    // 3 1
    //  2

    private static boolean checkValid(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9 || board[x][y]) return false;
        else return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("ttwo.in"));
        board = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            String line = in.readLine();
            for (int j = 0; j < 10; j++) {
                char c = line.charAt(j);
                if (c == '*') {
                    board[i][j] = true;
                } else if (c == 'F') {
                    fx = i;
                    fy = j;
                } else if (c == 'C') {
                    cx = i;
                    cy = j;
                }
            }
        }
        fd = cd = 0;
        sfx = fx;
        sfy = fy;
        sfd = fd;
        scx = cx;
        scy = cy;
        scd = cd;
        in.close();

        int t = 0;

        while (fx != cx || fy != cy) {

            // System.out.println(fx + " " + fy);
            // System.out.println(cx + " " + cy);
            // System.out.println();

            int[] fm = key[fd];
            int[] cm = key[cd];

            int fmx = fx;
            int fmy = fy;
            fmx += fm[0];
            fmy += fm[1];

            int cmx = cx;
            int cmy = cy;
            cmx += cm[0];
            cmy += cm[1];

            if (checkValid(fmx, fmy)) {
                fx = fmx;
                fy = fmy;
            } else {
                fd++;
                fd %= 4;
            }

            if (checkValid(cmx, cmy)) {
                cx = cmx;
                cy = cmy;
            } else {
                cd++;
                cd %= 4;
            }

            if (sfx == fx && sfy == fy && sfd == fd && scx == cx && scy == cy && scd == cd) {
                t = 0;
                break;
            }

            t++;

            if (t > 10000) {
                t = 0;
                break;
            }

        }

        PrintWriter out = new PrintWriter("ttwo.out");
        out.println(t);
        out.close();

    }

}