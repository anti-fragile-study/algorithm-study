import java.util.*;

class Solution {
    char[] vowel = {'A', 'E', 'I', 'O', 'U'};
    Set<String> words = new TreeSet<>();

    public int solution(String word) {
        find(0, "");
        List<String> wordsToList = new ArrayList<>(words);
        return wordsToList.indexOf(word);
    }

    public void find(int idx, String str) {
        words.add(str);
        if (idx == 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            find(idx + 1, str + vowel[i]);
        }
    }
}
