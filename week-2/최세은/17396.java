import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int idx;
        long dist;

        public Edge(int to, long dist) {
            this.idx = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(dist, o.dist);
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        final boolean[] canEnemyWatch = new boolean[N];
        for (int n = 0; n < canEnemyWatch.length; n++) {
            canEnemyWatch[n] = st.nextToken().charAt(0) != '0';
        }
        canEnemyWatch[N - 1] = false;

        final List<Edge>[] edges = new List[N];
        for (int n = 0; n < edges.length; n++) {
            edges[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int t = Integer.parseInt(st.nextToken());

            if (canEnemyWatch[a] || canEnemyWatch[b]) {
                continue;
            }
            edges[a].add(new Edge(b, t));
            edges[b].add(new Edge(a, t));
        }

        final long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Edge> pQueue = new PriorityQueue<>();
        pQueue.add(new Edge(0, 0));
        dist[0] = 0;

        while (!pQueue.isEmpty()) {
            final Edge curr = pQueue.poll();

            if (curr.idx == N - 1) {
                break;
            }

            // 이미 dist[curr.idx] 값이 최소라 더 이상의 처리 불필요
            if (dist[curr.idx] < curr.dist) {
                continue;
            }

            for (Edge next : edges[curr.idx]) {
                if (dist[next.idx] > dist[curr.idx] + next.dist) {
                    dist[next.idx] = dist[curr.idx] + next.dist;
                    pQueue.add(new Edge(next.idx, dist[next.idx]));
                }
            }
        }
    
        long result = dist[N - 1] == Long.MAX_VALUE ? -1 : dist[N - 1];

        sb.append(result).append('\n');
        bw.write(sb.toString());
        bw.flush();
    }
}
