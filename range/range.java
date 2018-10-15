// A-Star Platinum, Week 6
// Problem 1. Home on the Range
// **********

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class range {
    
    private static int N;
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while (line.equals("")) line = in.readLine();
        N = Integer.parseInt(line);
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String lin = in.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = lin.charAt(j) == '0' ? false : true;
            }
        }
        in.close();

        int[] res = new int[N+1];
        
        for (int i = 0; i < N; i++) {
            middle:
            for (int k = 0; k < N; k++) {
                // CHECK 1x1
                if (map[i][k] == false) continue;

                for (int size = 2; size <= N - i && size <= N - k; size++) {

                    // CHECK DOWN EDGES
                    for (int x = i; x < i + size; x++) {
                        if (map[x][k + size - 1] == false) {
                            continue middle;
                        }
                    }

                    // CHECK RIGHT EDGES
                    for (int y = k; y < k + size; y++) {
                        if (map[i + size - 1][y] == false) {
                            continue middle;
                        }
                    }

                    res[size]++;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (res[i] == 0) break;
            System.out.println(i + " " + res[i]);
        }

    }

}