N, K = map(int, input().split())
course = list(map(int, input().split()))
total = sum(course)

if K >= total:
    answer = N
    distance = total + course[N-1]
    while distance <= K:
        answer -= 1
        distance += course[answer-1]
        
else:
    answer = 1
    distance = course[0]
    while distance <= K:
        answer += 1
        distance += course[answer-1]

print(answer)