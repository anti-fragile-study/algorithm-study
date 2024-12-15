import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(stk.nextToken());
        int n = Integer.parseInt(stk.nextToken());
        var ary = new int[m][n];
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ary[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                boolean isMultiverse = true;
                compare:
                for (int k = 0; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int compareA = Integer.compare(ary[i][k], ary[i][l]);
                        int compareB = Integer.compare(ary[j][k], ary[j][l]);
                        if (compareA != compareB) {
                            isMultiverse = false;
                            break compare;
                        }
                    }
                }
                if (isMultiverse) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
