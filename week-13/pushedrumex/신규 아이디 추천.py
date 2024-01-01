from collections import deque

def solution(new_id):
    new_id = list(new_id.lower())
    
    temp = new_id[:]
    new_id = []
    for x in temp:
        if "a" <= x <= "z" or "0" <= x <= "9" or x in "-_.":
            new_id.append(x)

    temp = new_id[:]
    new_id = deque(new_id[0])
    for i in range(1, len(temp)):
        if temp[i] == "." and temp[i-1] == ".":
            continue
        new_id.append(temp[i])

    if new_id and new_id[0] == ".": new_id.popleft()
    if new_id and new_id[-1] == ".": new_id.pop()

    if len(new_id) == 0: new_id.append("a")

    while len(new_id) > 15:
        new_id.pop()

    if new_id[-1] == ".": new_id.pop()
    
    while len(new_id) < 3:
        new_id.append(new_id[-1])

    return "".join(new_id)