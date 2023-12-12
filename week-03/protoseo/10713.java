import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String s = br.readLine();
        String[] split = s.split(" ");

        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        long[] useCount = new long[n];
        int[] orders = new int[m];
        int[][] information = new int[n - 1][3];

        String[] order = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            orders[i] = Integer.parseInt(order[i]);
        }
        for (int i = 0; i < n - 1; i++) {
            final String[] input = br.readLine().split(" ");
            information[i][0] = Integer.parseInt(input[0]);
            information[i][1] = Integer.parseInt(input[1]);
            information[i][2] = Integer.parseInt(input[2]);
        }

        for (int i = 1; i < m; i++) {
            int prev = Math.min(orders[i - 1] - 1, orders[i] - 1);
            int next = Math.max(orders[i - 1] - 1, orders[i] - 1);
            useCount[prev]++;
            useCount[next]--;
        }
        for (int i = 1; i < n; i++) {
            useCount[i] += useCount[i - 1];
        }

        long answer = 0;
        for (int i = 0; i < n - 1; i++) {
            answer += Math.min(information[i][0] * useCount[i], information[i][1] * useCount[i] + information[i][2]);
        }
        System.out.println(answer);
    }
}
