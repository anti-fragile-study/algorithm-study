import java.util.*;

class Solution {

    int basicTime;
    int basicFee;
    int unitTime;
    int unitFee;
    int MAX_TIME = convert("23:59");

    public int[] solution(int[] fees, String[] records) {

        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        HashMap<String, Integer> inMap = new HashMap<>();
        TreeMap<String, Integer> timeMap = new TreeMap<>();

        for (String record: records) {
            StringTokenizer st = new StringTokenizer(record);
            int t = convert(st.nextToken());
            String car = st.nextToken();
            String info = st.nextToken();

            if (info.equals("IN")) {
                inMap.put(car, t);
            } else {
                int in = inMap.get(car);
                timeMap.put(car, timeMap.getOrDefault(car, 0)+t-in);
                inMap.remove(car);
            }
        }

        for (String car: inMap.keySet()) {
            int in = inMap.get(car);
            timeMap.put(car, timeMap.getOrDefault(car, 0)+MAX_TIME-in);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (String car: timeMap.keySet()) {
            answer.add(calculateFee(timeMap.get(car)));
        }

        return answer.stream().mapToInt(x->x).toArray();
    }

    private int calculateFee(int t) {
        int fee = basicFee;
        if (basicTime < t) {
            fee += Math.ceil((t - basicTime) / (double)unitTime) * unitFee;
        }
        return fee;
    }

    private int convert(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        return h * 60  + m;
    }
}