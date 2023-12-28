import java.util.*;

class Solution {

    // 안 이쁘지만 귀찮아서 제출
    
    private static final Map<Integer, int[]> coords = Map.of(
        1, new int[]{0, 0},
        2, new int[]{0, 1},
        3, new int[]{0, 2},
        4, new int[]{1, 0},
        5, new int[]{1, 1},
        6, new int[]{1, 2},
        7, new int[]{2, 0},
        8, new int[]{2, 1},
        9, new int[]{2, 2},
        0, new int[]{3, 1}
    );
    
    public String solution(final int[] numbers, final String hand) {
        final StringBuilder answer = new StringBuilder();
        
        int[] left = new int[]{3, 0};
        int[] right = new int[]{3, 2};
        
        for(int number : numbers) {
            if(number % 3 == 1) {
                answer.append('L');
                left = coords.get(number);
            } else if(number % 3 == 0 && number != 0) {
                answer.append('R');
                right = coords.get(number);
            } else {
                if(distance(left, coords.get(number)) == distance(right, coords.get(number))) {
                    if(hand.equals("left")) {
                        answer.append('L');
                        left = coords.get(number);
                    } else {
                        answer.append('R');
                        right = coords.get(number);
                    }
                }
                else if(distance(left, coords.get(number)) > distance(right, coords.get(number))) {
                    answer.append('R');
                    right = coords.get(number);
                } 
                else {
                    answer.append('L');
                    left = coords.get(number);
                }
            }
            // System.out.printf("left:%d,%d    right:%d,%d%n", left[0], left[1], right[0], right[1]);
        }
        
        return answer.toString();
    }
    
    private int distance(final int[] start, final int[] end) {
        return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
    }
}
