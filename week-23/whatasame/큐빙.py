import sys

input = sys.stdin.readline
n = int(input())
cases = []
for _ in range(n):
    length = int(input())
    cases.append(input().split())

def solution(cases):
    return [run(case) for case in cases]

def run(commands):
    cube = {
        "U": [['w' for _ in range(3)] for _ in range(3)],
        "D": [['y' for _ in range(3)] for _ in range(3)],
        "F": [['r' for _ in range(3)] for _ in range(3)],
        "B": [['o' for _ in range(3)] for _ in range(3)],
        "L": [['g' for _ in range(3)] for _ in range(3)],
        "R": [['b' for _ in range(3)] for _ in range(3)],
    }

    for command in commands:
        process(cube, command)

    return "\n".join("".join([cube["U"][row][col] for row in range(2, -1, -1)]) for col in range(3))

def process(cube, command):
    if command == "U+":
        cube["U"] = cw(cube["U"])
        rotate(
                cube,
                ("F", [(0, 0), (0, 1), (0, 2)]),
                ("R", [(0, 0), (0, 1), (0, 2)]),
                ("B", [(0, 0), (0, 1), (0, 2)]),
                ("L", [(0, 0), (0, 1), (0, 2)])
        )
    elif command == "U-":
        cube["U"] = ccw(cube["U"])
        rotate(
                cube,
                ("F", [(0, 0), (0, 1), (0, 2)]),
                ("L", [(0, 0), (0, 1), (0, 2)]),
                ("B", [(0, 0), (0, 1), (0, 2)]),
                ("R", [(0, 0), (0, 1), (0, 2)])
        )
    elif command == "D+":
        cube["D"] = cw(cube["D"])
        rotate(
                cube,
                ("F", [(2, 0), (2, 1), (2, 2)]),
                ("L", [(2, 0), (2, 1), (2, 2)]),
                ("B", [(2, 0), (2, 1), (2, 2)]),
                ("R", [(2, 0), (2, 1), (2, 2)])
        )
    elif command == "D-":
        cube["D"] = ccw(cube["D"])
        rotate(
                cube,
                ("F", [(2, 0), (2, 1), (2, 2)]),
                ("R", [(2, 0), (2, 1), (2, 2)]),
                ("B", [(2, 0), (2, 1), (2, 2)]),
                ("L", [(2, 0), (2, 1), (2, 2)])
        )
    elif command == "F+":
        cube["F"] = cw(cube["F"])
        rotate(
                cube,
                ("U", [(2, 2), (1, 2), (0, 2)]),
                ("L", [(2, 2), (1, 2), (0, 2)]),
                ("D", [(2, 2), (1, 2), (0, 2)]),
                ("R", [(0, 0), (1, 0), (2, 0)])
        )
    elif command == "F-":
        cube["F"] = ccw(cube["F"])
        rotate(
                cube,
                ("U", [(2, 2), (1, 2), (0, 2)]),
                ("R", [(0, 0), (1, 0), (2, 0)]),
                ("D", [(2, 2), (1, 2), (0, 2)]),
                ("L", [(2, 2), (1, 2), (0, 2)])
        )
    elif command == "B+":
        cube["B"] = cw(cube["B"])
        rotate(
                cube,
                ("U", [(0, 0), (1, 0), (2, 0)]),
                ("R", [(2, 2), (1, 2), (0, 2)]),
                ("D", [(0, 0), (1, 0), (2, 0)]),
                ("L", [(0, 0), (1, 0), (2, 0)])
        )
    elif command == "B-":
        cube["B"] = ccw(cube["B"])
        rotate(
                cube,
                ("U", [(0, 0), (1, 0), (2, 0)]),
                ("L", [(0, 0), (1, 0), (2, 0)]),
                ("D", [(0, 0), (1, 0), (2, 0)]),
                ("R", [(2, 2), (1, 2), (0, 2)])
        )
    elif command == "L+":
        cube["L"] = cw(cube["L"])
        rotate(
                cube,
                ("U", [(2, 0), (2, 1), (2, 2)]),
                ("B", [(2, 2), (1, 2), (0, 2)]),
                ("D", [(0, 2), (0, 1), (0, 0)]),
                ("F", [(0, 0), (1, 0), (2, 0)])
        )
    elif command == "L-":
        cube["L"] = ccw(cube["L"])
        rotate(
                cube,
                ("U", [(2, 0), (2, 1), (2, 2)]),
                ("F", [(0, 0), (1, 0), (2, 0)]),
                ("D", [(0, 2), (0, 1), (0, 0)]),
                ("B", [(2, 2), (1, 2), (0, 2)])
        )
    elif command == "R+":
        cube["R"] = cw(cube["R"])
        rotate(
                cube,
                ("U", [(0, 0), (0, 1), (0, 2)]),
                ("F", [(0, 2), (1, 2), (2, 2)]),
                ("D", [(2, 2), (2, 1), (2, 0)]),
                ("B", [(2, 0), (1, 0), (0, 0)])
        )
    elif command == "R-":
        cube["R"] = ccw(cube["R"])
        rotate(
                cube,
                ("U", [(0, 0), (0, 1), (0, 2)]),
                ("B", [(2, 0), (1, 0), (0, 0)]),
                ("D", [(2, 2), (2, 1), (2, 0)]),
                ("F", [(0, 2), (1, 2), (2, 2)])
        )

def cw(arr):
    return [
        [arr[2][0], arr[1][0], arr[0][0]],
        [arr[2][1], arr[1][1], arr[0][1]],
        [arr[2][2], arr[1][2], arr[0][2]]
    ]

def ccw(arr):
    return [
        [arr[0][2], arr[1][2], arr[2][2]],
        [arr[0][1], arr[1][1], arr[2][1]],
        [arr[0][0], arr[1][0], arr[2][0]]
    ]

def rotate(cube, *data):
    (area1, pos1), (area2, pos2), (area3, pos3), (area4, pos4) = data

    for i in range(3):
        tmp = cube[area1][pos1[i][0]][pos1[i][1]]
        cube[area1][pos1[i][0]][pos1[i][1]] = cube[area2][pos2[i][0]][pos2[i][1]]
        cube[area2][pos2[i][0]][pos2[i][1]] = cube[area3][pos3[i][0]][pos3[i][1]]
        cube[area3][pos3[i][0]][pos3[i][1]] = cube[area4][pos4[i][0]][pos4[i][1]]
        cube[area4][pos4[i][0]][pos4[i][1]] = tmp

print("\n".join(solution(cases)))
