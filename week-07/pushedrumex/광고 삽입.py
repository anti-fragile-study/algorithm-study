def solution(play_time, adv_time, logs):
    play_time = convert(play_time)
    adv_time = convert(adv_time)    

    arr = [0] * (play_time + 1)
    
    for log in logs:
        log = log.split("-")
        start = convert(log[0])
        end = convert(log[1])
        arr[start] += 1
        arr[end] -= 1
    
    # 누적합
    for i in range(1, play_time):
        arr[i] += arr[i-1]
    for i in range(1, play_time):
        arr[i] += arr[i-1]
        
    max_time = 0
    answer = 0
    for i in range(adv_time-1, play_time):
        temp = arr[i] - arr[i - adv_time]
        if temp > max_time:
            max_time = temp
            answer = i- adv_time + 1
    
    return disconvert(answer)

def convert(t):
    h, m, s = map(int, t.split(":"))
    return h * 60 * 60 + m * 60 + s

def disconvert(t):
    h = t // (60*60)
    t %= 60*60
    m = t // 60
    s = t % 60
    return ("0" + str(h))[-2:] + ":" + ("0" + str(m))[-2:] + ":" + ("0" + str(s))[-2:]
    