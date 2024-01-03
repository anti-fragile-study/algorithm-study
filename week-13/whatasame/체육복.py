# n - 1 <- n -> n + 1

# 여벌을 가지고 있는 학생이 도난당한 학생일 수도 있다
# 이 경우 여벌 학생은 못 빌려준다

# n - 1을 먼저 검사하고 n + 1을 검사하면 최적이다.

"""
5
[2, 4]
[1, 5]
-> 5

5
[1, 2, 4]
[2, 3, 4, 5]
-> 4
"""

def solution(n, lost, reserve):
    _lost, _reserve = set(lost), set(reserve)

    # 여벌 가져왔으면서 도난당한 학생 제외
    reserve = {num for num in _reserve if not num in _lost}
    lost = {num for num in _lost if not num in _reserve}

    # 1번부터 N번까지
    answer = 0
    for i in range(1, n + 1):
        # 평범한 사람 체크
        if not i in lost:
            answer += 1
            continue

        # i - 1, i + 1번째 순서로 체육복 빌리기
        for k in [i - 1, i + 1]:
            if k in reserve:
                reserve.remove(k)
                answer += 1
                break

    return answer


