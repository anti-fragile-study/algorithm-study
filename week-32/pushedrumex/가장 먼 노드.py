from collections import deque

def solution(n, edge):
    graph = [[] for _ in range(20001)]
    for a, b in edge:
        graph[a].append(b)
        graph[b].append(a)
    
    distance = [-1] * 20001
    distance[1] = 0
    q = deque([(1, 0)])
    while q:
        now, dist = q.popleft()
        for next in graph[now]:
            if distance[next] == -1:
                distance[next] = dist + 1
                q.append((next, dist + 1))
    distance.sort(reverse=True)
    max_distance = distance[0]
    answer = 0
    for d in distance:
        if d == max_distance:
            answer += 1
        else:
            break
    return answer