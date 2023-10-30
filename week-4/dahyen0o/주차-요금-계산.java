import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    
    public int[] solution(final int[] fees, final String[] records) {
        final Map<String, Integer> totalTimeByCar = new TreeMap<>();
        final Map<String, LocalTime> inByCar = new HashMap<>();
        
        for(final String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            final LocalTime time = LocalTime.parse(st.nextToken(), formatter);
            final String car = st.nextToken();
            final String type = st.nextToken();
            
            if(type.equals("IN")) {
                inByCar.put(car, time);
            } else { // type == "OUT"
                final LocalTime inTime = inByCar.get(car);
                inByCar.remove(car);
                final int totalTime = totalTimeByCar.getOrDefault(car, 0);
                final int addTime = (int) (Duration.between(inTime, time).toMinutes());
                totalTimeByCar.put(car, totalTime + addTime);
            }
        }
        
        // 출차 못한 차량들 출차시키기
        for(String car : inByCar.keySet()) {
            final LocalTime inTime = inByCar.get(car);
            final LocalTime outTime = LocalTime.parse("23:59", formatter);
            
            final int totalTime = totalTimeByCar.getOrDefault(car, 0);
            final int addTime = (int) (Duration.between(inTime, outTime).toMinutes());
            totalTimeByCar.put(car, totalTime + addTime);
        }
        
        int[] answer = new int[totalTimeByCar.size()];
        
        // 요금 계산
        final int baseTime = fees[0];
        final int baseFee = fees[1];
        
        int idx = 0;
        for(int totalTime : totalTimeByCar.values()) {
            int fee = baseFee;
            if(totalTime > baseTime) { // 추가 요금
                final int addTime = totalTime - baseTime;
                fee += Math.ceil(addTime / (double) fees[2]) * fees[3];
            }
            answer[idx++] = fee;
        }
        
        return answer;
    }
}
