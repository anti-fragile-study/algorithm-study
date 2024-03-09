# 회전하려는 면을 위쪽
def rotate(c):
    _U, _L, _F, _R, _B = U, L, F, R, B
    if c == 'L':
        _U, _L, _F, _R, _B = L, F, U, B, D
    if c == 'F':
        _U, _L, _F, _R, _B = F, U, L, D, R
    if c == 'R':
        _U, _L, _F, _R, _B = R, D, B, U, F
    if c == 'B':
        _U, _L, _F, _R, _B = B, R, D, L, U
    if c == 'D':
        _U, _L, _F, _R, _B = D, B, R, F, L
 
    # 위쪽면(회전하려는 면)은 _U[1][1] 를 제외하고 회전
    _U[0][2], _U[1][2], _U[2][2], _U[2][1], _U[2][0], _U[1][0], _U[0][0], _U[0][1] = \
    _U[0][0], _U[0][1], _U[0][2], _U[1][2], _U[2][2], _U[2][1], _U[2][0], _U[1][0]
    
    # 옆면 9개 회전
    _L[2][2], _L[2][1], _L[2][0], _F[2][0], _F[1][0], _F[0][0], _R[0][2], _R[1][2], _R[2][2], _B[0][0], _B[0][1], _B[0][2] = \
        _F[2][0], _F[1][0], _F[0][0], _R[0][2], _R[1][2], _R[2][2], _B[0][0], _B[0][1], _B[0][2], _L[2][2], _L[2][1], _L[2][0]
 
 
for _ in range(int(input())):
    U = [['w'] * 3 for _ in range(3)]
    D = [['y'] * 3 for _ in range(3)]
    F = [['r'] * 3 for _ in range(3)]
    B = [['o'] * 3 for _ in range(3)]
    L = [['g'] * 3 for _ in range(3)]
    R = [['b'] * 3 for _ in range(3)]
    n = int(input())
    data = list(input().split())
    for area, dir in data:
        rotate(area)
        if dir == '-':
            rotate(area)
            rotate(area)
    for tmp in U:
        print("".join(tmp))