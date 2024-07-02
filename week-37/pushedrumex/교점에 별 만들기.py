INF = int(1e15)
def solution(line):
    points = set()
    min_x = INF
    min_y = INF
    max_x = -INF
    max_y = -INF
    N = len(line)
    for i in range(N-1):
        A, B, E = line[i]
        for j in range(i+1, N):
            C, D, F = line[j]
            xc = B*F - E*D
            yc = E*C - A*F
            p = A*D - B*C
            if p == 0:
                continue
            x = xc / p
            y = yc / p
            int_x = int(x)
            int_y = int(y)
            if x == int_x and y == int_y:
                points.add((int_x, int_y))
                min_x, min_y = min(min_x, int_x), min(min_y, int_y)
                max_x, max_y = max(max_x, int_x), max(max_y, int_y)

    result = [["."] * (abs(max_x-min_x)+1) for _ in range(abs(max_y-min_y)+1)]
    for x, y in points:
        result[y-min_y][x-min_x] = "*"

    answer = []
    for row in result:
        answer.append("".join(row))
    return answer[::-1]