/*
ID: oliver.6
LANG: JAVA
TASK: sprime
*/

// USACO Training, Chapter 1.6
// Problem 4. Superprime Rib
// *****

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class sprime {

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
        N = Integer.parseInt(in.readLine());
        in.close();

        List<Integer> primes = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        List<Integer> possibles = new ArrayList<>(Arrays.asList(1, 3, 7, 9));

        mainLoop: while (Collections.max(primes) < Math.pow(10, N)) {
            List<Integer> toRemove = new ArrayList<>();
            List<Integer> toAdd = new ArrayList<>();

            for (int i: primes) {
                for (int j: possibles) {
                    int bootstrap = Integer.parseInt(Integer.toString(i) + Integer.toString(j));

                    if (bootstrap > Math.pow(10, N)) break mainLoop;

                    for (int k = 3; k <= Math.sqrt(bootstrap); k += 2) {
                        if ((double) bootstrap / (double) k % 1 == 0) break;
                        if (k == (int) Math.sqrt(bootstrap) - 1 || k == (int) Math.sqrt(bootstrap + 1)) {
                            toAdd.add(bootstrap);
                        }
                    }
                }
                toRemove.add(i);
            }

            primes.removeAll(toRemove);
            primes.addAll(toAdd);
        }

        PrintWriter out = new PrintWriter("sprime.out");
        for (int prime: primes) {
            out.println(prime);
        }
        out.close();

    }

}