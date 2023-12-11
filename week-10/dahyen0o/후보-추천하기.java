import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int pkGenerator = 0;

    static class Candidate implements Comparable<Candidate> {
        int student;
        int up;
        int pk;

        public Candidate(int student) {
            this.student = student;
            this.up = 1;
            this.pk = pkGenerator++;
        }

        @Override
        public String toString() {
            return "Candidate [student=" + student + ", up=" + up + "]";
        }

        @Override
        public int compareTo(Candidate o) {
            if (this.up == o.up) {
                return Integer.compare(this.pk, o.pk);
            }
            return Integer.compare(this.up, o.up);
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int MAX_STUDENT = 100;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());
        final int TOTAL_UP = Integer.parseInt(br.readLine());

        final Queue<Candidate> candidates = new PriorityQueue<>(N);

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < TOTAL_UP; idx++) {
            final int student = Integer.parseInt(st.nextToken());

            // 학생이 있는 지 탐색
            boolean exists = false;
            for (Candidate curr : candidates) {
                if (curr.student == student) {
                    candidates.remove(curr);
                    curr.up++;
                    candidates.add(curr);
                    exists = true;
                    break;
                }
            }
            if (exists) {
                continue;
            }

            // 사진틀에 새롭게 삽입
            if (candidates.size() == N) { // 포화 상태
                candidates.poll();
            }
            candidates.add(new Candidate(student));
        }

        final List<Integer> answer = new ArrayList<>();
        for (Candidate curr : candidates) {
            answer.add(curr.student);
        }
        answer.sort(Comparator.naturalOrder());
        for (Integer student : answer) {
            sb.append(student).append(" ");
        }

        bw.write(sb.append("\n").toString());
        bw.flush();
    }
}
