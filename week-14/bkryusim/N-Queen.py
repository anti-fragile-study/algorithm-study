def solution(n):
    def check_diagonal(r1, c1, r2, c2):
        if r1 - r2 == c1 - c2 or r1 + c1 == r2 + c2:
            return True
        return False

    def find(r, visited, log):
        result = 0
        if r == n:
            return 1

        for c in range(n):
            placeable = True
            for p in log:
                if visited[c] or check_diagonal(r, c, *p):
                    placeable = False
                    break
            if not placeable:
                continue

            visited[c] = True
            log.append((r, c))

            result += find(r + 1, visited, log)

            visited[c] = False
            log.pop()
        return result

    return find(0, [False] * n, [])
