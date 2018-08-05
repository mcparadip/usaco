// USACO 2011 November, Silver
// Problem 2. Cow Lineup
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class lineup {

    private static int N;
    private static int[] occurrences;
    private static Map<Integer, Integer> breedmap;
    private static Cow[] cows;

    private static class Cow implements Comparable<Cow> {

        int breed;
        int position;

        public Cow(int breed, int position) {
            this.breed = breed;
            this.position = position;
        }

        public int compareTo(Cow other) {

            return Integer.compare(this.position, other.position);

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("lineup.in"));

        N = Integer.parseInt(in.readLine());

        cows = new Cow[N];
        breedmap = new HashMap<>();

        int breedcounter = 0;

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            int position = Integer.parseInt(st.nextToken());
            int breed = Integer.parseInt(st.nextToken());

            if (!breedmap.containsKey(breed))
                breedmap.put(breed, breedcounter++);

            cows[i] = new Cow(breedmap.get(breed), position);



        }

        occurrences = new int[breedmap.size()];

        Arrays.sort(cows);

        int left = 0;
        int right = 0;

        occurrences[cows[0].breed]++;

        int min = Integer.MAX_VALUE;

        for (;;) {

            boolean mvleft = true;

            for (int i = 0; i < occurrences.length; i++) {

                if (occurrences[i] == 0) {

                    mvleft = false;
                    break;

                }

            }

            if (mvleft) {

                min = Math.min(min, cows[right].position - cows[left].position);
                /*System.out.println(left);
                System.out.println(right);
                System.out.println(min);
                System.out.println();*/

                occurrences[cows[left].breed]--;

                left++;

            } else {

                right++;

                if (right >= cows.length) {
                    break;
                }

                occurrences[cows[right].breed]++;

            }

            if (left >= cows.length) {
                break;
            }

        }

        PrintWriter out = new PrintWriter("lineup.out");
        out.println(min);
        out.close();

    }

}
