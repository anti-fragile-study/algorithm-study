def solution(word):
    answer = 0
    translate = {'A': 0, 'E': 1, 'I': 2, 'O': 3, 'U': 4}
    sum_list = [0, 5**1+5**0, 5**2+5**1+5**0, 5**3+5**2+5**1+5**0, 
               5**4+5**3+5**2+5**1+5**0]
    
    for spell, num in zip(word, range(5, 0, -1)):
        # 밑 자리들의 전체 개수 + 현재 자리의 개수
        answer += sum_list[num-1] * translate[spell]
        answer += 1 if num > 1 else translate[spell] + 1
    return answer
