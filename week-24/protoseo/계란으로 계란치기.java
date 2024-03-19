import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] w;
    static int[] s;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n];
        s = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(stk.nextToken());
            w[i] = Integer.parseInt(stk.nextToken());
        }
        System.out.println(find(0));
    }

    static int find(int t) {
        if (t >= n) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (s[i] <= 0) {
                    cnt++;
                }
            }
            return cnt;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (t == i) {
                continue;
            }
            if (s[t] < 0 || s[i] < 0) {
                result = Math.max(result, find(t + 1));
                continue;
            }
            s[t] -= w[i];
            s[i] -= w[t];
            result = Math.max(result, find(t + 1));
            s[t] += w[i];
            s[i] += w[t];
        }
        return result;
    }
}
