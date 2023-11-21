# 1초 ~ 10초 = 9초

def solution(play_time, adv_time, logs):
    # 재생 시간과 광고 시간을 초로 변경
    play_seconds, adv_seconds = to_seconds(play_time), to_seconds(adv_time)

    # 0 ~ play time
    timeline = [0 for _ in range(play_seconds + 1)]

    # 모든 재생 기록에 대하여
    for log in logs:
        start, end = to_section(log)
        # 각 초마다 1초씩 기록 (종료 시간 제외)
        for idx in range(start, end):
            timeline[idx] += 1

    # 광고 시간 구역을 0부터 끝까지 1초씩 옮겨가며 기존보다 길때만 갱신
    answer, max_adv = 0, 0
    section_adv = sum(timeline[:adv_seconds])
    for left in range(1, play_seconds - adv_seconds):
        section_adv = section_adv - timeline[left - 1] + timeline[
            left + adv_seconds - 1]

        # 삽입 시간을 갱신
        if section_adv > max_adv:
            answer = left
            max_adv = section_adv

    return to_time(answer)


def to_seconds(time):
    HH, MM, SS = map(int, time.split(":"))

    return HH * 3600 + MM * 60 + SS


def to_section(log):
    start_time, end_time = log.split("-")

    return to_seconds(start_time), to_seconds(end_time)


def to_time(seconds):
    HH = seconds // 3600
    MM = seconds % 3600 // 60
    SS = seconds % 60

    return ":".join(map(str, map(padding, [HH, MM, SS])))


def padding(num):
    if num < 10:
        return "0" + str(num)

    return str(num)
  
