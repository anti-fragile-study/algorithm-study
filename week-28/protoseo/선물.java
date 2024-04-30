import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int l = Integer.parseInt(stk.nextToken());
        int w = Integer.parseInt(stk.nextToken());
        int h = Integer.parseInt(stk.nextToken());

        double min = 0.0;
        double max = Math.min(l, Math.min(w, h));

        for (int i = 0; i < 100000 && min < max; i++) {
            if (Math.abs(max - min) <= 1e-9) {
                break;
            }
            double mid = (min + max) / 2;
            long boxCount = (long) (l / mid) * (long) (w / mid) * (long) (h / mid);
            if (boxCount < n) {
                max = mid;
            } else {
                min = mid;
            }
        }
        System.out.println(max);
    }
}
