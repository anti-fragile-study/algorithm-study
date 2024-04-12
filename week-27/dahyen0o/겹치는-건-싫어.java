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

    static final int MAX = 100_000;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        final int[] nums = new int[N];
        for (int n = 0; n < N; n++) {
            nums[n] = Integer.parseInt(st.nextToken());
        }

        final int[] counts = new int[MAX + 1];
        int answer = 0;
        int left = 0, right = 0;
        counts[nums[0]]++;
        
        while (left <= right) {
            if (++right == nums.length) {
                break;
            }
            counts[nums[right]]++;

            if (right < nums.length && counts[nums[right]] <= K) {
                answer = Math.max(answer, right - left + 1);
                continue;
            }

            while (left < right && counts[nums[right]] > K) {
                counts[nums[left]]--;
                left++;
            }
        }
        
        bw.write(sb.append(answer).append("\n").toString());
        bw.flush();
    }
}
