def solution(arr):
    min_num = min(arr)
    answer = []
    for num in arr:
        if num != min_num:
            answer.append(num)
    return answer if answer else [-1]