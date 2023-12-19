import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        int[] reportedCount = new int[n];
        Map<String, Integer> idByIndex = new HashMap<>();
        List<String>[] reportIdByIndex = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            idByIndex.put(id_list[i], i);
            reportIdByIndex[i] = new ArrayList<>();
        }

        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        for (String r : reportSet) {
            String[] split = r.split(" ");
            reportIdByIndex[idByIndex.get(split[0])].add(split[1]);
            reportedCount[idByIndex.get(split[1])]++;
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (String id : reportIdByIndex[i]) {
                if (reportedCount[idByIndex.get(id)] >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}
