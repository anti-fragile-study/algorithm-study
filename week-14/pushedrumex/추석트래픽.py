def solution(lines):
    logs = []
    for line in lines:
        _, time, term = line.split()
        end = int(convertToMili(time))
        start = int(end - float(term[:-1]) * 1000 + 1)
        logs.append((start, end))

    answer = 1
    for i in range(len(logs)-1):
        temp = 1
        end = logs[i][1]
        for j in range(i+1, len(logs)):
            start = logs[j][0]
            if start < end + 1000:
                temp += 1
        answer = max(answer, temp)
    return answer

def convertToMili(time):
    h, m, s = map(float, time.split(":"))
    return (h * 60 * 60 + m * 60 + s) * 1000