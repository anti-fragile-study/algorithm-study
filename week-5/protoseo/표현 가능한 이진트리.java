class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            if (canPresent(getBinary(number))) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    private boolean canPresent(String binary) {
        int length = binary.length();
        if (length == 1) {
            return true;
        }
        if (binary.charAt(length / 2) == '0') {
            boolean result = true;
            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '1') {
                    result = false;
                    break;
                }
            }
            return result;
        }

        return canPresent(binary.substring(0, length / 2)) &&
                canPresent(binary.substring((length / 2) + 1));
    }

    private String getBinary(long number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(number % 2);
            number /= 2;
        }
        int temp = 1;
        while (temp - 1 < sb.length()) {
            temp *= 2;
        }
        for (int i = sb.length(); i < temp - 1; i++) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }
}
