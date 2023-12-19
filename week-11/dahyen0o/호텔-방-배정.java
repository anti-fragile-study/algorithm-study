import java.util.*;

class Solution {
    public long[] solution(final long K, final long[] customers) {
        final long[] answer = new long[customers.length];
        final SortedSet<Long> filledRooms = new TreeSet<>();
        
        for(int i = 0; i < customers.length; i++) {
            final long customer = customers[i];
            long room = customer;
            if(filledRooms.contains(customer)) {
                room = findRoom(customer + 1, filledRooms);
            }
            answer[i] = room;
            filledRooms.add(room);
        }
        return answer;
    }
    
    private long findRoom(final long start, SortedSet<Long> filledRooms) { 
        if(start > filledRooms.last()) {
            return start;
        }
        if(start < filledRooms.first()) {
            return start;
        }
        if(filledRooms.size() == filledRooms.last() - filledRooms.first() + 1) {
            // filledRooms가 first부터 last까지 다 차 있음
            return filledRooms.last() + 1;
        }

        // filledRooms의 first부터 last 사이에 무조건 빈 방이 있음을 보장    
        // long room = binarySearch(start, filledRooms.last(), filledRooms);
        // if(room > -1) {
        //     return room;
        // }
        // for(long idx = start; idx < filledRooms.last(); idx++) {
        //     if(!filledRooms.contains(idx)) {
        //         return idx;
        //     }
        // }
        return filledRooms.last() + 1;
    }
    
    private long binarySearch(long left, long right, SortedSet<Long> filledRooms) {
        long room = -1;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            if(filledRooms.contains(mid)) {
                left = mid + 1;
                continue;
            }
            room = mid;
            right = mid - 1;
        }
        
        return room;
    }
}
