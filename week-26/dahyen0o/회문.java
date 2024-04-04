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

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            final String line = br.readLine();
            sb.append(check(line, 0, line.length() - 1, false)).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static int check(final String line, int start, int end, final boolean deleted) {
        while (start < end) {
            if (line.charAt(start) == line.charAt(end)) {
                start++;
                end--;
                continue;
            }
            if (deleted) {
                return 2;
            }

            int result = 2;
            if (line.charAt(start + 1) == line.charAt(end)) {
                result = Math.min(result, check(line, start + 1, end, true));
            }
            if (line.charAt(start) == line.charAt(end - 1)) {
                result = Math.min(result, check(line, start, end - 1, true));
            }
            return result;
        }
        return deleted ? 1 : 0;
    }
}
