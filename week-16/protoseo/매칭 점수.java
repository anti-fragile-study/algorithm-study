import java.util.*;
import java.util.stream.*;

class Solution {

    Map<String, Score> scoreByWeb = new HashMap<>();

    public int solution(String word, String[] pages) {
        for (int i = 0; i < pages.length; i++) {
            Score score = new Score(i);
            String page = pages[i].toLowerCase();
            String webPage = getWebPageUrl(getHeader(page));
            String body = getBody(page);

            score.basic = getBasicScore(word.toLowerCase(), body);
            score.externals = getExternalLinks(body);
            score.link = score.basic / (double)score.externals.size();
            scoreByWeb.put(webPage, score);
        }

        for (Map.Entry<String, Score> entry : scoreByWeb.entrySet()) {
            Score score = entry.getValue();
            List<String> externals = score.externals;
            for (String externalWeb : externals) {
                if (scoreByWeb.containsKey(externalWeb)) {
                    Score externalScore = scoreByWeb.get(externalWeb);
                    externalScore.addedLink += score.link;
                }
            }
        }

        return scoreByWeb.values().stream().sorted().collect(Collectors.toList()).get(0).idx;
    }
    
    private int getBasicScore(String word, String body) {
        int result = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < body.length(); i++) {
            char c = body.charAt(i);
           if (!Character.isAlphabetic(c)) {
               if (sb.toString().equals(word)) {
                   result++;
               }
               sb = new StringBuilder();
               continue;
           }
           sb.append(c);
        }
        return result;
    }

    private String getHeader(String page) {
        String headStartPattern = "<head>";
        String headEndPattern = "</head>";
        int headStart = page.indexOf(headStartPattern) + headStartPattern.length();
        int headEnd = page.indexOf(headEndPattern);
        return page.substring(headStart, headEnd);
    }

    private String getBody(String page) {
        String bodyStartPattern = "<body>";
        String bodyEndPattern = "</body>";
        int bodyStart = page.indexOf(bodyStartPattern) + bodyStartPattern.length();
        int bodyEnd = page.indexOf(bodyEndPattern);
        return page.substring(bodyStart, bodyEnd);
    }
    
    private String getWebPageUrl(String header) {
        String webPageStartPattern = "<meta property=\"og:url\" content=\"https://";
        String webPageEndPattern = "\"";
        int webPageStart = header.indexOf(webPageStartPattern) + webPageStartPattern.length();
        header = header.substring(webPageStart);
        int webPageEnd = header.indexOf(webPageEndPattern);
        return header.substring(0, webPageEnd);
    }

    private List<String> getExternalLinks(String body) {
        String externalStartPattern = "<a href=\"https://";
        String externalEndPattern = "\"";

        List<String> result = new ArrayList<>();
        int externalLinkStart = body.indexOf(externalStartPattern);
        while (externalLinkStart >= 0) {
            body = body.substring(externalLinkStart + externalStartPattern.length());
            int externalLinkEnd = body.indexOf(externalEndPattern);
            result.add(body.substring(0, externalLinkEnd));
            body = body.substring(externalLinkEnd + externalEndPattern.length());
            externalLinkStart = body.indexOf(externalStartPattern);
        }
        return result;
    }
}

class Score implements Comparable<Score> {
    int idx;
    int basic;
    double link;
    double addedLink;
    List<String> externals = new ArrayList<>();

    public Score(int idx) {
        this.idx = idx;
        this.basic = 0;
        this.link = 0.0;
        this.addedLink = 0.0;
    }

    private double getMatchScore() {
        return (double) this.basic + this.addedLink;
    }

    @Override
    public int compareTo(Score o) {
        if (Double.compare(o.getMatchScore(), this.getMatchScore()) == 0) {
            return this.idx - o.idx;
        }
        return Double.compare(o.getMatchScore(), this.getMatchScore());
    }
}
