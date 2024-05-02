import java.io.*;
import java.util.*;

public class Main {

    static Block[][] blocks = new Block[2][2];
    static int[][] ary;
    static int[] commandCounts = {0, 0, 0, 0, 0, 0};
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {1, 1, -1, -1};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        ary = new int[n][m];

        for (int i = 0; i < n; i++) {
            ary[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        blocks[0][0] = new Block(1);
        blocks[0][1] = new Block(2);
        blocks[1][0] = new Block(3);
        blocks[1][1] = new Block(4);

        int[] commands = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int command : commands) {
            commandCounts[command - 1]++;
            if (command == 1) {
                upDownFlip();
            } else if (command == 2) {
                leftRightFlip();
            } else if (command == 3) {
                rightRotate();
            } else if (command == 4) {
                leftRotate();
            } else if (command == 5) {
                totalRightRotate();
            } else if (command == 6) {
                totalLeftRotate();
            }
        }
        final int[][] resultAry = findResultAry();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultAry.length; i++) {
            for (int j = 0; j < resultAry[0].length; j++) {
                sb.append(resultAry[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    static int[][] findResultAry() {
        int[][] result;
        if (isClockWise) {
            result = new int[n][m];
        } else {
            result = new int[m][n];
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Block block = blocks[i][j];
                int idx = block.idx;
                detailsRotate(idx, i * 2 + j + 1, block, result);
            }
        }
        return result;
    }

    static void detailsRotate(int aryIdx, int nowIdx, Block block, int[][] result) {
        int x1, y1, x2, y2;
        int nx1, ny1, nx2, ny2;
        boolean isClockWise = isClockWise();
        if (aryIdx == 1) {
            x1 = 0;
            y1 = 0;
            x2 = m / 2 - 1;
            y2 = n / 2 - 1;
        } else if (aryIdx == 2) {
            x1 = m / 2;
            y1 = 0;
            x2 = m - 1;
            y2 = n / 2 - 1;
        } else if (aryIdx == 3) {
            x1 = 0;
            y1 = n / 2;
            x2 = m / 2 - 1;
            y2 = n - 1;
        } else {
            x1 = m / 2;
            y1 = n / 2;
            x2 = m - 1;
            y2 = n - 1;
        }

        if (nowIdx == 1) {
            nx1 = 0;
            ny1 = 0;
            nx2 = m / 2 - 1;
            ny2 = n / 2 - 1;
        } else if (nowIdx == 2) {
            nx1 = m / 2;
            ny1 = 0;
            nx2 = m - 1;
            ny2 = n / 2 - 1;
        } else if (nowIdx == 3) {
            nx1 = 0;
            ny1 = n / 2;
            nx2 = m / 2 - 1;
            ny2 = n - 1;
        } else {
            nx1 = m / 2;
            ny1 = n / 2;
            nx2 = m - 1;
            ny2 = n - 1;
        }

        int direction = getDirection(block);
        if (isClockWise) {
            int xx;
            int yy;

            if (direction == 0) {
                xx = nx1;
                yy = ny1;
            } else if (direction == 1) {
                xx = nx2;
                yy = ny1;
            } else if (direction == 2) {
                xx = nx2;
                yy = ny2;
            } else {
                xx = nx1;
                yy = ny2;
            }
            for (int i = y1; i <= y2; i++) {
                for (int j = x1; j <= x2; j++) {
                    int value = ary[i][j];
                    result[yy][xx] = value;

                    if (direction % 2 == 0) {
                        xx += dx[direction];
                    } else if (direction % 2 == 1) {
                        yy += dy[direction];
                    }
                }
                if (direction % 2 == 0) {
                    yy += dy[direction];
                } else if (direction % 2 == 1) {
                    xx += dx[direction];
                }
            }
        } else {
            int xx;
            int yy;

            if (direction == 0) {
                xx = nx1;
                yy = ny1;
            } else if (direction == 1) {
                xx = nx2;
                yy = ny1;
            } else if (direction == 2) {
                xx = nx2;
                yy = ny2;
            } else {
                xx = nx1;
                yy = ny2;
            }
            for (int i = y1; i <= y2; i++) {
                for (int j = x1; j <= x2; j++) {
                    int value = ary[i][j];
                    result[xx][yy] = value;

                    if (direction % 2 == 0) {
                        yy += dy[direction];
                    } else if (direction % 2 == 1) {
                        xx += dx[direction];
                    }
                }
                if (direction % 2 == 0) {
                    xx += dx[direction];
                } else if (direction % 2 == 1) {
                    yy += dy[direction];
                }
            }
        }
    }

    static boolean isClockWise() {
        int[][] blockAry = blocks[0][0].ary;
        int topLeft = blockAry[0][0];
        int topRight = blockAry[0][1];
        return (topLeft == 1 && topRight == 2) ||
                (topLeft == 3 && topRight == 1) ||
                (topLeft == 4 && topRight == 3) ||
                (topLeft == 2 && topRight == 4);
    }

    static int getDirection(Block block) {
        final int[][] blockAry = block.ary;
        int topLeft = blockAry[0][0];
        int topRight = blockAry[0][1];
        if (topLeft == 1) {
            return 0;
        } else if (topRight == 1) {
            return 1;
        } else if (topLeft == 4) {
            return 2;
        } else if (topRight == 4) {
            return 3;
        }
        return -1;
    }

    static void upDownFlip() {
        Block[] temp = blocks[0];
        blocks[0] = blocks[1];
        blocks[1] = temp;
        for (int i = 0; i < 4; i++) {
            blocks[i / 2][i % 2].upDownFlip();
        }
    }

    static void leftRightFlip() {
        Block[] temp = {blocks[0][0], blocks[0][1]};
        blocks[0][0] = blocks[1][0];
        blocks[0][1] = blocks[1][1];
        blocks[1][0] = temp[0];
        blocks[1][1] = temp[1];
        for (int i = 0; i < 4; i++) {
            blocks[i / 2][i % 2].leftRightFlip();
        }
    }

    static void rightRotate() {
        totalRightRotate();
        for (int i = 0; i < 4; i++) {
            blocks[i / 2][i % 2].rightRotate();
        }
    }

    static void leftRotate() {
        totalLeftRotate();
        for (int i = 0; i < 4; i++) {
            blocks[i / 2][i % 2].leftRotate();
        }
    }

    static void totalLeftRotate() {
        Block temp = blocks[0][0];
        blocks[0][0] = blocks[0][1];
        blocks[0][1] = blocks[1][1];
        blocks[1][1] = blocks[1][0];
        blocks[1][0] = temp;
    }

    static void totalRightRotate() {
        Block temp = blocks[0][0];
        blocks[0][0] = blocks[1][0];
        blocks[1][0] = blocks[1][1];
        blocks[1][1] = blocks[0][1];
        blocks[0][1] = temp;
    }
}

class Block {
    int idx;
    int[][] ary = {{1, 2}, {3, 4}};

    public Block(int idx) {
        this.idx = idx;
    }

    public void upDownFlip() {
        int[] temp = ary[0];
        ary[0] = ary[1];
        ary[1] = temp;
    }

    public void leftRightFlip() {
        int[] temp = {ary[0][0], ary[0][1]};
        ary[0][0] = ary[1][0];
        ary[0][1] = ary[1][1];
        ary[1][0] = temp[0];
        ary[1][1] = temp[1];
    }

    public void rightRotate() {
        int temp = ary[0][0];
        ary[0][0] = ary[1][0];
        ary[1][0] = ary[1][1];
        ary[1][1] = ary[0][1];
        ary[0][1] = temp;
    }

    public void leftRotate() {
        int temp = ary[0][0];
        ary[0][0] = ary[0][1];
        ary[0][1] = ary[1][1];
        ary[1][1] = ary[1][0];
        ary[1][0] = temp;
    }
}
