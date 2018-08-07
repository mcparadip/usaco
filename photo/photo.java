// USACO 2011 December, Gold
// Problem 1. Cow Photography
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class photo {

    private static class Compare implements Comparator<Integer> {

        public int compare(Integer a, Integer b) {

            int count = 0;
            for (int i = 0; i < 5; i++) {
                count += pos[i].get(a) < pos[i].get(b) ? 1 : 0;
            }

            return count > 2 ? -1 : 1;

        }

    }

    private static List<Integer> a;
    private static int N;
    private static Map<Integer, Integer>[] pos;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("photo.in"));

        N = Integer.parseInt(in.readLine());

        pos = new HashMap[5];

        a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(0);
        }

        for (int i = 0; i < 5; i++) {

            pos[i] = new HashMap<>();

            for (int j = 0; j < N; j++) {

                StringTokenizer st = new StringTokenizer(in.readLine());

                int x = Integer.parseInt(st.nextToken());
                pos[i].put(x, j);
                a.set(j, x);

            }

        }

        Collections.sort(a, new Compare());

        PrintWriter out = new PrintWriter("photo.out");
        for (int i = 0; i < N; i++) {
            out.println(a.get(i));
        }
        out.close();

    }

}
