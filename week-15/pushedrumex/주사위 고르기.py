from itertools import permutations, product
from collections import defaultdict

def solution(dice):
    N = len(dice)
    max_count = 0
    answer = []
    visited = defaultdict(bool)
    # 0~N-1 번의 주사위 중 순서를 고려해서 N 개 나열
    for dices in permutations(range(N), N):
        # 앞의 N//2 개 A 나눠줌
        A = tuple(sorted(dices[:N//2]))
        # 뒤의 N//2 개 B 나눠줌
        B = tuple(sorted(dices[N//2:]))
        
        # 주사위를 나누는 경우를 방문 처리(정렬 후 tuple 로 변환)
        if visited[A] or visited[B]:continue
        visited[A], visited[B] = True, True
        
        # 각각의 주사위를 담기
        A_dice, B_dice = [], []
        for i in range(N // 2):
            A_dice.append(dice[A[i]])
            B_dice.append(dice[B[i]])

        # 각자 가진 주사위에서 숫자를 하나씩 뽑아 합을 append
        A_sum, B_sum = [], []
        for nums in product(*A_dice):
            A_sum.append(sum(nums))
        for nums in product(*B_dice):
            B_sum.append(sum(nums))
        
        A_sum.sort()
        B_sum.sort()
        

        # 각 숫자가 이기는 경우의 수 계산 (이분탐색)
        A_count, B_count = 0, 0
        for A_score in A_sum:
            A_count += bs(A_score, B_sum)
        for B_score in B_sum:
            B_count += bs(B_score, A_sum)
        
        if A_count > max_count:
            answer = A
            max_count = A_count
        if B_count > max_count:
            answer = B
            max_count = B_count

    answer = list(answer)
    for i in range(N // 2):
        answer[i] += 1

    return answer

def bs(score, sums):
    left, right = 0, len(sums) - 1
    result = 0
    while left <= right:
        mid = (left + right) // 2
        if sums[mid] < score:
            result = mid + 1
            left = mid + 1
        else:
            right = mid - 1
            
    return result
    