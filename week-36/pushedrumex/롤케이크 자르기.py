def solution(topping):
    dic_left = [0] * 10001
    dic_right = [0] * 10001
    for t in topping:
        dic_right[t] += 1

    answer = 0
    count_left = 0
    count_right = len(set(topping))
    for i in range(len(topping)):
        t = topping[i]
        if dic_left[t] == 0:
            count_left += 1
        dic_left[t] += 1
        dic_right[t] -= 1
        if dic_right[t] == 0:
            count_right -= 1
        
        if count_left == count_right:
            answer += 1
            
    return answer