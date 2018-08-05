// USACO 2017 February, Silver
// Problem 2. Why Did the Cow Cross the Road II
// ***********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class maxcross {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));

        String line = in.readLine();
        int index = line.indexOf(" ");
        int N = Integer.parseInt(line.substring(0, index));
        line = line.substring(index+1);
        index = line.indexOf(" ");
        int K = Integer.parseInt(line.substring(0, index));
        int B = Integer.parseInt(line.substring(index+1));

        List<Boolean> lights = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            lights.add(true);
        }

        for (int i = 0; i < B; i++) {
            int ind = Integer.parseInt(in.readLine())-1;
            lights.set(ind, false);
        }

        boolean prev = false;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N-K+1; i++) {
            if (lights.get(i) && !prev) {
                int count = 0;
                for (boolean j : lights.subList(i, i+K)) {
                    if (!j) count++;
                }
                if (count < result) {
                    result = count;
                }
            }
            prev = lights.get(i);
        }

        PrintWriter writer = new PrintWriter("maxcross.out");
        writer.println(result);
        writer.close();

    }

}
