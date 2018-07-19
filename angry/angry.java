// USACO 2016 January, Gold
// Problem 1. Angry Cows
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class angry {

    private static int N;

    private static int[] bales;
    private static int[][] min;

    private static void reverse(int[] array) {

        for (int i = 0; i < array.length / 2; i++) {

            int temp = array[i];
            array[i] = array[N-1-i];
            array[N-1-i] = temp;

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("angry.in"));

        N = Integer.parseInt(in.readLine());

        bales = new int[N];

        for (int i = 0; i < N; i++) {

            bales[i] = 2 * Integer.parseInt(in.readLine());

        }

        in.close();

        Arrays.sort(bales);

        min = new int[2][N];

        Arrays.fill(min[0], 2000000000);
        Arrays.fill(min[1], 2000000000);

        for (int a = 0; a < 2; a++) {

            int next = 0;

            min[a][0] = -2;

            for (int i = 1; i < N; i++) {
                while (next + 1 < i && Math.abs(bales[i] - bales[next + 1]) > min[a][next + 1] + 2) {
                    next++;
                }
                min[a][i] = Math.min(Math.abs(bales[i] - bales[next]), min[a][next + 1] + 2);
            }

            reverse(bales);

        }

        reverse(min[1]);

        for (int i = 0; i < N; i++) {
            System.out.println(min[0][i]);
            System.out.println(min[1][i]);
            System.out.println();
        }

        int i = 0;
        int j = N - 1;
        int result = Integer.MAX_VALUE;

        while (i < j) {
            result = Math.min(result, Math.max((bales[j] - bales[i]) / 2, 2 + Math.max(min[0][i], min[1][j])));
            if (min[0][i + 1] < min[1][j - 1]) {
                i++;
            } else {
                j--;
            }
        }

        PrintWriter out = new PrintWriter("angry.out");

        out.print(result / 2);
        out.println(result % 2 == 0 ? ".0" : ".5");

        out.close();

    }

}
