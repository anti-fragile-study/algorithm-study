import java.util.*;

class Solution {

    int[][] isVisited = new int[501][501];
    int[] target = new int[2];

    public int solution(int alp, int cop, int[][] problems) {
        for (int i = 0; i < 501; i++) {
            Arrays.fill(isVisited[i], -1);
        }
        for (int[] problem : problems) {
            int a = problem[0];
            int c = problem[1];
            target[0] = Math.max(target[0], a);
            target[1] = Math.max(target[1], c);
        }
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });
        pq.add(new int[]{alp, cop, 0});
        isVisited[alp][cop] = 0;

        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            int nowAlp = p[0];
            int nowCop = p[1];
            int time = p[2];
            if (nowAlp >= target[0] && nowCop >= target[1]) {
                answer = time;
                break;
            }
            if (nowAlp > 150 && nowCop > 150) {
                continue;
            }
            for (int[] problem : problems) {
                int alpReq = problem[0];
                int copReq = problem[1];
                int alpRwd = problem[2];
                int copRwd = problem[3];
                int cost = problem[4];
                if (alpReq <= nowAlp && copReq <= nowCop) {
                    if (isVisited[nowAlp + alpRwd][nowCop + copRwd] == -1 || isVisited[nowAlp + alpRwd][nowCop + copRwd] > time + cost) {
                        isVisited[nowAlp + alpRwd][nowCop + copRwd] = time + cost;
                        pq.add(new int[]{nowAlp + alpRwd, nowCop + copRwd, time + cost});
                    }
                }
            }
            if (isVisited[nowAlp + 1][nowCop] == -1 || isVisited[nowAlp + 1][nowCop] > time + 1) {
                pq.add(new int[]{nowAlp + 1, nowCop, time + 1});
                isVisited[nowAlp + 1][nowCop] = time + 1;
            }
            if (isVisited[nowAlp][nowCop + 1] == -1 || isVisited[nowAlp][nowCop + 1] > time + 1) {
                pq.add(new int[]{nowAlp, nowCop + 1, time + 1});
                isVisited[nowAlp][nowCop + 1] = time + 1;
            }
        }

        return answer;
    }
}
