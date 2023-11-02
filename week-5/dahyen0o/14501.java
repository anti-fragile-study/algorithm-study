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

        final int N = Integer.parseInt(br.readLine());

        final int[][] infos = new int[N][2];
        for (int i = 0; i < infos.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                infos[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[day][0] = 해당 (day + 1)에 상담을 잡을 수 없는 경우 최대 금액
        // dp[day][1] = 해당 (day + 1)에 상담을 잡을 수 있는 경우 최대 금액
        final int[][] dp = new int[N][2];

        // 마지막 날 초기화
        final int lastDay = infos.length - 1;
        if (canGetMeeting(infos, lastDay)) {
            dp[lastDay][1] = infos[lastDay][1];
        }
        dp[lastDay][0] = 0;

        for (int day = infos.length - 2; day >= 0; day--) {
            // 현재 day에서 상담을 할 수 있음 => dp[day][1] 갱신 가능
            if (canGetMeeting(infos, day)) {
                dp[day][1] = infos[day][1];

                final int meetingDays = infos[day][0];
                if (day + meetingDays < infos.length) {
                    dp[day][1] += Math.max(dp[day + meetingDays][0], dp[day + meetingDays][1]);
                }
            }

            // 현재 day에서 상담을 하지 않음
            dp[day][0] = Math.max(dp[day + 1][0], dp[day + 1][1]);
        }

        sb.append(Math.max(dp[0][0], dp[0][1])).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean canGetMeeting(final int[][] infos, final int day) {
        return day + infos[day][0] <= infos.length;
    }
}
