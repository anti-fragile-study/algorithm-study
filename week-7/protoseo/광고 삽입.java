class Solution {

    long[] timeToViewer = new long[360001];

    public String solution(String play_time, String adv_time, String[] logs) {
        int advTime = convertTimeToInt(adv_time);

        for (String log : logs) {
            String[] split = log.split("-");
            int startTime = convertTimeToInt(split[0]);
            int endTime = convertTimeToInt(split[1]);

            timeToViewer[startTime]++;
            timeToViewer[endTime]--;
        }
        for (int i = 1; i <= 360000; i++) {
            timeToViewer[i] += timeToViewer[i - 1];
        }
        for (int i = 1; i <= 360000; i++) {
            timeToViewer[i] += timeToViewer[i - 1];
        }

        int answer = 0;
        long maxView = timeToViewer[advTime];
        for (int i = 1; i <= 360000 - advTime; i++) {
            long viewer = timeToViewer[i + advTime] - timeToViewer[i];
            if (maxView < viewer) {
                answer = i + 1;
                maxView = viewer;
            }
        }

        return convertIntToTime(answer);
    }

    String convertIntToTime(int value) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", (value / 3600))).append(":");
        value %= 3600;
        sb.append(String.format("%02d", (value / 60))).append(":");
        value %= 60;
        sb.append(String.format("%02d", value));
        return sb.toString();
    }

    int convertTimeToInt(String time) {
        String[] result = time.split(":");

        return Integer.parseInt(result[0]) * 3600 + Integer.parseInt(result[1]) * 60 + Integer.parseInt(result[2]);
    }
}
