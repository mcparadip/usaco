import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class horizon {

    private static class TreeMultiset<E> {

        TreeMap<E, Integer> count;
        private int size;

        public TreeMultiset() {

            count = new TreeMap<>();
            size = 0;

        }

        public boolean add(E e) {

            if (count.containsKey(e)) {
                count.put(e, count.get(e) + 1);
            } else {
                count.put(e, 1);
            }

            size++;

            return true;

        }

        public boolean removeFirst(E e) {

            if (count.containsKey(e)) {
                count.put(e, count.get(e) - 1);
                if (count.get(e) == 0) {
                    count.remove(e);
                }
                size--;
                return true;
            }

            return false;

        }

        public E first() {

            return count.firstKey();

        }

        public E last() {

            return count.lastKey();

        }

        public int size() {

            return size;

        }

        public String toString() {

            return count.toString();

        }

    }

    private static class Endpoint implements Comparable<Endpoint> {

        long pos;
        long height;
        boolean start;

        public Endpoint(long pos, long height, boolean start) {

            this.pos = pos;
            this.height = height;
            this.start = start;

        }

        public int compareTo(Endpoint other) {

            return this.pos != other.pos ? Long.compare(this.pos, other.pos) : -1;

        }

    }

    private static int N;

    private static Set<Endpoint> endpoints;

    private static Set<Long> skyline;

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader in = new BufferedReader(new FileReader("horizon.in"));

        N = Integer.parseInt(in.readLine());

        endpoints = new TreeSet<>();

        skyline = new TreeSet<>();

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            long h = Integer.parseInt(st.nextToken());

            endpoints.add(new Endpoint(a, h, true));
            endpoints.add(new Endpoint(b, h, false));

            skyline.add(a);
            skyline.add(b);

        }

        long result = 0;
        int j = 0;

        Long[] sky = skyline.toArray(new Long[N]);
        Endpoint[] ends = endpoints.toArray(new Endpoint[N*2]);

        TreeMultiset<Long> current = new TreeMultiset<>();

        for (int i = 0; i < sky.length - 1; i++) {

            long a = sky[i];
            long b = sky[i+1];

            for (;;) {

                if (ends[j].pos >= a && ends[j].pos < b) {

                    if (ends[j].start) {
                        current.add(ends[j].height);
                    } else {
                        current.removeFirst(ends[j].height);
                    }

                    j++;

                } else {
                    break;
                }

            }

            if (current.count.size() > 0)
                result += (b - a) * current.last();

        }

        PrintWriter out = new PrintWriter("horizon.out");
        out.println(result);
        out.close();

    }

}
