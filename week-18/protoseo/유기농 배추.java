import java.io.*;
import java.util.*;

public class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            final String[] split = br.readLine().split(" ");
            int m = Integer.parseInt(split[0]);
            int n = Integer.parseInt(split[1]);
            int k = Integer.parseInt(split[2]);
            int[][] map = new int[n][m];
            for (int i = 0; i < k; i++) {
                int[] position = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                map[position[1]][position[0]] = 1;
            }
            sb.append(find(map)).append('\n');
        }
        System.out.print(sb);
    }

    private static int find(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int result = 0;
        boolean[][] isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isVisited[i][j] && map[i][j] == 1) {
                    result++;
                    Deque<int[]> q = new ArrayDeque<>();
                    isVisited[i][j] = true;
                    q.add(new int[]{j, i});

                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0];
                        int y = p[1];

                        for (int k = 0; k < 4; k++) {
                            int xx = x + dx[k];
                            int yy = y + dy[k];

                            if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                                continue;
                            }
                            if (map[yy][xx] == 0 || isVisited[yy][xx]) {
                                continue;
                            }
                            isVisited[yy][xx] = true;
                            q.add(new int[]{xx, yy});
                        }
                    }
                }
            }
        }
        return result;
    }
}
