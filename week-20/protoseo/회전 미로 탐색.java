import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    static char[][][] map;
    static boolean[][][] isVisited;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[4][n * 4][n * 4];
        isVisited = new boolean[4][n * 4][n * 4];

        Pair start = null;
        for (int i = 0; i < n * 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < n * 4; j++) {
                map[0][i][j] = str.charAt(j);
                if (map[0][i][j] == 'S') {
                    start = new Pair(j, i, 0, 0);
                }
            }
        }
        convertRotateMap();

        Deque<Pair> q = new ArrayDeque<>();
        q.add(start);
        isVisited[0][start.y][start.x] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            int d = p.d;
            int c = p.c;

            if (map[d][y][x] == 'E') {
                System.out.println(c);
                return;
            }

            for (int i = 0; i < 5; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                int dd = (isSameSection(x, y, xx, yy)) ? d : 0;
                if (xx < 0 || yy < 0 || xx >= (4 * n) || yy >= (4 * n)) {
                    continue;
                }
                Pair rotate = rotate(new Pair(xx, yy, dd, c + 1));
                if (map[rotate.d][rotate.y][rotate.x] == '#' || isVisited[rotate.d][rotate.y][rotate.x]) {
                    continue;
                }
                isVisited[rotate.d][rotate.y][rotate.x] = true;
                q.add(rotate);
            }
        }
        System.out.println(-1);
    }

    private static boolean isSameSection(int x, int y, int xx, int yy) {
        return x / 4 == xx / 4 && y / 4 == yy / 4;
    }

    private static void convertRotateMap() {
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < n * 4; j += 4) {
                for (int k = 0; k < n * 4; k += 4) {
                    rotate(k, j, i);
                }
            }
        }
    }

    private static void rotate(int x, int y, int d) {
        char[][] temp = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[j][3 - i] = map[d - 1][i + y][j + x];
            }
        }
        for (int i = y; i < y + 4; i++) {
            System.arraycopy(temp[i - y], 0, map[d][i], x, 4);
        }
    }

    static Pair rotate(Pair p) {
        int dx = p.x / 4;
        int dy = p.y / 4;
        int xx = (3 - (p.y - (4 * dy))) + (4 * dx);
        int yy = (p.x - (4 * dx)) + (4 * dy);
        return new Pair(xx, yy, (p.d + 1) % 4, p.c);
    }
}

class Pair {
    int x;
    int y;
    int d;
    int c;

    public Pair(int x, int y, int d, int c) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.c = c;
    }
}
