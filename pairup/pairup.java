// USACO 2017 US Open, Silver
// Problem 1. Paired Up
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class pairup {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("pairup.in"));

        int N = Integer.parseInt(in.readLine());

        Map<Integer, Integer> cowsmap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            int index = line.indexOf(' ');
            int x = Integer.parseInt(line.substring(0, index));
            int y = Integer.parseInt(line.substring(index + 1));

            cowsmap.put(y, x);

        }

        List<Map.Entry<Integer, Integer>> cows = new ArrayList<>(cowsmap.entrySet());

        int result = 0;
        int i = 0;
        int j = N-1;

        while (i <= j) {
            int amt = Integer.min(cows.get(i).getValue(), cows.get(j).getValue());
            if (i == j) {
                amt = amt / 2;
            }

            result = Integer.max(result, cows.get(i).getKey() + cows.get(j).getKey());
            cows.get(i).setValue(cows.get(i).getValue() - amt);
            cows.get(j).setValue(cows.get(j).getValue() - amt);

            if (cows.get(i).getValue() == 0) {
                i++;
            }
            if (cows.get(j).getValue() == 0) {
                j--;
            }
        }


        PrintWriter writer = new PrintWriter("pairup.out");
        writer.println(result);
        writer.close();

    }

}
