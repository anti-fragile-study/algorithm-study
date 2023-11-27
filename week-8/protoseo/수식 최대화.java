import java.util.*;

class Solution {
    String[] orders = {"*-+", "*+-", "+-*", "+*-", "-*+", "-+*"};
    List<Long> numbers = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    public long solution(String expression) {
        init(expression);
        long answer = -1;
        for (String order : orders) {
            List<Long> nowNumbers = new ArrayList<>(numbers);
            List<Character> nowOperators = new ArrayList<>(operators);
            for (int j = 0; j < 3; j++) {
                Deque<Long> numberStack = new ArrayDeque<>();
                Deque<Character> operatorStack = new ArrayDeque<>();
                char c = order.charAt(j);

                numberStack.addLast(nowNumbers.get(0));
                for (int k = 1; k < nowNumbers.size(); k++) {
                    numberStack.addLast(nowNumbers.get(k));
                    operatorStack.addLast(nowOperators.get(k - 1));
                    if (operatorStack.getLast() == c) {
                        long r = numberStack.pollLast();
                        long l = numberStack.pollLast();
                        numberStack.addLast(calculate(l, r, operatorStack.pollLast()));
                    }
                }
                nowNumbers = new ArrayList<>(numberStack);
                nowOperators = new ArrayList<>(operatorStack);
            }
            answer = Math.max(Math.abs(nowNumbers.get(0)), answer);
        }
        return answer;
    }

    private long calculate(long l, long r, char c) {
        if (c == '*') {
            return l * r;
        } else if (c == '+') {
            return l + r;
        }
        return l - r;
    }

    private void init(String expression) {
        StringBuilder sb = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == '*' || c == '+' || c == '-') {
                numbers.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
                operators.add(c);
                continue;
            }
            sb.append(c);
        }
        numbers.add(Long.parseLong(sb.toString()));
    }
}
