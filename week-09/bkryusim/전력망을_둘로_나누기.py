def solution(n, wires):
    answer = n

    visited = set()

    def dfs(g, current):
        if current in visited:
            return
        visited.add(current)
        for e in g[current]:
            dfs(g, e)

    graph = dict()

    for i in range(1, n + 1):
        graph[i] = set()

    for e in wires:
        graph[e[0]] = graph.get(e[0], set()).union({e[1]})
        graph[e[1]] = graph.get(e[1], set()).union({e[0]})

    for w in wires:
        visited = set()

        graph[w[0]] = graph[w[0]].difference({w[1]})
        graph[w[1]] = graph[w[1]].difference({w[0]})

        dfs(graph, 1)

        if answer > abs(n - (len(visited) * 2)):
            answer = abs(n - (len(visited) * 2))

        graph[w[0]] = graph[w[0]].union({w[1]})
        graph[w[1]] = graph[w[1]].union({w[0]})

    return answer
