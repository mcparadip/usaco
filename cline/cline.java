// USACO 2009 US Open, Silver
// Problem 2. Cow Line
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class cline {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("cline.in"));

        int S = Integer.parseInt(in.readLine());

        int t = 0;

        Deque<Integer> line = new ArrayDeque<>();

        for (int i = 0; i < S; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            String s = st.nextToken();
            if (s.equals("A")) {
                s = st.nextToken();
                if (s.equals("L"))
                    line.addFirst(++t);
                else if (s.equals("R"))
                    line.addLast(++t);
            } else if (s.equals("D")) {
                s = st.nextToken();
                int c = Integer.parseInt(st.nextToken());
                if (s.equals("L"))
                    for (int j = 0; j < c; j++)
                        line.removeFirst();
                else if (s.equals("R"))
                    for (int j = 0; j < c; j++)
                        line.removeLast();
            }

        }

        PrintWriter out = new PrintWriter("cline.out");
        for (int i : line) {
            out.println(i);
        }
        out.close();

    }

}
