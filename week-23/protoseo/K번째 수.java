import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long l = 0;
        long r = 10_000_000_000L;

        while (l < r) {
            long m = (l + r) / 2;
            long cnt = 0;
            for (int i = 1; i <= n; i++) {
                long t = m / i;
                if (t > n) {
                    cnt += n;
                } else {
                    cnt += t;
                }
            }

            if (cnt < k) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        System.out.println(l);
    }
}
