// A-Star Platinum, Week 6
// Problem 2. Shaping Regions
// **********

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.io.IOException;

public class rect1 {

    private static int A;
    private static int B;
    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[A][B];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int llx = Integer.parseInt(st.nextToken());
            int lly = Integer.parseInt(st.nextToken());
            int urx = Integer.parseInt(st.nextToken());
            int ury = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken()); color = color == 1 ? 0 : color;
            for (int j = llx; j < urx; j++) {
                for (int k = lly; k < ury; k++) {
                    map[j][k] = color;
                }
            }
        }
        in.close();

        int res = 0;
        Map<Integer, Integer> colors = new TreeMap<>();
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                if (!colors.containsKey(map[i][j])) colors.put(map[i][j], 0);
                colors.put(map[i][j], colors.get(map[i][j]) + 1);
            }
        }

        for (int key : colors.keySet()) {
            System.out.println((key == 0 ? 1 : key) + " " + colors.get(key));
        }

    }

}