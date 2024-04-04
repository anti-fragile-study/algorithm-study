import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        var ary = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(ary[i], INF);
            ary[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            ary[s][e] = 0;
            ary[e][s] = 0;
            if (b == 0) {
                ary[e][s] = 1;
            }
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (ary[i][j] > ary[i][k] + ary[k][j]) {
                        ary[i][j] = ary[i][k] + ary[k][j];
                    }
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            sb.append(ary[s][e]).append('\n');
        }
        System.out.println(sb);
    }
}
