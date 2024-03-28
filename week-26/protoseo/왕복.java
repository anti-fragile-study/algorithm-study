import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        long k = Long.parseLong(stk.nextToken());

        int[] ary = new int[n];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(stk.nextToken());
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + ary[i] > k) {
                System.out.println(i + 1);
                return;
            }
            sum += ary[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            if (sum + ary[i] > k) {
                System.out.println(i + 1);
                return;
            }
            sum += ary[i];
        }
    }
}
