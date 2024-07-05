def solution(n):
    third = []
    while n > 0:
        third.append(n%3)
        n //= 3
    answer = 0
    m = 0
    for k in third[::-1]:
        answer += k *(3**m)
        m += 1
    return answer