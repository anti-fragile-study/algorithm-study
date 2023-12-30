# n번째 수 < n+1번째 수 = 삭제

# 문자열로 반환

"""
"4177718"
1
-> "477718"

"4177718"
2
-> "77718"

"77777"
1
-> "7777"

"4321"
2
-> "43"

"53245"
2
-> 545
"""

def solution(number, k):
    # 스택
    stack = []

    # 모든 수에 대하여
    for num in number:
        # num이 현재까지 만든 숫자의 끝자리보다 크면 작을 때까지 pop
        while stack and num > stack[-1] and k > 0:
            stack.pop()
            k -= 1
        # num push
        stack.append(num)

    # 문자열 반환
    return "".join(stack[:len(stack) - k])
