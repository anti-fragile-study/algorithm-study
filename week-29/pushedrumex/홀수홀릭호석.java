import java.io.*;

public class Main {
    static int minValue = Integer.MAX_VALUE;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dfs(N, 0);
        System.out.println(minValue + " " + maxValue);
    }

    static int countOdd(int N) {
        int odd = 0;
        String[] str = String.valueOf(N).split("");
        for (String n: str) {
            if (Integer.parseInt(n) % 2 != 0) {
                odd++;
            }
        }
        return odd;
    }

    static void dfs(int N, int odd) {
        odd += countOdd(N);
        int length = String.valueOf(N).length();

        if (length == 1) {
            minValue = Math.min(minValue, odd);
            maxValue = Math.max(maxValue, odd);
        } else if (length == 2) {
            dfs(N / 10 + N % 10, odd);
        } else {
            String str = String.valueOf(N);
            for (int i = 1; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    int next = Integer.parseInt(str.substring(0, i)) +
                            Integer.parseInt(str.substring(i, j)) +
                            Integer.parseInt(str.substring(j, length));

                    dfs(next, odd);
                }
            }
        }
    }

}
