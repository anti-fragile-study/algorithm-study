def solution(park, routes):
    height = len(park)
    width = len(park[0])

    for r in range(height):
        for c in range(width):
            if park[r][c] == 'S':
                position = (r, c)

    move = {
        'E': (0, 1),
        'W': (0, -1),
        'N': (-1, 0),
        'S': (1, 0)
    }

    for route in routes:
        direction, distance = route.split()
        distance = int(distance)

        moved = (position[0] + move[direction][0] * distance, position[1] + move[direction][1] * distance)

        noop = False
        if moved[0] >= height or moved[0] < 0 or moved[1] < 0 or moved[1] >= width:
            continue

        for i in range(1, distance + 1):
            if park[position[0] + move[direction][0] * i][position[1] + move[direction][1] * i] == 'X':
                noop = True
                break
        if noop:
            continue

        position = (position[0] + move[direction][0] * distance, position[1] + move[direction][1] * distance)

    return list(position)
