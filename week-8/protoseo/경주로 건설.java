import java.util.*;

class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] isVisited = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(isVisited[i][j], Integer.MAX_VALUE);
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0});
        q.add(new int[]{0, 0, 1});
        for (int i = 0; i < 4; i++) {
            isVisited[0][0][i] = 0;
        }

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            int d = p[2];

            if (x == n && y == n) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (Math.abs(d - i) == 2) {
                    continue;
                }
                int xx = x + dx[i];
                int yy = y + dy[i];
                int cost = (i == d) ? 100 : 600;

                if (xx < 0 || yy < 0 || xx >= n || yy >= n) {
                    continue;
                }
                if (board[yy][xx] == 1 || isVisited[yy][xx][i] < isVisited[y][x][d] + cost) {
                    continue;
                }
                q.add(new int[]{xx, yy, i});
                isVisited[yy][xx][i] = isVisited[y][x][d] + cost;
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, isVisited[n - 1][n - 1][i]);
        }
        return answer;
    }
}
