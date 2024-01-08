import re
from collections import defaultdict

def solution(lines):
    infos = []
    
    for line in lines:
        start, end = parse_to_start_and_end(line)
        infos.append((max(0, start), end))
        
    answer = 0
    for i in range(len(infos)):
        count = 0
        start = infos[i][0]
        end = start + 1000 - 1
        for j in range(len(infos)):
            if infos[j][0] > end or infos[j][1] < start: continue
            count += 1
            j += 1
        answer = max(answer, count)
        
        count = 0
        start = infos[i][1]
        end = start + 1000 - 1
        for j in range(len(infos)):
            if infos[j][0] > end or infos[j][1] < start: continue
            count += 1
            j += 1
        answer = max(answer, count)
    
    return answer

def parse_to_start_and_end(line):
    # line 형식 = 2016-09-15 hh:mm:ss.sss
    end, T = line[11:].split(' ')
    end = datetime_to_sec(end)
    return sec_to_ms(round(end - float(T[:-1]), 4)), sec_to_ms(end) - 1

def datetime_to_sec(datetime):
    # hh:mm:ss.sss
    hour, minute, second = datetime.split(':')
    return int(hour) * 60 * 60 + int(minute) * 60 + round(float(second), 4)

def sec_to_ms(sec):
    return int(sec * 1000)
