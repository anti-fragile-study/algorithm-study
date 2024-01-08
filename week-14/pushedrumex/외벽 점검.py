from itertools import permutations

def solution(n, weak, dist):
    N, W = n, len(weak)
    answer = []
    weaks = weak[:]
    # 선형으로 만들어서 시계 방향만 고려 (반시계를 따로 고려할 필요 없음)
    for w in weak:
        weaks.append(w + N)
    
    # 시작점
    for i, point in enumerate(weak):
        for friends in permutations(dist):
            # 친구 수
            count = 1
            start = point

            for friend in friends:
                end = start + friend
                # 아직 모든 외벽을 점검하지 못했다면
                if end < weaks[i+W-1]:
                    # 친구 더 필요
                    count += 1
                    for w in weak[i+1:i+W]:
                        if w > end:
                            start = w
                            break
                else:
                    answer.append(count)
                    break
                    
    return min(answer) if answer else -1