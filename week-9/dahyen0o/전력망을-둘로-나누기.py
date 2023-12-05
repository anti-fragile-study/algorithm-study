from collections import defaultdict, deque

def bfs(wires, except_wire):
    cnt = 0
    checked = defaultdict(int)
    checked[tuple(except_wire)] = 1
    queue = [1]
    queue = deque(queue)
    
    while queue:
        curr = queue.popleft()
        cnt += 1
        for wire in wires:
            if wire[0] == curr and checked[tuple(wire)] == 0:
                checked[tuple(wire)] = 1
                queue.append(wire[1])
            if wire[1] == curr and checked[tuple(wire)] == 0:
                checked[tuple(wire)] = 1
                queue.append(wire[0])
        
    return cnt

def solution(n, wires):
    answer = -1
    min_diff = 10000
    
    for wire in wires:
        cnt = bfs(wires, wire)
        min_diff = min(abs(2 * cnt - n), min_diff)
        
    return min_diff
