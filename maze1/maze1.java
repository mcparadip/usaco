/*
ID: oliver.6
LANG: JAVA
TASK: maze1
*/

// USACO Training, Chapter 2.4
// Problem 3. Overfencing
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class maze1 {

    private static int W;
    private static int H;

    private static int[][] board;

    private static Deque<int[]> starts;

    private static boolean checkValid(int i, int j, int r) {
        if (i < 0 || i > 2*H || j < 0 || j > 2*W || board[i][j] == -1 || r >= board[i][j]) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[2*H+1][2*W+1];
        starts = new ArrayDeque<>();
        for (int i = 0; i < 2*H+1; i++) {
            String line = in.readLine();
            for (int j = 0; j < 2*W+1; j++) {
                char c = line.charAt(j);
                if (c == ' ') {
                    board[i][j] = Integer.MAX_VALUE;
                    if (i == 0 || j == 0 || i == 2*H || j == 2*W) {
                        starts.push(new int[]{i, j, 0});
                    }
                } else {
                    board[i][j] = -1;
                }
            }
        }
        in.close();

        int t = 0;

        while (!starts.isEmpty()) {

            int[] c = starts.pop();
            board[c[0]][c[1]] = c[2];

            for (int i = 0; i < 2*H+1; i++) {
                for (int j = 0; j < 2*W+1; j++) {
                    if (board[i][j] == -1) System.out.print("#");
                    else if (board[i][j] == Integer.MAX_VALUE) System.out.print(" ");
                    else System.out.print(board[i][j] % 10);
                }
                System.out.println();
            }
            System.out.println();

            if (checkValid(c[0] - 1, c[1], c[2] + 1)) {
                starts.push(new int[]{c[0] - 1, c[1], c[2] + 1});
            }
            if (checkValid(c[0], c[1] - 1, c[2] + 1)) {
                starts.push(new int[]{c[0], c[1] - 1, c[2] + 1});
            }
            if (checkValid(c[0] + 1, c[1], c[2] + 1)) {
                starts.push(new int[]{c[0] + 1, c[1], c[2] + 1});
            }
            if (checkValid(c[0], c[1] + 1, c[2] + 1)) {
                starts.push(new int[]{c[0], c[1] + 1, c[2] + 1});
            }

        }

        PrintWriter out = new PrintWriter("maze1.out");
        int max = 0;
        for (int i = 0; i < 2*H+1; i++) {
            for (int j = 0; j < 2*W+1; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        out.println((max + 1) / 2);
        out.close();

    }

}