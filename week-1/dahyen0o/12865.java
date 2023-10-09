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

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        final int[] W = new int[N + 1];
        final int[] V = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            W[n] = Integer.parseInt(st.nextToken());
            V[n] = Integer.parseInt(st.nextToken());
        }

        final int[][] dp = new int[N + 1][K + 1];

        for (int n = 1; n < dp.length; n++) {
            // n번째 물건을 담을 수 없는 경우
            for (int w = 0; w < W[n] && w < dp[n].length; w++) {
                dp[n][w] = dp[n - 1][w];
            }
            // n번째 물건을 담을 수 있는 경우: 담는 경우와 담지 않는 경우 중 최선의 방법 선택
            for (int w = W[n]; w < dp[n].length; w++) {
                dp[n][w] = Math.max(dp[n - 1][w - W[n]] + V[n], dp[n - 1][w]);
            }
        }
        
        bw.write(sb.append(dp[N][K]).append("\n").toString());
        bw.flush();
    }
}
