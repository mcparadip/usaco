/*
ID: oliver.6
LANG: JAVA
TASK: castle
*/

// USACO Training, Chapter 2.1
// Problem 3. The Castle
// ********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class castle {

    private static int N;
    private static int M;

    private static boolean[][][] modules;

    private static int[][] map;

    private static List<Room> rooms;

    private static class Room {

        int id;
        int size;
        int sx;
        int sy;
        Map<Integer, int[]> neighbors;

        public Room(int id, int sx, int sy) {
            this.id = id;
            this.sx = sx;
            this.sy = sy;
            this.size = 0;
            this.neighbors = new HashMap<>();
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("castle.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        modules = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 4; k++) {
                    modules[i][j][k] = v % 2 == 1;
                    v /= 2;
                }
            }
        }
        in.close();

        map = new int[N][M];
        rooms = new ArrayList<>();
        ArrayDeque<int[]> bfs = new ArrayDeque<>();

        for (int room = 1; ; room++) {

            startCheck:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        rooms.add(new Room(room, i, j));
                        bfs.add(new int[]{i, j});
                        break startCheck;
                    }
                }
            }

            if (bfs.isEmpty()) break;

            while (!bfs.isEmpty()) {

                int[] v = bfs.pop();
                int i = v[0];
                int j = v[1];

                if (map[i][j] != 0) continue;
                rooms.get(room - 1).size++;
                map[i][j] = room;

                if (j > 0) {
                    if (map[i][j - 1] == 0 && !modules[i][j][0]) {
                        bfs.push(new int[]{i, j - 1});
                    } else if (modules[i][j][0] && map[i][j - 1] != 0) {
                        if (rooms.get(room - 1).neighbors.containsKey(map[i][j - 1])) {
                            int[] c = rooms.get(room - 1).neighbors.get(map[i][j - 1]);
                            if (j < c[1] || (j == c[1] && i > c[0]) || (j == c[1] && i == c[0] && 3 <= c[2])) {
                                rooms.get(room - 1).neighbors.put(map[i][j - 1], new int[]{i, j, 0});
                                rooms.get(map[i][j - 1] - 1).neighbors.put(room, new int[]{i, j - 1, 2});
                            }
                        } else {
                            rooms.get(room - 1).neighbors.put(map[i][j - 1], new int[]{i, j, 0});
                            rooms.get(map[i][j - 1] - 1).neighbors.put(room, new int[]{i, j - 1, 2});
                        }
                    }
                }
                if (i > 0) {
                    if (map[i - 1][j] == 0 && !modules[i][j][1]) {
                        bfs.push(new int[]{i - 1, j});
                    } else if (modules[i][j][1] && map[i - 1][j] != 0) {
                        if (rooms.get(room - 1).neighbors.containsKey(map[i - 1][j])) {
                            int[] c = rooms.get(room - 1).neighbors.get(map[i - 1][j]);
                            if (j < c[1] || (j == c[1] && i > c[0]) || (j == c[1] && i == c[0] && 3 <= c[2])) {
                                rooms.get(room - 1).neighbors.put(map[i - 1][j], new int[]{i, j, 1});
                                rooms.get(map[i - 1][j] - 1).neighbors.put(room, new int[]{i - 1, j, 3});
                            }
                        } else {
                            rooms.get(room - 1).neighbors.put(map[i - 1][j], new int[]{i, j, 1});
                            rooms.get(map[i - 1][j] - 1).neighbors.put(room, new int[]{i - 1, j, 3});
                        }
                    }
                }
                if (j < M - 1) {
                    if (map[i][j + 1] == 0 && !modules[i][j][2]) {
                        bfs.push(new int[]{i, j + 1});
                    } else if (modules[i][j][2] && map[i][j + 1] != 0) {
                        if (rooms.get(room - 1).neighbors.containsKey(map[i][j + 1])) {
                            int[] c = rooms.get(room - 1).neighbors.get(map[i][j + 1]);
                            if (j < c[1] || (j == c[1] && i > c[0]) || (j == c[1] && i == c[0] && 3 <= c[2])) {
                                rooms.get(room - 1).neighbors.put(map[i][j + 1], new int[]{i, j, 2});
                                rooms.get(map[i][j + 1] - 1).neighbors.put(room, new int[]{i, j + 1, 0});
                            }
                        } else {
                            rooms.get(room - 1).neighbors.put(map[i][j + 1], new int[]{i, j, 2});
                            rooms.get(map[i][j + 1] - 1).neighbors.put(room, new int[]{i, j + 1, 0});
                        }
                    }
                }
                if (i < N - 1) {
                    if (map[i + 1][j] == 0 && !modules[i][j][3]) {
                        bfs.push(new int[]{i + 1, j});
                    } else if (modules[i][j][3] && map[i + 1][j] != 0) {
                        if (rooms.get(room - 1).neighbors.containsKey(map[i + 1][j])) {
                            int[] c = rooms.get(room - 1).neighbors.get(map[i + 1][j]);
                            if (j < c[1] || (j == c[1] && i > c[0]) || (j == c[1] && i == c[0] && 3 <= c[2])) {
                                rooms.get(room - 1).neighbors.put(map[i + 1][j], new int[]{i, j, 3});
                                rooms.get(map[i + 1][j] - 1).neighbors.put(room, new int[]{i + 1, j, 3});
                            }
                        } else {
                            rooms.get(room - 1).neighbors.put(map[i + 1][j], new int[]{i, j, 3});
                            rooms.get(map[i + 1][j] - 1).neighbors.put(room, new int[]{i + 1, j, 3});
                        }
                    }
                }

            }

        }

        PrintWriter out = new PrintWriter("castle.out");
        out.println(rooms.size());

        int maxSingle = 0;
        int maxDouble = 0;
        int[] wall = new int[3];
        for (Room room : rooms) {
            maxSingle = Math.max(maxSingle, room.size);

            for (int n : room.neighbors.keySet()) {
                if (n == room.id) continue;
                if (room.neighbors.get(n)[2] == 0 || room.neighbors.get(n)[2] == 3) continue;
                int size = rooms.get(n - 1).size + room.size;
                int[] wall1 = room.neighbors.get(n);
                int[] wall2 = rooms.get(n - 1).neighbors.get(room.id);
                if (size == maxDouble) {
                    maxDouble = size;
                    if (wall1[1] < wall2[1] || (wall1[1] == wall2[1] && wall1[0] > wall2[0])) {
                        if (wall1[1] < wall[1] || (wall1[1] == wall[1] && wall1[0] > wall[0])) {
                            wall = wall1;
                        }
                    } else {
                        if (!(wall[1] < wall2[1] || (wall[1] == wall2[1] && wall1[0] > wall2[0]))) {
                            wall = wall2;
                        }
                    }
                }
                if (size > maxDouble) {
                    maxDouble = size;
                    if (wall1[1] < wall2[1] || (wall1[1] == wall2[1] && wall1[0] > wall2[0])) {
                        wall = wall1;
                    } else {
                        wall = wall2;
                    }
                }
                if (size == maxDouble) {
                    maxDouble = size;
                    if (wall1[1] < wall2[1] || (wall1[1] == wall2[1] && wall1[0] > wall2[0])) {
                        if (wall1[1] < wall[1] || (wall1[1] == wall[1] && wall1[0] > wall[0])) {
                            wall = wall1;
                        }
                    } else {
                        if (!(wall[1] < wall2[1] || (wall[1] == wall2[1] && wall1[0] > wall2[0]))) {
                            wall = wall2;
                        }
                    }
                }
            }
        }

        out.println(maxSingle);
        out.println(maxDouble);
        String[] legend = new String[]{"W", "N", "E", "S"};
        out.println(wall[0] + 1 + " " + (wall[1] + 1) + " " + legend[wall[2]]);
        out.close();

    }

}