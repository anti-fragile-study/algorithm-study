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

        final int N = Integer.parseInt(br.readLine());
        sb.append(findMinCount(N)).append("\n");
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static int findMinCount(final int N) {
        if (N % 5 == 0) {
            return N / 5;
        }
        int answer = Integer.MAX_VALUE;
        if (N % 3 == 0) {
            answer = Math.min(answer, N / 3);
        }
        for (int fiveCount = 1; fiveCount * 5 < N; fiveCount++) {
            if ((N - fiveCount * 5) % 3 == 0) {
                answer = Math.min(answer, fiveCount + (N - fiveCount * 5) / 3);
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
