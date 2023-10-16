import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
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
        final int M = Integer.parseInt(st.nextToken());

        final Set<String> S = new HashSet<>(N);

        for (int n = 0; n < N; n++) {
            S.add(br.readLine());
        }

        int result = 0;

        for (int m = 0; m < M; m++) {
            final String pattern = br.readLine();
            if (S.contains(pattern)) {
                result++;
            }
        }

        sb.append(result);
        bw.write(sb.append("\n").toString());
        bw.flush();
    }
}
