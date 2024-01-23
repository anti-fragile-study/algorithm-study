import java.util.*;

class Solution {

    private char[][] map;

    public int[] solution(String[] park, String[] routes) {
        /* Generate map */
        int nowX = -1, nowY = -1;
        int rowLength = park.length;
        int colLength = park[0].length();
        map = new char[rowLength][colLength];

        for (int x = 0; x < rowLength; x++) {
            for (int y = 0; y < colLength; y++) {
                char data = park[x].charAt(y);
                if (data == 'S') {
                    /* Set start position */
                    nowX = x;
                    nowY = y;
                }
                map[x][y] = data;
            }
        }

        /* Compute routes */
        for (String route : routes) {
            StringTokenizer st = new StringTokenizer(route, " ");
            char operate = st.nextToken().charAt(0);
            int amount = Integer.parseInt(st.nextToken());

            switch (operate) { // Check out of index and obstacle then move
                case 'N':
                    if (nowX - amount >= 0 && !hasObstacleInColumn(nowY, nowX, nowX - amount)) {
                        nowX -= amount;
                    }
                    break;
                case 'S':
                    if (nowX + amount < rowLength && !hasObstacleInColumn(nowY, nowX, nowX + amount)) {
                        nowX += amount;
                    }
                    break;
                case 'W':
                    if (nowY - amount >= 0 && !hasObstacleInRow(nowX, nowY, nowY - amount)) {
                        nowY -= amount;
                    }
                    break;
                case 'E':
                    if (nowY + amount < colLength && !hasObstacleInRow(nowX, nowY, nowY + amount)) {
                        nowY += amount;
                    }
                    break;

            }
        }

        /* Return final position */
        return new int[]{nowX, nowY};
    }

    private boolean hasObstacleInColumn(int colIdx, int from, int to) {
        if (from < to) {
            for (int x = from; x <= to; x++) {
                if (map[x][colIdx] == 'X') {
                    return true;
                }
            }
        } else {
            for (int x = from; x >= to; x--) {
                if (map[x][colIdx] == 'X') {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasObstacleInRow(int rowIdx, int from, int to) {
        if (from < to) {
            for (int y = from; y <= to; y++) {
                if (map[rowIdx][y] == 'X') {
                    return true;
                }
            }
        }
        // } else {
        //     for (int y = from; y >= to; y--) {
        //         if (map[rowIdx][y] == 'X') {
        //             return true;
        //         }
        //     }
        // }
        return false;
    }

}
