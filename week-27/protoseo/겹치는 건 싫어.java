import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        int[] ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(stk.nextToken());
        }

        int l = 0;
        int r = 0;
        int answer = 0;
        int[] count = new int[100001];
        while (l <= r && r < n) {
            int value = ary[r];
            count[value]++;
            r++;

            while (count[value] > k) {
                count[ary[l]]--;
                l++;
            }
            answer = Math.max(answer, r - l);
        }
        System.out.println(answer);
    }
}
