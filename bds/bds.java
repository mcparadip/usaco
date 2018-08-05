import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// USACO 2006 February, Silver
// Problem 3. Backward Digit Sums
// **********

public class bds {

    private static int[][] pascal = {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}, {1, 5, 10, 10, 5, 1}, {1, 6, 15, 20, 15, 6, 1}, {1, 7, 21, 35, 35, 21, 7, 1}, {1, 8, 28, 56, 70, 56, 28, 8, 1}, {1, 9, 36, 84, 126, 126, 84, 36, 9, 1}};
    private static int N;
    private static int total;
    private static boolean done = false;

    private static void permute(int[] nums) throws IOException {
        List<Integer> result = new ArrayList<>();
        PrintWriter out = new PrintWriter("bds.out");
        dfs(nums, result, out);
        out.close();
    }

    private static void dfs(int[] nums, List<Integer> result, PrintWriter out) {
        if (done == true)
            return;
        if (nums.length == result.size()) {

            int tot = 0;

            for (int j = 0; j < N; j++) {

                tot += result.get(j) * pascal[N-1][j];

            }

            if (tot == total) {
                for (int ii : result) {
                    out.print(ii);
                    out.print(" ");
                }
                out.println();
                done = true;
                return;
            }

        }
        for (int i=0; i<nums.length; i++) {
            if (!result.contains(nums[i])) {
                result.add(nums[i]);
                dfs(nums, result, out);
                result.remove(result.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("bds.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        total = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }

        permute(arr);

    }

}
