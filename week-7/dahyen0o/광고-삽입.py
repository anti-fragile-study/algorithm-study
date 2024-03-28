from datetime import datetime

def to_sec(time):
    if len(time) > 8:
        raise Error("NOT PROPER TIME", time)
        
    return int(time[:2]) * 3600 + int(time[3:5]) * 60 + int(time[6:])

def to_str(time):
    hour = time // 3600
    minute = (time % 3600) // 60
    sec = time % 60
    return '{:02d}:{:02d}:{:02d}'.format(hour, minute, sec)

def solution(play_time, adv_time, logs):
    times = [0 for _ in range(to_sec(play_time) + 2)]
    
    for log in logs:
        log = log.split("-")
        times[to_sec(log[0]) + 1] += 1
        times[to_sec(log[1]) + 1] -= 1
    
    for t in range(1, len(times)):
        times[t] += times[t - 1]
    
    # 누적 합
    for t in range(1, len(times)):
        times[t] += times[t - 1]
        
    adv = to_sec(adv_time)
    maxTime = 0
    answer = 0
    
    for t in range(adv, len(times)):
        time = times[t] - times[t - adv]
            
        if maxTime < time:
            maxTime = time
            answer = t - adv
    
    return to_str(answer)
