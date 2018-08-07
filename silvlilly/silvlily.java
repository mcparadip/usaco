// USACO 2007 February, Silver
// Problem 2. Silver Lilypad Pond
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class silvlily {

    private static class SPointData {

        long count;
        int newpads;
        int jumps;

        public SPointData(long count, int newpads, int jumps) {

            this.count = count;
            this.newpads = newpads;
            this.jumps = jumps;

        }

        public boolean adddata(SPointData newdata) {

            if (newdata.newpads == this.newpads && newdata.jumps == this.jumps) {

                this.count += newdata.count;

            } else if (newdata.newpads < this.newpads) {

                this.count = newdata.count;
                this.newpads = newdata.newpads;
                this.jumps = newdata.jumps;

            } else if (newdata.newpads == this.newpads && newdata.jumps < this.jumps) {

                this.count = newdata.count;
                this.jumps = newdata.jumps;

            } else {

                return false;

            }

            return true;

        }

        @Override
        public String toString() {

            return "{newpads=" + newpads + ", count=" + count + ", jumps=" + jumps + "}";

        }

    }

    private static class SPoint {

        int x;
        int y;

        public SPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SPoint) {
                SPoint pnt = (SPoint) obj;
                if (pnt.x == this.x && pnt.y == this.y) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

    }

    private static int M;
    private static int N;

    private static int[][] map;

    private static int[] dx = {2,  2, -2, -2, 1, -1,  1, -1};
    private static int[] dy = {1, -1,  1, -1, 2,  2, -2, -2};

    private static List<SPoint> getJumps(SPoint pos) {

        List<SPoint> result = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            int nx = dx[i] + pos.x;
            int ny = dy[i] + pos.y;

            if (nx >= 0 && nx <= N-1 && ny >= 0 && ny <= M-1 && map[nx][ny] != 2) {
                result.add(new SPoint(nx, ny));
            }

        }

        return result;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("silvlily.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Map<SPoint, SPointData> bfsmap = new HashMap<>();
        Set<SPoint> bfsset = new HashSet<>();
        SPoint end = null;

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(in.readLine());

            for (int j = 0; j < N; j++) {

                map[j][i] = Integer.parseInt(st.nextToken());
                if (map[j][i] == 3) {
                    bfsmap.put(new SPoint(j, i), new SPointData(1, 0, 0));
                    bfsset.add(new SPoint(j, i));
                } else if (map[j][i] == 4) {
                    end = new SPoint(j, i);
                }

            }

        }

        int t = 0;
        int x = 0;
        boolean done = false;

        PrintWriter out = new PrintWriter("silvlily.out");

        for (;;) {

            if (bfsset.size() == 0) {
                if (bfsmap.containsKey(end)) {
                    SPointData enddata = bfsmap.get(end);
                    out.println(enddata.newpads);
                    out.println(enddata.jumps);
                    out.println(enddata.count);
                } else {
                    out.println(-1);
                }
                out.close();
                return;
            }

            for (Object obj : bfsset.toArray()) {

                SPoint pnt = (SPoint) obj;
                bfsset.remove(pnt);

                for (SPoint n : getJumps(pnt)) {

                    SPointData pntdata = bfsmap.get(pnt);
                    SPointData newdata = new SPointData(pntdata.count, pntdata.newpads, pntdata.jumps);
                    if (map[n.x][n.y] == 0) {
                        newdata.newpads++;
                    }
                    newdata.jumps++;

                    if (bfsmap.containsKey(n)) {
                        if (bfsmap.get(n).adddata(newdata)) {
                            bfsset.add(n);
                        }
                    } else {
                        bfsmap.put(n, newdata);
                        bfsset.add(n);
                    }

                }


            }

        }



    }


}
