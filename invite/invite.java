// USACO 2013 January, Silver
// Problem 3. Party Invitations
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class invite {

    private static int N;
    private static int G;

    private static Set<Integer>[] groups;
    private static Set<Integer>[] cows;
    private static boolean[] invited;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("invite.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        groups = new Set[G];
        cows = new Set[N];
        invited = new boolean[N];

        for (int i = 0; i < N; i++) {

            cows[i] = new HashSet<>();

        }

        for (int i = 0; i < G; i++) {

            groups[i] = new HashSet<>();

            st = new StringTokenizer(in.readLine());

            int S = Integer.parseInt(st.nextToken());

            for (int j = 0; j < S; j++) {

                groups[i].add(Integer.parseInt(st.nextToken()) - 1);
                cows[j].add(i);

            }

        }

        Deque<Integer> q = new ArrayDeque<>();

        long time = System.nanoTime();

        int result = 0;
        q.push(0);
        invited[0] = true;

        while (!q.isEmpty()) {

            int cow = q.pop();

            result++;

            for (int i = 0; i < G; i++) {

                groups[i].remove(cow);

                if (groups[i].size() == 1) {

                    int last = (Integer) groups[i].toArray()[0];

                    if (!invited[last]) {
                        invited[last] = true;
                        q.push(last);
                    }

                }

            }

        }

        PrintWriter out = new PrintWriter("invite.out");
        out.println(result);
        out.close();

    }

}
