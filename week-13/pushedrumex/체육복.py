# 우선 앞번호한테 빌린 다음 없으면 뒷번호한테 빌리기
def solution(n, lost, reserve):
    체육복 = [1] * (n+2)
    for i in lost:
        체육복[i] -= 1
    for i in reserve:
        체육복[i] += 1
    answer = 0
    for i in range(1, n+1):
        if 체육복[i] >= 1:
            answer += 1
            continue
        if 체육복[i-1] > 1:
            체육복[i-1] -= 1
            answer += 1
        elif 체육복[i+1] > 1:
            체육복[i+1] -= 1
            answer += 1
    return answer