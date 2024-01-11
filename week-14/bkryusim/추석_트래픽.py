from collections import defaultdict, deque
from decimal import Decimal


def solution(lines):
    answer = 0
    max_time = 3600 * 24 * 1000
    inlog = defaultdict(int)
    outlog = defaultdict(int)

    for line in lines:
        date, time, duration = line.split(" ")
        hour, minute, second = time.split(":")
        hour = int(hour) * 1000
        minute = int(minute) * 1000
        second = int(Decimal(second) * 1000)
        duration = int(Decimal(duration.replace("s", "")) * 1000)
        start = second - duration + 1 + hour * 3600 + minute * 60
        end = second + hour * 3600 + minute * 60

        inlog[max(start, 0)] += 1
        outlog[min(end, max_time)] += -1

    data = deque(sorted(list(inlog.items()) + list(outlog.items())))

    current = 0
    while data:
        start, type = data.popleft()

        if type > 0:
            current += type

        acc = 0
        for time, t in data:
            if time - start >= 1000:
                break

            if t > 0:
                acc += t

        answer = max(answer, current + acc)

        if type < 0:
            current += type

    return answer

