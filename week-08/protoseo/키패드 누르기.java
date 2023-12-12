class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[] left = new int[]{0, 3};
        int[] right = new int[]{2, 3};
        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                left[0] = 0;
                left[1] = number / 3;
                answer.append('L');
            } else if (number == 3 || number == 6 || number == 9) {
                right[0] = 2;
                right[1] = (number - 1) / 3;
                answer.append('R');
            } else {
                int leftDist = Math.abs(left[0] - 1);
                int rightDist = Math.abs(right[0] - 1);
                if (number == 0) {
                    leftDist += Math.abs(left[1] - 3);
                    rightDist += Math.abs(right[1] - 3);
                } else {
                    leftDist += Math.abs(left[1] - (number / 3));
                    rightDist += Math.abs(right[1] - (number / 3));
                }

                if (leftDist > rightDist || (leftDist == rightDist && hand.equals("right"))) {
                    right[0] = 1;
                    right[1] = (number == 0) ? 3 : (number / 3);
                    answer.append('R');
                } else if (leftDist < rightDist || (leftDist == rightDist && hand.equals("left"))) {
                    left[0] = 1;
                    left[1] = (number == 0) ? 3 : (number / 3);
                    answer.append('L');
                }
            }
        }
        return answer.toString();
    }
}
