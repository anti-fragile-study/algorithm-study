import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
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

        final int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            if (canVPS(br.readLine())) {
                sb.append("YES\n");
                continue;
            }
            sb.append("NO\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean canVPS(final String PS) {
        int stack = 0;
        for (int i = 0; i < PS.length(); i++) {
            final char ch = PS.charAt(i);
            if (ch == '(') {
                stack++;
                continue;
            }
            if (ch == ')' && stack > 0) {
                stack--;
                continue;
            }
            return false;
        }
        return stack == 0;
    }
}
