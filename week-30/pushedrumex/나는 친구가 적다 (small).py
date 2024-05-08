S = input()
K = input()

origin = ""
for s in list(S):
    if not ("0" <= s <= "9"):
        origin += s

for i in range(len(S)-len(K)+1):
    if origin[i:i+len(K)] == K:
        print(1)
        exit()

print(0)
