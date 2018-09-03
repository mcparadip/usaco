/*
ID: oliver.6
LANG: JAVA
TASK: msquare
*/

// USACO Training, Chapter 3.2
// Problem 6. Magic Squares
// ******tttt

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class msquare {

    private static int[] seq;

    private static final int[] da = {4, 5, 6, 7,
                                     0, 1, 2, 3};
    private static final int[] db = {3, 0, 1, 2,
                                     7, 4, 5, 6};
    private static final int[] dc = {0, 5, 1, 3,
                                     4, 6, 2, 7};
    
    private static final int[] in = {1, 8, 8*7, 8*7*6, 8*7*6*5, 8*7*6*5*4, 8*7*6*5*4*3, 8*7*6*5*4*3*2};

    private static final int[] want = {0, 1, 2, 3, 7, 6, 5, 4};

    private static int[] A(int[] s) {
        int[] n = new int[8];
        for (int i = 0; i < 8; i++) {
            n[i] = s[da[i]];
        }
        return n;
    }

    private static int[] B(int[] s) {
        int[] n = new int[8];
        for (int i = 0; i < 8; i++) {
            n[i] = s[db[i]];
        }
        return n;
    }

    private static int[] C(int[] s) {
        int[] n = new int[8];
        for (int i = 0; i < 8; i++) {
            n[i] = s[dc[i]];
        }
        return n;
    }

    private static int index(int[] s) {
        int[] look = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] rlook = {0, 1, 2, 3, 4, 5, 6, 7};
        int rv = 0;

        for (int lv = 0; lv < 8; lv++) {
            int t = look[s[lv]];
            rv += t * in[lv]; 
            look[rlook[7-lv]] = t;
            rlook[t] = rlook[7-lv];
        }
        return rv;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        seq = new int[8];
        for (int i = 0; i < 4; i++) {
            seq[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        for (int i = 7; i >= 4; i--) {
            seq[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        in.close();

        Deque<int[]> q = new ArrayDeque<>();
        Deque<String> e = new ArrayDeque<>();
        q.add(new int[]{0, 1, 2, 3, 7, 6, 5, 4});
        e.add("");

        boolean[] visited = new boolean[40320];

        String r = "";

        while (!q.isEmpty()) {
            int[] s = q.pop();
            String d = e.pop();
            visited[index(s)] = true;

            if (Arrays.equals(s, seq)) {
                r = d;
                break;
            }

            int[] a = A(s);
            if (!visited[index(a)]) {
                q.add(a);
                e.add(d + "A");
            }
            int[] b = B(s);
            if (!visited[index(b)]) {
                q.add(b);
                e.add(d + "B");
            }
            int[] c = C(s);
            if (!visited[index(c)]) {
                q.add(c);
                e.add(d + "C");
            }
        }

        PrintWriter out = new PrintWriter("msquare.out");
        out.println(r.length());
        out.println(r);
        out.close();

    }

}