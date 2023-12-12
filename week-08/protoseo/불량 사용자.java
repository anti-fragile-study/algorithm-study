import java.util.*;

class Solution {
    String[] userId;
    String[] bannedId;
    Set<String> bannedUserIndexSet = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;
        int[] bannedUserIndex = new int[bannedId.length];
        Arrays.fill(bannedUserIndex, -1);
        find(0, bannedUserIndex);
        return bannedUserIndexSet.size();
    }

    private void find(int idx, int[] bannedUserIndex) {
        if (idx == userId.length) {
            boolean isBanned = true;
            List<Integer> index = new ArrayList<>();
            for (int i = 0; i < bannedId.length; i++) {
                if (bannedUserIndex[i] == -1) {
                    isBanned = false;
                    break;
                }
                index.add(bannedUserIndex[i]);
            }
            if (isBanned) {
                StringBuilder sb = new StringBuilder();
                Collections.sort(index);
                for (int i : index) {
                    sb.append(i);
                }
                bannedUserIndexSet.add(sb.toString());
            }
            return;
        }
        String user = userId[idx];
        for (int i = 0; i < bannedId.length; i++) {
            if (bannedUserIndex[i] != -1) {
                continue;
            }
            String mask = bannedId[i];
            boolean isBanned = true;
            if (user.length() == mask.length()) {
                for (int j = 0; j < user.length(); j++) {
                    if (mask.charAt(j) == '*' || mask.charAt(j) == user.charAt(j)) {
                        continue;
                    }
                    isBanned = false;
                    break;
                }
                if (isBanned) {
                    bannedUserIndex[i] = idx;
                    find(idx + 1, bannedUserIndex);
                    bannedUserIndex[i] = -1;
                }
            }
        }
        find(idx + 1, bannedUserIndex);
    }
}
