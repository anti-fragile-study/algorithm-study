import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] d = {2, 4, 1, 3};
    static int[] destroyBlockCount = new int[4];
    static List<Block> blocks = new ArrayList<>();
    static List<Block> indexInformation = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        initBlocks();

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());
            blizzard(d, s);
            boolean isExplode = explode();
            while (isExplode) {
                isExplode = explode();
            }
            recreateBlocks();
        }
        System.out.println(destroyBlockCount[1] + 2 * destroyBlockCount[2] + 3 * destroyBlockCount[3]);
    }

    static void initBlocks() {
        int cnt = 1;
        int x = (n / 2);
        int y = (n / 2);
        while (true) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < cnt * 2; j++) {
                    x = x + dx[d[i]];
                    y = y + dy[d[i]];
                    if (map[y][x] != 0) {
                        blocks.add(new Block(map[y][x], x, y));
                    }
                    indexInformation.add(new Block(0, x, y));
                }
            }
            x -= 1;
            y -= 1;
            if (x == 0 && y == 0) {
                return;
            }
            cnt++;
        }
    }

    static void recreateBlocks() {
        List<int[]> information = new ArrayList<>();
        int prev = 0;
        int count = 0;
        for (int i = 0; i <= blocks.size(); i++) {
            int c = 0;
            if (i <= blocks.size() - 1) {
                c = blocks.get(i).c;
            }

            if (prev == c) {
                count++;
            } else {
                if (prev != 0) {
                    information.add(new int[]{prev, count});
                }
                prev = c;
                count = 1;
            }
        }
        List<Block> newBlocks = new ArrayList<>();
        int idx = 0;
        for (int[] info : information) {
            if (newBlocks.size() >= n * n - 1) {
                break;
            }
            int c = info[0];
            int s = info[1];
            Block sizeBlock = indexInformation.get(idx);
            Block colorBlock = indexInformation.get(idx + 1);
            idx += 2;
            newBlocks.add(new Block(s, sizeBlock.x, sizeBlock.y));
            newBlocks.add(new Block(c, colorBlock.x, colorBlock.y));
        }
        blocks = newBlocks;
    }

    static void blizzard(int d, int s) {
        int y = (n + 1) / 2;
        int x = (n + 1) / 2;

        for (int i = 0; i < s; i++) {
            x = x + dx[d];
            y = y + dy[d];
            for (int j = 0; j < blocks.size(); j++) {
                Block b = blocks.get(j);
                int bx = b.x;
                int by = b.y;

                if (bx == x && by == y) {
                    blocks.remove(j);
                    break;
                }
            }
        }
    }

    static boolean explode() {
        List<int[]> removes = new ArrayList<>();
        int prev = 0;
        int count = 0;
        for (int i = 0; i <= blocks.size(); i++) {
            int c = 0;
            if (i <= blocks.size() - 1) {
                c = blocks.get(i).c;
            }

            if (prev == c) {
                count++;
            } else {
                if (count >= 4) {
                    removes.add(new int[]{i - count, count});
                }
                prev = c;
                count = 1;
            }
        }
        if (removes.isEmpty()) {
            return false;
        }
        removes.sort(((o1, o2) -> o2[0] - o1[0]));
        for (int[] remove : removes) {
            int idx = remove[0];
            int size = remove[1];
            for (int i = 0; i < size; i++) {
                Block removed = blocks.remove(idx);
                destroyBlockCount[removed.c]++;
            }
        }
        return true;
    }

}

class Block {
    int c;
    int x;
    int y;

    public Block(int c, int x, int y) {
        this.c = c;
        this.x = x;
        this.y = y;
    }
}
