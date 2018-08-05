// USACO 2015 December, Gold
// Problem 3. Bessie's Dream
// **x*!*!!**!!!!!!*

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class dream {

    private static int N;
    private static int M;

    private static PData[][] map;

    private static class PQueue {

        int x;
        int y;
        int st;
        int m;

        public PQueue(int x, int y, int st, int m) {
            this.x = x;
            this.y = y;
            this.st = st;
            this.m = m;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof PQueue) {
                PQueue q = (PQueue) o;
                if (this.x == q.x && this.y == q.y && this.st == q.st && this.m == q.m) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + st + ", " + m + ")";
        }

    }

    private static class PData {

        int type;
        int moves;
        int moveso;

        public PData(int type, int moves, int moveso) {
            this.type = type;
            this.moves = moves;
            this.moveso = moveso;
        }

        public int move(int moves, int state) {

            if (type == 0)
                return -1;

            if (type == 1) {
                if (moves < this.moves) {
                    this.moves = moves;
                    return 0;
                }
                if (state == 1 && moves < this.moveso) {
                    this.moveso = moves;
                    return 0;
                }
                return -1;
            }

            if (type == 2) {
                if (moves < this.moves) {
                    this.moves = moves;
                    return 1;
                }
                return -1;
            }

            if (type == 3) {
                if (state == 1) {
                    if (moves < this.moves) {
                        this.moves = moves;
                        return 0;
                    }
                }
                return -1;
            }

            if (type == 4) {
                return 2;
            }

            return -2;

        }

        public String toString() {
            return Integer.toString(type);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("dream.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new PData[N][M];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(in.readLine());

            for (int j = 0; j < M; j++) {

                map[i][j] = new PData(Integer.parseInt(st.nextToken()), Integer.MAX_VALUE, Integer.MAX_VALUE);

            }

        }

        Deque<PQueue> q = new ArrayDeque<>();
        q.add(new PQueue(0, 0, 0, 0));

        while (!q.isEmpty()) {

            //System.out.println(q);

            PQueue pq = q.pop();

            int x = pq.x;
            int y = pq.y;
            int state = pq.st;
            int mm = pq.m;

            /*System.out.println("orig:");
            System.out.println(x);
            System.out.println(y);
            System.out.println(state);
            System.out.println(mm);
            System.out.println();*/

            int[][] n = {
                    {x+1, y},
                    {x-1, y},
                    {x, y+1},
                    {x, y-1}
            };

            for (int i = 0; i < 4; i++) {

                int[] v = n[i];

                int m = mm;

                int vx = v[0];
                int vy = v[1];

                if (vx < 0 || vx >= N || vy < 0 || vy >= M)
                    continue;

                int stt = state;

                m += 1;

                int move = map[vx][vy].move(m, stt);

                while (move == 2) {

                    stt = 0;

                    if (i == 0) {
                        vx += 1;
                        move = map[vx][vy].move(m, stt);
                        if (vx >= N || move == -1) {
                            vx -= 1;
                            break;
                        }
                    } else if (i == 1) {
                        vx -= 1;
                        move = map[vx][vy].move(m, stt);
                        if (vx < 0 || move == -1) {
                            vx += 1;
                            break;
                        }
                    } else if (i == 2) {
                        vy += 1;
                        move = map[vx][vy].move(m, stt);
                        if (vy >= M || move == -1) {
                            vy -= 1;
                            break;
                        }
                    } else {
                        vy -= 1;
                        move = map[vx][vy].move(m, stt);
                        if (vy < 0 || move == -1) {
                            vy += 1;
                            break;
                        }
                    }

                    m += 1;

                }


                if (move == -1) {
                    continue;
                }

                if (move == 1) {
                    stt = 1;
                }

                PQueue pqq = new PQueue(vx, vy, stt, m);

                if (!q.contains(pqq)) {
                    q.add(pqq);
                }

            }

        }

        PrintWriter out = new PrintWriter("dream.out");
        if (map[N-1][M-1].moves == Integer.MAX_VALUE) out.println(-1);
        else out.println(map[N-1][M-1].moves);
        out.close();

    }

}
