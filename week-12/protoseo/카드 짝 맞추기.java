import java.util.*;

class Solution {

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    int[][] map;
    int[][][] points = new int[7][2][2];
    boolean[] hasPoint = new boolean[7];
    int answer = Integer.MAX_VALUE;
    int number = 0;

    public int solution(int[][] board, int r, int c) {
        map = board;
        for (int i = 0; i < 4; i++) {
            for (int j = 0 ; j < 4; j++) {
                if (board[i][j] != 0 && !hasPoint[board[i][j]]) {
                    hasPoint[board[i][j]] = true;
                    number++;
                    points[board[i][j]][0] = new int[]{j, i};
                } else if (board[i][j] != 0 && hasPoint[board[i][j]]) {
                    points[board[i][j]][1] = new int[]{j, i};
                }
            }
        }

        find(0, 0, new int[]{c, r});
        return answer;
    }

    void find(int idx, int cnt, int[] start) {
        if (idx == number) {
            answer = Math.min(cnt, answer);
            return;
        }

        for (int i = 1; i < 7; i++) {
            if (!hasPoint[i]) continue;

            int dist1 = bfs(start, points[i][0]) + bfs(points[i][0], points[i][1]) + 2;
            int dist2 = bfs(start, points[i][1]) + bfs(points[i][1], points[i][0]) + 2;

            hasPoint[i] = false;
            map[points[i][0][0]][points[i][0][1]] = 0;
            map[points[i][1][0]][points[i][1][1]] = 0;

            int[] nextStart = (dist1 < dist2) ? points[i][1] : points[i][0];
            int nextDist = (dist1 < dist2) ? dist1 : dist2;
            find(idx + 1, cnt + nextDist, nextStart);

            map[points[i][0][0]][points[i][0][1]] = i;
            map[points[i][1][0]][points[i][1][1]] = i;
            hasPoint[i] = true;
        }
    }

    int bfs(int[] start, int[] target) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[4][4];
        q.add(new int[]{start[0], start[1], 0});
        isVisited[start[1]][start[0]] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int c = now[2];

            if (x == target[0] && y == target[1]) {
                return c;
            }

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                if (!canVisit(xx, yy) || isVisited[yy][xx]) {
                    continue;
                }
                isVisited[yy][xx] = true;
                q.add(new int[]{xx, yy, c + 1});
            }
            for (int i = 0; i < 4; i++) {
                int xx = x;
                int yy = y;
                while (canVisit(xx + dx[i], yy + dy[i])) {
                    xx += dx[i];
                    yy += dy[i];
                    if (map[yy][xx] != 0) {
                        break;
                    }
                }
                if (isVisited[yy][xx]) {
                    continue;
                }
                isVisited[yy][xx] = true;
                q.add(new int[]{xx, yy, c + 1});
            }
        }
        return -1;
    }

    boolean canVisit(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}
