import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    private static final int MIN = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        final int[] nums = new int[N];
        for (int n = 0; n < nums.length; n++) {
            nums[n] = Integer.parseInt(br.readLine());
        }

        final int[][][] dp = new int[N][M + 1][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
            dp[i][1][1] = nums[i];
        }

        for (int idx = 1; idx < dp.length; idx++) {
            for (int m = 1; m < dp[idx].length; m++) {
                dp[idx][m][0] = Math.max(dp[idx][m][0], Math.max(dp[idx - 1][m][0], dp[idx - 1][m][1]));

                if (dp[idx - 1][m][1] != MIN) {
                    dp[idx][m][1] = Math.max(dp[idx][m][1], dp[idx - 1][m][1] + nums[idx]);
                }

                if (dp[idx - 1][m - 1][0] != MIN) {
                    dp[idx][m][1] = Math.max(dp[idx][m][1], dp[idx - 1][m - 1][0] + nums[idx]);
                }
            }
        }

        final int answer = Math.max(dp[dp.length - 1][M][0], dp[dp.length - 1][M][1]);
        sb.append(answer).append("\n");
        
        bw.write(sb.toString());
        bw.flush();
    }
}
