from itertools import permutations, product
from collections import defaultdict

def solution(dice):
    n = len(dice)

    max_win = 0
    visited = defaultdict(bool)
    for dices in permutations(range(n), n):
        dice1 = tuple(sorted(dices[:n//2]))
        dice2 = tuple(sorted(dices[n//2:]))
        
        if visited[dice1] or visited[dice2]: continue
        visited[dice1], visited[dice2] = True, True
        
        values1, values2 = [], []
        for i in range(n//2):
            values1.append(dice[dice1[i]])
            values2.append(dice[dice2[i]])
            
        result1, result2 = [], []
        for result in product(*values1):
            result1.append(sum(result))
        for result in product(*values2):
            result2.append(sum(result))
        
        result1.sort()
        result2.sort()
        
        win1 = 0
        for n1 in result1:
            win1 += bs(n1, result2)
                    
        win2 = 0
        for n2 in result2:
            win2 += bs(n2, result1)
        
        if max_win < win1:
            max_win = win1
            answer = dice1
        
        if max_win < win2:
            max_win = win2
            answer = dice2
        
    answer = list(answer)
    for i in range(n//2):
        answer[i] += 1

    return answer

def bs(n, sums):
    left, right = 0, len(sums) - 1
    result = 0
    while left <= right:
        mid = (left + right) // 2
        if sums[mid] < n:
            result = mid + 1
            left = mid + 1
        else:
            right = mid - 1
            
    return result