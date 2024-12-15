import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String[] split = br.readLine().split(" ");
        final int n = Integer.parseInt(split[0]);
        final int k = Integer.parseInt(split[1]);
        final int[] ary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = 2000000;
        while (l <= r) {
            int m = (l + r) / 2;
            int cnt = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += ary[i];
                if (sum >= m) {
                    sum = 0;
                    cnt++;
                }
            }
            if (cnt >= k) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(r);
    }
}
