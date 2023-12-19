class Solution {
    public String solution(String p) {
        if (p.equals("")) {
            return p;
        }
        int idx = findIndexToSplit(p) + 1;
        String u = p.substring(0, idx);
        String v = p.substring(idx);
        if (isComplete(u)) {
            return u + solution(v);
        }
        return '(' + solution(v) + ')' + reverseWithCuttedString(u);
    }

    public String reverseWithCuttedString(String u) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            result.append((u.charAt(i) == ')') ? '(' : ')');
        }
        return result.toString();
    }

    public boolean isComplete(String s) {
        int l = 0;
        int r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                r++;
            }

            if (l < r) {
                return false;
            }
        }
        return true;
    }

    public int findIndexToSplit(String p) {
        int l = 0;
        int r = 0;

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') {
                l++;
            } else if(c == ')') {
                r++;
            }

            if (l == r) {
                return i;
            }
        }
        return p.length() - 1;
    }
}
