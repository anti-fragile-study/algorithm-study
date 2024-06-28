def solution(s):
    answer = 1
    l = len(s)
    for i in range(1, l-1):
        count = 1
        left, right = i-1, i+1
        while 0 <= left and right <= l-1:
            if s[left] == s[right]:
                count += 2
                left -= 1
                right += 1
            else:
                break
        answer = max(answer, count)
        
    for i in range(1, l):
        count = 0
        left, right = i-1, i
        while 0 <= left and right <= l-1:
            if s[left] == s[right]:
                count += 2
                left -= 1
                right += 1
            else:
                break
        answer = max(answer, count)
    return answer