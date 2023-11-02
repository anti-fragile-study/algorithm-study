/*
입력값  0 -> pop
입력값 not 0 -> push
*/

/*
1 1 1 1 1 -> nothing
2 9 -1 0 3 -> -1
0 0 0 0 0 -> 0 0 0 0 0
-4 -3 -2 0 0 0 -> -2 -3 -4
*/

import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static AbsNum[] nums;

    public static List<Integer> run() {
        PriorityQueue<AbsNum> pq = new PriorityQueue<>();

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i].value == 0) { // pop
                if (pq.isEmpty()) {
                    answer.add(0);
                } else {
                    answer.add(pq.remove().toInt());
                }
            } else { // push
                pq.add(nums[i]);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new AbsNum[n];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = new AbsNum(num);
        }

        List<Integer> answer = run();

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append('\n');
        }
        System.out.println(sb);
    }

    public static class AbsNum implements Comparable<AbsNum> {

        int sign;
        int value;

        public AbsNum(int num) {
            this.sign = num > 0 ? 1 : -1;
            this.value = Math.abs(num);
        }

        public int toInt() {
            return value * sign;
        }

        @Override
        public int compareTo(AbsNum other) {
            if (this.value == other.value) {
                return this.sign - other.sign;
            }

            return this.value - other.value;
        }
    }
}
