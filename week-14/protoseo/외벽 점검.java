import java.util.*;

class Solution {
    int[] d;
    int[] w;
    int m;
    int answer = -1;

    public int solution(int n, int[] weak, int[] dist) {
        d = dist;
        m = weak.length;
        w = new int[m * 2];
        for (int i = 0; i < m; i++) {
            w[i] = weak[i];
            w[i + m] = weak[i] + n;
        }
        find(0, new boolean[dist.length], new int[dist.length]);

        return answer;
    }

    private void checkWeakPoint(int start, int[] selected) {
        int result = -1;
        int count = 0;
        int weakPoint = w[start];

        for (int i = 0; i < selected.length; i++) {
            int covered = weakPoint + selected[i];

            while (count < m && weakPoint <= covered) {
                weakPoint = w[start + ++count];
            }

            if (count == m) {
                if (answer == -1 || answer > i + 1) {
                    answer = i + 1;
                }
                return;
            }
        }
    }

    private void find(int idx, boolean[] isUsed, int[] selected) {
        if (idx == d.length) {
            for (int i = 0; i < m; i++) {
                checkWeakPoint(i, selected);
            }
            return;
        }
        for (int i = 0; i < d.length; i++) {
            if (isUsed[i]) {
                continue;
            }
            isUsed[i] = true;
            selected[idx] = d[i];
            find(idx + 1, isUsed, selected);
            isUsed[i] = false;
        }
    }
}