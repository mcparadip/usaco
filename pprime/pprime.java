/*
ID: oliver.6
LANG: JAVA
TASK: pprime
*/

// USACO Training, Chapter 1.6
// Problem 3. Prime Palindromes
// *********

import java.io.*;
import java.util.*;

public class pprime {

    private static List<Integer> res = new ArrayList<Integer>();
    private static String[] init = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "11", "22", "33", "44", "55", "66", "77", "88", "99"};
    private static int A;
    private static int B;
    private static String bStr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        bStr = Integer.toString(B);

        in.close();

        for (String s : init) {
            dfs(s);
        }

        Collections.sort(res);

        PrintWriter out = new PrintWriter("pprime.out");
        for (int num: res) {
            out.println(num);
        }
        out.close();
    }

    private static void dfs(String n) {
        checkAndRecord(n);

        if (n.length() + 2 > bStr.length()) return;

        for (int i = 0; i <= 9; i++) {
            String tmp = i + n + i;
            dfs(tmp);
        }
    }

    private static void checkAndRecord(String n) {
        int tmp = Integer.parseInt(n);

        if (tmp % 2 == 0 || tmp % 3 == 0) return;
        if (tmp < A || tmp > B) return;
        if (Integer.toString(tmp).length() != n.length()) return;

        for (int i = 5; i * i <= tmp; i += 2) {
            if (tmp % i == 0) return;
        }
        res.add(tmp);
    }

}