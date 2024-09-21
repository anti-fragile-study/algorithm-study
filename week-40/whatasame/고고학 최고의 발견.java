// 시계바늘: 상하좌우
// 인접한 시계도 돌아감
// 반시계도 되나? X

// 모든 시계 바늘이 12시를 가리키도록 최소한의 조작

// 돌리는 순서는 중요하지 않다. 횟수가 중요.

import java.util.*;

class Solution {

    // 제자리, 상하좌우
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    static int n;
    static int[][] original;
    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] clockHands) {
        // 원본 백업
        n = clockHands.length;
        original = new int[n][n];
        for (int i = 0; i < n; i++) {
            original[i] = Arrays.copyOf(clockHands[i], n);
        }

        backtrack(new int[n], 0);

        return answer;
    }

    // 첫 번째 줄에서 모든 경우의 수 찾기 - 전부 안 돌리기 ~ 다 3번 돌리기 = O(4^8)
    private void backtrack(int[] perm, int idx) {
        if (idx == n) {
            int[][] copy = new int[n][n];
            for (int i = 0; i < n; i++) {
                copy[i] = Arrays.copyOf(original[i], n);
            }

            // 돌리기
            int cost = rotate(copy, 0, toCommand(perm), 0);

            // 전부 0인지 확인 후 최솟값 업데이트 = O(8^2)
            for (int[] row : copy) {
                for (int val : row) {
                    if (val != 0) return;
                }
            }
            answer = Math.min(answer, cost);

            return;
        }

        // 중복 순열
        for (int cnt = 0; cnt < 4; cnt++) {
            perm[idx] = cnt;
            backtrack(perm, idx + 1);
        }
    }

    private int rotate(int[][] board, int x, int[] command, int cost) {
        if (x == n) {
            return cost;
        }

        for (int y = 0; y < n; y++) {
            for (int dir = 0; dir < dx.length; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                board[nx][ny] += command[y];
                board[nx][ny] %= 4;
            }
            cost += command[y];
        }

        return rotate(board, x + 1, toCommand(board[x]), cost);
    }

    private int[] toCommand(int[] row) {
        int[] command = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            command[i] = (4 - row[i]) % 4;
        }

        return command;
    }
}
