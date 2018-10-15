// A-Star Platinum, Week 6
// Problem 3. Soda Machine
// **********

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class soda {

    private static int N;
    private static int[] starts;
    private static int[] ends;
    private static int[] cpoints;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        starts = new int[N];
        ends = new int[N];
        cpoints = new int[2*N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            starts[i] = s;
            ends[i] = e;
            cpoints[2 * i] = s;
            cpoints[2 * i + 1] = e;
        }
        in.close();

        Arrays.sort(starts);
        Arrays.sort(ends);
        Arrays.sort(cpoints);

        int r = 0;
        int c = 0;
        int ps = 0;
        int pe = 0;

        for (int x : cpoints) {
            if (ps < N && x == starts[ps]) {
                c++; ps++;
            }
            r = Math.max(r, c);
            if (pe < N && x == ends[pe]) {
                c--; pe++;
            }
        }

        System.out.println(r);

    }

}