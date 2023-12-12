import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        FileName[] result = new FileName[files.length];
        for (int i = 0; i < files.length; i++) {
            String[] parseResult = parse(files[i]);
            result[i] = new FileName(parseResult[0], parseResult[1], parseResult[2], i);
        }

        Arrays.sort(result);
        for (int i = 0; i < files.length; i++) {
            answer[i] = result[i].getName();
        }
        return answer;
    }

    private String[] parse(String file) {
        String[] result = new String[3];
        for (int i = 0; i < file.length();i++) {
            if (Character.isDigit(file.charAt(i))) {
                int j = i;
                while (j < file.length() && Character.isDigit(file.charAt(j))) {
                    j++;
                }
                result[0] = file.substring(0, i);
                result[1] = file.substring(i, j);
                result[2] = file.substring(j);
                break;
            }
        }
        return result;
    }
}

class FileName implements Comparable<FileName> {

    String head;
    String number;
    String tail;
    int idx;

    public FileName(String head, String number, String tail, int idx) {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.idx = idx;
    }

    public String getName() {
        return this.head + this.number + this.tail;
    }

    public int compareTo(FileName fn) {
        int headCompare = this.head.compareToIgnoreCase(fn.head);
        if (headCompare != 0) {
            return headCompare;
        }
        int numberCompare = Integer.compare(Integer.parseInt(this.number), Integer.parseInt(fn.number));
        if (numberCompare != 0) {
            return numberCompare;
        }
        return Integer.compare(this.idx, fn.idx);
    }
}
