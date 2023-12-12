# K개의 그룹으로 나누어 그룹의 합이 가장 작은 것이 최대일 때 최대를 출력
# 순서 그대로

"""
1 1
777
-> 777

3 3
333 444 555
-> 333

8 3
12 7 19 20 17 14 9 10
-> 33
"""

import sys

input = sys.stdin.readline

n, k = map(int, input().split())
nums = list(map(int, input().split()))


def solution(n, k, nums):
    # 이분 탐색 경계: 그룹 원소가 1개인 경우(가능한 최솟값) ~ 그룹 원소가 전체인 경우(가능한 최댓값)
    left, right = min(nums), sum(nums)

    # 이분 탐색의 경계가 교차하지 않을 때까지
    while left <= right:
        # middle: 가능한지 확인하려는 최소 그룹합
        middle = (left + right) // 2

        # middle과 같거나 클때까지 더한 후
        section_sum, section_count = 0, 0
        for num in nums:
            section_sum += num

            # 조건을 만족하면 그룹 완성, 새로운 그룹 생성
            if section_sum >= middle:
                section_sum = 0
                section_count += 1

        # 그룹의 개수가 k보다 크면 middle + 1도 가능한지 검사
        if section_count >= k:
            left = middle + 1
        # 그렇지 않으면 middle은 불가능한 값, middle - 1이 가능한지 검사
        else:
            right = middle - 1

    # right: 가능한 최댓값 반환
    return right


answer = solution(n, k, nums)

print(answer)
