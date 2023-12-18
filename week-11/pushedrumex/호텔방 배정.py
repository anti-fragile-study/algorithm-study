import sys
from collections import defaultdict

sys.setrecursionlimit(10000)

def find(parent, node):
    if parent[node] == 0:
        parent[node] = node + 1
        return node
    parent[node] = find(parent, parent[node])
    return parent[node]
        
def solution(k, room_number):
    parent = defaultdict(int)
    answer = []
    for n in room_number:
        answer.append(find(parent, n))
    return answer