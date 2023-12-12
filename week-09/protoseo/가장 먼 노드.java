import java.util.*;

class Solution {
    int[] isVisited;
    List<Integer>[] adjList;
    public int solution(int n, int[][] edge) {
        isVisited = new int[n + 1];
        Arrays.fill(isVisited, -1);
        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            adjList[e[0]].add(e[1]);
            adjList[e[1]].add(e[0]);
        }

        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        isVisited[1] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : adjList[now]) {
                if (isVisited[next] != -1) {
                    continue;
                }
                q.add(next);
                isVisited[next] = isVisited[now] + 1;
            }
        }
        int dist = 0;
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (dist < isVisited[i]) {
                answer = 1;
                dist = isVisited[i];
            } else if (dist == isVisited[i]) {
                answer++;
            }
        }
        return answer;
    }
}
