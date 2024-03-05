import java.io.*;

public class Main {
    static int[][] dp = new int[11][101];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i < 10; i++) {
            dp[i][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[j][i] = dp[j + 1][i - 1];
                } else {
                    dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
                }
                dp[j][i] %= 1000000000;
            }
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[i][n];
            answer %= 1000000000;
        }
        System.out.println(answer);
    }
}
