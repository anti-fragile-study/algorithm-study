def solution(s):
    answer = []
    for nums in s:
        stack = []
        count_110 = 0
        # 110 제거 및 개수 카운트
        for num in nums:
            if len(stack) >= 2 and num == "0" and stack[-1] == "1" and stack[-2] == "1":
                count_110 += 1
                stack.pop()
                stack.pop()
            else:
                stack.append(num)
        right_zero = -1
        for i in range(len(stack)-1, -1, -1):
            if stack[i] == "0":
                right_zero = i
                break
        if right_zero == -1:
            answer.append("110" * count_110 + "".join(stack))
        else:
            answer.append("".join(stack[:right_zero+1]) + "110" * count_110 + "".join(stack[right_zero+1:]))
    return answer