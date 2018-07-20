/*
ID: oliver.6
LANG: JAVA
TASK: runround
*/

// USACO Training, Chapter 2.2
// Problem 5. Runaround Numbers
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;

public class runround {

    private static int M;

    private static boolean isValid(String n) {

        Set<Character> seen = new HashSet<>();

        for (char c : n.toCharArray()) {

            if (seen.contains(c)) return false;
            if (c == '0') return false;
            seen.add(c);

        }

        return true;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("runround.in"));
        M = Integer.parseInt(in.readLine());
        in.close();

        int res;

        mainLoop:
        for (res = M + 1; ; res++) {

            String n = Integer.toString(res);

            if (!isValid(n)) continue;

            int pos = 0;
            boolean[] visited = new boolean[n.length()];

            for (;;) {

                visited[pos] = true;

                pos += Character.getNumericValue(n.charAt(pos));
                pos %= n.length();

                if (pos == 0) break;
                if (visited[pos] == true) continue mainLoop;

            }

            for (boolean b : visited) {
                if (!b) continue mainLoop;
            }

            break;

        }

        PrintWriter out = new PrintWriter("runround.out");
        out.println(res);
        out.close();

    }

}