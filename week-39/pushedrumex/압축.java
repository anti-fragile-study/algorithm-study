import java.util.*;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx = 1;
        for (int i=0;i<26;i++) {
            map.put(alpha.substring(i, i+1), idx++);
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        int i = 0;
        int N = msg.length();
        while (i < N) {
            String word = msg.substring(i, i+1);
            i++;

            // 사전에 없는 단어가 나올 때까지
            while (i < N && map.containsKey(word)) {
                word += msg.substring(i, i+1);
                i++;
            }
            
            // 사전에 존재하지 않는다면
            if (!map.containsKey(word)) {
                answer.add(map.get(word.substring(0, word.length()-1)));
                map.put(word, idx++);
                i--;
            } else {
                answer.add(map.get(word));
            }
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }
}