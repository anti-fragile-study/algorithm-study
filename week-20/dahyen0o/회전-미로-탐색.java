import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int[] dr = { 0, 0, -1, 0, +1 };
    static final int[] dc = { 0, -1, 0, +1, 0 };

    static int K;
    static int[] start = new int[2];

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        
        K = Integer.parseInt(br.readLine());
        // [rK][cK][dir][r][c]
        // R = rK + r, C = cK + c
        final char[][][][][] mazes = new char[K][K][4][4][4];
        for (int R = 0; R < 4 * K; R++) {
            final char[] row = br.readLine().toCharArray();
            for (int C = 0; C < 4 * K; C++) {
                mazes[R / 4][C / 4][0][R % 4][C % 4] = row[C];
                if (row[C] == 'S') {
                    mazes[R / 4][C / 4][0][R % 4][C % 4] = '.';
                    start[0] = R;
                    start[1] = C;
                }
            }
        }

        setRotationInfo(mazes);

        sb.append(minTime(mazes)).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static int minTime(final char[][][][][] mazes) {
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(start[0], start[1], 0, 0));
        
        final boolean[][][] visited = new boolean[4 * K][4 * K][4]; // [R][C][dir]
        visited[start[0]][start[1]][0] = true;

        while (!queue.isEmpty()) {
            final Point curr = queue.poll();

            if (getMazeVal(mazes, curr.R(), curr.C(), curr.dir) == 'E') {
                return curr.time;
            }

            for (int idx = 0; idx < dr.length; idx++) {
                // 1. 이동하거나 가만히 있는다
                final int R = curr.R() + dr[idx];
                final int C = curr.C() + dc[idx];
                final Point next = new Point(R, C, curr.dir, curr.time + 1);

                if ((curr.kR != next.kR) || (curr.kC != next.kC)) { // 구역이 달라짐
                    next.resetDir();
                }
                if (!inBound(R, C) || getMazeVal(mazes, R, C, next.dir) == '#') {
                    continue;
                }
                
                // 2. 현재 구역이 회전한다
                next.rotate();

                if (visited[next.R()][next.C()][next.dir]) {
                    continue;
                }

                queue.add(next);
                visited[next.R()][next.C()][next.dir] = true;
            }
        }
        return -1;
    }

    private static boolean inBound(int R, int C) {
        return R >= 0 && C >= 0 && R < 4 * K && C < 4 * K;
    }

    private static char getMazeVal(char[][][][][] mazes, int R, int C, int dir) {
        return mazes[R / 4][C / 4][dir][R % 4][C % 4];
    }

    private static void setRotationInfo(final char[][][][][] mazes) {
        // 시계 방향으로 회전한 내용 저장
        for (int rK = 0; rK < K; rK++) {
            for (int cK = 0; cK < K; cK++) {
                for (int dir = 0; dir < 3; dir++) {
                    mazes[rK][cK][dir + 1] = rotate(mazes[rK][cK][dir]);
                }
            }
        }
    }

    private static char[][] rotate(final char[][] arr) {
        final char[][] result = new char[arr.length][arr.length];
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr.length; c++) {
                result[r][c] = arr[arr.length - 1 - c][r];
            }
        }
        return result;
    }

    static protected class Point {
    
        int kR, kC;
        int r, c;
        int dir;
        int time;

        public Point(int R, int C, int dir, int time) {
            if (dir < 0 || dir >= 4) {
                throw new IllegalArgumentException("dir 값이 이상함");
            }
            this.kR = R / 4;
            this.kC = C / 4;
            this.r = R % 4;
            this.c = C % 4;
            this.dir = dir;
            this.time = time;
        }

        public int R() {
            return kR * 4 + r;
        }

        public int C() {
            return kC * 4 + c;
        }

        public void rotate() {
            this.dir = (dir + 1) % 4;

            int oldR = this.r;
            int oldC = this.c;
            this.r = oldC;
            this.c = 4 - 1 - oldR;
        }

        public void resetDir() {
            this.dir = 0;
        }
    }
}
