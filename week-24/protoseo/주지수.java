import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[][] ary = new int[n][m];
        int[][] preSum = new int[n][m];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ary[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        preSum[0][0] = ary[0][0];
        for (int i = 1; i < m; i++) {
            preSum[0][i] = ary[0][i] + preSum[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            preSum[i][0] = ary[i][0] + preSum[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] + ary[i][j] - preSum[i - 1][j - 1];
            }
        }

        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(stk.nextToken());
            int x1 = Integer.parseInt(stk.nextToken());
            int y2 = Integer.parseInt(stk.nextToken());
            int x2 = Integer.parseInt(stk.nextToken());

            int sum = preSum[y2 - 1][x2 - 1];
            if (x1 > 1) {
                sum -= preSum[y2 - 1][x1 - 2];
            }
            if (y1 > 1) {
                sum -= preSum[y1 - 2][x2 - 1];
            }
            if (x1 > 1 && y1 > 1) {
                sum += preSum[y1 -2][x1 - 2];
            }

            sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }
}
