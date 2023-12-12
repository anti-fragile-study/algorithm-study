sheet = [["EMPTY"] * 51 for _ in range(51)]
def solution(commands):
    answer = []
    for cmd in commands:
        cmd = cmd.split(" ")
        if cmd[0] == "UPDATE" and len(cmd) == 4: update1(cmd)
        elif cmd[0] == "UPDATE" and len(cmd) == 3: update2(cmd)
        elif cmd[0] == "MERGE": merge(cmd)
        elif cmd[0] == "UNMERGE": unmerge(cmd)
        else: answer.append(sheet[int(cmd[1])][int(cmd[2])])
    return answer

mg = [[0] * 51 for _ in range(51)]

for i in range(1, 51):
    for j in range(1, 51):
        mg[i][j] = 50 * (i-1) + j

def update1(cmd):
    r, c = map(int, cmd[1:-1])
    for i in range(1, 51):
        for j in range(1, 51):
            if mg[i][j] == mg[r][c]: sheet[i][j] = cmd[-1]
    
def update2(cmd):
    value1, value2 = cmd[1:]
    for i in range(1, 51):
        for j in range(1, 51):
            if sheet[i][j] == value1: sheet[i][j] = value2
            
def merge(cmd):
    r1, c1, r2, c2 = map(int, cmd[1:])
    value = sheet[r1][c1] if sheet[r1][c1] != "EMPTY" else sheet[r2][c2]
    temp = mg[r2][c2]
    
    for i in range(1, 51):
        for j in range(1, 51):
            if mg[i][j] == temp or mg[i][j] == mg[r1][c1]:
                mg[i][j] = mg[r1][c1]
                sheet[i][j] = value
                
def unmerge(cmd):
    r, c = map(int, cmd[1:])
    value = sheet[r][c]
    temp = mg[r][c]
    for i in range(1, 51):
        for j in range(1, 51):
            if mg[i][j] == temp:
                mg[i][j] = 50 * (i-1) + j
                sheet[i][j] = "EMPTY"
    sheet[r][c] = value