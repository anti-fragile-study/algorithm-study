import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
     
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

        final boolean[] woman = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            if (st.nextToken().equals("W")) {
                woman[n] = true;
                continue;
            }
        }

        final Queue<Edge> queue = new PriorityQueue<>();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            if (woman[a] == woman[b]) {
                continue;
            }
            queue.add(new Edge(a, b, cost));
        }

        sb.append(mst(queue, woman)).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }
    
    private static int mst(final Queue<Edge> queue, final boolean[] woman) {
        // Kruskal MST
        final int[] parents = new int[woman.length];
        initRoots(parents);

        int edgeCount = 0;
        int cost = 0;

        while (!queue.isEmpty()) {
            final Edge curr = queue.poll();

            if (findRoot(curr.a, parents) == findRoot(curr.b, parents)) { // 사이클 발생
                continue;
            }

            edgeCount++;
            cost += curr.cost;
            union(curr.a, curr.b, parents);
        }

        return (edgeCount == woman.length - 2) ? cost : -1;
    }

    private static void union(int a, int b, int[] parents) {
        final int rootA = findRoot(a, parents);
        final int rootB = findRoot(b, parents);
        if (rootA > rootB) {
            parents[rootA] = parents[rootB];
        } else {
            parents[rootB] = parents[rootA];
        }
    }

    private static int findRoot(final int curr, final int[] parents) {
        if (parents[curr] == curr) {
            return curr;
        }
        return parents[curr] = findRoot(parents[curr], parents);
    }

    private static void initRoots(int[] roots) {
        for (int i = 1; i < roots.length; i++) {
            roots[i] = i;
        }
    }

    private static class Edge implements Comparable<Edge> {
    
        final int a, b;
        final int cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
