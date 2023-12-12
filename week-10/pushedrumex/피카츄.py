s = input()
s = s.replace("pi", " ")
s = s.replace("ka", " ")
s = s.replace("chu", " ")

if len(s.split()) == 0:
    print("YES")
else:
    print("NO")