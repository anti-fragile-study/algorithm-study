from collections import deque

def solution(m, musicinfos):
    answer = '(None)'
    m = convertLyrics(m)
    maxT = 0
    for musicinfo in musicinfos:
        start, end, title, lyrics = musicinfo.split(",")
        t = convertTime(end)-convertTime(start)
        lyrics = convertLyrics(lyrics)
        l = len(lyrics)
        play = lyrics * (t // l) + lyrics[:t % l]
        if m in play and t > maxT:
            maxT = t
            answer = title
    return answer

def convertLyrics(s):
    result = []
    q = deque(list(s))
    while q:
        temp = q.popleft()
        if temp == "#":
            result[-1] = result[-1].lower()
        else:
            result.append(temp)
    return "".join(result)
            
def convertTime(s):
    h, m = s.split(":")
    return int(h) * 60 + int(m)