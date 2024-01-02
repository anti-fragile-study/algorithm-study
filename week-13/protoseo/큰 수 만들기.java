import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int deleteCount = 0;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < number.length(); i++) {
            int now = number.charAt(i) - '0';
            if (stk.isEmpty() || deleteCount >= k) {
                stk.addLast(now);
                continue;
            }
            while (!stk.isEmpty() && now > stk.peekLast() && deleteCount < k) {
                stk.pollLast();
                deleteCount++;
            }
            stk.addLast(now);
        }
        while (deleteCount < k) {
            stk.pollLast();
            deleteCount++;
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pollFirst());
        }
        return sb.toString();
    }
}