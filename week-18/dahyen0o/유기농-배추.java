import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder answer;

    static int M, N, K;
    static int[][] cabbages;
    static boolean[][] field;
    static boolean[][] isVisited;
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());
        answer = new StringBuilder();

        for(int t = 1; t <= T; t++) {
            readInput();
            answer.append(solution()).append('\n');
        }

        System.out.print(answer.toString());
    }

    private static int solution() {
        int count = 0;
        isVisited = new boolean[M][N];
        for(int k = 0; k < K; k++) {
            if(bfs(k)) count++;
        }
        return count;
    }

    private static boolean bfs(int k) {
        if(isVisited[cabbages[k][0]][cabbages[k][1]]) return false;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(cabbages[k]);
        isVisited[cabbages[k][0]][cabbages[k][1]] = true;
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = curr[0] + dx[i];
                int nextY = curr[1] + dy[i];
                if(canMove(nextX, nextY)) {
                    isVisited[nextX][nextY] = true;
                    queue.add(new int[] {nextX, nextY});
                }
            }
        }
        return true;
    }

    private static boolean canMove(int x, int y) {
        return x >= 0 && y >= 0 && x < M & y < N && !isVisited[x][y] && field[x][y];
    }

    private static void readInput() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cabbages = new int[K][2];
        field = new boolean[M][N];

        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            cabbages[k][0] = Integer.parseInt(st.nextToken());
            cabbages[k][1] = Integer.parseInt(st.nextToken());
            field[cabbages[k][0]][cabbages[k][1]] = true;
        }
    }
}
