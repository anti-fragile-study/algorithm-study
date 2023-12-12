def solution(n, results):
    graph = [[None for _ in range(n + 1)] for _ in range(n + 1)]

    for u, v in results:
        graph[u][v] = True
        graph[v][u] = False

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            for k in range(1, n + 1):
                if graph[j][i] == None:
                    continue

                if graph[j][i] == graph[i][k]:
                    graph[j][k] = graph[j][i]
                    graph[k][j] = not graph[j][i]

    return sum(map(lambda row: row.count(None) == 2, graph[1:]))
