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
        final int[] students = new int[N];
        for (int idx = 0; idx < students.length; idx++) {
            students[idx] = Integer.parseInt(st.nextToken());
        }

        final int[] diffs = new int[N - 1];
        for (int idx = 0; idx < diffs.length - 1; idx++) {
            diffs[idx] = students[idx + 1] - students[idx];
        }
        diffs[diffs.length - 1] = students[students.length - 1] - students[students.length - 2];

        if (diffs.length == 0) {
            bw.write(sb.append(0).append("\n").toString());
            bw.flush();
            return;
        }

        // diffs 배열에서 (K - 1)개의 원소를 '합이 최대가 되도록' 선택해 삭제
        Arrays.sort(diffs);
        long sum = 0;
        for (int idx = 0; idx < diffs.length - (K - 1); idx++) {
            sum += diffs[idx];
        }

        bw.write(sb.append(sum).append("\n").toString());
        bw.flush();
    }
}
