import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] isUsed = new boolean[5][5];
    static int[][] bingo = new int[5][5];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bingo = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                bingo[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        int answer = 0;
        for (int i = 0; i < 5; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(stk.nextToken());
                called(num);
                if (answer == 0 && isBingo()) {
                    answer = i * 5 + j + 1;
                }
            }
        }
        System.out.println(answer);
    }

    static void called(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == num) {
                    isUsed[i][j] = true;
                    return;
                }
            }
        }
    }

    static boolean isBingo() {
        int count = 0;
        boolean down = true;
        boolean up = true;
        for (int i = 0; i < 5; i++) {
            if (!isUsed[i][i]) {
                down = false;
            }
            if (!isUsed[i][4 - i]) {
                up = false;
            }
            boolean isCol = true;
            boolean isRow = true;
            for (int j = 0; j < 5; j++) {
                if (!isUsed[i][j]) {
                    isCol = false;
                }
                if (!isUsed[j][i]) {
                    isRow = false;
                }
            }
            if (isCol) {
                count++;
            }
            if (isRow) {
                count++;
            }
        }
        if (down) {
            count++;
        }
        if (up) {
            count++;
        }
        return count >= 3;
    }
}
