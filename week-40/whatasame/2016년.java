import java.util.*;

class Solution {
    public String solution(int a, int b) {
        // 월별 날짜 초기화
        int[] days = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 입력된 월 이전의 모든 일수를 더함
        int totalDays = b;
        for (int i = 1; i < a; i++) {
            totalDays += days[i];
        }

        // 요일 배열
        String[] answers = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};

        // 요일 계산
        return answers[(totalDays - 1) % 7];
    }
}
