T = int(input())

def is_palin(left, right):
    while left < right:
        if s[left] != s[right]:
            return (left, right)
        left += 1
        right -= 1
    return True

for _ in range(T):
    s = input()
    left, right = 0, len(s) - 1
    result = is_palin(left, right)
    if result == True:
        print(0)
    else:
        left, right = result
        if is_palin(left+1, right) == True or is_palin(left, right-1) == True:
            print(1)
        else:
            print(2)