class Solution {

    int[] dx = {10, 20, 30, 40};
    int[] answer = new int[2];
    int n = 0;
    int m = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        find(0, new int[m], users, emoticons);
        return answer;
    }

    public void find (int now, int[] percentages, int[][] users, int[] emoticons) {
        if (now == m) {
            int[] result = new int[2];
            for (int i = 0; i < n; i++) {
                int ratio = users[i][0];
                int useMoney = 0;
                for (int j = 0; j < m; j++) {
                    if (percentages[j] >= ratio) {
                        useMoney += ((emoticons[j] * ((100 - percentages[j]))) / 100);
                    }
                }
                if (useMoney >= users[i][1]) {
                    result[0]++;
                } else {
                    result[1] += useMoney;
                }
            }
            if (result[0] > answer[0]) {
                answer = result;
            } else if (result[0] == answer[0] && result[1] > answer[1]) {
                answer = result;
            }

            return;
        }
        for (int i = 0; i < 4; i++) {
            percentages[now] = dx[i];
            find(now + 1, percentages, users, emoticons);
        }
    }
}
