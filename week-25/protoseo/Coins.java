import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = new int[n];
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(stk.nextToken());
            }
            int m = Integer.parseInt(br.readLine());

            int[] dp = new int[m + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= m; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            sb.append(dp[m]).append('\n');
        }
        System.out.println(sb);
    }
}
