import java.util.*;

class Solution {
    Map<Long, Long> parents = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            long emptyRoom = find(room_number[i]);
            answer[i] = emptyRoom;
            union(emptyRoom, emptyRoom + 1);
        }
        return answer;
    }

    public long find(long a) {
        if (!parents.containsKey(a)) {
            return a;
        }
        long parent = find(parents.get(a));
        parents.put(a, parent);
        return parent;
    }

    public void union(long a, long b) {
        long pa = find(a);
        long pb = find(b);
        parents.put(pa, pb);
    }
}
