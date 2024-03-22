import re

def solution(word, pages):
    word = word.lower()    
    # 웹페이지 주소
    urls = []
    pattern = re.compile(r'<meta property="og:url" content="(.*?)"/>', re.DOTALL)
    for page in pages:
        url = pattern.search(page).group(1)
        urls.append(url)
    
    # 기본점수
    basic_scores = []
    pattern = re.compile(r'<body>(.*?)</body>', re.DOTALL)
    for page in pages:
        body = pattern.search(page).group(1)
        text = re.sub(r'<[^>]+>', '', body).strip()
        words = re.split(r'[^a-zA-Z]+', text)
        
        score = 0
        for w in words:
            if w.lower() == word: score += 1        
        basic_scores.append(score)
    
    # 링크 목록
    links = []
    pattern = re.compile(r'<a href="(.*?)">', re.DOTALL)
    for page in pages:
        links.append(pattern.findall(page))

    # 링크점수
    link_scores = [0 for _ in range(len(pages))]
    for url_idx, link in enumerate(links):
        if not links[url_idx]: continue
        link_score = basic_scores[url_idx] / len(links[url_idx])
        for l in link:
            try: 
                link_idx = urls.index(l)
                link_scores[link_idx] += link_score
            except (ValueError): pass
    
    # 매칭점수
    scores = []
    for basic, link in zip(basic_scores, link_scores):
        scores.append(basic + link)
    
    max_score = 0
    answer = 0
    for idx, score in enumerate(scores):
        if score > max_score:
            max_score = score
            answer = idx
    
    return answer
