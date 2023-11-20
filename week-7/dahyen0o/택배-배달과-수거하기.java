import java.util.*;

class Solution {
    public long solution(final int cap, final int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int d = deliveries.length - 1;
        int p = pickups.length - 1;
        
        while(d >= 0 && p >= 0) {
            // cap으로 가능한 배달과 수거 진행
            while(d >= 0 && deliveries[d] == 0) {
                d--;
            }
            while(p >= 0 && pickups[p] == 0) {
                p--;
            }
            final int endPoint = Math.max(d, p) + 1;
            answer += endPoint * 2;
            
            int dCap = cap;
            while(d >= 0) {
                if(dCap > deliveries[d]) {
                    dCap -= deliveries[d--];
                } else {
                    deliveries[d] -= dCap;
                    break;
                }
            }
            
            int pCap = cap;
            while(p >= 0) {
                if(pCap > pickups[p]) {
                    pCap -= pickups[p--];
                } else {
                    pickups[p] -= pCap;
                    break;
                }
            }
        }
                
        if(d >= 0) { // pickup ended
            while(d >= 0) {
                while(d >= 0 && deliveries[d] == 0) {
                    d--;
                }
                answer += (d + 1) * 2;

                int dCap = cap;
                while(d >= 0) {
                    if(dCap > deliveries[d]) {
                        dCap -= deliveries[d--];
                    } else {
                        deliveries[d] -= dCap;
                        break;
                    }
                }
            }
        } else { // deliveries ended
            while(p >= 0) {
                while(p >= 0 && pickups[p] == 0) {
                    p--;
                }
                answer += (p + 1) * 2;

                int pCap = cap;
                while(p >= 0) {
                    if(pCap > pickups[p]) {
                        pCap -= pickups[p--];
                    } else {
                        pickups[p] -= pCap;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
    
}
