import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        var map = new char[n][m];
        var isDown = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '.') {
                    continue;
                }
                int waterCount = 0;
                for (int k = 0; k < 4; k++) {
                    int yy = i + dy[k];
                    int xx = j + dx[k];
                    if (yy >= n || xx >= m || yy < 0 || xx < 0 || map[yy][xx] == '.') {
                        waterCount++;
                    }
                }
                if (waterCount >= 3) {
                    isDown[i][j] = true;
                }
            }
        }

        int x1 = 11, x2 = -1, y1 = 11, y2 = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isDown[i][j]) {
                    map[i][j] = '.';
                }
                if (map[i][j] == 'X') {
                    x1 = Math.min(x1, j);
                    x2 = Math.max(x2, j);
                    y1 = Math.min(y1, i);
                    y2 = Math.max(y2, i);
                }
            }
        }

        var sb = new StringBuilder();
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
