import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int l;
    static int r;
    static int x;
    static Set<Integer> answer = new HashSet<>();
    static int[] ary;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        l = Integer.parseInt(split[1]);
        r = Integer.parseInt(split[2]);
        x = Integer.parseInt(split[3]);
        ary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        find(0, new boolean[n]);
        System.out.println(answer.size());
    }

    static void find(int now, boolean[] isVisited) {
        if (now == n) {
            int sum = 0;
            int min = 10000000;
            int max = -1;
            int result = 0;
            for (int i = 0; i < n; i++) {
                if (isVisited[i]) {
                    sum += ary[i];
                    result = result | (1 << i);
                    min = Math.min(min, ary[i]);
                    max = Math.max(max, ary[i]);
                }
            }
            if (sum >= l && sum <= r && (max - min) >= x) {
                answer.add(result);
            }
            return;
        }

        isVisited[now] = true;
        find(now + 1, isVisited);
        isVisited[now] = false;
        find(now + 1, isVisited);
    }
}
