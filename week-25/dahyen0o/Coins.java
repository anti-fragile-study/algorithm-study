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

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            final int N = Integer.parseInt(br.readLine());

            final int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < coins.length; c++) {
                coins[c] = Integer.parseInt(st.nextToken());
            }

            final int money = Integer.parseInt(br.readLine());

            sb.append(answer(coins, money)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static long answer(final int[] coins, final int MONEY) {
        final long[] dp = new long[MONEY + 1];

        for (final int coin : coins) {
            dp[coin] += 1;
            for (int money = coin + 1; money < dp.length; money++) {
                dp[money] += dp[money - coin];
            }
        }

        return dp[MONEY];
    }
}
