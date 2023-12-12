import sys
input = sys.stdin.readline
sys.setrecursionlimit(2*10**6)

N, R = map(int, input().split())

tree = [[] for _ in range(N+1)]
for _ in range(N-1):
    a, b, d = map(int, input().split())
    tree[a].append((b, d))
    tree[b].append((a, d))

visited = [False] * (N+1)
def find_giga(node, dist):
    visited[node] = True
    if node == R and len(tree[node]) >= 2:
        return (node, dist)
    if len(tree[node]) >= 3:
        return (node, dist)
        
    for _node, _dist in tree[node]:
        if not visited[_node]:
            return find_giga(_node, dist + _dist)
    
    return (node, dist)
    
giga_node, col_len = find_giga(R, 0)

max_branch = 0
def find_max_branch(node, dist):
    global max_branch
    visited[node] = True
    max_branch = max(max_branch, dist)
    for _node, _dist in tree[node]:
        if not visited[_node]:
            find_max_branch(_node, dist + _dist)

find_max_branch(giga_node, 0)

print(*[col_len, max_branch])