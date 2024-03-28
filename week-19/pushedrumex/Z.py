import sys
sys.setrecursionlimit = 10000000

N, r, c = map(int, input().split())
size = 2**N

def dfs(x, y, size, order):
    if (x, y) == (r, c):
        print(order)
        exit()
    
    size //= 2
    if r < x + size and c < y + size:
        dfs(x, y, size, order)
    elif r < x + size and c >= y + size:
        dfs(x, y+size, size, order+size*size)
    elif r >= x + size and c < y + size:
        dfs(x+size, y, size, order+size*size*2)
    else:
        dfs(x+size, y+size, size, order+size*size*3)

dfs(0, 0, size, 0)
