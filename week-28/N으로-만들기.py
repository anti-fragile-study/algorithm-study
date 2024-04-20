N = input()
cache = set()

def search(start, end, val, path):
    global N, cache, answer
    if val == N:
        cache.add(path)
        return
    if len(val) >= len(N):
        return
    
    if start > 0:
        next_path = path + ' ' + N[start - 1] + val
        search(start - 1, end, N[start - 1] + val, next_path)

    if end + 1 < len(N):
        next_path = path + ' ' + val + N[end + 1]
        search(start, end + 1, val + N[end + 1], next_path) 
    

for i, mid in enumerate(N):
    search(i, i, mid, mid)

print(len(cache))
