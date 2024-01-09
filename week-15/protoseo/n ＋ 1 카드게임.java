class Solution {
    int n;
    boolean[] hands;
    boolean[] candidate;

    public int solution(int coin, int[] cards) {
        n = cards.length;
        hands = new boolean[n + 1];
        candidate = new boolean[n + 1];
        for (int i = 0; i < n / 3; i++) {
           hands[cards[i]] = true;
        }
        
        int round = 0;
        while(coin >= 0) {
            if (n / 3 + (round * 2) >= n) {
                break;
            }
            if (!hasCardInHand() && coin <= 0) {
                break;
            }
            candidate[cards[n / 3 + (round * 2)]] = true;
            candidate[cards[n / 3 + (round * 2) + 1]] = true;
            if (!canGoNextRound(coin)) {
                break;
            }
            coin = exchange(coin);
            round++;
        }
        return round + 1;
    }
    
    private boolean hasCardInHand() {
        for (int i = 1; i <= n; i++) {
            if (hands[i]) {
                return true;
            }
        }
        return false;
	}

    private boolean canGoNextRound(int coin) {
        boolean result = false;
        for (int i = 1; i <= n; i++) {
            result |= hands[i] && hands[n + 1 - i];
            result |= coin >= 1 && hands[i] && candidate[n + 1 - i];
            result |= coin >= 2 && candidate[i] && candidate[n + 1 - i];
        }
        return result;
    }

    private int exchange(int coin) {
        for (int i = 1; i <= n / 2; i++) {
            if (hands[i] && hands[n + 1 - i]) {
                hands[i] = false;
                hands[n + 1 - i] = false;
                return coin;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (coin >= 1 && hands[i] && candidate[n + 1 - i]) {
                hands[i] = false;
                candidate[n + 1 - i] = false;
                return coin - 1;
            }
        }
        for (int i = 1; i <= n / 2; i++) {
            if (coin >= 2 && candidate[i] && candidate[n + 1 - i]) {
                candidate[i] = false;
                candidate[n + 1 - i] = false;
                return coin - 2;
            }
        }
        return -1;
    }
}
