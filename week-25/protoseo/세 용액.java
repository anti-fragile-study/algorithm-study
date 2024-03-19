import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        long[] ary = new long[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Long.parseLong(stk.nextToken());
        }
        Arrays.sort(ary);

        long[] selected = new long[3];
        long result = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                long sum = ary[i] + ary[l] + ary[r];
                if (sum == 0) {
                    System.out.println(ary[i] + " " + ary[l] + " " + ary[r]);
                    return;
                }
                if (Math.abs(sum) < result) {
                    result = Math.abs(sum);
                    selected[0] = ary[i];
                    selected[1] = ary[l];
                    selected[2] = ary[r];
                }
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                }
            }
        }
        System.out.println(selected[0] + " " + selected[1] + " " + selected[2]);
    }
}