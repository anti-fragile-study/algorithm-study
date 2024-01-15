import java.util.*;

class Solution {
        
    public int solution(int coin, final int[] cards) {
        final int N = cards.length;
        
        final Set<Integer> have = new HashSet<>();
        final Set<Integer> canBuy = new HashSet<>();
        
        // 초기 카드 뽑기
        int c = 0;
        for(; c < N / 3; c++) {
            have.add(cards[c]);
        }
        
        // 게임 진행
        int round = 1;
        for(; c < cards.length; c += 2, round++) {
            canBuy.add(cards[c]);
            canBuy.add(cards[c + 1]);
            
            // have로 다음 라운드 가능
            boolean next = false;
            for(final int card : have) {
                final int pairCard = N + 1 - card;
                if(have.contains(pairCard)) {
                    have.remove(card);
                    have.remove(pairCard);
                    next = true;
                    break;
                }
            }
            if(next) continue;
            
            // 하나 사고 다음 라운드 가능
            if(coin < 1) return round;
            
            next = false;
            for(final int card : have) {
                final int pairCard = N + 1 - card;
                if(canBuy.contains(pairCard)) {
                    have.remove(card);
                    canBuy.remove(pairCard);
                    coin--;
                    next = true;
                    break;
                }
            }
            if(next) continue;
            
            // 두 개 사고 다음 라운드 가능
            if(coin < 2) return round;
            
            next = false;
            for(final int card : canBuy) {
                final int pairCard = N + 1 - card;
                if(canBuy.contains(pairCard)) {
                    canBuy.remove(card);
                    canBuy.remove(pairCard);
                    coin -= 2;
                    next = true;
                    break;
                }
            }
            if(next) continue;
            return round;
        }
        
        return round;
    }
}
