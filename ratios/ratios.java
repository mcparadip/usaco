/*
ID: oliver.6
LANG: JAVA
TASK: ratios
*/

// USACO Training, Chapter 3.2
// Problem 5. Feed Ratios
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.StringTokenizer;

public class ratios {

    private static int[] want;
    private static int[][] feeds;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("ratios.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        want = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        feeds = new int[3][];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(in.readLine());
            feeds[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        in.close();

        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {

                    if (i == j && j == k && k == 0) continue;

                    int a = feeds[0][0] * i + feeds[1][0] * j + feeds[2][0] * k;
                    int b = feeds[0][1] * i + feeds[1][1] * j + feeds[2][1] * k;
                    int c = feeds[0][2] * i + feeds[1][2] * j + feeds[2][2] * k;

                    if ((want[0] == 0 || a % want[0] == 0) && (want[1] == 0 || b % want[1] == 0) && (want[2] == 0 || c % want[2] == 0)) {
                        
                        int r = -1;
                        if (want[0] != 0 && a != 0) r = a / want[0];
                        if (want[1] != 0 && b != 0) r = b / want[1];
                        if (want[2] != 0 && c != 0) r = c / want[2];

                        if (a % r != 0 || b % r != 0 || c % r != 0) continue;

                        if (a / r == want[0] && b / r == want[1] && c / r == want[2]) {     
                            // System.out.println(a + " " + b + " " + c + " " + r);                    
                            res.add(new int[]{i, j, k, r});
                        }
                    }

                }
            }
        }

        Collections.sort(res, Comparator.comparingInt((int[] o) -> o[0] + o[1] + o[2]));

        PrintWriter out = new PrintWriter("ratios.out");
        if (res.size() > 0) {
            out.println(res.get(0)[0] + " " + res.get(0)[1] + " " + res.get(0)[2] + " " + res.get(0)[3]);
        } else {
            out.println("NONE");
        }
        out.close();

    }

}