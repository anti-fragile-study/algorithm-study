import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[2]);
        int c = Integer.parseInt(split[3]);

        int[] sushi = new int[n + k - 1];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < k - 1; i++) {
            sushi[n + i] = sushi[i];
        }

        int answer = 0;
        Map<Integer, Integer> countBySushi = new HashMap<>();
        for (int i = 0; i < n + k; i++) {
            if (i >= k) {
                if (countBySushi.containsKey(c)) {
                    answer = Math.max(answer, countBySushi.size());
                } else {
                    answer = Math.max(answer, countBySushi.size() + 1);
                }

                int count = countBySushi.get(sushi[i - k]);
                if (count == 1) {
                    countBySushi.remove(sushi[i - k]);
                } else if (count > 1) {
                    countBySushi.put(sushi[i - k], count - 1);
                }
            }
            if (i < n + k - 1) {
                countBySushi.put(sushi[i], countBySushi.getOrDefault(sushi[i], 0) + 1);
            }
        }
        System.out.println(answer);
    }
}