from collections import defaultdict


def solution(n, results):
    answer = 0
    winnings = defaultdict(list)
    losings = defaultdict(list)

    def dfs(node, graph, visited):
        count = 1
        for next in graph[node]:
            if not visited[next]:
                count += dfs(next, graph, visited)
                visited[next] = True
        return count

    for winner, loser in results:
        winnings[winner].append(loser)
        losings[loser].append(winner)

    for player in range(1, n + 1):
        if dfs(player, winnings, [False] * (n + 1)) + dfs(player, losings, [False] * (n + 1)) == n + 1:
            answer += 1

    return answer
