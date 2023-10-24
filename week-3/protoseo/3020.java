import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String s = br.readLine();
        final String[] split = s.split(" ");

        final int n = Integer.parseInt(split[0]);
        final int h = Integer.parseInt(split[1]);

        final int[] up = new int[n];
        final int[] down = new int[n];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                up[i] = Integer.parseInt(br.readLine()) - 1;
            } else {
                down[i] = h - Integer.parseInt(br.readLine());
            }
        }

        final int[] l = new int[h];
        final int[] r = new int[h];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                l[up[i]]++;
            } else {
                r[down[i]]++;
            }
        }

        for (int i = 1; i < h; i++) {
            r[i] += r[i - 1];
            l[h - i - 1] += l[h - i];
        }

        final int[] result = new int[h];
        for (int i = 0; i < h; i++) {
            result[i] = l[i] + r[i];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            if (min > result[i]) {
                min = result[i];
                cnt = 1;
            } else if (min == result[i]) {
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }
}
