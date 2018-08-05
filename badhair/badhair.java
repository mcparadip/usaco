// USACO 2006 November, Silver
// Problem 1. Bad Hair Day
// ************

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class badhair {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("badhair.in"));

        int N = Integer.parseInt(in.readLine());
        int[] cows = new int[N];

        for (int i = 0; i < N; i++) {

            cows[i] = Integer.parseInt(in.readLine());

        }

        long result = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        int i;
        for (i = 0; i < N; i++) {
            while (map.size() > 0 && cows[i] >= map.firstKey()) {
                result += i - map.firstEntry().getValue() - 1;
                map.remove(map.firstKey());
            }
            map.put(cows[i], i);
        }

        while (map.size() > 0) {
            result += i - map.firstEntry().getValue() - 1;
            map.remove(map.firstKey());
        }

        PrintWriter out = new PrintWriter("badhair.out");
        out.println(result);
        out.close();

    }

}
