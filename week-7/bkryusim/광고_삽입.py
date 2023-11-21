def solution(play_time, adv_time, logs):

    # 시간 문자열을 int형으로
    def time2second(time):
        h, m, s = map(int, time.split(":"))
        return h * 3600 + m * 60 + s

    # int를 시간 문자열로
    def second2time(second):
        return '{0:02d}:{1:02d}:{2:02d}'.format(second // 3600, (second % 3600) // 60, second % 60)

    # 숫자로 변환
    play_second = time2second(play_time)
    adv_second = time2second(adv_time)
    dp = [0 for _ in range(play_second + 1)]

    # 각 초마다 사용자 수의 증/감 변화만 기록한 그래프
    for log in logs:
        start, end = map(time2second, log.split("-"))
        dp[start] += 1
        dp[end] -= 1

    # 위 결과 배열의 0부터 x까지의 적분이 각 시간당 사용자 분포
    for i in range(0, play_second + 1):
        dp[i] += dp[i - 1]

    # 다시 위 배열의 0부터 x까지의 적분이 누적합
    for i in range(0, play_second + 1):
        dp[i] += dp[i - 1]

    # 초기값은 0초부터 광고를 재생했을 때 값으로 설정
    ans_time = 0
    ans_duration = dp[adv_second - 1]

    # 광고를 재생할 수 있는 가장 나중의 시간
    max_candidate = time2second(play_time) - time2second(adv_time)

    # 초기값이 0이니 1초부터 max_candidate까지 부분합을 이용해 최대값 계산
    for candidate in range(1, max_candidate + 1):
        # 이거 1 안 빼는 것 때문에 두시간 날림
        # 배열의 인덱스가 의미하는 것은, "이 인덱스의 값은 0초부터 (index+1)초 까지의 시청자 수 부분합이 있어요"
        # 그래서 1초에 시작하는 경우를 찾고 싶으면 dp[adv_second] - dp[0]을 해줘야함
        current_duration = dp[candidate + adv_second - 1] - dp[candidate - 1]

        if current_duration > ans_duration:
            ans_duration = current_duration
            ans_time = candidate

        if current_duration == ans_duration and candidate < ans_time:
            ans_time = candidate

    return second2time(ans_time)
