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

    static int ROW, COL;
    static long[][] prefixSums;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());

        prefixSums = new long[ROW + 1][COL + 1];
        for (int r = 1; r < prefixSums.length; r++) {
            st = new StringTokenizer(br.readLine());
            prefixSums[r][0] = prefixSums[r - 1][COL];
            for (int c = 1; c < prefixSums[r].length; c++) {
                final int curr = Integer.parseInt(st.nextToken());
                prefixSums[r][c] = prefixSums[r][c - 1] + prefixSums[r - 1][c] - prefixSums[r - 1][c - 1] + curr;
            }
        }

        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            final int x1 = Integer.parseInt(st.nextToken());
            final int y1 = Integer.parseInt(st.nextToken());
            final int x2 = Integer.parseInt(st.nextToken());
            final int y2 = Integer.parseInt(st.nextToken());

            sb
            .append(prefixSums[x2][y2] - prefixSums[x2][y1 - 1] - prefixSums[x1 - 1][y2] + prefixSums[x1 - 1][y1 - 1])
            .append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }
}
