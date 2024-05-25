def convert_to_sec(time):
    h, m, s = map(int, time.split(":"))
    return h * 3600 + m * 60 + s

def convert_to_time(sec):
    h = sec // 3600
    sec %= 3600
    m = sec // 60
    sec %= 60
    return ("00" + str(h))[-2:] + ":" + ("00" + str(m))[-2:] + ":" + ("00" + str(sec))[-2:]

def solution(play_time, adv_time, logs):
    play_sec = convert_to_sec(play_time)
    times = [0] * (play_sec + 2)
    for log in logs:
        start, end = log.split("-")
        start = convert_to_sec(start)
        end = convert_to_sec(end)
        times[start] += 1
        times[end] -= 1
    
    # 시청 시간 누적
    for i in range(play_sec+1):
        times[i] += times[i-1]
    
    # 총 시청 시간 누적
    for i in range(play_sec+1):
        times[i] += times[i-1]
        
    adv_sec = convert_to_sec(adv_time)   
    max_value = times[adv_sec]
    answer = 0
    for start in range(1, play_sec - adv_sec + 1):
        count = times[start+adv_sec-1] - times[start-1] 
        if count > max_value:
            max_value = count
            answer = start

    return convert_to_time(answer)