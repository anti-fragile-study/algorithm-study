import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int n = Integer.parseInt(br.readLine());
        int answer = 0;
        int sum = 0;
        int l = 0;
        int r = 0;
        while (l <= n) {
            if (sum == n) {
                answer++;
                sum -= ++l;
            } else if (sum < n) {
                sum += ++r;
            } else {
                sum -= ++l;
            }
        }
        System.out.println(answer);
    }
}
