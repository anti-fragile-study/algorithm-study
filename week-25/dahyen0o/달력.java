import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int LAST_DAY = 365;
    static final int[] dr = { 0, -1, 0, +1 };
    static final int[] dc = { -1, 0, +1, 0 };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());
        final int[][] schedules = new int[N][2];
        for (int n = 0; n < schedules.length; n++) {
            st = new StringTokenizer(br.readLine());
            schedules[n][0] = Integer.parseInt(st.nextToken());
            schedules[n][1] = Integer.parseInt(st.nextToken());
        }

        // schedules 정렬
        Arrays.sort(schedules, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int result = Integer.compare(a[0], b[0]);
                if (result != 0) {
                    return result;
                }
                return Integer.compare(b[1], a[1]);
            }
        });

        final boolean[][] calender = new boolean[N][LAST_DAY + 1];

        // calender 색칠
        for (final int[] schedule : schedules) {
            final int row = minRow(schedule, calender);
            fill(schedule, calender[row]);
        }

        // print(calender);

        // 코팅할 면적 구하기
        int answer = 0;
        int startC = 0, maxR = -1;
        for (int c = 0; c < calender[0].length; c++) {
            final int row = maxRow(c, calender);
            if (row == -1) { // 일정 없음
                answer += (c - 1 - startC) * (maxR + 1);

                startC = c;
                maxR = -1;
                continue;
            }

            maxR = Math.max(maxR, row);
        }
        answer += (calender[0].length - 1 - startC) * (maxR + 1);

        bw.write(sb.append(answer).append("\n").toString());
        bw.flush();
    }

    private static int maxRow(final int c, final boolean[][] calender) {
        int maxRow = -1;
        for (int r = 0; r < calender.length; r++) {
            if (calender[r][c]) {
                maxRow = r;
            }
        }
        return maxRow;
    }

    private static void print(boolean[][] calender) {
        for (int r = 0; r < calender.length; r++) {
            for (int c = 1; c <= 12; c++) {
                if (calender[r][c]) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void fill(final int[] schedule, final boolean[] cal) {
        for (int c = schedule[0]; c <= schedule[1]; c++) {
            if (cal[c])
                throw new IllegalAccessError("ALREADY FILLED");

            cal[c] = true;
        }
    }

    private static int minRow(final int[] schedule, final boolean[][] calender) {
        for (int r = 0; r < calender.length; r++) {
            boolean canRow = true;
            for (int day = schedule[0]; day <= schedule[1]; day++) {
                if (calender[r][day]) {
                    canRow = false;
                    break;
                }
            }

            if (canRow) {
                return r;
            }
        }
        throw new IllegalArgumentException("NO ROW TO FILL");
    }
}
