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

    static int N, L, R, X;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        final int[] scores = new int[N];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        // scores 정렬
        Arrays.sort(scores);

        for (int start = 0; start < scores.length; start++) {
            for (int end = start + 1; end < scores.length; end++) {
                if (scores[end] - scores[start] < X) {
                    continue;
                }
                dfs(start + 1, end, scores[start] + scores[end], scores);
            }
        }

        sb.append(answer).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(final int idx, final int right, final int sum, final int[] scores) {
        if (idx == right) {
            if (sum >= L && sum <= R) {
                answer++;
            }
            return;
        }

        dfs(idx + 1, right, sum, scores);
        dfs(idx + 1, right, sum + scores[idx], scores);
    }
}
