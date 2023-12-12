class Solution {
    private static final int INF = 700000000;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                dist[i][j] = INF;
            }
        }
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            dist[c - 1][d - 1] = dist[d - 1][c - 1] = f;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] <= dist[i][k] + dist[k][j]) {
                        continue;
                    }
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        int answer = dist[s - 1][a - 1] + dist[s - 1][b - 1];
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, dist[s - 1][i] + dist[i][a - 1] + dist[i][b - 1]);
        }
        return answer;
    }
}
