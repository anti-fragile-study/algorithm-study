import java.util.*;

class Solution {

    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    int[][] map = new int[101][101];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int[] rec : rectangle) {
            createRectangle(rec[0] * 2, rec[1] * 2, rec[2] * 2, rec[3] * 2);
        }

        int answer = 0;
        int[][] isVisited = new int[101][101];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{characterX * 2, characterY * 2});
        isVisited[characterY * 2][characterY * 2] = 0;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            int c = isVisited[y][x];

            if (x == itemX * 2 && y == itemY * 2) {
                answer = c;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                if (xx < 0 || xx > 100 || yy < 0 || yy > 100 || isVisited[yy][xx] != 0 || map[yy][xx] <= 0) {
                    continue;
                }
                q.add(new int[]{xx, yy});
                isVisited[yy][xx] = c + 1;
            }
        }
        return answer / 2;
    }

    private void createRectangle(int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (x == x1 || x == x2 || y == y1 || y == y2) {
                    map[y][x]++;
                    continue;
                }
                map[y][x] -= 10;
            }
        }
    }
}
