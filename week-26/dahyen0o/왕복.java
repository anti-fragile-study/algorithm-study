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
        final long K = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        final int[] dists = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            dists[n] = Integer.parseInt(st.nextToken());
        }

        sb.append(courseNum(dists, K)).append("\n");
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static int courseNum(final int[] dists, final long K) {
        final long[] prefixSums = new long[dists.length];
        for (int i = 1; i < prefixSums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + dists[i];

            if (prefixSums[i] > K) {
                return i;
            }
        }

        final long NEW_K = K - prefixSums[prefixSums.length - 1];
        for (int i = 1; i < prefixSums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + dists[dists.length - i];

            if (prefixSums[i] > NEW_K) {
                return dists.length - i;
            }
        }
        
        throw new IllegalArgumentException("K >= (LEN_COURSE * 2)");
    }
}
