// 사전순: d - l - r - u

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int[] moved = new int[4]; // 0: down, 1: left, 2: right, 3: up

        // 최단 거리 이동
        int remain = k - Math.abs(x - r) - Math.abs(y - c);

        if (remain < 0 || remain % 2 != 0) return "impossible";

        if (x > r) {
            moved[3] += x - r;
        } else {
            moved[0] += r - x;
        }
        if (y > c) {
            moved[1] += y - c;
        } else {
            moved[2] += c - y;
        }

        // 남은 경로 비용 채우기
        int maxDownable = n - (x + moved[0]);
        int vLoop = Math.min(remain / 2, maxDownable); // vertical loop
        moved[0] += vLoop;
        moved[3] += vLoop;
        remain -= vLoop * 2;

        int maxLeftable = y - moved[1] - 1; // -1: array index is 0
        int hLoop = Math.min(remain / 2, maxLeftable); // horizontal loop
        moved[1] += hLoop;
        moved[2] += hLoop;
        remain -= hLoop * 2;

        // 경로 계산
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < moved[0]; i++) {
            answer.append('d');
        }
        for (int i = 0; i < moved[1]; i++) {
            answer.append('l');
        }
        for (int i = 0; i < remain / 2; i++) { // 남은 경로 loop
            answer.append('r');
            answer.append('l');
        }
        for (int i = 0; i < moved[2]; i++) {
            answer.append('r');
        }
        for (int i = 0; i < moved[3]; i++) {
            answer.append('u');
        }

        return answer.toString();
    }
}
