from collections import deque
import re

def solution(word, pages):
    word = word.lower()
    N = len(pages)
    링크 = {}
    기본점수 = [0] * N # word 몇 개 포함
    외부링크 = [0] * N # 다른 웹페이지로 링크 걸린 개수
    graph = [[] for _ in range(N)]
    for i in range(N):
        link = pages[i].split("<meta property=\"og:url\" content=")[1].split("/>")[0].strip()
        링크[link] = i
        
    for i in range(N):
        page = pages[i].lower()
        body = page.split("<body>")[1].split("</body>")[0]
        
        q = deque(body.split("<"))
        while q:
            temp = q.popleft()
            if temp[:7] == "a href=":
                link = temp.split("a href=")[1][:-1]
                외부링크[i] += 1
                if link in 링크:
                    graph[링크[link]].append(i)
                continue
            
            기본점수[i] += re.split(r'[^a-zA-Z]', temp).count(word)
            
    answer = 0
    max_score = 0
    for i in range(N):
        temp = 기본점수[i]
        for link in graph[i]:
            temp += 기본점수[link] / 외부링크[link]
        if max_score < temp:
            max_score = temp
            answer = i

    return answer