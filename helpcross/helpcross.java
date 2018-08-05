// USACO 2017 February, Silver
// Problem 1. Why Did the Cow Cross the Road
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class helpcross {

    private static class Tuple<X, Y> {

        public final X x;
        public final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));

        String line = in.readLine();
        int index = line.indexOf(" ");
        int C = Integer.parseInt(line.substring(0, index));
        int N = Integer.parseInt(line.substring(index+1));

        List<Integer> chickens = new ArrayList<>();

        for (int i = 0; i < C; i++) {
            chickens.add(Integer.parseInt(in.readLine()));
        }

        List<Tuple<Integer, Integer>> cows = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line2 = in.readLine();
            int index2 = line2.indexOf(" ");
            int A = Integer.parseInt(line2.substring(0, index2));
            int B = Integer.parseInt(line2.substring(index2+1));
            cows.add(new Tuple<>(A, B));
        }

        Collections.sort(chickens);

        int result = 0;

        for (int i : chickens) {
            int ci = -1;
            int lowest = Integer.MAX_VALUE;
            for (int j = 0; j < cows.size(); j++) {
                if (cows.get(j).x <= i && i <= cows.get(j).y) {
                    if (cows.get(j).y < lowest) {
                        lowest = cows.get(j).y;
                        ci = j;
                    }
                }
            }

            if (ci != -1) {
                result += 1;
                cows.remove(ci);
            }
        }

        // Do stuff

        PrintWriter out = new PrintWriter("helpcross.out");
        out.println(result);
        out.close();

    }

}
