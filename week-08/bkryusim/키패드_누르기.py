def solution(numbers, hand):
    answer = ''

    def distance(p1, p2):
        return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

    # r, c
    position = [(3, 1)] + [((i - 1) // 3, (i - 1) % 3) for i in range(1, 10)]

    l_pos = (3, 0)
    r_pos = (3, 2)

    for n in numbers:
        if n in (1, 4, 7):
            answer += 'L'
            l_pos = position[n]
        elif n in (3, 6, 9):
            answer += 'R'
            r_pos = position[n]
        else:
            l_distance = distance(l_pos, position[n])
            r_distance = distance(r_pos, position[n])

            if l_distance > r_distance:
                answer += 'R'
                r_pos = position[n]

            elif l_distance < r_distance:
                answer += 'L'
                l_pos = position[n]

            else:
                if hand == 'right':
                    answer += 'R'
                    r_pos = position[n]
                else:
                    answer += 'L'
                    l_pos = position[n]

    return answer
