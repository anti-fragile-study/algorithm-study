import java.util.*;

class Solution {
    
    public int solution(String[] lines) {
        List<Time> times = new ArrayList<>();
        for (String line : lines) {
            times.add(convert(line));
        }
        
        int answer = 0;
        for (int i = 0; i < times.size(); i++) {
            int rangeStart = times.get(i).end;
            int rangeEnd = rangeStart + 1000;
            int count = 0;
            for (int j = 0; j < times.size(); j++) {
                Time target = times.get(j);
                int start = target.start;
                int end = target.end;
                if (rangeStart <= start && end < rangeEnd) {
                    count++;
                } else if (rangeStart <= end && end < rangeEnd) {
                    count++;
                } else if (rangeStart <= start && start < rangeEnd) {
                    count++;
                } else if (start <= rangeStart && end >= rangeEnd) {
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