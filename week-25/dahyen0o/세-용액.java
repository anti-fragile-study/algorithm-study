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

        final int N = Integer.parseInt(br.readLine());
        final long[] liquids = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < liquids.length; n++) {
            liquids[n] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(liquids);

        long ansVal = Long.MAX_VALUE;
        final long[] answer = new long[3];

        for (int idx = 0; idx < liquids.length; idx++) {
            int lIdx = 0;
            int rIdx = liquids.length - 1;
            while (lIdx < rIdx) {
                if (lIdx == idx) {
                    lIdx++;
                    continue;
                } else if (rIdx == idx) {
                    rIdx--;
                    continue;
                }

                final long sum = liquids[idx] + liquids[lIdx] + liquids[rIdx];
                if (Math.abs(sum) < ansVal) {
                    ansVal = Math.abs(sum);
                    answer[0] = liquids[idx];
                    answer[1] = liquids[lIdx];
                    answer[2] = liquids[rIdx];
                }

                if (sum < 0) {
                    lIdx++;
                } else {
                    rIdx--;
                }
            }
        }

        Arrays.sort(answer);
        for (long ans : answer) {
            sb.append(ans).append(" ");
        }

        bw.write(sb.append("\n").toString());
        bw.flush();
    }
}
