class Solution {
    int n;
    int m;
    int[][] preSum;
    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        preSum = new int[n + 1][m + 1];

        for (int[] detail : skill) {
            int type = detail[0];
            int r1 = detail[1];
            int c1 = detail[2];
            int r2 = detail[3];
            int c2 = detail[4];
            int degree = detail[5];

            if (type == 1) {
                preSum[r1][c1] -= degree;
                preSum[r1][c2 + 1] += degree;
                preSum[r2 + 1][c1] += degree;
                preSum[r2 + 1][c2 + 1] -= degree;
            } else if (type == 2) {
                preSum[r1][c1] += degree;
                preSum[r1][c2 + 1] -= degree;
                preSum[r2 + 1][c1] -= degree;
                preSum[r2 + 1][c2 + 1] += degree;
            }
        }

        for (int i = 0; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                preSum[j][i] += preSum[j - 1][i];
            }
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                preSum[i][j] += preSum[i][j - 1];
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + preSum[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
