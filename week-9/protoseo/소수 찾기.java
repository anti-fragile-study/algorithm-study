import java.util.*;

class Solution {
    Set<Integer> candidate = new TreeSet<>();
    char[] numbersToArray;
    public int solution(String numbers) {
        numbersToArray = numbers.toCharArray();
        find(0, "0", new boolean[numbers.length()]);

        int answer = 0;
        for (int c : candidate) {
            if (isPrime(c)) {
                answer++;
            }
        }
        return answer;
    }

    void find(int idx, String s, boolean[] isVisited) {
        candidate.add(Integer.parseInt(s));
        if (idx == numbersToArray.length) {
            return;
        }
        for (int i = 0; i < numbersToArray.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            find(idx + 1, s + numbersToArray[i], isVisited);
            isVisited[i] = false;
        }
    }

    boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
