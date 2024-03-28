import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] calendar = new int[367];
        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            for (int j = s; j <= e; j++) {
                calendar[j]++;
            }
        }

        int width = 0;
        int height = 0;
        int answer = 0;
        for (int i = 1; i <= 366; i++) {
            if (calendar[i] > 0) {
                width++;
                height = Math.max(height, calendar[i]);
            } else if (calendar[i] == 0) {
                answer += (width * height);
                width = 0;
                height = 0;
            }
        }
        System.out.println(answer);
    }
}
