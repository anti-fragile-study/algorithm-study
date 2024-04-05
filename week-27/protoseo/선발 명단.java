import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        while (c-- > 0) {
            var stat = new int[11][11];
            for (int i = 0; i < 11; i++) {
                var stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    stat[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            sb.append(findPosition(0, stat, new boolean[11], 0)).append('\n');
        }
        System.out.println(sb);
    }

    static int findPosition(int idx, int[][] stat, boolean[] position, int sum) {
        if (idx >= 11) {
            for (int i = 0; i < 11; i++) {
                if (!position[i]) {
                    return 0;
                }
            }
            return sum;
        }
        int result = 0;
        for (int i = idx; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (stat[i][j] == 0 || position[j]) {
                    continue;
                }
                position[j] = true;
                result = Math.max(result, findPosition(i + 1, stat, position, sum + stat[i][j]));
                position[j] = false;
            }
        }
        return result;
    }
}
