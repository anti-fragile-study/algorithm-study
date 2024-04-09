import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int N = 11;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            final int[][] scores = new int[N][N];
            for (int player = 0; player < N; player++) {
                st = new StringTokenizer(br.readLine());
                for (int s = 0; s < N; s++) {
                    scores[player][s] = Integer.parseInt(st.nextToken());
                }
            }

            answer = -1;
            setPlayer(scores, new boolean[N], 0, 0);

            if (answer == -1) {
                throw new IllegalArgumentException("INVALID LOGIC");
            }
            sb.append(answer).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static void setPlayer(final int[][] scores, final boolean[] visited, final int player, final int score) {
        if (player == scores.length) {
            answer = Math.max(answer, score);
            return;
        }

        for (int position = 0; position < scores[player].length; position++) {
            if (scores[player][position] == 0) {
                continue;
            }
            if (visited[position]) {
                continue;
            }

            visited[position] = true;
            setPlayer(scores, visited, player + 1, score + scores[player][position]);
            visited[position] = false;
        }
    }
}
