import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int SIZE = 5;
    static final Point[] points = new Point[SIZE * SIZE + 1];
    static final int[] rowCounts = new int[SIZE];
    static final int[] colCounts = new int[SIZE];
    static final int[] xCounts = new int[2];

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        for (int r = 0; r < SIZE; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < SIZE; c++) {
                final int val = Integer.parseInt(st.nextToken());
                points[val] = new Point(r, c);
            }
        }

        final int[] calls = new int[SIZE * SIZE];
        for (int r = 0; r < SIZE; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < SIZE; c++) {
                calls[r * SIZE + c] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(sb.append(play(calls)).append("\n").toString());
        bw.flush();
    }

    private static int play(final int[] calls) {
        int bingo = 0;
        for (int c = 0; c < calls.length; c++) {
            final int count = c + 1;
            final Point point = points[calls[c]];

            if (++rowCounts[point.r] == SIZE && ++bingo == 3) {
                return count;
            }
            if (++colCounts[point.c] == SIZE && ++bingo == 3) {
                return count;
            }
            if (point.r == point.c && ++xCounts[0] == SIZE && ++bingo == 3) {
                return count;
            }
            if (point.r + point.c == SIZE - 1 && ++xCounts[1] == SIZE && ++bingo == 3) {
                return count;
            }
        }
        throw new IllegalArgumentException("NOT FOUND ANSWER");
    }

    private static class Point {

        final int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
