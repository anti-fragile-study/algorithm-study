import java.util.*;

class Solution {

    private boolean[][] isCol;
    private boolean[][] isRow;

    public int[][] solution(int n, int[][] build_frame) {
        isCol = new boolean[n + 1][n + 1];
        isRow = new boolean[n + 1][n + 1];

        for (int[] command : build_frame) {
            int x = command[0];
            int y = command[1];
            int a = command[2];
            int b = command[3];

            if (b == 1) {
                if (a == 0) {
                    buildCol(x, y, n);
                } else if (a == 1) {
                    buildRow(x, y, n);
                }
            } else if (b == 0) {
                remove(x, y, a, n);
            }
        }
        List<int[]> resultList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (isCol[j][i]) {
                    resultList.add(new int[]{i, j, 0});
                }
                if (isRow[j][i]) {
                    resultList.add(new int[]{i, j, 1});
                }
            }
        }
        int[][] answer = new int[resultList.size()][3];
        for (int i = 0; i < resultList.size(); i++) {
            answer[i] = resultList.get(i);
        }
        return answer;
    }

    private boolean buildCol(int x, int y, int n) {
        if (y == 0) {
            return isCol[y][x] = true;
        } else if (y - 1 >= 0 && isCol[y - 1][x]) {
            return isCol[y][x] = true;
        } else if (x - 1 >= 0 && isRow[y][x - 1]) {
            return isCol[y][x] = true;
        } else if (isRow[y][x]) {
            return isCol[y][x] = true;
        }
        return false;
    }

    private boolean buildRow(int x, int y, int n) {
        if (y - 1 >= 0 && isCol[y - 1][x]) {
            return isRow[y][x] = true;
        } else if (y - 1 >= 0 && x + 1 <= n && isCol[y - 1][x + 1]) {
            return isRow[y][x] = true;
        } else if (x - 1 >= 0 && x + 1 <= n && isRow[y][x - 1] && isRow[y][x + 1]) {
            return isRow[y][x] = true;
        }
        return false;
    }

    private boolean remove(int x, int y, int a, int n) {
        boolean isCanRemove = true;
        if (a == 0) {
            isCol[y][x] = false;
        } else if (a == 1) {
            isRow[y][x] = false;
        }
        for (int i = 0; i <= n; i++) {
            boolean isContinue = true;
            for (int j = 0; j <= n; j++) {
                if (isCol[i][j] && !buildCol(j, i, n)) {
                    isCanRemove = false;
                    isContinue = false;
                    break;
                }
                if (isRow[i][j] && !buildRow(j, i, n)) {
                    isCanRemove = false;
                    isContinue = false;
                    break;
                }
            }
            if (!isContinue) {
                break;
            }
        }
        if (!isCanRemove) {
            if (a == 0) {
                isCol[y][x] = true;
            } else if (a == 1) {
                isRow[y][x] = true;
            }
        }
        return isCanRemove;
    }
}
