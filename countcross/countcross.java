// USACO 2017 February, Silver
// Problem 3. Why Did the Cow Cross the Road III
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class countcross {

    private static class Position {

        public final int x;
        public final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public int hashCode() {
            return this.x * 31 + this.y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                Position pos = (Position) obj;
                if (pos.x == this.x && pos.y == this.y) return true;
            }
            return false;
        }

    }

    private static class Road {

        public final Position pos1;
        public final Position pos2;

        public Road(Position pos1, Position pos2) {
            if (pos1.x == pos2.x) {
                if (pos1.y < pos2.y) {
                    this.pos1 = pos1;
                    this.pos2 = pos2;
                } else {
                    this.pos1 = pos2;
                    this.pos2 = pos1;
                }
            } else if (pos1.x < pos2.x) {
                this.pos1 = pos1;
                this.pos2 = pos2;
            } else {
                this.pos1 = pos2;
                this.pos2 = pos1;
            }
        }

        public String toString() {
            return pos1.toString() + " - " + pos2.toString();
        }

        @Override
        public int hashCode() {
            return this.pos1.hashCode() * 31 + this.pos2.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Road) {
                Road road = (Road) obj;
                if (road.pos1.equals(this.pos1) && road.pos2.equals(this.pos2)) return true;
            }
            return false;
        }

    }

    public static List<Position> cows = new ArrayList<>();
    public static Set<Road> roads = new HashSet<>();
    public static int N, K, R;

    public static Set<Position> floodfill(Position pos, Set<Position> visited) {

        visited.add(pos);

        Road up = null;
        Road down = null;
        Road left = null;
        Road right = null;

        Position pup = null;
        Position pdown = null;
        Position pleft = null;
        Position pright = null;

        if (pos.y > 0) {
            pup = new Position(
                    pos.x,
                    pos.y - 1
            );
            if (!visited.contains(pup)) {
                up = new Road(
                        pos,
                        pup
                );
            }
        }
        if (pos.y < N - 1) {
            pdown = new Position(
                    pos.x,
                    pos.y + 1
            );
            if (!visited.contains(pdown)) {
                down = new Road(
                        pos,
                        pdown
                );
            }
        }
        if (pos.x > 0) {
            pleft = new Position(
                    pos.x - 1,
                    pos.y
            );
            if (!visited.contains(pleft)) {
                left = new Road(
                        pos,
                        pleft
                );
            }
        }
        if (pos.x < N - 1) {
            pright = new Position(
                    pos.x + 1,
                    pos.y
            );
            if (!visited.contains(pright)) {
                right = new Road(
                        pos,
                        pright
                );
            }
        }

        if (up != null && !roads.contains(up)) {
            visited = floodfill(pup, visited);
        }
        if (down != null && !roads.contains(down)) {
            visited = floodfill(pdown, visited);
        }
        if (left != null && !roads.contains(left)) {
            visited = floodfill(pleft, visited);
        }
        if (right != null && !roads.contains(right)) {
            visited = floodfill(pright, visited);
        }


        return visited;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("countcross.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(in.readLine());
            roads.add(
                    new Road(
                            new Position(
                                    Integer.parseInt(st.nextToken()) - 1,
                                    Integer.parseInt(st.nextToken()) - 1
                            ),
                            new Position(
                                    Integer.parseInt(st.nextToken()) - 1,
                                    Integer.parseInt(st.nextToken()) - 1
                            )
                    )
            );
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            cows.add(
                    new Position(
                            Integer.parseInt(st.nextToken()) - 1,
                            Integer.parseInt(st.nextToken()) - 1
                    )
            );
        }

        int result = 0;

        for (int i = 0; i < K; i++) {
            Set<Position> visited = floodfill(cows.get(i), new HashSet<>());
            for (int j = i + 1; j < K; j++) {
                if (!visited.contains(cows.get(j))) {
                    result += 1;
                }
            }
        }

        PrintWriter writer = new PrintWriter("countcross.out");
        writer.println(result);
        writer.close();

    }

}
