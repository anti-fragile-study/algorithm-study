N = int(input())
K = int(input())
arr = list(map(int, input().split()))

photos = []
students = [0] * 101

for n in arr:
    if n in photos:
        students[n] += 1
        continue
    elif len(photos) >= N:
        min_student, min_score = 0, 1000
        for s in photos:
            if students[s] < min_score:
                min_score = students[s]
                min_student = s
        photos.remove(min_student)
        students[min_student] = 0

    students[n] += 1
    photos.append(n)

print(sorted(photos))
