/*
ID: oliver.6
LANG: JAVA
TASK: fracdec
*/

// USACO Training, Chapter 2.4
// Problem 6. Fractions to Decimals
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class fracdec {

    private static int N;
    private static int D;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("fracdec.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        in.close();

        PrintWriter out = new PrintWriter("fracdec.out");


		if (N % D == 0) {
            out.println((double) N / D);
        } else {
            StringBuilder dec = new StringBuilder(N / D + ".");
			List<Integer> d = new ArrayList<Integer>();
			List<Integer> n = new ArrayList<Integer>();
            boolean[] visited = new boolean[100001];
            
            N %= D;
            
			while (N > 0 && !visited[N]) {
				visited[N] = true;
				n.add(N);
				N *= 10;
				d.add(N / D);
				N %= D;
			}
			
			for (int i = 0; i < d.size(); i++) {
				if (N > 0 && n.get(i) == N) dec.append("(");
				dec.append(d.get(i));
			}
			
			dec.append(N > 0 ? ")" : "");
			
            String res = dec.toString();
            
            for (int i = 0; ; i++) {
                if ((i + 1) * 76 >= res.length()) {
                    out.println(res.substring(i * 76));
                    break;
                } else {
                    out.println(res.substring(i * 76, (i + 1) * 76));
                }
            }
			
        }
        
        out.close();

    }

}