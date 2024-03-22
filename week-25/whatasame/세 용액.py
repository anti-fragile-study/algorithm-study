# 특성값이 0에 가장 가까운 용액을 만들어내는 세 용액의 특성 값을 오름차순으로 반환

"""
5
-97 -6 1 2 3
-> -6 2 3
"""

import sys

input = lambda:sys.stdin.readline().strip()

n = int(input())
stats = list(map(int, input().split()))

def solution(n, _stats):
    # 오름차순으로 정렬 -> O(nlogn)
    stats = sorted(_stats)

    # 숫자 하나를 선택한다 -> O(n)
    answer = (stats[0], stats[1], stats[2])
    result = sum(answer)
    for first in range(n - 2):
        # 선택한 숫자 뒤의 리스트의 양 끝을 투포인터로 선언
        second, third = first + 1, n - 1
        while second < third: # 투포인터가 마주칠 때까지 -> O(n)
            # 갱신
            tmp = (stats[first], stats[second], stats[third])
            tmp_sum = sum(tmp)
            if abs(tmp_sum) < abs(result):
                answer = tmp
                result = tmp_sum

            # 투포인터의 합이 0에 가깝게 되도록 이동
            if tmp_sum < 0:
                second += 1
            else:
                third -= 1

    # 오름차순 반환
    return answer

print(" ".join(map(str, solution(n, stats))))
