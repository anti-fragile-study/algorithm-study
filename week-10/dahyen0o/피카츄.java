import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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

        String input = br.readLine();
        input = input.replaceAll("pi|ka|chu", "");
        if (input.isEmpty()) {
            sb.append("YES");
        } else {
            sb.append("NO");
        }
        
        bw.write(sb.toString());
        bw.flush();
    }
}
