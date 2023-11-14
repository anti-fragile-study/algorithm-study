import java.util.*;

class Solution {

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            if (isAnswer(places[i])) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    public boolean isAnswer(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    Deque<int[]> q = new ArrayDeque<>();
                    boolean[][] isVisited = new boolean[5][5];
                    q.add(new int[]{j, i, 0});
                    isVisited[i][j] = true;

                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0];
                        int y = p[1];
                        int cnt = p[2];
                        if (cnt == 2) {
                            continue;
                        }
                        for (int k = 0; k < 4; k++) {
                            int xx = x + dx[k];
                            int yy = y + dy[k];
                            if (xx < 0 || yy < 0 || xx >= 5 || yy>= 5 || isVisited[yy][xx]) {
                                continue;
                            }
                            char c = place[yy].charAt(xx);
                            if (c == 'X') {
                                continue;
                            }
                            if (c == 'P') {
                                return false;
                            }
                            q.add(new int[]{xx, yy, cnt + 1});
                            isVisited[yy][xx] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
