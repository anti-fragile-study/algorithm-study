import java.util.*;

class Solution {

    private Map<String, Integer> timeRecords = new TreeMap<>();
    private Map<String, ParkingRecord> parkingRecords = new TreeMap<>();

    public int[] solution(int[] fees, String[] records) {
        for (String record : records) {
            ParkingRecord pr = new ParkingRecord(record.split(" "));
            if (pr.command.equals("IN")) {
                parkingRecords.put(pr.number, pr);
                continue;
            }

            ParkingRecord prev = parkingRecords.get(pr.number);
            parkingRecords.remove(pr.number);
            int parkingTime = pr.time - prev.time;
            timeRecords.put(pr.number, timeRecords.getOrDefault(pr.number, 0) + parkingTime);
        }
        for (ParkingRecord pr : parkingRecords.values()) {
            timeRecords.put(pr.number, timeRecords.getOrDefault(pr.number, 0) + (23 * 60 + 59) - pr.time);
        }

        int[] answer = new int[timeRecords.size()];
        List<Integer> parkingTimes = new ArrayList<>(timeRecords.values());
        for (int i = 0; i < answer.length; i++) {
            answer[i] = convertTimeToMoney(fees, parkingTimes.get(i));
        }
        return answer;
    }

    private int convertTimeToMoney(int[] fees, int time) {
        int overTime = time - fees[0];
        if (overTime <= 0) {
            return fees[1];
        }
        return fees[1] + ((int) Math.ceil(overTime / (double) fees[2]) * fees[3]);
    }
}

class ParkingRecord {

    int time;
    String number;
    String command;

    public ParkingRecord(String[] record) {
        this.time = convertTime(record[0]);
        this.number = record[1];
        this.command = record[2];
    }

    private int convertTime(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}
