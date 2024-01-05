class Solution {
    int[][] isVisited;
    int answer;
    int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] dy = {1, 0, -1, 0, -1, 1, -1, 1};

    public int solution(int n) {
        isVisited = new int[n][n];
        find(0, n);
        return answer;
    }

    private void find(int y, int n) {
        if (y == n) {
            answer++;
            return;
        }
        for (int x = 0; x < n; x++) {
            if (isVisited[y][x] > 0) {
                continue;
            }
            visit(y, x, n);
            find(y + 1, n);
            unvisit(y, x, n);
        }
    }

    private void visit(int y, int x, int n) {
        isVisited[y][x]++;
        for (int k = 0; k < 8; k++) {
            int xx = x + dx[k];
            int yy = y + dy[k];

            while (0 <= xx && xx < n && 0 <= yy && yy < n) {
                isVisited[yy][xx]++;
                xx += dx[k];
                yy += dy[k];
            }
        }
    }

    private void unvisit(int y, int x, int n) {
        isVisited[y][x]--;
        for (int k = 0; k < 8; k++) {
            int xx = x + dx[k];
            int yy = y + dy[k];

            while (0 <= xx && xx < n && 0 <= yy && yy < n) {
                isVisited[yy][xx]--;
                xx += dx[k];
                yy += dy[k];
            }
        }
    }
}