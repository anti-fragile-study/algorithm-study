import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int n;
    static int m;
    static List<Integer> records = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        map = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < n * m; i++) {
            for (int j = i + 1; j < n * m; j++) {
                int y1 = i / m;
                int x1 = i % m;
                int y2 = j / m;
                int x2 = j % m;
                if (map[y1][x1] == 0 && map[y2][x2] == 0) {
                    map[y1][x1] = 1;
                    map[y2][x2] = 1;
                    bfs();
                    map[y1][x1] = 0;
                    map[y2][x2] = 0;
                }
            }
        }

        records.sort(Comparator.reverseOrder());
        System.out.println((records.isEmpty()) ? 0 : records.get(0));
    }

    static void bfs() {
        int result = 0;
        boolean[][] isVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 2 || isVisited[i][j]) {
                    continue;
                }
                boolean isDied = true;
                Deque<int[]> q = new ArrayDeque<>();
                q.add(new int[]{j, i});
                Set<Integer> count = new HashSet<>();
                while (!q.isEmpty()) {
                    int[] p = q.poll();
                    int x = p[0];
                    int y = p[1];
                    count.add(y * n + x);
                    for (int k = 0; k < 4; k++) {
                        int xx = x + dx[k];
                        int yy = y + dy[k];
                        if (xx < 0 || yy < 0 || xx >= m || yy >= n || isVisited[yy][xx]) {
                            continue;
                        }
                        if (map[yy][xx] == 0) {
                            isDied = false;
                        }
                        if (map[yy][xx] == 2) {
                            q.add(new int[]{xx, yy});
                            isVisited[yy][xx] = true;
                        }
                    }
                }
                if (isDied) {
                    result += count.size();
                }
            }
        }
        records.add(result);
    }
}
