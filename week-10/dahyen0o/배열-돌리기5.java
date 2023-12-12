import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ17276 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int num = Integer.parseInt(br.readLine());

        for (int q = 0; q < num; q++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            final int[][] arr = new int[N][N];
            final int angle = Integer.parseInt(st.nextToken());
            final int r = angle / 45;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    map[i][j] = value;
                    arr[i][j] = value;
                }
            }

            for (int i = 0; i < Math.abs(r); i++) {
                if (angle > 0) {
                    rotateRight();
                } else {
                    rotateLeft();
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = arr[i][j];
                    }
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    public static void rotateRight(){
        final int[][] temp = new int[N][N];

        map = temp;
    }

    public static void rotateLeft(){
        final int[][] temp = new int[N][N];

        map = temp;
    }
}
