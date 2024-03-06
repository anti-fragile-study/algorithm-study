import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[][] map;
    static int[] papers = { 0, 5, 5, 5, 5, 5 };
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        map = new int[10][10];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0, 0);

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        bw.write(sb.append(result).append("\n").toString());
        bw.flush();
    }

    private static void DFS(int x, int y, int cnt) {
        if (x >= 9 && y > 9) { // 끝
            result = Math.min(result, cnt);
            return;
        } else if (result <= cnt) { // 최솟값 아님
            return;
        }

        if (y > 9) {
            DFS(x + 1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (papers[i] > 0 && isAttached(x, y, i)) {
                    attach(x, y, i, 0);
                    papers[i]--;

                    DFS(x, y + 1, cnt + 1);

                    attach(x, y, i, 1);
                    papers[i]++;
                }
            }
        } else {
            DFS(x, y + 1, cnt);
        }
    }

    private static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = state;
            }
        }
    }

    private static boolean isAttached(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }

                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
