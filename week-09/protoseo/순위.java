import java.util.*;

class Solution {
    List<Integer>[] win;
    List<Integer>[] lose;
    public int solution(int n, int[][] results) {
        win = new ArrayList[n + 1];
        lose = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            win[i] = new ArrayList<>();
            lose[i] = new ArrayList<>();
        }
        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            win[winner].add(loser);
            lose[loser].add(winner);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int winCount = dfs(i, 0, win, new boolean[n + 1]);
            int loseCount = dfs(i, 0, lose, new boolean[n + 1]);
            if (winCount + loseCount == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    int dfs(int now, int cnt, List<Integer>[] adjList, boolean[] isVisited) {
        if (isVisited[now]) {
            return cnt;
        }
        isVisited[now] = true;
        for (int next : adjList[now]) {
            if (isVisited[next]) {
                continue;
            }
            cnt = Math.max(cnt, dfs(next, cnt + 1, adjList, isVisited));
        }
        return cnt;
    }
}
