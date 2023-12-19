def solution(n, k, cmd):
    answer = ["O"] * n
    delete = []
    node = []
    for i in range(n):
        node.append([i-1, i+1]) # -1 ~ n
        
    for c in cmd:
         if c == "C":
            delete.append(k)
            answer[k] = "X"
            # 마지막이라면
            if node[k][1] == n:
                k = node[k][0]
                node[k][1] = n
            # 처음이라면
            elif node[k][0] == -1:
                k = node[k][1]
                node[k][0] = -1
            # 중간이라면
            else:
                node[node[k][0]][1] = node[k][1]
                node[node[k][1]][0] = node[k][0]
                k = node[k][1]
         elif c == "Z":
            temp = delete.pop()
            if node[temp][0] != -1:
                node[node[temp][0]][1] = temp
            if node[temp][1] != n:
                node[node[temp][1]][0] = temp
            answer[temp] = "O"
         else:
            a, b = c.split()
            b = int(b)
            if a == "D":
                while b > 0:
                    b -= 1
                    k = node[k][1]
            elif a == "U":
                while b > 0:
                    b -= 1
                    k = node[k][0]

    return "".join(answer)