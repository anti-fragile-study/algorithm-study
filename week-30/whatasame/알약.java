import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[][] dp = new long[31][31]; // r개의 큰 알약, c개의 작은 알약
        for (int c = 0; c <= 30; c++) {
            dp[0][c] = 1;
        }

        for (int r = 1; r <= 30; r++) {
            for (int c = 0; c < 30; c++) {
                if (c == 0) {
                    dp[r][c] = dp[r - 1][c + 1];
                } else {
                    dp[r][c] = dp[r][c - 1] + dp[r - 1][c + 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while (!(line = br.readLine()).equals("0")) {
            int n = Integer.parseInt(line);
            sb.append(dp[n][0]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
