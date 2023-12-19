import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17951 {
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

        st = new StringTokenizer(br.readLine());
        final int[] scores = new int[N + 1];

        // 누적 합 계산
        for (int i = 1; i < scores.length; i++) {
            scores[i] = scores[i - 1];
            scores[i] += Integer.parseInt(st.nextToken());
        }

        final double avg = scores[N] / (double) K;

        // 평균 값과 최대한 가깝게 맞추기
        int minSum = Integer.MAX_VALUE;
        int start = 1;
        for (int end = 1; end < scores.length; end++) {
            final int sum = scores[end] - scores[start - 1];
            if (sum > avg) {
                final int preSum = scores[end - 1] - scores[start - 1];
                if (Math.abs(preSum - avg) < Math.abs(sum - avg) && start < end) {
                    start = end;
                    minSum = Math.min(minSum, preSum);
                } else {
                    start = end + 1;
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        minSum = Math.min(minSum, scores[scores.length - 1] - scores[start - 1]);
        
        sb.append(minSum).append("\n");
        bw.write(sb.toString());
        bw.flush();
    }
}
