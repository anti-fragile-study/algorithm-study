# 20 (0) -> 8 (6) -> 4 (8) -> 2 (9)

"""
3 2 10
->
"""

def solution(a, b, n):
    answer = 0
    while n // a > 0:
        # 빈 병을 콜라로 변경
        coke = n // a * b
        n -= n // a * a

        # 받은 콜라 마시기
        answer += coke
        n += coke

    return answer
