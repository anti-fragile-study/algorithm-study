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
        int start, end;
        long cost;

        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int[] starts = new int[3];

    static long[] finalDists;
    static long maxDist = -1;
    static int minNum = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            starts[i] = Integer.parseInt(st.nextToken());
        }

        final int M = Integer.parseInt(br.readLine());
        final List<Edge>[] edges = new List[N + 1];

        for (int idx = 0; idx < edges.length; idx++) {
            edges[idx] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(a, b, cost));
            edges[b].add(new Edge(b, a, cost));
        }

        finalDists = new long[N + 1];
        Arrays.fill(finalDists, Long.MAX_VALUE);

        for (int start : starts) {
            dijkstra(start, edges, N, M);
        }

        for (int idx = 1; idx < finalDists.length; idx++) {
            if (finalDists[idx] != Long.MAX_VALUE && finalDists[idx] > maxDist) {
                maxDist = finalDists[idx];
                minNum = idx;
            }
        }

        sb.append(minNum).append("\n");
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dijkstra(final int start, final List<Edge>[] edges, final int N, final int M) {
        final long[] dists = new long[N + 1];
        Arrays.fill(dists, 6 * 1_000_000_000L);
        dists[start] = 0;

        final PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, start, 0));

        while (!priorityQueue.isEmpty()) {
            final Edge curr = priorityQueue.poll();

            edges[curr.end].forEach(next -> {
                if (dists[next.end] > dists[next.start] + next.cost) {
                    dists[next.end] = dists[next.start] + next.cost;
                    priorityQueue.add(new Edge(next.start, next.end, dists[next.end]));
                }
            });
        }

        for (int idx = 1; idx < dists.length; idx++) {
            finalDists[idx] = Math.min(finalDists[idx], dists[idx]);
        }
    }
}
