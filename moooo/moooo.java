// USACO 2006 March, Silver
// Problem 1. Mooo
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class moooo {

    private static class Cow {

        int index;
        int V;
        int H;

        public Cow(int index, int H, int V) {

            this.index = index;
            this.V = V;
            this.H = H;

        }

        public int hashCode() {

            return V * 31 + H + 1000 * index;

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("moooo.in"));

        int N = Integer.parseInt(in.readLine());

        List<Cow> cows = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            cows.add(new Cow(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        }

        Stack<Cow> tall = new Stack<>();

        int[] result = new int[N];

        for (Cow cow : cows) {

            while (tall.size() > 0 && tall.peek().H < cow.H) {

                result[cow.index] += tall.pop().V;

            }

            tall.push(cow);

        }

        Collections.reverse(cows);

        tall = new Stack<>();

        for (Cow cow : cows) {

            while (tall.size() > 0 && tall.peek().H < cow.H) {

                result[cow.index] += tall.pop().V;

            }

            tall.push(cow);

        }

        int max = 0;
        for (int i = 0; i < N; i++) {

            if (result[i] > max) {

                max = result[i];

            }

        }

        PrintWriter out = new PrintWriter("moooo.out");
        out.println(max);
        out.close();

    }

}
