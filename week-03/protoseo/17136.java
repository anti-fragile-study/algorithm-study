import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] ary = new int[10][10];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            final String[] split = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                ary[i][j] = Integer.parseInt(split[j]);
            }
        }

        final int[] usePaper = new int[5];
        Arrays.fill(usePaper, 5);
        find(0, 0, 0, usePaper);

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    static void find(int x, int y, int count, int[] usePaper) {
        if (result < count) {
            return;
        }
        if (x == 0 && y == 10) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (ary[i][j] == 1) {
                        return;
                    }
                }
            }
            result = count;
            return;
        }
        int nextX = (x + 1 == 10) ? 0 : x + 1;
        int nextY = (x + 1 == 10) ? y + 1 : y;
        if (ary[y][x] == 1) {
            for (int k = 4; k >= 0; k--) {
                if (!canVisit(x, y, k) || usePaper[k] == 0) {
                    continue;
                }
                usePaper[k]--;
                color(x, y, k);
                find(nextX, nextY, count + 1, usePaper);
                usePaper[k]++;
                erase(x, y, k);
            }
        } else {
            find(nextX, nextY, count, usePaper);
        }
    }

    static boolean canVisit(int x, int y, int k) {
        int count = 0;
        for (int i = y; i < 10 && i <= y + k; i++) {
            for (int j = x; j < 10 && j <= x + k; j++) {
                if (ary[i][j] == 1) {
                    count++;
                }
            }
        }
        return count == (k + 1) * (k + 1);
    }

    static void color(int x, int y, int k) {
        for (int i = y; i <= y + k; i++) {
            for (int j = x; j <= x + k; j++) {
                ary[i][j] = 0;
            }
        }
    }

    static void erase(int x, int y, int k) {
        for (int i = y; i <= y + k; i++) {
            for (int j = x; j <= x + k; j++) {
                ary[i][j] = 1;
            }
        }
    }
}
