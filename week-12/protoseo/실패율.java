import java.util.List;
import java.util.ArrayList;

class Solution {
    int[] tryCount;
    int[] failCount;
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        tryCount = new int[N + 2];
        failCount = new int[N + 2];
        for (int stage : stages) {
            failCount[stage]++;
            for (int i = 1; i <= stage; i++) {
                tryCount[i]++;
            }
        }
        
        List<StageInfo> stageInfos = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            double failRate = (tryCount[i] == 0) ? 0 : failCount[i] / (double) tryCount[i];
            stageInfos.add(new StageInfo(i, failRate));
        }
        stageInfos.sort((o1, o2) -> {
            int result = Double.compare(o2.failRate, o1.failRate);                
            if (result == 0) {
                return Integer.compare(o1.number, o2.number);
            }
            return result;
        });
       	for (int i = 0; i < N; i++) {
            answer[i] = stageInfos.get(i).number;
        } 
        return answer;
    }
}

class StageInfo {
    int number;
    double failRate;
    
    public StageInfo(int number, double failRate) {
        this.number = number;
        this.failRate = failRate;
    }
}