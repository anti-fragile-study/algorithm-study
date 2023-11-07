import java.util.*;

class Solution {

    Map<String, List<Integer>> scoreByQuery = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (String i : info) {
            createQueryWithScore(0, i.split(" "), new String[4]);
        }

        for (List<Integer> list : scoreByQuery.values()) {
            Collections.sort(list);
        }

        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            answer[i] = countByQuery(q[0] + q[2] + q[4] + q[6], Integer.parseInt(q[7]));
        }
        return answer;
    }

    int countByQuery(String query, int score) {
        List<Integer> list = scoreByQuery.getOrDefault(query, new ArrayList<>());

        int l = 0;
        int r = list.size();

        while (l < r) {
            int mid = (l + r) / 2;
            int value = list.get(mid);
            if (value < score) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return list.size() - r;
    }

    void createQueryWithScore(int idx, String[] info, String[] query) {
        if (idx == 4) {
            String q = query[0] + query[1] + query[2] + query[3];
            List<Integer> scores = scoreByQuery.getOrDefault(q, new ArrayList<>());
            scores.add(Integer.parseInt(info[4]));
            scoreByQuery.put(q, scores);
            return;
        }

        query[idx] = info[idx];
        createQueryWithScore(idx + 1, info, query);
        query[idx] = "-";
        createQueryWithScore(idx + 1, info, query);
    }
}
