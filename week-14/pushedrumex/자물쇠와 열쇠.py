from copy import deepcopy

def solution(key, lock):
    m, n = len(key), len(lock)
    size = 3 * n
    graph = [[0] * size for _ in range(size)]
    
    for i in range(n):
        for j in range(n):
            graph[i+n][j+n] = lock[i][j]
    
    for i1 in range(n-m+1, 2*n):
        for j1 in range(n-m+1, 2*n):
            if is_open(deepcopy(graph), key, i1, j1, m, n):
                return True 
    
    for _ in range(3):
        key = rotate(key, m)
        for i1 in range(n-m+1, 2*n):
            for j1 in range(n-m+1, 2*n):
                if is_open(deepcopy(graph), key, i1, j1, m, n):
                    return True 
    return False

def rotate(key, m):
    result = [[0] * m for _ in range(m)]
    for i in range(m):
        for j in range(m):
            result[j][m-i-1] = key[i][j]
    return result

def is_open(graph, key, i1, j1, m, n):
    for i2 in range(m):
        for j2 in range(m):
            x, y = i1 + i2, j1 + j2
            if n <= x < 2 * n and n <= y < 2 * n:
                if graph[x][y] + key[i2][j2] != 1: return False
                graph[x][y] = 1
                
    for i in range(n, 2*n):
        for j in range(n, 2*n):
            if graph[i][j] == 0: return False

    return True