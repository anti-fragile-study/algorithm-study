def find_parent(node):
    if parent[node] != node:
        parent[node] = find_parent(parent[node])
    return parent[node]

def union(node1, node2):
    parent1 = find_parent(node1)
    parent2 = find_parent(node2)
    if parent1 < parent2:
        parent[parent2] = parent1
    else:
        parent[parent1] = parent2

def solution(n, costs):
    global parent
    
    parent = [0] * n
    for i in range(n):
        parent[i] = i
    
    answer = 0
    costs.sort(key = lambda x: x[2])

    for node1, node2, cost in costs:
        if find_parent(node1) != find_parent(node2):
            union(node1, node2)
            answer += cost

    return answer