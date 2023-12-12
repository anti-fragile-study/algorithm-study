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

    static int N;
    static int[] array, lis;

    public static void main(String[] args) throws Exception {
      
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        
        array = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n++) {
            array[n] = Integer.parseInt(st.nextToken());
        }

        lis = new int[N];
        Arrays.fill(lis, Integer.MAX_VALUE);

        int lisIdx = 0;

        lis[lisIdx++] = array[0];

        for (int n = 1; n < N; n++) {
            if (array[n] > lis[lisIdx - 1]) {
                lis[lisIdx++] = array[n];
            } else {
                lis[binarySearch(0, lisIdx, array[n])] = array[n];
            }
        }

        bw.write(sb.append(lisIdx).toString());
        bw.flush();
    }

    private static int binarySearch(int left, int right, int key) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (lis[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
