class Solution {
    public String solution(String new_id) {
        String answer = step2(new_id.toLowerCase());
      	answer = step3(answer);
        answer = step4(answer);
        if (answer.length() == 0) {
            answer = "a";
        }
        if (answer.length() > 15) {
            answer = answer.substring(0, 15);
            while (answer.charAt(answer.length() - 1) == '.') {
                answer = answer.substring(0, answer.length() - 1);
            }
        }
        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }
        return answer;
    }
    
    public String step2(String id) {
        StringBuilder sb = new StringBuilder();
        for (char c : id.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public String step3(String id) {
        StringBuilder sb = new StringBuilder(); 
        boolean prevIsDot = false;
        for (char c : id.toCharArray()) {
            if (c == '.' && !prevIsDot) {
                prevIsDot = true;
            } else if (c == '.' && prevIsDot) {
                continue;
            } else {
                prevIsDot = false;
            }
           	sb.append(c);
        }
        return sb.toString();
    }
    
    public String step4(String id) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            if ((i == 0 || i == id.length() - 1) && id.charAt(i) == '.') {
                continue;
            }
            sb.append(id.charAt(i));
        }
        return sb.toString();
    }
}