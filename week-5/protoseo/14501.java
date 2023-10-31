import java.io.*;

public class Main {

    static int n;
    static int answer;
    static int[] t;
    static int[] p;
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            final String[] split = br.readLine().split(" ");
            t[i] = Integer.parseInt(split[0]);
            if (i + t[i] > n) {
                p[i] = 0;
                continue;
            }
            p[i] = Integer.parseInt(split[1]);
        }

        find(0, 0);
        System.out.println(answer);
    }

    static void find(int now, int sum) {
        if (now >= n) {
            answer = Math.max(answer, sum);
            return;
        }
        find(now + t[now], sum + p[now]);
        find(now + 1, sum);
    }
}
