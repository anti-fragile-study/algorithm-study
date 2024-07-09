import java.util.*;

class Solution {
    int[][] dxdy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[][] graph = new int[n][m];

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.addLast(new int[]{0, 0});
        graph[0][0] = 1;

        int answer = 0;
        while (!dq.isEmpty()) {
            int[] temp = dq.removeFirst();
            int x = temp[0];
            int y = temp[1];
            if (x == n-1 && y == m-1) {
                answer = graph[x][y];
                break;
            }
            for (int[] d: dxdy) {
                int _x = x + d[0];
                int _y = y + d[1];
                if (_x < 0 || _x >= n || _y < 0 || _y >= m) {
                    continue;
                }
                if (maps[_x][_y] == 0 || graph[_x][_y] > 0) {
                    continue;
                }
                graph[_x][_y] = graph[x][y] + 1;
                dq.addLast(new int[]{_x, _y});
            }
        }
        if (answer > 0) {
            return answer;
        }
        return -1;
    }
}