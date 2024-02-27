import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int one = 0;
        int two = 0;
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int h = Integer.parseInt(stk.nextToken());
            one += h % 2;
            two += h / 2;
        }
        System.out.println(((one + two * 2) % 3 == 0 && one <= two) ? "YES" : "NO");
    }
}