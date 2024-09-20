import java.util.*;

class Solution {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 원본 값
    static int originalRow, originalCol;
    static int[][] originalBoard = new int[4][4];

    // 백 트래킹
    static int[] permutation = new int[6];
    static boolean[] selected = new boolean[7];

    static Pair[][] cardPositions = new Pair[7][2];
    static int cardCount = 0;
    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] board, int startRow, int startCol) {
        // 원본 백업
        originalRow = startRow;
        originalCol = startCol;
        for (int i = 0; i < 4; i++) {
            originalBoard[i] = Arrays.copyOf(board[i], board.length);
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                cardPositions[i][j] = new Pair(-1, -1);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    continue;
                }

                if (cardPositions[board[i][j]][0].x == -1) {
                    cardPositions[board[i][j]][0] = new Pair(i, j);
                    cardCount++;
                } else {
                    cardPositions[board[i][j]][1] = new Pair(i, j);
                }
            }
        }

        solve(0);

        return answer + 2 * cardCount;
    }

    static void solve(int idx) {
        if (idx == cardCount) {
            int[][] copyBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                copyBoard[i] = Arrays.copyOf(originalBoard[i], originalBoard.length);
            }

            // i번째 카드 제거 후 비용
            // j=0: 0번째 위치에 있는 i번 카드 먼저 제거
            // j=1: 1번째 위치에 있는 i번 카드 먼저 제거
            int[][] dp = new int[7][2];

            // DP 초기화
            Pair[] firstCardPositions = cardPositions[permutation[0]];
            dp[0][0] = bfs(copyBoard, new Pair(originalRow, originalCol), firstCardPositions[0])
                    + bfs(copyBoard, firstCardPositions[0], firstCardPositions[1]);
            dp[0][1] = bfs(copyBoard, new Pair(originalRow, originalCol), firstCardPositions[1])
                    + bfs(copyBoard, firstCardPositions[1], firstCardPositions[0]);

            copyBoard[firstCardPositions[0].x][firstCardPositions[0].y] = 0;
            copyBoard[firstCardPositions[1].x][firstCardPositions[1].y] = 0;

            // DP 계산
            for (int i = 1; i < cardCount; i++) {
                Pair[] beforeCardPositions = cardPositions[permutation[i - 1]];
                Pair[] currentCardPositions = cardPositions[permutation[i]];

                dp[i][0] = Math.min(
                        dp[i - 1][0] + bfs(copyBoard, beforeCardPositions[1], currentCardPositions[0]),
                        dp[i - 1][1] + bfs(copyBoard, beforeCardPositions[0], currentCardPositions[0]))
                        + bfs(copyBoard, currentCardPositions[0], currentCardPositions[1]);
                dp[i][1] = Math.min(
                        dp[i - 1][0] + bfs(copyBoard, beforeCardPositions[1], currentCardPositions[1]),
                        dp[i - 1][1] + bfs(copyBoard, beforeCardPositions[0], currentCardPositions[1]))
                        + bfs(copyBoard, currentCardPositions[1], currentCardPositions[0]);

                copyBoard[currentCardPositions[0].x][currentCardPositions[0].y] = 0;
                copyBoard[currentCardPositions[1].x][currentCardPositions[1].y] = 0;
            }

            // 현재 최소 비용, 0번째 선택, 1번째 선택 중 최소값
            int currMin = Math.min(dp[cardCount - 1][0], dp[cardCount - 1][1]);
            answer = Math.min(answer, currMin);
        }

        // Permutation
        for (int num = 1; num <= 6; num++) {
            if (cardPositions[num][0].x == -1 || selected[num]) {
                continue;
            }
            permutation[idx] = num;
            selected[num] = true;

            solve(idx + 1);

            selected[num] = false;
        }
    }


    static int bfs(int[][] board, Pair from, Pair to) {
        int[][] costs = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                costs[i][j] = -1; // not visited
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(from.x, from.y));
        costs[from.x][from.y] = 0;

        while (!q.isEmpty()) {
            Pair curr = q.remove();

            if (curr.x == to.x && curr.y == to.y) {
                break;
            }

            // 상, 하, 좌, 우
            for (int dir = 0; dir < 4; dir++) {
                int maxStep = 0;
                while (true) {
                    int nx = curr.x + dx[dir] * maxStep;
                    int ny = curr.y + dy[dir] * maxStep;
                    if (isOutOfBounds(nx + dx[dir], ny + dy[dir])
                            || (maxStep != 0 && board[nx][ny] != 0) // 처음 위치가 아니면서 카드를 만난 경우
                    ) {
                        break;
                    }
                    maxStep++;
                }

                // 방향키 이동 및 Ctrl 이동
                for (int isCtrl = 0; isCtrl < 2; isCtrl++) {
                    int step = 1;
                    if (isCtrl == 1) {
                        step = maxStep;
                    }

                    int nx = curr.x + dx[dir] * step;
                    int ny = curr.y + dy[dir] * step;
                    if (isOutOfBounds(nx, ny) || costs[nx][ny] != -1) {
                        continue;
                    }

                    q.add(new Pair(nx, ny));
                    costs[nx][ny] = costs[curr.x][curr.y] + 1;
                }
            }
        }

        return costs[to.x][to.y];
    }

    static boolean isOutOfBounds(int x, int y) {
        return x < 0 || x > 3 || y < 0 || y > 3;
    }

    static class Pair {

        public int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
