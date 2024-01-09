# 초당 최대 처리량: 1초당 처리하는 요청의 최대 개수
# 처리 시간은 시작 시간과 끝 시간을 포함 (10 ~ 20 = 11초)
# 타임아웃 3초

# 로그 개수: 2000개
# 로그 당 최대 시간 3000ms
# 날짜는 9월 15일 고정
# lines는 오름차순으로 정렬되어있다.

def solution(lines):
    # 하루를 배열로 선언 -> O(1000 * 60 * 60 * 24 = 86,400,000)
    logs = [0 for _ in range(1000 * 60 * 60 * 24)]

    # 모든 로그에 대하여 O(n)
    for line in lines:
        # 시작시간과 끝 시간을 1씩 추가 -> O(t)
        start, end = to_time(line)

        for idx in range(start, end):
            logs[idx] += 1

    # 배열 중 가장 큰 값을 반환 -> O(86,400,000)
    answer = sum(logs[:1000])
    for idx in range(1000, len(logs) - 1000):
        cnt = answer - logs[idx - 1] + logs[idx]
        answer = max(answer, cnt)

    return answer

def to_time(line):
    # 응답 시간과 처리 시간을 ms로 변환해서 시작과 끝 시간을 반환
    times = line.split()
    res_time, proc_time = times[1], times[2]

    # '23:59:59.999' -> ???
    hhmmss, ms = res_time.split(".")
    hhmmss = hhmmss.split(":")
    hhmmss = int(hhmmss[2]) * 1000 + int(hhmmss[1]) * 1000 * 60 + int(hhmmss[0]) * 1000 * 60 * 60
    ms = int(ms)

    # "1.234s" -> 1234
    proc_ms = int(float(proc_time[:-1]) * 1000)

    end = hhmmss + ms
    start = end - proc_ms

    return start, end



