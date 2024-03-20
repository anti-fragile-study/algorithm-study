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

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        final int[] scores = new int[N];
        int MAX_SCORE = 0;
        for (int n = 0; n < scores.length; n++) {
            scores[n] = Integer.parseInt(st.nextToken());
            MAX_SCORE += scores[n];
        }

        int left = 0, right = MAX_SCORE;
        int answer = 0;

        while (left <= right) {
            int minScore = (left + right) / 2;
            
            // K개의 그룹으로 쪼갰을 때 합이 모두 minScore 넘을 수 있을지 검사
            if (canLowerBound(minScore, scores, K)) {
                answer = Math.max(answer, minScore);
                left = minScore + 1;
            } else {
                right = minScore - 1;
            }
        }
        
        bw.write(sb.append(answer).append("\n").toString());
        bw.flush();
    }

    private static boolean canLowerBound(final int lowerBound, final int[] scores, final int K) {
        int k = 0;
        int score = 0;

        for (int i = 0; i < scores.length; i++) {
            score += scores[i];

            if (score >= lowerBound) {
                score = 0;
                k++;
            }
        }

        return k >= K;
    }
}
