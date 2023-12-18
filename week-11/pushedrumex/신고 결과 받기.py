def solution(id_list, report, k):
    N = len(id_list)
    graph = [[False] * N for _ in range(N)]
    
    idToIdx = {}
    for i in range(N):
        idToIdx[id_list[i]] = i
        
    cnt = [0] * N
    for s in report:
        a, b = s.split()
        # graph[신고자][피신고자]
        if graph[idToIdx[a]][idToIdx[b]] == False:
            cnt[idToIdx[b]] += 1
            graph[idToIdx[a]][idToIdx[b]] = True

    answer = [0] * N
    for i in range(N):
        for j in range(N):
            if graph[i][j] and cnt[j] >= k:
                answer[i] += 1
    return answer