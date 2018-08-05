// USACO 2017 US Open, Silver
// Problem 2. Bovine Genomics
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class cownomics {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));

        String line = in.readLine();
        int index = line.indexOf(" ");
        int N = Integer.parseInt(line.substring(0, index));
        int M = Integer.parseInt(line.substring(index + 1));

        List<List<Integer>> spotted = new ArrayList<>();
        List<List<Integer>> plain = new ArrayList<>();
        List<Integer> aa = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            aa.add(0);
        }

        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            spotted.add(new ArrayList<>());
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                if (c == 'A') spotted.get(i).add(0);
                if (c == 'C') spotted.get(i).add(1);
                if (c == 'G') spotted.get(i).add(2);
                if (c == 'T') spotted.get(i).add(3);
            }
        }

        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            plain.add(new ArrayList<>());
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                if (c == 'A') plain.get(i).add(0);
                if (c == 'C') plain.get(i).add(1);
                if (c == 'G') plain.get(i).add(2);
                if (c == 'T') plain.get(i).add(3);
            }
        }

        // Do stuff

        int result = 0;

        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int k = j + 1; k < M; k++) {
                    boolean valid = true;

                    for (int a = 0; a < N; a++) {
                        aa.set(spotted.get(a).get(i) * 16 + spotted.get(a).get(j) * 4 + spotted.get(a).get(k), 1);
                    }
                    for (int a = 0; a < N; a++) {
                        if (aa.get(plain.get(a).get(i) * 16 + plain.get(a).get(j) * 4 + plain.get(a).get(k)) != 0) {
                            valid = false;
                        }
                    }
                    for (int a = 0; a < N; a++) {
                        aa.set(spotted.get(a).get(i) * 16 + spotted.get(a).get(j) * 4 + spotted.get(a).get(k), 0);
                    }

                    if (valid) {
                        result++;
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter("cownomics.out");
        out.println(result);
        out.close();

    }

}
