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

        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            final int N = Integer.parseInt(br.readLine());
            final int[] nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                nums[n] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(nums);
            sb.append(nums[0]).append(" ").append(nums[N - 1]).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }
}
