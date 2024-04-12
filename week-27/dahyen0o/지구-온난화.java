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

    static final int[] dr = { 0, -1, 0, +1 };
    static final int[] dc = { -1, 0, +1, 0 };

    static int R, C;
    static boolean[][] oldMap;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        oldMap = new boolean[R + 2][C + 2];
        for (int r = 0; r < R; r++) {
            final String row = br.readLine();
            for (int c = 0; c < C; c++) {
                if (row.charAt(c) == 'X') {
                    oldMap[r + 1][c + 1] = true;
                }
            }
        }

        final boolean[][] newMap = newMap();
        for (int r = 0; r < newMap.length; r++) {
            for (int c = 0; c < newMap[r].length; c++) {
                if (newMap[r][c]) {
                    sb.append('X');
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean[][] newMap() {
        final boolean[][] map = new boolean[R + 2][C + 2];

        int minR = Integer.MAX_VALUE, maxR = 0;
        int minC = Integer.MAX_VALUE, maxC = 0;

        for (int r = 1; r < oldMap.length - 1; r++) {
            for (int c = 1; c < oldMap[r].length - 1; c++) {
                if (!oldMap[r][c]) {
                    continue;
                }
                
                int count = 0;
                for (int dir = 0; dir < 4; dir++) {
                    final int rr = r + dr[dir];
                    final int cc = c + dc[dir];
                    if (oldMap[rr][cc]) {
                        count++;
                    }
                }

                if (count > 1) {
                    map[r][c] = true;

                    minR = Math.min(minR, r);
                    maxR = Math.max(maxR, r);
                    minC = Math.min(minC, c);
                    maxC = Math.max(maxC, c);
                }
            }
        }

        final boolean[][] newMap = new boolean[maxR - minR + 1][maxC - minC + 1];
        for (int r = minR; r <= maxR; r++) {
            for (int c = minC; c <= maxC; c++) {
                newMap[r - minR][c - minC] = map[r][c];
            }
        }

        return newMap;
    }
}
