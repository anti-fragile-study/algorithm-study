import java.io.*;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        find(n, getOddCount(n));
        System.out.println(min + " " + max);
    }

    static void find(int n, int count) {
        int len = getLength(n);
        if (len == 1) {
            max = Math.max(max, count);
            min = Math.min(min, count);
        } else if (len == 2) {
            int next = n / 10 + n % 10;
            find(next, count + getOddCount(next));
        } else {
            var str = "" + n;
            for (int i = 1; i < len; i++) {
                int a = Integer.parseInt(str.substring(0, i));
                for (int j = i + 1; j < len; j++) {
                    int b = Integer.parseInt(str.substring(i, j));
                    int c = Integer.parseInt(str.substring(j));
                    find(a + b + c, count + getOddCount(a + b + c));
                }
            }
        }
    }

    static int getOddCount(int n) {
        int result = 0;
        while (n > 0) {
            int t = n % 10;
            if (t % 2 == 1) {
                result++;
            }
            n /= 10;
        }
        return result;
    }

    static int getLength(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 0;
        while (n > 0) {
            n /= 10;
            result++;
        }
        return result;
    }
}
