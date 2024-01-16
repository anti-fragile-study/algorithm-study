import java.util.*;

class Solution {
    static final int L = 1000000;
    List<Integer>[] adjList = new ArrayList[L + 1];
    int[] outDegree = new int[L + 1];
    int[] inDegree = new int[L + 1];
    boolean[] isVisited = new boolean[L + 1];
    
    public int[] solution(int[][] edges) {
        for (int i = 0; i <= L; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList[from].add(to);
            inDegree[to]++;
            outDegree[from]++;
        }
        int[] answer = new int[4];
        for (int i = 1; i <= L; i++) {
            if (outDegree[i] >= 2 && inDegree[i] == 0) {
                answer[0] = i;
                break;
            }
        }
        for (int now : adjList[answer[0]]) {
            int cycleCount = findCycleCount(now);
            if (cycleCount == 0) {
                answer[2]++;
            } else if (cycleCount == 2) {
                answer[3]++;
            } else {
                answer[1]++;
            }
        }
        return answer;
    }
    
    private int findCycleCount(int s) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(s);
        isVisited[s] = true;
        
        int cycleCount = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int next : adjList[now]) {
                if (isVisited[next]) {
                    cycleCount++;
                    continue;
                }
                q.add(next);
               	isVisited[next] = true;
            }
        }
        return cycleCount; 
    }
}