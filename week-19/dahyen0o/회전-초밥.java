import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, D, K, C;
    static int[] dishes;
    static int[] dishesCount;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dishes = new int[N];
        for(int n = 0; n < N; n++) {
            dishes[n] = Integer.parseInt(br.readLine());
        }

        bw.write(String.valueOf(solve()));
        bw.flush();
    }

    private static int solve() {
        int maxValue = 0;
        int currValue = 0;

        dishesCount = new int[D + 1];
        for(int idx = 0; idx < K; idx++) {
            if(++dishesCount[dishes[idx]] == 1) currValue++;
        }
        if(dishesCount[C] == 0) {
            maxValue = Math.max(maxValue, currValue + 1);
        } else {
            maxValue = Math.max(maxValue, currValue);
        }

        for(int start = 1, end = start + K - 1
            ; start < N
            ; start++, end = (end + 1) % N) {
            
            if(--dishesCount[dishes[start - 1]] == 0) currValue--;
            if(++dishesCount[dishes[end]] == 1) currValue++;
            
            if(dishesCount[C] == 0) {
                maxValue = Math.max(maxValue, currValue + 1);
            } else {
                maxValue = Math.max(maxValue, currValue);
            }
        }

        return maxValue;
    }
}
