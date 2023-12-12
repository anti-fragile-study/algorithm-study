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
        final int H = Integer.parseInt(st.nextToken());

        final int[] down = new int[N / 2];
        final int[] up = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            down[i] = a;
            up[i] = b;
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = N;
        int cnt = 0;

        for (int i = 1; i < H + 1; i++) {
            int conflict = binarySearch(0, N / 2, i, down) + binarySearch(0, N / 2, H - i + 1, up);
            if (min == conflict) {
                cnt++;
            } else if (min > conflict) {
                min = conflict;
                cnt = 1;
            }
        }

        sb.append(min).append(" ").append(cnt).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    static int binarySearch(int left, int right, int h, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr.length - right;
    }
}
