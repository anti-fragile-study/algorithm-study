// package template;

import java.util.*;

class Solution {

    public String solution(final int n, int idx, String[] cmds) {
        final Stack<Integer> deleted = new Stack<>();

        final int[] prev = new int[n];
        final int[] next = new int[n];

        // init
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        for (String cmd : cmds) {
            String[] c = cmd.split(" ");
            if ("U".equals(c[0])) {
                final int count = Integer.parseInt(c[1]);
                for (int cnt = 0; cnt < count; cnt++) {
                    idx = prev[idx];
                }
                continue;
            }
            if ("D".equals(c[0])) {
                final int count = Integer.parseInt(c[1]);
                for (int cnt = 0; cnt < count; cnt++) {
                    idx = next[idx];
                }
                continue;
            }
            if ("C".equals(c[0])) {
                deleted.push(idx);
                if (next[idx] >= n) { // 마지막 행 삭제
                    int nextIdx = prev[idx];
                    next[nextIdx] = n;
                    idx = nextIdx;
                    continue;
                }
                int nextIdx = next[idx];
                if (prev[idx] >= 0)
                    next[prev[idx]] = nextIdx;
                if (nextIdx < n)
                    prev[nextIdx] = prev[idx];
                idx = nextIdx;
                continue;
            }
            if ("Z".equals(c[0])) {
                final int restored = deleted.pop();
                // 복구할 자리로 이동
                if (prev[restored] >= 0)
                    next[prev[restored]] = restored;
                if (next[restored] < n)
                    prev[next[restored]] = restored;
                continue;
            }
            throw new RuntimeException("이상한 커맨드");
        }

        final boolean[] lived = new boolean[n];
        for (int d : deleted) {
            lived[d] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!lived[i]) {
                sb.append("O");
                continue;
            }
            sb.append("X");
        }
        return sb.toString();
    }
}
