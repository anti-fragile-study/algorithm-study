import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(final String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        final int N = Integer.parseInt(split[0]);
        final int M = Integer.parseInt(split[1]);

        final int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        final int answer = solution(N, M, board);

        System.out.println(answer);
    }

    static int solution(final int n, final int m, final int[][] board) {
        final List<Pos> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    candidates.add(new Pos(i, j, 0));
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (final List<Pos> candidate : combinations(candidates, m)) {
            int result = 0;

            final boolean[][] visited = new boolean[n][n];
            final Queue<Pos> q = new LinkedList<>(candidate);
            for (final Pos c : candidate) {
                visited[c.x][c.y] = true;
            }
            while (!q.isEmpty()) {
                final Pos now = q.remove();
                result = Math.max(result, now.t);

                for (int i = 0; i < 4; i++) {
                    final int nx = now.x + dx[i];
                    final int ny = now.y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (board[nx][ny] == 1 || visited[nx][ny]) {
                        continue;
                    }

                    q.add(new Pos(nx, ny, now.t + 1));
                    visited[nx][ny] = true;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 1) {
                        continue;
                    }

                    if (!visited[i][j]) {
                        result = Integer.MAX_VALUE;
                        break;
                    }
                }
            }

            answer = Math.min(answer, result);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    static List<List<Pos>> combinations(final List<Pos> candidates, final int m) {
        final List<List<Pos>> result = new ArrayList<>();
        final List<Pos> combination = new ArrayList<>();
        comb(candidates, m, 0, combination, result);

        return result;
    }

    static void comb(final List<Pos> candidates, final int m, final int start, final List<Pos> combination,
            final List<List<Pos>> result) {
        if (m == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.size(); i++) {
            combination.add(candidates.get(i));
            comb(candidates, m - 1, i + 1, combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    static class Pos {

        int x, y, t;

        Pos(final int x, final int y, final int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}
