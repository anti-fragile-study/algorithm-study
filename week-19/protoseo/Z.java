import java.io.*;

public class Main {

    static int[] dx = {0, 1, 0, 1};
    static int[] dy = {0, 0, 1, 1};

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int r = Integer.parseInt(split[1]);
        int c = Integer.parseInt(split[2]);
        System.out.println(find(n, r, c, 0, 0) - 1);
    }

    private static int find(int n, int r, int c, int x, int y) {
        int result = 0;
        if (n == 0 && y <= r && x <= c) {
            return 1;
        } else if (n == 0) {
            return 0;
        }

        int next = (int) Math.pow(2, n - 1);
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i] * next;
            int yy = y + dy[i] * next;
            int endX = xx + next - 1;
            int endY = yy + next - 1;
            if (xx <= c && c <= endX && yy <= r && r <= endY) {
                result += (i * next * next);
                result += find(n - 1, r, c, xx, yy);
            }
        }
        return result;
    }
}
