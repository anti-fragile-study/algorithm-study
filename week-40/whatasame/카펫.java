// 가로 >= 세로

class Solution {
    public int[] solution(int brown, int yellow) {
        for (int h = 1; h <= yellow; h++) {
            // 노란색 가로, 세로의 경우의 수를 구한다 -> O(yellow)
            // (24, 1), (12, 2), (8, 3), (6, 4)
            if (yellow % h != 0) continue;

            int w = yellow / h;

            // 해당하는 경우에 갈색 개수를 구한다.
            // (w, h, w*2 + h*2 + 4)
            // (24, 1, 54), (12, 2, 32), (8, 3, 26), (6, 4, 24)
            int sum = w * 2 + h * 2 + 4;


            // 일치하는 경우에서 테두리만큼 더한다
            // (x, y) = (w + 2, h + 2)
            if (sum == brown) {
                return new int[]{w + 2, h + 2};
            }
        }

        throw new RuntimeException();
    }
}
