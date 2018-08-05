// USACO 2017 February, Gold
// Problem 3. Why Did the Cow Cross the Road III
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class circlecross {

    private static int N;

    private static Pair[] cows;

    private static class Pair implements Comparable<Pair> {

        public int first;
        public int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair other) {
            return Integer.compare(this.first, other.first);
        }

    }

    private static void updateBIT(int index, int val) {

        for (int i = index | 131072; i < (131072 << 1); i += i & -i)
            bit[i ^ 131072] += val;

    }

    private static int getBIT(int index) {

        int sum = 0;

        for (int i = index - 1; i != 0 && index != 0; i &= i - 1)
            sum += bit[i];

        return sum;

    }

    private static int[] bit;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));

        // Code

        N = Integer.parseInt(in.readLine());

        cows = new Pair[N];

        for (int i = 0; i < N; i++) {

            cows[i] = new Pair(-1, -1);

        }

        for (int i = 0; i < 2 * N; i++) {

            int x = Integer.parseInt(in.readLine()) - 1;

            if (cows[x].first == -1) {
                cows[x].first = i;
            } else {
                cows[x].second = i;
            }

        }

        Arrays.sort(cows);

        bit = new int[131072];

        int result = 0;
        for (int i = 0; i < N; i++) {
            result += getBIT(cows[i].second) - getBIT(cows[i].first);
            updateBIT(cows[i].second, 1);
        }


        PrintWriter out = new PrintWriter("circlecross.out");
        out.println(result);
        out.close();

    }

}
