import java.util.*;

class Solution {
    static final int L = 6;
    int n;
    int[][] diceCopy;
    Map<String, Integer> winCountByDiceIdx = new HashMap<>();
    public int[] solution(int[][] dice) {
        n = dice.length;
        diceCopy = dice;
        selectDice(0, 0, new int[n / 2], new boolean[n]);

        int winCount = 0;
        int[] answer = new int[n / 2];
        for (Map.Entry<String, Integer> entry : winCountByDiceIdx.entrySet()) {
            if (entry.getValue() > winCount) {
                winCount = entry.getValue();
                answer = getAnswer(entry.getKey());
            }
        }
        return answer;
    }

    private int[] getAnswer(String key) {
       return Arrays.stream(key.substring(1, key.length() - 1).split(", "))
               .mapToInt(s -> Integer.parseInt(s) + 1)
               .toArray();
    }

    private void selectDice(int idx, int num, int[] selected, boolean[] isVisited) {
        if (idx == n / 2) {
            String key = Arrays.toString(selected);
            if (winCountByDiceIdx.containsKey(key)) {
                return;
            }
            int[] unselected = new int[n / 2];
            int t = 0;
            for (int i = 0; i < n; i++) {
                if (!isVisited[i]) {
                    unselected[t++] = i;
                }
            }
            calculateWinRate(selected, unselected);
            return;
        }
        for (int i = num; i < n; i++) {
            if (isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            selected[idx] = i;
            selectDice(idx + 1, i + 1, selected, isVisited);
            isVisited[i] = false;
        }
    }

    private void calculateWinRate(int[] selected, int[] unselected) {
        Map<Integer, Integer> selectedSumByCount = findDiceSum(selected);
        Map<Integer, Integer> unselectedSumByCount = findDiceSum(unselected);

        int winCount = 0;
        int loseCount = 0;
        for (int v1 : selectedSumByCount.keySet()) {
            for (int v2 : unselectedSumByCount.keySet()) {
                int value = selectedSumByCount.get(v1) * unselectedSumByCount.get(v2);
                if (v1 > v2) {
                    winCount += value;
                } else if (v1 < v2) {
                    loseCount += value;
                }
            }
        }
        winCountByDiceIdx.put(Arrays.toString(selected), winCount);
        winCountByDiceIdx.put(Arrays.toString(unselected), loseCount);
    }

    private Map<Integer, Integer> findDiceSum(int[] diceIdx) {
        int[] result = new int[]{0};
        for (int idx : diceIdx) {
            int[] temp = new int[result.length * L];
            int tempIdx = 0;
            for (int i = 0; i < L; i++) {
                int diceValue = diceCopy[idx][i];
                for (int j = 0; j < result.length; j++) {
                    temp[tempIdx++] = result[j] + diceValue;
                }
            }
            result = temp;
        }
        Map<Integer, Integer> countByValue = new HashMap<>();
        for (int i : result) {
            countByValue.put(i, countByValue.getOrDefault(i, 0) + 1);
        }
        return countByValue;
    }
}
