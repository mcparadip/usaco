/*
ID: oliver.6
LANG: JAVA
TASK: contact
*/

// USACO Training, Chapter 3.1
// Problem 5. Contact
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class contact {

    private static int A;
    private static int B;
    private static int N;
    private static String seq;

    private static String intToString(long a) {
        return Long.toString(a, 2).substring(1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("contact.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        StringBuilder s = new StringBuilder();
        String ss = in.readLine();
        while (ss != null) {
            s.append(ss);
            ss = in.readLine();
        }
        seq = s.toString();
        in.close();

        Map<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i < seq.length(); i++) {
            for (int j = i + A; j <= seq.length() && j <= i + B; j++) {
                Long sub = Long.parseLong("1" + seq.substring(i, j), 2);
                freq.putIfAbsent(sub, 0);
                freq.put(sub, freq.get(sub) + 1);
            }
        }

        PrintWriter out = new PrintWriter("contact.out");
        List<Map.Entry<Long, Integer>> res = freq.entrySet().stream()
                                                .sorted(Map.Entry.comparingByValue((Integer a, Integer b) -> Integer.compare(b, a)))
                                                .collect(Collectors.toList());
        int prev = -1;
        int t = 0;
        int n = 0;
        for (int i = 0; i < res.size(); i++) {
            if (prev == res.get(i).getValue()) {
                if (n == 6) {
                    out.println();
                    n = 0;
                }
                else out.print(" ");
                out.print(intToString(res.get(i).getKey()));
                n++;
            } else {
                if (t == N) break;
                if (t > 0) out.println();
                prev = res.get(i).getValue();
                out.println(prev);
                out.print(intToString(res.get(i).getKey()));
                n = 1;
                t++;
            }
        }
        out.println();
        out.close();

    }

}