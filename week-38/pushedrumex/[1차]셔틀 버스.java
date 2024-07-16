// 가장 마지막 타임 or 자리가 부족하다면 마지막으로 타는 크루 시간 -1분
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = convertToMin("9:00") + t * (n-1);
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        Arrays.sort(timetable);
        for (String time: timetable) {
            dq.add(convertToMin(time));
        }

        int lastTime = 0;
        int count = 0;
        for (int i=0;i<n;i++) {
            int shuttle = convertToMin("9:00") + t*i;
            count = 0;
            while (count < m && !dq.isEmpty() && dq.peekFirst() <= shuttle) {
                count++;
                lastTime = dq.removeFirst();
            }
        }

        if (count == m) {
            answer = lastTime - 1;
        }
        return convertToTime(answer);
    }

    int convertToMin(String s) {
        String[] temp = s.split(":");
        int h = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        return h * 60 + m;
    }

    String convertToTime(int min) {
        String h = "0"+String.valueOf(min / 60);
        String m = "0"+String.valueOf(min % 60);
        return h.substring(h.length()-2) + ":" + m.substring(m.length()-2);
    }
}