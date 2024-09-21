import java.util.*;

class Solution {
    public int solution(String skill, String[] trees) {
        Map<String, String> need = new HashMap<>();
        String [] split = skill.split("");
        for (int i = 1; i < skill.length(); i++) {
            need.put(split[i], split[i - 1]);
        }

        int answer = 0;
        for (String tree : trees) {
            Set<String> learned = new HashSet<>();
            boolean valid = true;
            for (String c : tree.split("")) {
                if (need.containsKey(c) && !learned.contains(need.get(c))) {
                    valid = false;
                    break;
                }
                learned.add(c);
            }
            if (valid) answer++;
        }

        return answer;
    }
}
