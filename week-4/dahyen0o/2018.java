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
        int count = 0;

        /*
         * ... + (n - 1) + n + (n + 1) + ...
         */
        
        for (int k = 1; k <= N; k++) {
            if (k % 2 == 0) {
                final int temp = N - (k / 2);
                final int n = temp / k;
                if (temp % k == 0 && isValid(N, k, n)) {
                    count++;
                }
            } else {
                final int n = N / k;
                if (N % k == 0 && isValid(N, k, n)) {
                    count++;
                }
            }
        }
        
        sb.append(count).append("\n");
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean isValid(final int N, int k, final int n) {
        // n이 조건에 부합하는 지 검사
        if (n - ((k - 1) / 2) > 0 && n + (k / 2) <= N) {
            return true;
        }
        return false;
    }
}
