import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= n / 5; i++) {
            int m = n - (i * 5);
            if (m % 3 == 0) {
                answer = Math.min(answer, i + m / 3);
            }
        }
        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }
}
