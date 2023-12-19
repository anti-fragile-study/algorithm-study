class Solution {

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int n, m;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        return play(0, board, aloc, bloc);
    }

    public int play(int count, int[][] board, int[] aloc, int[] bloc) {
        int x = (count % 2 == 0) ? aloc[1] : bloc[1];
        int y = (count % 2 == 0) ? aloc[0] : bloc[0];

        if (board[y][x] == 0 || !canMove(board, new int[]{y, x})) {
            return count;
        }

        int defeat = 0;
        int victory = 100;
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if (xx < 0 || yy < 0 || xx >= m || yy >= n || board[yy][xx] == 0) {
                continue;
            }
            board[y][x] = 0;
            int result = (count % 2 == 0) ? play(count + 1, board, new int[]{yy, xx}, bloc)
                    : play(count + 1, board, aloc, new int[]{yy, xx});
            board[y][x] = 1;
            if (count % 2 == result % 2) {
                defeat = Math.max(defeat, result);
                continue;
            }
            victory = Math.min(victory, result);
        }
        if (victory == 100) {
            return defeat;
        }
        return victory;
    }

    private boolean canMove(int[][] board, int[] loc) {
        for (int i = 0; i < 4; i++) {
            int x = loc[1] + dx[i];
            int y = loc[0] + dy[i];

            if (x < 0 || y < 0 || x >= m || y >= n || board[y][x] == 0) {
                continue;
            }
            return true;
        }
        return false;
    }
}
