// USACO 2009 February, Silver
// Problem 1. The Leprechaun
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class lepr {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("lepr.in"));

        int N = Integer.parseInt(in.readLine());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

            }

        }

        List<List<Integer>> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            List<Integer> row = new ArrayList<>();
            List<Integer> column = new ArrayList<>();
            List<Integer> diag1 = new ArrayList<>();
            List<Integer> diag2 = new ArrayList<>();

            for (int j = 0; j < N; j++) {

                // Rows: i, j
                row.add(map[i][j]);

                // Columns: j, i
                column.add(map[j][i]);

                // Diagonal 1: i+j, j
                diag1.add(map[(i+j)%N][j]);

                // Diagonal 2: i+j, N-j-1
                diag2.add(map[(i+j)%N][N-j-1]);

            }
            lines.add(row);
            lines.add(column);
            lines.add(diag1);
            lines.add(diag2);

        }

        int max = Integer.MIN_VALUE;
        for (List<Integer> i : lines) {
            int total = 0;
            for (int j : i) {
                total += j;
            }
            if (total > max) {
                max = total;
            }
            for (int j = 0; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    int sum = 0;
                    for (int l : i.subList(j, k)) {
                        sum += l;
                    }
                    if (sum > max) {
                        max = sum;
                    }
                    if (total - sum > max) {
                        max = total - sum;
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter("lepr.out");
        out.println(max);
        out.close();

    }

}