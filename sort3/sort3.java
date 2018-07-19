/*
ID: oliver.6
LANG: JAVA
TASK: sort3
*/

// USACO Training, Chapter 2.1
// Problem 5. Sorting a Three-Valued Sequence
// ********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class sort3 {

    private static int N;

    private static int[] numbers;
    private static int[] sorted;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
        N = Integer.parseInt(in.readLine());
        numbers = new int[N];
        sorted = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(in.readLine());
            sorted[i] = numbers[i];
        }
        in.close();

        Arrays.sort(sorted);

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(sorted));

        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (numbers[i] != sorted[i] && numbers[j] != sorted[j] && numbers[i] == sorted[j] && numbers[j] == sorted[i]) {
                    numbers[i] = sorted[i];
                    numbers[j] = sorted[j];
                    res++;
                }
            }
        }

        int bad = 0;

        for (int i = 0; i < N; i++) {
            if (numbers[i] != sorted[i]) {
                bad++;
            }
        }

        res += bad / 3 * 2;

        PrintWriter out = new PrintWriter("sort3.out");
        out.println(res);
        out.close();

    }

}