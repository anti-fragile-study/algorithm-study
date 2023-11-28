def solution(numbers, hand):
    answer = ''
    dic ={}
    pad = [[1, 2, 3], [4, 5, 6], [7, 8, 9], ['*', 0, '#']]
    for i in range(4):
        for j in range(3):
            dic[pad[i][j]] = (i, j)
    
    left = [3, 0]
    right = [3, 2]
    
    for number in numbers:
        x, y = dic[number]
        if number in (1, 4, 7):
            answer += "L"
            left = [x, y]
        elif number in (3, 6, 9):
            answer += "R"
            right = [x, y]
        else:
            d_l = abs(x - left[0]) + abs(y - left[1])
            d_r = abs(x - right[0]) + abs(y - right[1])
            if d_l < d_r or (d_l == d_r and hand == "left"): 
                answer += "L"
                left = [x, y]
            else:
                answer += "R"
                right = [x, y]

    return answer