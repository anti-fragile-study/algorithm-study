# 순서대로 방을 배정
# 원하는 방이 비어있다면 바로 배정
# 비어있지 않다면 원하는 방 번호보다 크면서 비어있는 방을 배정

# k => 1조

import sys

sys.setrecursionlimit(123456789)


def solution(k, numbers):
    hotel = {}  # 방번호 : 다음 비어있는 방

    for number in numbers:
        assign(hotel, number)

    return list(hotel.keys())  # python dict keys: 삽입 순서 보장 >= 3.6


def assign(hotel, number):
    # 방이 비어있다면 방 번호 리턴
    if number not in hotel:
        hotel[number] = number + 1
        return number

    # 방이 비어있지 않다면 다음 방에서 찾기
    empty = assign(hotel, hotel[number])

    # 비어있지 않은 방 업데이트
    hotel[number] = empty

    return empty

