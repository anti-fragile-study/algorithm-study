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
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX = Integer.MAX_VALUE;

    static int N, M, X, Y;
    static List<int[]>[] edges;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        edges = new List[N];
        for (int n = 0; n < N; n++) {
            edges[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            edges[a].add(new int[] { b, cost });
            edges[b].add(new int[] { a, cost });
        }

        final int[] dists = dijkstra(Y);
        // System.out.println(Arrays.toString(dists));
        sb.append(minDays(dists)).append("\n");
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static int minDays(final int[] dists) {
        Arrays.sort(dists);
        if (((long) dists[dists.length - 1]) * 2 > X) {
            return -1;
        }

        final Queue<Long> queue = new PriorityQueue<>();
        for (final int dist : dists) {
            if (queue.isEmpty() || queue.peek() + 2 * (long)dist > X) {
                queue.add(2 * (long) dist);
            } else {
                final long curr = queue.poll();
                queue.add(curr + 2 * (long) dist);
            }
        }
        return queue.size();
    }

    private static int[] dijkstra(final int start) {
        final int[] dists = new int[N];
        Arrays.fill(dists, MAX);
        dists[start] = 0;

        final Queue<int[]> queue = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        queue.add(new int[] { start, 0 });

        while (!queue.isEmpty()) {
            final int[] curr = queue.poll();

            for (final int[] next : edges[curr[0]]) {
                if (dists[next[0]] > dists[curr[0]] + next[1]) {
                    dists[next[0]] = dists[curr[0]] + next[1];
                    queue.add(new int[] {next[0], dists[next[0]]});
                }
            }
        }
        return dists;
    }
}
