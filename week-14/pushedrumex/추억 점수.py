from collections import defaultdict

def solution(name, yearning, photo):
    dic = defaultdict(int)
    for i in range(len(name)):
        dic[name[i]] = yearning[i]
        
    answer = []
    for names in photo:
        temp = 0
        for name in names:
            temp += dic[name]
        answer.append(temp)
    return answer