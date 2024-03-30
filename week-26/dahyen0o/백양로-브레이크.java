import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;
    static boolean[][] edges;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        edges = new boolean[N + 1][N + 1];

        final int M = Integer.parseInt(st.nextToken());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int bi = Integer.parseInt(st.nextToken());

            edges[a][b] = true;
            if (bi == 1) {
                edges[b][a] = true;
            }
        }

        // minChanges[start][end]: start -> end 경로의 정답
        final int[][] minChanges = new int[N + 1][];

        for (int start = 1; start <= N; start++) {
            minChanges[start] = dijkstra(start);
        }

        final int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            final int s = Integer.parseInt(st.nextToken());
            final int e = Integer.parseInt(st.nextToken());
            if (minChanges[s][e] == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("WRONG LOGIC");
            }
            sb.append(minChanges[s][e]).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static int[] dijkstra(final int START) {
        final int[] minChanges = new int[N + 1];
        Arrays.fill(minChanges, Integer.MAX_VALUE);
        minChanges[START] = 0;

        // {node, cost}
        final Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        queue.add(new int[] { START, 0 });

        while (!queue.isEmpty()) {
            final int[] curr = queue.poll();
            
            for (int next = 1; next <= N; next++) {
                if (edges[curr[0]][next] && minChanges[next] > curr[1]) { // 다음 가는 길 존재
                    minChanges[next] = curr[1];
                    queue.add(new int[] { next, minChanges[next] });
                    continue;
                }
                if (edges[next][curr[0]] && minChanges[next] > curr[1] + 1) { // 양뱡향으로 바꾸면 다음 가는 길 존재
                    minChanges[next] = curr[1] + 1;
                    queue.add(new int[] { next, minChanges[next] });
                    continue;
                }
            }
        }

        return minChanges;
    }
}
