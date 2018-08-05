// USACO 2007 January, Bronze
// Problem 2. Making Change
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class change {

    private static int N;
    private static int C;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("change.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        TreeSet<Integer> c = new TreeSet<>();

        for (int i = 0; i < N; i++) {

            c.add(Integer.parseInt(in.readLine()));

        }

        int r = 0;
        while (C != 0) {
            r += C / c.last();
            C %= c.last();
            c.remove(c.last());
        }

        PrintWriter out = new PrintWriter("change.out");
        out.println(r);
        out.close();

    }

}
