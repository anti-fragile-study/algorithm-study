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

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (N > 0) {
            if (R >= Math.pow(2, N - 1)) {
                R -= Math.pow(2, N - 1);
                answer += Math.pow(2, N * 2 - 1);
            }
            if (C >= Math.pow(2, N - 1)) {
                C -= Math.pow(2, N - 1);
                answer += Math.pow(2, N * 2 - 2);
            }
            N--;
        }
        
        bw.write(sb.append(answer).append("\n").toString());
        bw.flush();
    }
}
