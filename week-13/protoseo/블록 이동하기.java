import java.util.*;

class Solution {

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    boolean[][][] isVisited;
    int n;

    public int solution(int[][] board) {
        n = board.length;
        isVisited = new boolean[n][n][2];
        Deque<Robot> q = new ArrayDeque<>();
        q.add(new Robot(0, 0, 1, 0, 0));
        isVisited[0][0][0] = isVisited[0][1][0] = true;

        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Robot now = q.poll();
            int x1 = now.x1;
            int y1 = now.y1;
            int x2 = now.x2;
            int y2 = now.y2;
            int c = now.c;
            int t = now.getType();

            if ((x1 == n - 1 && y1 == n - 1) || (x2 == n - 1 && y2 == n - 1)) {
                answer = Math.min(c, answer);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int xx1 = x1 + dx[i];
                int yy1 = y1 + dy[i];
                int xx2 = x2 + dx[i];
                int yy2 = y2 + dy[i];

                if (!canVisit(xx1, yy1) || !canVisit(xx2, yy2)) {
                    continue;
                }
                if (isVisited[yy1][xx1][t] && isVisited[yy2][xx2][t]) {
                    continue;
                }
                if (board[yy1][xx1] == 1 || board[yy2][xx2] == 1) {
                   	continue; 
                }

                isVisited[yy1][xx1][t] = isVisited[yy2][xx2][t] = true;
                q.add(new Robot(xx1, yy1, xx2, yy2, c + 1));
            }
            if (canRotateRight(now, board)) {
                if (t == 0 && !(isVisited[y2][x2][1] && isVisited[y2 + 1][x2][1])) {
                    isVisited[y2][x2][1] = isVisited[y2 + 1][x2][1] = true;
                    q.add(new Robot(x2, y2, x2, y2 + 1, c + 1));
                }
                if (t == 0 && !(isVisited[y1][x1][1] && isVisited[y1 + 1][x1][1])) {
                    isVisited[y1][x1][1] = isVisited[y1 + 1][x1][1] = true;
                    q.add(new Robot(x1, y1, x1, y1 + 1, c + 1));
                }
                if (t == 1 && !(isVisited[y1][x1][0] && isVisited[y1][x1 + 1][0])) {
                    isVisited[y1][x1][0] = isVisited[y1][x1 + 1][0] = true;
                    q.add(new Robot(x1, y1, x1 + 1, y1, c + 1));
                }
                if (t == 1 && !(isVisited[y2][x2][0] && isVisited[y2][x2 + 1][0])) {
                    isVisited[y2][x2][0] = isVisited[y2][x2 + 1][0] = true;
                    q.add(new Robot(x2, y2, x2 + 1, y2, c + 1));
                }
            }
            if (canRotateLeft(now, board)) {
                if (t == 0 && !(isVisited[y2][x2][1] && isVisited[y2 - 1][x2][1])) {
                    isVisited[y2][x2][1] = isVisited[y2 - 1][x2][1] = true;
                    q.add(new Robot(x2, y2 - 1, x2, y2, c + 1));
                }
                if (t == 0 && !(isVisited[y1][x1][1] && isVisited[y1 - 1][x1][1])) {
                    isVisited[y1][x1][1] = isVisited[y1 - 1][x1][1] = true;
                    q.add(new Robot(x1, y1 - 1, x1, y1, c + 1));
                }
                if (t == 1 && !(isVisited[y1][x1][0] && isVisited[y1][x1 - 1][0])) {
                    isVisited[y1][x1][0] = isVisited[y1][x1 - 1][0] = true;
                    q.add(new Robot(x1 - 1, y1, x1, y1, c + 1));
                }
                if (t == 1 && !(isVisited[y2][x2][0] && isVisited[y2][x2 - 1][0])) {
                    isVisited[y2][x2][0] = isVisited[y2][x2 - 1][0] = true;
                    q.add(new Robot(x2 - 1, y2, x2, y2, c + 1));
                }
            }
        }
        return answer;
    }

    private boolean canVisit(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private boolean canRotateRight(Robot r, int[][] board) {
        if (r.getType() == 1 && r.x1 + 1 < n) {
            return board[r.y1][r.x1 + 1] == 0 && board[r.y2][r.x2 + 1] == 0;
        } else if (r.getType() == 0 && r.y1 + 1 < n) {
            return board[r.y1 + 1][r.x1] == 0 && board[r.y1 + 1][r.x2] == 0;
        }
        return false;
    }

    private boolean canRotateLeft(Robot r, int[][] board) {
        if (r.getType() == 1 && r.x1 > 0) {
            return board[r.y1][r.x1 - 1] == 0 && board[r.y2][r.x2 - 1] == 0;
        } else if (r.getType() == 0 && r.y1 > 0) {
            return board[r.y1 - 1][r.x1] == 0 && board[r.y1 - 1][r.x2] == 0;
        }
        return false;
    }
}

class Robot {
    int x1;
    int y1;
    int x2;
    int y2;
    int c;

    public Robot(int x1, int y1, int x2, int y2, int c) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.c = c;
    }

    public int getType() {
        if (this.y1 == this.y2) {
            return 0;
        }
        return 1;
    }
}
