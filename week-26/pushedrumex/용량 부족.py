from collections import deque

T = int(input())

def remove(arr, prefix):
    l = len(prefix)
    for target in arr:
        if target[:l] == prefix:
            return True
    return False

for _ in range(T):

    N1 = int(input())
    arr1 = [input() for _ in range(N1)]

    N2 = int(input())
    arr2 = [input() for _ in range(N2)]

    if N2 == 0:
        print(1)
        continue

    q = deque(sorted(arr1))
    arr2.sort()

    count = 0
    while q:
        count += 1
        qname = deque(list(q.popleft()))
        rm = ""
        while qname:
            rm += qname.popleft()
            if remove(arr2, rm):
                continue
            l = len(rm)
            while q and q[0][:l] == rm:
                q.popleft()
    
    print(count)