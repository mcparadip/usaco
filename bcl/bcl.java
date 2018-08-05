import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// USACO 2007 November, Silver
// Problem 3. Best Cow Line
// **********

public class bcl {

    private static int N;

    private static int[] cows;

    private static int[][] less;

    private static String[] abc = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("bcl.in"));

        N = Integer.parseInt(in.readLine());

        cows = new int[N];
        less = new int[N][N];

        for (int i = 0; i < N; i++) {

            cows[i] = in.readLine().charAt(0) - 'A' + 1;

        }

        PrintWriter out = new PrintWriter("bcl.out");

        int low = 0;
        int high = N-1;
        int counter = 0;
        while (low < high) {

            if (less[low][high] == 0 && cows[low] == cows[high]) {
                boolean d = false;
                for (int i = 1; i < (high - low)/2+1; i++) {
                    if (less[low+i][high-i] == -1 || cows[low+i] < cows[high-i]) {
                        less[low+i][high-i] = -1;
                        out.print(abc[cows[low]]);
                        low++;
                        d = true;
                        break;
                    } else if (less[low+i][high-i] == 1 || cows[low+i] > cows[high-i]) {
                        less[low+i][high-i] = 1;
                        out.print(abc[cows[high]]);
                        high--;
                        d = true;
                        break;
                    }
                }
                if (!d) {
                    out.print(abc[cows[low]]);
                    low++;
                }
            } else if (less[low][high] == -1 || cows[low] < cows[high]) {
                less[low][high] = -1;
                out.print(abc[cows[low]]);
                low++;
            } else if (less[low][high] == 1 || cows[low] > cows[high]) {
                less[low][high] = 1;
                out.print(abc[cows[high]]);
                high--;
            }
            counter++;
            if (counter % 80 == 0)
                out.println();

        }

        out.println(abc[cows[low]]);
        out.close();

    }

}
