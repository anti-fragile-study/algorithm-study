# 행 선택(파란색), 삭제, 복구

# U/D X -> x칸 위/아래 선택 (표 범위를 벗어나진 않음)
# C -> 선택한 칸 삭제하고 아래로 (마지막이라면 바로 위)
# Z -> 최근에 삭제한 행을 복구, 선택된 행은 그대로

# 모든 명령어를 수행 후 처음 표에서 살아남은 행은 O로 삭제된 행은 X로 표시한 문자열을 반환

# 표 100만개
# 명령어 20만개
# X(<= 30만) x cmd <= 1,000,000

def solution(n, k, cmds):
    # node : [prev, next] / -1, n == end of list
    linked_list = {idx : [idx - 1, idx + 1] for idx in range(n)}
    start = 0 # start of LL
    deleted = [] # stack

    now = k
    for cmd in cmds:
        if cmd[0] == "U": # left
            for _ in range(int(cmd[2])):
                now = linked_list[now][0]

        if cmd[0] == "D": # right
            for _ in range(int(cmd[2])):
                now = linked_list[now][1]

        if cmd[0] == "C":
            # 연결 수정
            pre, nxt = linked_list[now]
            if pre != -1:
                linked_list[pre][1] = nxt
            if nxt != n:
                linked_list[nxt][0] = pre

            # 삭제
            deleted.append([pre, now, nxt])
            del linked_list[now]

            # 선택 행 변경
            now = nxt if nxt != n else pre

        if cmd[0] == "Z":
            pre, num, nxt = deleted.pop()

            # 복원
            linked_list[num] = [pre, nxt]

            # 연결 수정
            if pre != -1:
                linked_list[pre][1] = num
            if nxt != n:
                linked_list[nxt][0] = num

    return "".join(["O" if idx in linked_list else "X" for idx in range(n)])
