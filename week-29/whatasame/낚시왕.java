import java.io.*;

class Main {

    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        int M = Integer.parseInt(split[2]);

        Shark[][] board = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");
            int r = Integer.parseInt(split[0]);
            int c = Integer.parseInt(split[1]);
            int s = Integer.parseInt(split[2]);
            int d = Integer.parseInt(split[3]);
            int z = Integer.parseInt(split[4]);

            board[r - 1][c - 1] = new Shark(s, d, z);
        }

        int answer = 0;
        for (int i = 0; i < C; i++) {
            answer += fish(board, i);
            board = move(board);
        }

        System.out.println(answer);
    }

    static int fish(Shark[][] board, int c) {
        for (int r = 0; r < R; r++) {
            if (board[r][c] == null) {
                continue;
            }

            Shark target = board[r][c];
            board[r][c] = null;
            return target.z;
        }

        return 0;
    }

    static Shark[][] move(Shark[][] board) {
        final Shark[][] newBoard = new Shark[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] == null) {
                    continue;
                }

                Shark now = new Shark(board[r][c].s, board[r][c].d, board[r][c].z);
                int nr, nc, nd;
                if (now.d == 1) { // 위
                    int[] result = next(r, -1, now.s, R);
                    nr = result[0];
                    nc = c;
                    nd = result[1] == -1 ? 1 : 2;
                } else if (now.d == 2) { // 아래
                    int[] result = next(r, 1, now.s, R);
                    nr = result[0];
                    nc = c;
                    nd = result[1] == -1 ? 1 : 2;
                } else if (now.d == 3) { // 오른쪽
                    int[] result = next(c, 1, now.s, C);
                    nr = r;
                    nc = result[0];
                    nd = result[1] == 1 ? 3 : 4;
                } else { // d == 4, 왼쪽
                    int[] result = next(c, -1, now.s, C);
                    nr = r;
                    nc = result[0];
                    nd = result[1] == 1 ? 3 : 4;
                }
                now.d = nd;

                /* 잡아먹기 */
                if (newBoard[nr][nc] != null) {
                    Shark present = newBoard[nr][nc];
                    now = present.z > now.z ? present : now;
                }

                newBoard[nr][nc] = now;
            }
        }

        return newBoard;
    }

    static int[] next(int pos, int bias, int s, int max) {
        for (int i = 0; i < s; i++) {
            pos += bias;
            if (pos == -1) {
                pos = 1;
                bias = 1;
            } else if (pos == max) {
                pos = max - 2;
                bias = -1;
            }
        }

        return new int[]{pos, bias};
    }

    static class Shark {

        int s, d, z;

        Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
