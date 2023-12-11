import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int EMPTY = 0;
    static final int ME = 1;
    static final int YOU = 2;
    static final int[] dr = { +1, 0, -1, 0 };
    static final int[] dc = { 0, +1, 0, -1 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        final int[][] board = new int[N][M];
        for (int r = 0; r < board.length; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int maxScore = 0;

        for (int idx = 0; idx < N * M; idx++) {
            final int r = idx / M;
            final int c = (idx - r * M);
            if (board[r][c] != EMPTY) {
                continue;
            }

            board[r][c] = ME;

            for (int idxx = idx + 1; idxx < N * M; idxx++) {
                final int rr = idxx / M;
                final int cc = (idxx - rr * M);
                if (board[rr][cc] != EMPTY) {
                    continue;
                }

                board[rr][cc] = ME;
                maxScore = Math.max(maxScore, calculateScore(board));
                board[rr][cc] = EMPTY;
            }

            board[r][c] = EMPTY;
        }

        bw.write(sb.append(maxScore).append("\n").toString());
        bw.flush();
    }

    private static int calculateScore(final int[][] board) {
        final boolean[][] visited = new boolean[board.length][board[0].length];

        int score = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != YOU || visited[r][c]) {
                    continue;
                }

                // BFS
                score += bfs(board, visited, new Point(r, c));

            }
        }
        return score;
    }

    private static int bfs(int[][] board, boolean[][] visited, Point start) {
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.r][start.c] = true;

        int score = 0;
        boolean canScore = true;
        while (!queue.isEmpty()) {
            final Point curr = queue.poll();
            score++;

            for (int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];

                if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
                    continue;
                }
                if (board[r][c] == EMPTY) {
                    canScore = false;
                    continue;
                }
                if (visited[r][c] || board[r][c] == ME) {
                    continue;
                }
                queue.add(new Point(r, c));
                visited[r][c] = true;
            }
        }
        return canScore ? score : 0;
    }
}
