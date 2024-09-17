import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;
    static List<int[]>[] adjList;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int x = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());
        adjList = new List[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            adjList[a].add(new int[]{b, c});
            adjList[b].add(new int[]{a, c});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{y, 0});
        dist[y] = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int now = p[0];
            int cost = p[1];
            if (dist[now] < cost) {
                continue;
            }
            for (int[] node : adjList[now]) {
                int next = node[0];
                int nextCost = node[1];
                if (dist[next] > nextCost + cost) {
                    dist[next] = nextCost + cost;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new int[]{i, dist[i]});
        }
        result.sort((o1, o2) -> o1[1] - o2[1]);

        int answer = 1;
        int total = 0;
        boolean canVisit = true;
        for (int[] node : result) {
            if ((total + node[1]) * 2 <= x) {
                total += node[1];
                continue;
            }
            if (node[1] * 2 <= x) {
                total = node[1];
                answer++;
            } else {
                canVisit = false;
                break;
            }
        }

        System.out.println(canVisit ? answer : -1);
    }
}
