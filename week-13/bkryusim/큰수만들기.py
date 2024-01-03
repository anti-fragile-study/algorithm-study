def solution(number, k):
    answer = ''
    selected = len(number) - k
    number = list(number)
    cursor = 0

    while number and selected > 0:
        max_c = '0'
        max_i = 0

        for i, c in enumerate(number[cursor:len(number) - selected + 1]):
            if c == '9':
                max_c = c
                max_i = i
                break

            if c > max_c:
                max_c = c
                max_i = i

        answer += max_c
        cursor += max_i + 1
        selected -= 1

    return answer
