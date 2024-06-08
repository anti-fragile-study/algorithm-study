# 계단 수: 인접한 모든 자리의 차이가 1
# 길이가 N인 계단 수의 개수를 10억으로 나눈 나머지를 반환

# 0과 9에 이어 붙이는 경우 1과 8만 가능하다
# 나머지는 2개씩 가능

"""
한 자리인 경우
1
-> 9
[1, 2, 3, 4, 5, 6, 7, 8, 9]

2
-> 17
[
    10, 12, 21, 23,
    32, 34, 43, 45,
    54, 56, 65, 67,
    76, 78, 87, 89,
    97,
]
"""

MODULO = 1_000_000_000

n = int(input())

def solution(n):
    # 각 자리 수에서 숫자의 개수
    count = [0 for _ in range(10)] # 0 ~ 9

    # N = 1
    for num in range(1, 10):
        count[num] = 1

    # N >= 2
    for digit in range(2, n + 1):
        tmp = [0 for _ in range(10)]
        for num, cnt in enumerate(count):
            if num != 0:
                tmp[num - 1] += cnt
                tmp[num - 1] %= MODULO
            if num != 9:
                tmp[num + 1] += cnt
                tmp[num + 1] %= MODULO
        count = tmp

    return sum(count) % MODULO

print(solution(n))
