# n개의 사진틀
# 추천받은 학생
# 사진틀에 있다면 추천 횟수만 1 증가
# 사진틀이 비어있다면 바로 게시
# 사진틀이 비어있지 않다면 사진틀에서 추천 횟수가 가장 적은 사진 삭제
# 추천 횟수가 가장 적은 학생이 두 명 이상이면 오래된 사진 삭제
# 삭제한다면 추천 횟수 0으로 초기화

import sys

input = sys.stdin.readline
n = int(input())
total = int(input())
students = list(map(int, input().split()))


def solution(n, total, students):
    album = {}

    for order, student in enumerate(students):
        # 사진틀에 학생이 있으면 숫자 증가만하고 건너뛰기
        if student in album:
            album[student][0] += 1
            continue

        # 사진틀이 꽉 찼다면
        if len(album) == n:
            # 조건에 의해 삭제
            target = min(album, key=lambda _student: album[_student])
            del album[target]

        # 사진 추가
        album[student] = [1, order]  # 추천수, 삽입 순서

    return sorted(album.keys())


answer = solution(n, total, students)

print(" ".join(map(str, answer)))
