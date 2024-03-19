import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int[] dr = { 0, -1, 0, +1 };
    static final int[] dc = { -1, 0, +1, 0 };

    static int N;
    static int[][] board;
    static Set<Integer>[] favsByStudent;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        favsByStudent = new Set[N * N + 1];

        for (int count = 0; count < N * N; count++) {
            st = new StringTokenizer(br.readLine());
            final int student = Integer.parseInt(st.nextToken());

            favsByStudent[student] = new HashSet<>(4);
            for (int fav = 0; fav < 4; fav++) {
                favsByStudent[student].add(Integer.parseInt(st.nextToken()));
            }

            setStudent(student);
        }

        
        bw.write(sb.append(satisfactions()).append("\n").toString());
        bw.flush();
    }

    private static int satisfactions() {
        int result = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 0) {
                    continue;
                }

                result += (int) Math.pow(10, countFav(r, c, favsByStudent[board[r][c]]) - 1);
            }
        }
        return result;
    }

    private static void setStudent(final int student) {
        final Set<Integer> favs = favsByStudent[student];

        // 1. 좋아하는 학생이 인접한 칸에 가장 많은 자리
        int max = 0; // 인접한 자리에 있는 좋아하는 학생 수
        // max 계산
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] > 0) {
                    continue;
                }
                max = Math.max(max, countFav(r, c, favs));
            }
        }
        // max인 빈 자리 목록 구하기
        final Queue<int[]> candidates = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] > 0) {
                    continue;
                }
                if (max == countFav(r, c, favs)) {
                    candidates.add(new int[] { r, c });
                }
            }
        }
        // 후보가 하나면 끝
        if (candidates.size() == 1) {
            final int[] seat = candidates.poll();
            board[seat[0]][seat[1]] = student;
            return;
        }

        // 2. 비어있는 칸이 가장 많은 자리
        max = 0; // 인접한 자리에 비어있는 좌석 수
        // max 계산
        for (final int[] candidate : candidates) {
            max = Math.max(max, countEmpty(candidate[0], candidate[1]));
        }
        // max인 빈 자리 목록 업데이트
        final int size = candidates.size();
        for (int count = 0; count < size; count++) {
            final int[] candidate = candidates.poll();
            if (countEmpty(candidate[0], candidate[1]) == max) {
                candidates.add(candidate);
            }
        }
        // 후보가 하나면 끝
        if (candidates.size() == 1) {
            final int[] seat = candidates.poll();
            board[seat[0]][seat[1]] = student;
            return;
        }
        
        // 3. 행, 열 순으로 번호가 가장 작은 자리
        final int[] seat = candidates.poll();
        board[seat[0]][seat[1]] = student;
    }

    private static int countEmpty(int R, int C) {
        int empty = 0;
        for (int dir = 0; dir < 4; dir++) {
            final int r = R + dr[dir];
            final int c = C + dc[dir];

            if (inBound(r, c) && board[r][c] == 0) {
                empty++;
            }
        }
        return empty;
    }

    private static int countFav(final int R, final int C, final Set<Integer> favs) {
        int fav = 0;
        for (int dir = 0; dir < 4; dir++) {
            final int r = R + dr[dir];
            final int c = C + dc[dir];

            if (!inBound(r, c)) {
                continue;
            }

            if (favs.contains(board[r][c])) {
                fav++;
            }
        }
        return fav;
    }

    private static boolean inBound(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
