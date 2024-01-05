import java.util.*;

class Solution {
    
    public int solution(String[] lines) {
        List<Time> times = new ArrayList<>();
        for (String line : lines) {
            times.add(convert(line));
        }
        
        int answer = 0;
        for (int i = 0; i < times.size(); i++) {
            int rangeEnd = times.get(i).end + 1000;
            int count = 0;
            for (int j = i; j < times.size(); j++) {
                Time target = times.get(j);
                int start = target.start;
                if (start < rangeEnd) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
    
    public Time convert(String line) {
        String[] split = line.split(" ");
        int t = (int)(Double.parseDouble(split[2].substring(0, split[2].length() - 1)) * 1000);
        String[] times = split[1].split(":");
        int end = Integer.parseInt(times[0]) * 60 * 60 * 1000;
        end += Integer.parseInt(times[1]) * 60 * 1000;
        end += (int)(Double.parseDouble(times[2]) * 1000);
        return new Time((end - t + 1 < 0) ? 0 : end - t + 1, end);
    }
}

class Time { 
    int start;
    int end;
    
    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
}