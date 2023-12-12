import java.util.*;

class Solution {
    Map<String, Integer> menus = new TreeMap<>();

    public String[] solution(String[] orders, int[] course) {
        for (String order : orders) {
            find(0, order, new boolean[26]);
        }

        int[] maxOrder = new int[course.length];
        for (Map.Entry<String, Integer> entry : menus.entrySet()) {
            String menu = entry.getKey();
            int orderCount = entry.getValue();
            for (int i = 0; i < course.length; i++) {
                if (menu.length() == course[i] && orderCount > maxOrder[i]) {
                    maxOrder[i] = entry.getValue();
                    break;
                }
            }
        }

        List<String> answerList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : menus.entrySet()) {
            String menu = entry.getKey();
            int orderCount = entry.getValue();
            for (int i = 0; i < course.length; i++) {
                if (menu.length() == course[i] && orderCount > 1 && orderCount == maxOrder[i]) {
                    answerList.add(entry.getKey());
                    break;
                }
            }
        }

        return answerList.stream().toArray(String[]::new);
    }

    public void find(int idx, String str, boolean[] used) {
        if (idx == str.length()) {
            StringBuilder menu = new StringBuilder();
            for (char c = 'A'; c <= 'Z'; c++) {
                if (used[c - 'A']) {
                    menu.append(c);
                }
            }
            menus.put(menu.toString(), menus.getOrDefault(menu.toString(), 0) + 1);
            return;
        }
        used[str.charAt(idx) - 'A'] = true;
        find(idx + 1, str, used);
        used[str.charAt(idx) - 'A'] = false;
        find(idx + 1, str, used);
    }
}
