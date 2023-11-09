import re

def solution(infos, queries):
    answer = []
    
    language = {'java': 0, 'python': 1, 'cpp': 2, '-': 3}
    part = {'backend': 0, 'frontend': 1, '-': 2}
    career = {'junior': 0, 'senior': 1, '-': 2}
    food = {'pizza': 0, 'chicken': 1, '-': 2}
    
    count = [[[[[] for _ in range(3)] for _ in range(3)] for _ in range(3)] for _ in range(4)]
    
    for info in infos:
        info = info.split()
        count[language[info[0]]][part[info[1]]][career[info[2]]][food[info[3]]].append(int(info[4]))
        count[-1][part[info[1]]][career[info[2]]][food[info[3]]].append(int(info[4]))
        count[language[info[0]]][-1][career[info[2]]][food[info[3]]].append(int(info[4]))
        count[language[info[0]]][part[info[1]]][-1][food[info[3]]].append(int(info[4]))
        count[language[info[0]]][part[info[1]]][career[info[2]]][-1].append(int(info[4]))
        # ... 실패^^
        
    for query in queries:
        query = re.sub(' and', '', query).split()
        result = count[language[query[0]]][part[query[1]]][career[query[2]]][food[query[3]]]
        
        print(result)

    # print(count)
    return answer
