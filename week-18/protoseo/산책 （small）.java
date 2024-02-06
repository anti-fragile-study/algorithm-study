import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = 987654321;
    private static List<Integer>[] adjList;
    private static boolean[] isVisited;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        adjList = new ArrayList[n + 1];
        isVisited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            adjList[a].add(b);
            adjList[b].add(a);
        }
        for (int i = 1; i <= n; i++) {
            adjList[i].sort((o1, o2) -> o1 - o2);
        }
        split = br.readLine().split(" ");
        int s = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);
        int answer = dijkstra(s, e);
        answer += dijkstra(e, s);
        System.out.println(answer);
    }

    private static int dijkstra(int s, int e) {
        int[] result = new int[n + 1];
        int[] visited = new int[n + 1];
        Arrays.fill(result, INF);

        Deque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(s, 0));
        result[s] = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int now = p.e;
            int weight = p.w;

            if (result[now] < weight) {
                continue;
            }
            for (int next : adjList[now]) {
                if (isVisited[next] || visited[next] > 0) {
                    continue;
                }
                if (result[next] > result[now] + 1) {
                    result[next] = result[now] + 1;
                    visited[next] = now;
                    q.add(new Pair(next, result[next]));
                }
            }
        }

        int distCount = 0;
        int nextNode = e;
        while (nextNode > 0) {
            distCount++;
            nextNode = visited[nextNode];
            if (nextNode == s) {
                break;
            }
            isVisited[nextNode] = true;
        }
        return distCount;
    }
}

class Pair {
    int e;
    int w;

    public Pair(int e, int w) {
        this.e = e;
        this.w = w;
    }
}
