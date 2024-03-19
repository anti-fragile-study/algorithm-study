# 깰 수 있는 계란의 최대 수를 반환

# 계란(내구도, 무게)
# 계란끼리 부딪히면 상대방의 무게만큼 내구도가 감소
# 내구도가 0 이하가 되면 깨진다

import sys

input = lambda:sys.stdin.readline().strip()

n = int(input())
eggs = [tuple(map(int, input().split())) for _ in range(n)]

def solution(eggs):
    return dfs(eggs, 0,)

def dfs(eggs, now):
    if now == n:
        return sum(1 for durability, _ in eggs if durability <= 0) # 깨진 계란 수 합

    # 현재 계란이 깨져있으면 스킵
    nd, nw = eggs[now] # now_durability, now_weight
    if nd <= 0:
        return dfs(eggs, now + 1)

    # 깰 수 있는 계란이 없다면 스킵
    for idx, (td, tw) in enumerate(eggs):
        if idx == now or td <= 0:
            continue
        break
    else:
        return dfs(eggs, now + 1)

    # 계란 깨기
    result = 0
    for idx, (td, tw) in enumerate(eggs): # target_durability, target_weight
        if idx == now or td <= 0:
            continue

        eggs[now] = (nd - tw, nw)
        eggs[idx] = (td - nw, tw)

        result = max(result, dfs(eggs, now + 1))

        eggs[now] = (nd, nw)
        eggs[idx] = (td, tw)

    return result

print(solution(eggs))
