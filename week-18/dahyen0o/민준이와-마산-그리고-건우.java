import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        int node;
        long cost;

        public Edge(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge [node=" + node + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static StringBuilder sb;

    private static List<Edge>[] nextEdges;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int V = Integer.parseInt(st.nextToken());
        final int E = Integer.parseInt(st.nextToken());
        final int P = Integer.parseInt(st.nextToken());

        nextEdges = new List[V + 1];
        for (int i = 0; i < nextEdges.length; i++) {
            nextEdges[i] = new ArrayList<>();
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            nextEdges[a].add(new Edge(b, cost));
            nextEdges[b].add(new Edge(a, cost));
        }

        final long minDist = minDist(1, V);
        if (minDist < minDist(1, P) + minDist(P, V)) {
            sb.append("GOOD BYE");
        } else {
            sb.append("SAVE HIM");
        }
        
        bw.write(sb.append("\n").toString());
        bw.flush();
    }

    private static long minDist(final int start, final int end) {
        final Queue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));

        final long[] dists = new long[nextEdges.length];
        Arrays.fill(dists, Long.MAX_VALUE / 3);
        dists[start] = 0;
        
        while (!queue.isEmpty()) {
            final Edge curr = queue.poll();

            for (final Edge next : nextEdges[curr.node]) {
                if (curr.cost + next.cost < dists[next.node]) {
                    dists[next.node] = curr.cost + next.cost;
                    queue.add(new Edge(next.node, curr.cost + next.cost));
                }
            }
        }
        return dists[end];
    }
}
