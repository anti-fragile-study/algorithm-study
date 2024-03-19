import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(br.readLine());
        }

        long l = 0;
        long r = 1000000000L * m;

        while (l < r) {
            long mid = (l + r) / 2;
            long cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += (mid / t[i]);
                if (cnt >= m) {
                    break;
                }
            }
            if (cnt >= m) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(r);
    }
}
