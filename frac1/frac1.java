/*
ID: oliver.6
LANG: JAVA
TASK: frac1
*/

// USACO Training, Chapter 2.1
// Problem 4. Ordered Fractions
// ***********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class frac1 {

    private static int N;

    private static Set<Fraction> fracs;

    private static class Fraction implements Comparable<Fraction> {

        int n;
        int d;

        public Fraction(int n, int d) {
            this.n = n;
            this.d = d;
        }

        public String toString() {
            return this.n + "/" + this.d;
        }

        public int compareTo(Fraction other) {
            return Double.compare((double) this.n / this.d, (double) other.n / other.d);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
        N = Integer.parseInt(in.readLine());
        in.close();

        fracs = new TreeSet<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                fracs.add(new Fraction(j, i));
            }
        }

        PrintWriter out = new PrintWriter("frac1.out");
        for (Fraction frac : fracs) {
            out.println(frac);
        }
        out.close();

    }

}