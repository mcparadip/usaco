/*
ID: oliver.6
LANG: JAVA
TASK: barn1
*/

// USACO Training, Chapter 1.4
// Problem 3. Barn Repair
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class barn1 {

    private static int M;
    private static int S;
    private static int C;

    private static int[] cows;
    private static List<Integer> runs;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cows = new int[C];
        runs = new ArrayList<>();

        for (int i = 0; i < C; i++) {
            cows[i] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(cows);

        int prev = cows[0];
        for (int i = 1; i < C; i++) {
            runs.add(cows[i] - prev - 1);
            prev = cows[i];
        }

        in.close();

        Collections.sort(runs, Collections.reverseOrder());

        PrintWriter out = new PrintWriter("barn1.out");
        out.println(cows[C - 1] - cows[0] + 1 - runs.stream().mapToInt(Integer::intValue).limit(M - 1).sum());
        out.close();

    }

}
