/*
ID: oliver.6
LANG: JAVA
TASK: zerosum
*/

// USACO Training, Chapter 2.3
// Problem 3. Zero Sum
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zerosum {

    private static int N;
    private static final String[] key = {" ", "+", "-"};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
        N = Integer.parseInt(in.readLine());
        in.close();

        List<String> results = new ArrayList<>();

        for (int i = 0; i < Math.pow(3, N-1); i++) {

            int t = i;

            int sum = 0;
            int num = 1;
            int op = 1;

            StringBuilder b = new StringBuilder("1");

            for (int j = 2; j <= N; j++) {

                if (t % 3 == 0) {
                    num *= 10;
                    num += j;
                } else {
                    if (op == 1) sum += num;
                    else sum -= num;
                    op = t % 3;
                    num = j;
                }
                b.append(key[t % 3]);
                b.append(j);
                t /= 3;

            }

            if (op == 1) sum += num;
            else sum -= num;

            if (sum == 0) results.add(b.toString());

        }

        Collections.sort(results);

        PrintWriter out = new PrintWriter("zerosum.out");
        for (String res : results) out.println(res);
        out.close();

    }

}