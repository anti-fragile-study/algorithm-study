import java.util.*;

class Solution {
    int INF = 987654321;
    List<int[]>[] adjList;
    Set<Integer> summitSet = new HashSet<>();
    Set<Integer> gateSet = new HashSet<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int gate : gates) {
            gateSet.add(gate);
        }
        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int[] path : paths) {
            adjList[path[0]].add(new int[]{path[1], path[2]});
            adjList[path[1]].add(new int[]{path[0], path[2]});
        }

        return findPath(n);
    }

    int[] findPath(int n) {
        int[] answer = new int[]{INF, INF};
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] result = new int[n + 1];
        Arrays.fill(result, INF);

        for (int gate : gateSet) {
            pq.add(new int[]{gate, 0});
            result[gate] = 0;
        }

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int now = p[0];
            int w = p[1];

            if (w > result[now]) {
                continue;
            }
            if (summitSet.contains(now)) {
                if (answer[1] > w || (answer[1] == w && answer[0] > now)) {
                    answer[0] = now;
                    answer[1] = w;
                }
                continue;
            }

            for (int[] nextNode : adjList[now]) {
                int next = nextNode[0];
                int nextWeight = nextNode[1];
                if (gateSet.contains(next)) {
                    continue;
                }

                int intensity = Math.max(nextWeight, w);
                if (result[next] > intensity) {
                    result[next] = intensity;
                    pq.add(new int[]{next, result[next]});
                }
            }
        }
        return answer;
    }
}
