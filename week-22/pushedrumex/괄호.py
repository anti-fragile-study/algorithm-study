T = int(input())

for _ in range(T):
    count = 0
    flag = False
    for c in list(input()):
        if c == "(":
            count += 1
        else:
            if count < 1:
                flag = True
                break
            count -= 1
    if count > 0 or flag == True:
        print("NO")
    else:
        print("YES")
    print(count)