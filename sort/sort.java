import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

// USACO 2018 US Open, Platinum
// Problem 1. Out of Sorts
// **********

public class sort {

    private static int N;
    private static int[] array;
    private static Integer[] sorted;
    private static int[] preq;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("sort.in"));
        N = Integer.parseInt(in.readLine());

        array = new int[N];
        sorted = new Integer[N];
        preq = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(in.readLine());
            sorted[i] = i;
        }

        in.close();

        Arrays.sort(sorted, Comparator.comparingInt(o -> array[o]));

        int mx = 0;
        int high = 0;

        for (int i = 1; i < N; i++) {
            mx = Math.max(mx, sorted[i - 1]);
            preq[i] = mx + 1 - i;
            high = Math.max(high, preq[i]);
        }

        long ans = 0;

        for (int i = 0; i < N; i++) {
            int tdone = 0;
            if (i > 0) {
                tdone = Math.max(tdone, preq[i]);
            }
            if (i < N - 1) {
                tdone = Math.max(tdone, preq[i + 1]);
            }
            if (tdone == 0 && N > 1) {
                tdone++;
            }
            ans += tdone;
        }

        PrintWriter out = new PrintWriter("sort.out");
        out.println(ans);
        out.close();

    }

}