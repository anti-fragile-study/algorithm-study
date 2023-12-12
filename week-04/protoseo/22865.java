import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        final String[] split = br.readLine().split(" ");
        int[] friends = new int[3];
        friends[0] = Integer.parseInt(split[0]);
        friends[1] = Integer.parseInt(split[1]);
        friends[2] = Integer.parseInt(split[2]);

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            adjList[s].add(new Node(e, d));
            adjList[e].add(new Node(s, d));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < 3; i++) {
            pq.add(new int[]{friends[i], 0});
            dist[friends[i]] = 0;
        }

        while (!pq.isEmpty()) {
            final int[] p = pq.poll();
            int now = p[0];
            int weight = p[1];

            if (dist[now] < weight) {
                continue;
            }

            for (Node node : adjList[now]) {
                int next = node.e;
                int nextWeight = node.d;
                if (dist[now] + nextWeight < dist[next]) {
                    dist[next] = dist[now] + nextWeight;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        int answer = 0;
        int maxDist = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (maxDist < dist[i]) {
                maxDist = dist[i];
                answer = i;
            }
        }
        System.out.println(answer);
    }
}

class Node {

    int e;
    int d;

    public Node(final int e, final int d) {
        this.e = e;
        this.d = d;
    }
}
