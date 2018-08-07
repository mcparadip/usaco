// USACO 2013 March, Bronze
// Problem 2. Breed Proximity
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class proximity {

    private static int N;
    private static int K;
    private static int s[];
    private static int breed[] = new int[1000001];

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("proximity.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        s = new int[N];

        int max = -1;

        for (int i = 0; i < N; i++) {

            s[i] = Integer.parseInt(in.readLine());

            breed[s[i]]++;

            if (i > K) {
                breed[s[i-K-1]]--;
            }

            if (breed[s[i]] > 1 && s[i] > max) {
                max = s[i];
            }

        }

        PrintWriter out = new PrintWriter("proximity.out");
        out.println(max);
        out.close();

    }

}
